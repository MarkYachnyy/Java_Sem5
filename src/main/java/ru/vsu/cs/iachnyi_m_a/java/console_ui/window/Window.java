package ru.vsu.cs.iachnyi_m_a.java.console_ui.window;

import ru.vsu.cs.iachnyi_m_a.java.console_ui.command.Command;

import java.util.List;

public interface Window {
    void draw();
    List<Command> getCommands();
}
