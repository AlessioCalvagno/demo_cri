package it.cri.demo.controller;

import it.cri.demo.entity.Brevet;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class BrevetTableModel extends AbstractCustomTableModel<Brevet>{
    public BrevetTableModel(List<Brevet> data) {
        super(data, new String[]{"id","date","doctor"});
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Brevet record = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> record.getId();
            case 1 -> record.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            case 2 -> record.getDoctor();
            default -> null;
        };
    }
}
