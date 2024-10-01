package ru.vsu.cs.iachnyi_m_a.java.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Warehouse {
    private long id;
    private String name;

    public Warehouse(Warehouse warehouse1){
        this.id = warehouse1.getId();
        this.name = warehouse1.getName();
    }
}
