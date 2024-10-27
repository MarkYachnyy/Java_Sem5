package ru.vsu.cs.iachnyi_m_a.java.console_ui.window.implementation;

import ru.vsu.cs.iachnyi_m_a.java.console_ui.ConsoleInterfaceApp;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.command.Command;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.ui_component.ConsoleUIComponent;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.ui_component.SelectItemPageList;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.ui_component.TextLabel;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.window.InputState;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.window.Window;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.window.WindowType;
import ru.vsu.cs.iachnyi_m_a.java.context.ApplicationContextProvider;
import ru.vsu.cs.iachnyi_m_a.java.entity.Product;
import ru.vsu.cs.iachnyi_m_a.java.entity.User;
import ru.vsu.cs.iachnyi_m_a.java.entity.cart.CartItem;
import ru.vsu.cs.iachnyi_m_a.java.entity.order.Order;
import ru.vsu.cs.iachnyi_m_a.java.entity.order.OrderItem;
import ru.vsu.cs.iachnyi_m_a.java.entity.order.OrderItemId;
import ru.vsu.cs.iachnyi_m_a.java.entity.order.OrderStatus;
import ru.vsu.cs.iachnyi_m_a.java.service.CartService;
import ru.vsu.cs.iachnyi_m_a.java.service.OrderService;
import ru.vsu.cs.iachnyi_m_a.java.service.ProductService;
import ru.vsu.cs.iachnyi_m_a.java.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutWindow implements Window {

    private UserService userService;
    private CartService cartService;
    private ProductService productService;
    private OrderService orderService;

    private User user;
    private List<CartItem> cart;

    private TextLabel TextLabelHeader;
    private SelectItemPageList<CartItem> SelectItemPageListCart;
    private TextLabel TextLabelTotalPrice;
    private TextLabel TextLabelDeliveryInfo;

    private Command commandOpenCartWindow;
    private Command commandMakeOrder;

    public CheckoutWindow(ConsoleInterfaceApp app, Map<String, Object> params) {
        userService = ApplicationContextProvider.getContext().getBean(UserService.class);
        cartService = ApplicationContextProvider.getContext().getBean(CartService.class);
        productService = ApplicationContextProvider.getContext().getBean(ProductService.class);
        orderService = ApplicationContextProvider.getContext().getBean(OrderService.class);

        user = params.get("userId") == null ? null : userService.findUserById((Long) params.get("userId"));
        if (user != null) {
            cart = cartService.getCartOfUser(user.getId());
        }

        TextLabelHeader = new TextLabel("Оформить заказ");
        SelectItemPageListCart = new SelectItemPageList<>(5, cart, ci -> {
            Product p = productService.getProductById(ci.getId().getProductId());
            return String.format("%s | %s шт. : %s₽", p.getName(), ci.getQuantity(), ci.getQuantity() * p.getPrice());
        }, false);
        TextLabelTotalPrice = new TextLabel(String.format("Итого: %s₽", cart.stream().
                mapToInt(ci -> ci.getQuantity() * productService.getProductById(ci.getId().getProductId()).getPrice()).reduce(Integer::sum).orElse(0)));
        TextLabelDeliveryInfo = new TextLabel("Доставка в пункт выдачи Карла Маркса, 67\nсо склада Подольский: 2-3 дней");

        commandOpenCartWindow = new Command() {
            @Override
            public String getName() {
                return "Обратно в корзину";
            }

            @Override
            public void execute() {
                Map<String, Object> params = new HashMap<>();
                params.put("userId", user.getId());
                app.setCurrentWindow(WindowType.CART, params);
            }
        };

        commandMakeOrder = new Command() {
            @Override
            public String getName() {
                return "Оформить заказ";
            }

            @Override
            public void execute() {
                Order toInsert = new Order(0, user.getId(), new Date(), OrderStatus.ASSEMBLY, null);
                toInsert.setItems(cart.stream().map(ci -> new OrderItem(new OrderItemId(0, ci.getId().getProductId()),
                        ci.getQuantity(), productService.getProductById(ci.getId().getProductId()).getPrice())).toList());
                Order res = orderService.addOrder(toInsert);
                for(CartItem cartItem : cart) {
                    Product product = productService.getProductById(cartItem.getId().getProductId());
                    product.setStockQuantity(product.getStockQuantity() - cartItem.getQuantity());
                    productService.updateProduct(product);
                }
                for(CartItem cartItem : cart) {
                    cartService.deleteCartItem(cartItem.getId());
                }
                Map<String, Object> params = new HashMap<>();
                params.put("userId", user.getId());
                params.put("orderId", res.getId());
                app.setCurrentWindow(WindowType.ORDER, params);
            }
        };
    }

    @Override
    public List<Command> getCommands() {
        return List.of(SelectItemPageListCart.getSelectPreviousPageCommand(), SelectItemPageListCart.getSelectNextPageCommand(),commandOpenCartWindow, commandMakeOrder);
    }

    @Override
    public InputState getInputState() {
        return InputState.COMMAND;
    }

    @Override
    public List<ConsoleUIComponent> getComponents() {
        return List.of(TextLabelHeader, SelectItemPageListCart, TextLabelTotalPrice, TextLabelDeliveryInfo);
    }

    @Override
    public void acceptInputValue(String value) {

    }
}
