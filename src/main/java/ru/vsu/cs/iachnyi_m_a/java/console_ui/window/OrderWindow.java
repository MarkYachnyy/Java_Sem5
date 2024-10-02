package ru.vsu.cs.iachnyi_m_a.java.console_ui.window;

import ru.vsu.cs.iachnyi_m_a.java.console_ui.ConsoleInterfaceApp;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.command.Command;

import java.util.HashMap;
import java.util.List;

public class OrderWindow implements Window{
    public OrderWindow(ConsoleInterfaceApp app, HashMap<String, Object> params) {

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
        return InputState.COMMAND;
    }

    @Override
    public void acceptInputValue(String value) {

    }
}
