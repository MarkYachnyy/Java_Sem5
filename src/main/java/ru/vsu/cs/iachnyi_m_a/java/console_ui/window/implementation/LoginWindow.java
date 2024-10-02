package ru.vsu.cs.iachnyi_m_a.java.console_ui.window.implementation;

import ru.vsu.cs.iachnyi_m_a.java.console_ui.ConsoleInterfaceApp;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.command.Command;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.component.TextInput;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.component.TextLabel;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.window.InputState;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.window.Window;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.window.WindowType;
import ru.vsu.cs.iachnyi_m_a.java.entity.User;
import ru.vsu.cs.iachnyi_m_a.java.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoginWindow implements Window {

    private TextInput TextInputEmail;
    private TextInput TextInputPassword;
    private TextLabel TextLabelHeader;
    private TextLabel TextLabelStatus;
    private User user;

    private InputState inputState;
    private TextInput currentTextInput;

    private Command commandEnterEmail;
    private Command commandEnterPassword;
    private Command commandConfirmLogin;
    private Command commandOpenRegisterWindow;
    private Command commandOpenAllProductsWindow;

    private UserService userService;

    public LoginWindow(ConsoleInterfaceApp app, Map<String, Object> params) {
        inputState = InputState.COMMAND;
        userService = UserService.getInstance();

        TextLabelHeader = new TextLabel("Войти в аккаунт");
        TextLabelStatus = new TextLabel("");
        TextInputEmail = new TextInput("Почта");
        TextInputPassword = new TextInput("Пароль");

        commandEnterEmail = new Command() {
            @Override
            public String getName() {
                return "Ввести почту";
            }

            @Override
            public void execute() {
                inputState = InputState.VALUE;
                currentTextInput = TextInputEmail;
            }
        };
        commandEnterPassword = new Command() {
            @Override
            public String getName() {
                return "Ввести пароль";
            }

            @Override
            public void execute() {
                inputState = InputState.VALUE;
                currentTextInput = TextInputPassword;
            }
        };
        commandConfirmLogin = new Command() {

            @Override
            public String getName() {
                return "Войти";
            }

            @Override
            public void execute() {
                if(TextInputEmail.getValue() == null || TextInputPassword.getValue() == null) {
                    TextLabelStatus.setText("Не все поля заполнены");
                } else {
                    User existing = userService.findUserByEmail(TextInputEmail.getValue());
                    System.err.println(existing);
                    if(existing == null || !existing.getPassword().equals(TextInputPassword.getValue())) {
                        TextLabelStatus.setText("Неверная почта или пароль");
                    } else {
                        TextLabelStatus.setText("Успешный вход в аккаунт под именем " + existing.getName());
                        user = existing;
                    }
                }
            }
        };
        commandOpenRegisterWindow = new Command() {

            @Override
            public String getName() {
                return "Нет аккаунта? Зарегистрироваться";
            }

            @Override
            public void execute() {
                app.setCurrentWindow(WindowType.REGISTER, new HashMap<>());
            }
        };
        commandOpenAllProductsWindow = new Command() {
            @Override
            public String getName() {
                return "Вернуться к списку товаров";
            }

            @Override
            public void execute() {
                Map<String, Object> params = new HashMap<>();
                params.put("userId", user.getId());
                app.setCurrentWindow(WindowType.ALL_PRODUCTS, params);
            }
        };
    }

    @Override
    public String getDrawableContent() {
        return TextLabelHeader.getDrawableContent() + '\n' +
                "-".repeat(ConsoleInterfaceApp.SEPARATOR_DASH_COUNT) + '\n' +
                Stream.of(TextInputEmail,TextInputPassword).
                        map(input -> input == currentTextInput ? "\033[43m" + input.getDrawableContent() + "\033[0m" : input.getDrawableContent()).
                        collect(Collectors.joining("\n")) + '\n' + "\033[43m" + TextLabelStatus.getDrawableContent() + "\033[0m";
    }

    @Override
    public List<Command> getCommands() {
        return List.of(commandEnterEmail, commandEnterPassword, commandConfirmLogin, commandOpenRegisterWindow, commandOpenAllProductsWindow);
    }

    @Override
    public InputState getInputState() {
        return inputState;
    }

    @Override
    public void acceptInputValue(String value) {
        if(currentTextInput != null && !"".equals(value)) {
            currentTextInput.setValue(value);
        }
        inputState = InputState.COMMAND;
        currentTextInput = null;
    }
}
