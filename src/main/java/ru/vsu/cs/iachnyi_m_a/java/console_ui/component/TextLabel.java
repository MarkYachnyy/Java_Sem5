package ru.vsu.cs.iachnyi_m_a.java.console_ui.component;

public class TextLabel implements UIComponent {
    private String text;

    public TextLabel(String text) {
        this.text = text;
    }

    @Override
    public String getDrawableContent() {
        return text;
    }
}
