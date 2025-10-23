package it.cri.demo.controller;

import it.cri.demo.entity.Promotion;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class PromotionTableModel extends AbstractCustomTableModel<Promotion> {


    public PromotionTableModel(List<Promotion> data) {
        super(data, new String[]{"ID","Data","Grado"});
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Promotion record = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> record.getId();
            case 1 -> record.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            case 2 -> record.getDegree();
            default -> null;
        };
    }
}
