package ru.vsu.cs.iachnyi_m_a.java.console_ui.window.implementation;

import ru.vsu.cs.iachnyi_m_a.java.console_ui.ConsoleInterfaceApp;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.command.Command;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.window.InputState;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.window.Window;
import ru.vsu.cs.iachnyi_m_a.java.context.ApplicationContextProvider;
import ru.vsu.cs.iachnyi_m_a.java.entity.Product;
import ru.vsu.cs.iachnyi_m_a.java.entity.User;
import ru.vsu.cs.iachnyi_m_a.java.service.CartService;
import ru.vsu.cs.iachnyi_m_a.java.service.ProductService;
import ru.vsu.cs.iachnyi_m_a.java.service.SellerService;
import ru.vsu.cs.iachnyi_m_a.java.service.UserService;

import java.util.List;
import java.util.Map;

public class ProductWindow implements Window {

    private SellerService sellerService;
    private ProductService productService;
    private UserService userService;
    private CartService cartService;
    private User user;
    private Product product;
    private Command commandAddToCart;
    private Command commandRemoveFromCart;
    private Command commandOpenCart;

    public ProductWindow(ConsoleInterfaceApp app, Map<String, Object> params) {
        sellerService = ApplicationContextProvider.getContext().getBean(SellerService.class);
        productService = ApplicationContextProvider.getContext().getBean(ProductService.class);
        cartService = ApplicationContextProvider.getContext().getBean(CartService.class);
        userService = ApplicationContextProvider.getContext().getBean(UserService.class);
        product = productService.getProductById((Long) params.get("productId"));
        user = params.get("userId") == null ? null: userService.findUserById((Long) params.get("userId"));
    }

    @Override
    public String getDrawableContent() {
        return null;
    }

    @Override
    public List<Command> getCommands() {
        return null;
    }

    @Override
    public InputState getInputState() {
        return null;
    }

    @Override
    public void acceptInputValue(String value) {

    }
}
