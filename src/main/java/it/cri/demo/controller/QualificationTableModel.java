package it.cri.demo.controller;

import it.cri.demo.entity.Qualification;

import java.util.List;

public class QualificationTableModel extends AbstractCustomTableModel<Qualification> {


    public QualificationTableModel(List<Qualification> data) {
        super(data, new String[]{"id","type","committee"});
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Qualification record = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> record.getId();
            case 1 -> record.getType();
            case 2 -> record.getCommittee();
            default -> null;
        };
    }
}
