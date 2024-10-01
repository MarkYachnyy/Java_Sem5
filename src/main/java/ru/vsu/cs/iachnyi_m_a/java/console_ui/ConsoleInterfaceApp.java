package ru.vsu.cs.iachnyi_m_a.java.console_ui;

import ru.vsu.cs.iachnyi_m_a.java.console_ui.window.Window;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.window.WindowFactory;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.window.WindowType;

import java.util.HashMap;

public class ConsoleInterfaceApp {

    private WindowFactory windowFactory;
    private Window currentWindow;

    public ConsoleInterfaceApp() {
        windowFactory = WindowFactory.getInstance();
        currentWindow = windowFactory.createWindow(WindowType.ALL_PRODUCTS, new HashMap<>());
    }

}
