package it.cri.demo.controller;

import it.cri.demo.entity.Recall;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class RecallTableModel extends AbstractCustomTableModel<Recall> {


    public RecallTableModel(List<Recall> data) {
        super(data, new String[]{"ID","Data inizio","Data fine","Attività"});
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Recall record = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> record.getId();
            case 1 -> record.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            case 2 -> record.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            case 3 -> record.getActivity();
            default -> null;
        };
    }
}
