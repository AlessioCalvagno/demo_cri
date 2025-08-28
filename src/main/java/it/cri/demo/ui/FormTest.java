package it.cri.demo.ui;

import it.cri.demo.entity.Volunteer;
import it.cri.demo.service.VolunteerService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormTest extends JFrame {
    private JPanel panel1;
    private JList<Volunteer> list1;
    private DefaultListModel<Volunteer> listModel;
    private JScrollPane scrollPane;
    private final VolunteerService service;

    public FormTest(VolunteerService service) throws HeadlessException {
        this.service = service;

        setContentPane(panel1);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("GUI Test");
        setSize(800, 400);
        setVisible(true);

        listModel = new DefaultListModel<>();
        list1.setModel(listModel);

        updateListWiew();
//        list1.addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                System.out.println("Clicked element: "+ e.toString());
//            }
//        });
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    Volunteer volunteer = list1.getSelectedValue();
                }
            }
        });
    }

    private void updateListWiew() {
        listModel.removeAllElements();
        listModel.addAll(service.getAll());
    }
}
