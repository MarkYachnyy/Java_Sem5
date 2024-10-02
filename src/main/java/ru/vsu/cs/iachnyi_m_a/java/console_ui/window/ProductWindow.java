package ru.vsu.cs.iachnyi_m_a.java.console_ui.window;

import ru.vsu.cs.iachnyi_m_a.java.console_ui.ConsoleInterfaceApp;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.command.Command;
import ru.vsu.cs.iachnyi_m_a.java.entity.Product;
import ru.vsu.cs.iachnyi_m_a.java.entity.User;
import ru.vsu.cs.iachnyi_m_a.java.service.CartService;
import ru.vsu.cs.iachnyi_m_a.java.service.ProductService;
import ru.vsu.cs.iachnyi_m_a.java.service.SellerService;
import ru.vsu.cs.iachnyi_m_a.java.service.UserService;

import java.util.HashMap;
import java.util.List;

public class ProductWindow implements Window {

    private SellerService sellerService;
    private ProductService productService;
    private UserService userService;
    private CartService cartService;
    private User user;
    private Product product;

    public ProductWindow(ConsoleInterfaceApp app, HashMap<String, Object> params) {
        sellerService = SellerService.getInstance();
        productService = ProductService.getInstance();
        cartService = CartService.getInstance();
        userService = UserService.getInstance();
        product = productService.getProductById((Long) params.get("productId"));
        user = userService.findUserById((Long) params.get("userId"));
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
