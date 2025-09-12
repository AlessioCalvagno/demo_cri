package it.cri.demo.controller;

import it.cri.demo.entity.AssociativeFee;

import java.util.List;

public class AssociativeFeeTableModel extends AbstractCustomTableModel<AssociativeFee>{


    public AssociativeFeeTableModel(List<AssociativeFee> data) {
        super(data, new String[]{"id","year","committee"});
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AssociativeFee record = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> record.getId();
            case 1 -> record.getYear();
            case 2 -> record.getCommittee();
            default -> null;
        };
    }
}
