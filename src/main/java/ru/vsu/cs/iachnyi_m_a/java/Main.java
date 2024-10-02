package ru.vsu.cs.iachnyi_m_a.java;

import ru.vsu.cs.iachnyi_m_a.java.console_ui.ConsoleInterfaceApp;

public class Main {
    public static void main(String[] args) {
        ConsoleInterfaceApp app = new ConsoleInterfaceApp(System.in, System.out);
        app.run();
    }
}