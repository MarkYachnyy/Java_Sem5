package ru.vsu.cs.iachnyi_m_a.java.console_ui;

import ru.vsu.cs.iachnyi_m_a.java.console_ui.command.Command;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.window.InputState;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.window.Window;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.window.WindowFactory;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.window.WindowType;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

public class ConsoleInterfaceApp {

    private WindowFactory windowFactory;
    private Window currentWindow;
    private boolean running = false;
    private Scanner scanner;
    private PrintStream outputStream;

    public ConsoleInterfaceApp(InputStream inputStream, PrintStream outputStream) {
        this.windowFactory = new WindowFactory(this);
        this.currentWindow = windowFactory.createWindow(WindowType.ALL_PRODUCTS, new HashMap<>());
        this.scanner = new Scanner(inputStream);
        this.outputStream = outputStream;
    }

    public void run(){
        running = true;
        while (running) {
            outputStream.print("\033[H\033[2J");
            outputStream.flush();
            outputStream.println(currentWindow.getDrawableContent());
            if(currentWindow.getInputState() == InputState.COMMAND){
                HashMap<String, Command> commands = new HashMap<>();
                for (int i = 0; i < currentWindow.getCommands().size(); i++) {
                    commands.put(String.valueOf(i+1), currentWindow.getCommands().get(i));
                    System.out.println(String.format("[%d] %s", i + 1, currentWindow.getCommands().get(i).getName()));
                }
                System.out.println("Введите команду: ");
                String commandKey = scanner.nextLine();
                Command command = commands.get(commandKey);
                if(command != null){
                    command.execute();
                } else {
                    System.out.println("Выбрана неверная команда");
                }
            } else {
                currentWindow.acceptInputValue(scanner.nextLine());
            }
        }
    }

    public void stop(){
        running = false;
    }

    public void setCurrentWindow(WindowType type, HashMap<String, Object> params){
        this.currentWindow = windowFactory.createWindow(type, params);
    }

}
