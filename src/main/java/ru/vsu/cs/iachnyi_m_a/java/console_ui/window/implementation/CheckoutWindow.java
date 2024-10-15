package ru.vsu.cs.iachnyi_m_a.java.console_ui.window.implementation;

import ru.vsu.cs.iachnyi_m_a.java.console_ui.ConsoleInterfaceApp;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.command.Command;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.ui_component.SelectItemPageList;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.ui_component.TextLabel;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.window.InputState;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.window.Window;
import ru.vsu.cs.iachnyi_m_a.java.context.ApplicationContextProvider;
import ru.vsu.cs.iachnyi_m_a.java.entity.Product;
import ru.vsu.cs.iachnyi_m_a.java.entity.User;
import ru.vsu.cs.iachnyi_m_a.java.entity.cart.CartItem;
import ru.vsu.cs.iachnyi_m_a.java.service.CartService;
import ru.vsu.cs.iachnyi_m_a.java.service.OrderService;
import ru.vsu.cs.iachnyi_m_a.java.service.ProductService;
import ru.vsu.cs.iachnyi_m_a.java.service.UserService;

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
    }

    @Override
    public String getDrawableContent() {
        return TextLabelHeader.getDrawableContent() + '\n' + "-".repeat(ConsoleInterfaceApp.SEPARATOR_DASH_COUNT) + '\n' +
                SelectItemPageListCart.getDrawableContent() + '\n' + "-".repeat(ConsoleInterfaceApp.SEPARATOR_DASH_COUNT) + '\n' +
                TextLabelTotalPrice.getDrawableContent() + '\n' + "-".repeat(ConsoleInterfaceApp.SEPARATOR_DASH_COUNT) + '\n' +
                TextLabelDeliveryInfo.getDrawableContent();
    }

    @Override
    public List<Command> getCommands() {
        return List.of();
    }

    @Override
    public InputState getInputState() {
        return InputState.COMMAND;
    }

    @Override
    public void acceptInputValue(String value) {

    }
}
