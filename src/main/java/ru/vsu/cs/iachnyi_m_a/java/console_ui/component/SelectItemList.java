package ru.vsu.cs.iachnyi_m_a.java.console_ui.component;

import lombok.Getter;
import ru.vsu.cs.iachnyi_m_a.java.console_ui.command.Command;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SelectItemList<T> implements UIComponent {

    private List<T> content;
    private int selectedIndex;
    private Function<T, String> adapter;
    @Getter
    private Command selectUpCommand;
    @Getter
    private Command selectDownCommand;

    public SelectItemList(List<T> content, Function<T, String> adapter) {
        this.content = content;
        this.adapter = adapter;
        this.selectedIndex = 0;
        selectUpCommand = new Command() {
            @Override
            public String getName() {
                return "Наверх";
            }

            @Override
            public void execute() {
                if (selectedIndex > 0) {
                    selectedIndex--;
                }
            }
        };
        selectDownCommand = new Command() {

            @Override
            public String getName() {
                return "Вниз";
            }

            @Override
            public void execute() {
                if (selectedIndex < SelectItemList.this.content.size() - 1) {
                    selectedIndex++;
                }
            }
        };
    }

    public T getSelectedItem(){
        return content.get(selectedIndex);
    }

    @Override
    public String getDrawableContent() {
        return IntStream.range(0, content.size()).mapToObj(i -> {
            if (i == selectedIndex) {
                return "\\033[43m" + adapter.apply(content.get(i)) + " \\033[0m";
            } else {
                return adapter.apply(content.get(i));
            }
        }).collect(Collectors.joining("\n"));
    }

}
