package ru.vsu.cs.iachnyi_m_a.java.console_ui.window;

import lombok.Getter;

import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Supplier;

public class WindowFactory {

    @Getter
    private static WindowFactory instance = new WindowFactory();

    private HashMap<WindowType, Function<HashMap<String, Object>, Window>> windows = new HashMap<>();

    private WindowFactory() {
        windows.put(WindowType.CART, CartWindow::new);
        windows.put(WindowType.ALL_PRODUCTS, AllProductsWindow::new);
        windows.put(WindowType.ORDER, OrderWindow::new);
        windows.put(WindowType.PRODUCT, ProductWindow::new);
        windows.put(WindowType.USER_PROFILE, UserProfileWindow::new);
        windows.put(WindowType.SELLER_PROFILE, SellerProfileWindow::new);
    }

    public Window createWindow(WindowType type, HashMap<String, Object> params) {
        return windows.get(type).apply(params);
    }
}
