package ru.vsu.cs.iachnyi_m_a.java.console_ui.window.implementation;

import ru.vsu.cs.iachnyi_m_a.java.console_ui.ConsoleInterfaceApp;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.command.Command;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.component.TextInput;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.component.TextLabel;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.window.InputState;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.window.Window;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.window.WindowType;
import ru.vsu.cs.iachnyi_m_a.java.context.ApplicationContextProvider;
import ru.vsu.cs.iachnyi_m_a.java.entity.User;
import ru.vsu.cs.iachnyi_m_a.java.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RegisterWindow implements Window {

    private TextLabel TextLabelHeader;
    private TextInput TextInputName;
    private TextInput TextInputEmail;
    private TextInput TextInputPassword;
    private TextInput TextInputPasswordConfirm;
    private TextInput currentTextInput;
    private TextLabel TextLabelStatus;

    private Command commandEnterName;
    private Command commandEnterEmail;
    private Command commandEnterPassword;
    private Command commandEnterPasswordConfirm;
    private Command commandConfirmRegister;
    private Command commandOpenLoginWindow;

    private InputState inputState;

    private UserService userService;

    public RegisterWindow(ConsoleInterfaceApp app, Map<String, Object> params) {
        userService = ApplicationContextProvider.getContext().getBean(UserService.class);

        TextLabelHeader = new TextLabel("Зарегистрировать аккаунт");
        TextInputName = new TextInput("Имя");
        TextInputEmail = new TextInput("Почта");
        TextInputPassword = new TextInput("Пароль");
        TextInputPasswordConfirm = new TextInput("Подтвердите пароль");
        TextLabelStatus = new TextLabel("");

        inputState = InputState.COMMAND;
        commandEnterName = new Command() {
            @Override
            public String getName() {
                return "Ввести имя";
            }

            @Override
            public void execute() {
                inputState = InputState.VALUE;
                currentTextInput = TextInputName;
            }
        };
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
        commandEnterPasswordConfirm = new Command() {
            @Override
            public String getName() {
                return "Ввести подтверждение пароля";
            }

            @Override
            public void execute() {
                inputState = InputState.VALUE;
                currentTextInput = TextInputPasswordConfirm;
            }
        };
        commandConfirmRegister = new Command() {
            @Override
            public String getName() {
                return "Зарегистрироваться";
            }

            @Override
            public void execute() {
                String nameValue = TextInputName.getValue();
                String emailValue = TextInputEmail.getValue();
                String passwordValue = TextInputPassword.getValue();
                String passwordConfirmValue = TextInputPasswordConfirm.getValue();
                if(nameValue == null || emailValue == null || passwordValue == null || passwordConfirmValue == null) {
                    TextLabelStatus.setText("Заполнены не все поля");
                } else if (!Objects.equals(TextInputPasswordConfirm.getValue(), TextInputPassword.getValue())){
                    TextLabelStatus.setText("Пароли не совпадают");
                } else {
                    User existing = userService.findUserByEmail(TextInputEmail.getValue());
                    if (existing != null) {
                        TextLabelStatus.setText("Пользователь с таким email существует");
                    } else {
                        userService.registerUser(new User(0, TextInputEmail.getValue(), TextInputName.getValue(), TextInputPassword.getValue()));
                        TextInputName.setValue(null);
                        TextInputEmail.setValue(null);
                        TextInputPassword.setValue(null);
                        TextInputPasswordConfirm.setValue(null);
                        TextLabelStatus.setText("Пользователь успешно зарегистрирован");
                    }
                }
            }
        };
        commandOpenLoginWindow = new Command() {
            @Override
            public String getName() {
                return "Войти в аккаунт";
            }

            @Override
            public void execute() {
                app.setCurrentWindow(WindowType.LOGIN, new HashMap<>());
            }
        };
    }

    @Override
    public String getDrawableContent() {
        return TextLabelHeader.getDrawableContent() + '\n' +
                "-".repeat(ConsoleInterfaceApp.SEPARATOR_DASH_COUNT) + '\n' +
                Stream.of(TextInputName, TextInputEmail,TextInputPassword, TextInputPasswordConfirm).
                map(input -> input == currentTextInput ? "\033[43m" + input.getDrawableContent() + "\033[0m" : input.getDrawableContent()).
                collect(Collectors.joining("\n")) + '\n' + "\033[43m" + TextLabelStatus.getDrawableContent() + "\033[0m";
    }

    @Override
    public List<Command> getCommands() {
        return List.of(commandEnterName, commandEnterEmail, commandEnterPassword, commandEnterPasswordConfirm, commandConfirmRegister,commandOpenLoginWindow);
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
