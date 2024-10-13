package ru.vsu.cs.iachnyi_m_a.java.console_ui.window.implementation;

import ru.vsu.cs.iachnyi_m_a.java.console_ui.ConsoleInterfaceApp;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.command.Command;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.component.SelectItemPageList;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.component.TextLabel;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.window.InputState;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.window.Window;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.window.WindowType;
import ru.vsu.cs.iachnyi_m_a.java.context.ApplicationContextProvider;
import ru.vsu.cs.iachnyi_m_a.java.entity.Product;
import ru.vsu.cs.iachnyi_m_a.java.entity.User;
import ru.vsu.cs.iachnyi_m_a.java.service.ProductService;
import ru.vsu.cs.iachnyi_m_a.java.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllProductsWindow implements Window {

    private InputState inputState;
    private ProductService productService;

    private TextLabel LabelTitle;
    private SelectItemPageList<Product> SelectItemPageListProduct;

    private Command commandOpenProductWindow;
    private Command commandLoginLogout;

    private UserService userService;
    private User user;

    public AllProductsWindow(ConsoleInterfaceApp app, Map<String, Object> params) {
        userService = ApplicationContextProvider.getContext().getBean(UserService.class);
        productService = ApplicationContextProvider.getContext().getBean(ProductService.class);

        user = params.get("userId") == null ? null : userService.findUserById((Long) params.get("userId"));
        inputState = InputState.COMMAND;

        LabelTitle = new TextLabel("Все товары");
        SelectItemPageListProduct = new SelectItemPageList<>(5, productService.getAllProducts(), product -> product.getName() + ": " + product.getPrice() + " | " + product.getStockQuantity() + " шт. в наличии");

        commandOpenProductWindow = new Command() {
            @Override
            public String getName() {
                return "Выбрать товар";
            }

            @Override
            public void execute() {
                Long productId = SelectItemPageListProduct.getSelectedItem().getId();
                HashMap<String, Object> params = new HashMap<>();
                params.put("productId", productId);
                app.setCurrentWindow(WindowType.PRODUCT, params);
            }
        };

        commandLoginLogout = new Command() {
            @Override
            public String getName() {
                return user == null ? "Войти в аккаунт" : String.format("Выполнен вход под именем %s. Выберите, чтобы выйти", user.getName());
            }

            @Override
            public void execute() {
                if(user == null) {
                    app.setCurrentWindow(WindowType.LOGIN, new HashMap<>());
                } else {
                    user = null;
                }
            }
        };
    }

    @Override
    public String getDrawableContent() {
        return String.join("\n", LabelTitle.getDrawableContent(), "-".repeat(ConsoleInterfaceApp.SEPARATOR_DASH_COUNT), SelectItemPageListProduct.getDrawableContent());
    }

    @Override
    public List<Command> getCommands() {

        return List.of(SelectItemPageListProduct.getSelectUpCommand(), SelectItemPageListProduct.getSelectDownCommand(),
                SelectItemPageListProduct.getSelectNextPageCommand(),
                SelectItemPageListProduct.getSelectPreviousPageCommand(), commandOpenProductWindow, commandLoginLogout);
    }

    @Override
    public InputState getInputState() {
        return inputState;
    }

    @Override
    public void acceptInputValue(String value) {
        if(inputState == InputState.COMMAND) {
            throw new IllegalStateException("Trying to pass input value while the window is in COMMAND input state");
        }
    }
}
