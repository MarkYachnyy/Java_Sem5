package ru.vsu.cs.iachnyi_m_a.java.console_ui.window;

import lombok.Getter;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.ConsoleInterfaceApp;

import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Supplier;

public class WindowFactory {


    private HashMap<WindowType, Function<HashMap<String, Object>, Window>> windows = new HashMap<>();

    public WindowFactory(ConsoleInterfaceApp app) {
        windows.put(WindowType.CART, CartWindow::new);
        windows.put(WindowType.ALL_PRODUCTS, params -> new AllProductsWindow(app, params));
        windows.put(WindowType.ORDER, params -> new OrderWindow(app, params));
        windows.put(WindowType.PRODUCT, ProductWindow::new);
        windows.put(WindowType.USER_PROFILE, UserProfileWindow::new);
        windows.put(WindowType.SELLER_PROFILE, SellerProfileWindow::new);
    }

    public Window createWindow(WindowType type, HashMap<String, Object> params) {
        return windows.get(type).apply(params);
    }
}
