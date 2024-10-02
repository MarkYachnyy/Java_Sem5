package ru.vsu.cs.iachnyi_m_a.java.console_ui.window;

import ru.vsu.cs.iachnyi_m_a.java.console_ui.ConsoleInterfaceApp;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.command.Command;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.component.SelectItemPageList;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.component.TextLabel;
import ru.vsu.cs.iachnyi_m_a.java.entity.Product;
import ru.vsu.cs.iachnyi_m_a.java.service.ProductService;

import java.util.HashMap;
import java.util.List;

public class AllProductsWindow implements Window{

    private InputState inputState;
    private ProductService service;

    private TextLabel LabelTitle;
    private SelectItemPageList<Product> SelectItemPageListProduct;

    private Command openProductWindowCommand;

    public AllProductsWindow(ConsoleInterfaceApp app, HashMap<String, Object> params) {
        inputState = InputState.COMMAND;
        service = ProductService.getInstance();

        LabelTitle = new TextLabel("Все товары");
        SelectItemPageListProduct = new SelectItemPageList<>(5, service.getAllProducts(), product -> product.getName() + ": " + product.getPrice() + " | " + product.getStockQuantity() + " шт. в наличии");

        openProductWindowCommand = new Command() {
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
    }

    @Override
    public String getDrawableContent() {
        return String.join("\n", LabelTitle.getDrawableContent(), "-".repeat(20), SelectItemPageListProduct.getDrawableContent(), "-".repeat(20));
    }

    @Override
    public List<Command> getCommands() {

        return List.of(SelectItemPageListProduct.getSelectUpCommand(), SelectItemPageListProduct.getSelectDownCommand(),
                SelectItemPageListProduct.getSelectNextPageCommand(),
                SelectItemPageListProduct.getSelectPreviousPageCommand(), openProductWindowCommand);
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
