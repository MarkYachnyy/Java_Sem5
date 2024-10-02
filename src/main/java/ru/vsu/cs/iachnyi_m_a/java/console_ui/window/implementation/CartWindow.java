package ru.vsu.cs.iachnyi_m_a.java.console_ui.window.implementation;

import ru.vsu.cs.iachnyi_m_a.java.console_ui.ConsoleInterfaceApp;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.command.Command;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.window.InputState;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.window.Window;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartWindow implements Window {
    public CartWindow(ConsoleInterfaceApp app, Map<String, Object> params) {

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
