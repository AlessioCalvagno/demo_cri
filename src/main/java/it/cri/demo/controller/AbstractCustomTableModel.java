package it.cri.demo.controller;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * It's inside controller package, since this layer acts as a controller in MVC pattern:
 * It's actually a bridge between logic (Service) and UI.
 */
public abstract class AbstractCustomTableModel<Entity> extends AbstractTableModel {
    protected final String[] columnNames;
    protected final List<Entity> data;

    public AbstractCustomTableModel(List<Entity> data, String[] columnNames) {
        this.data = data;
        this.columnNames = columnNames;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int idx) {
        return columnNames[idx];
    }

}
