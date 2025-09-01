package it.cri.demo.ui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import it.cri.demo.entity.Volunteer;
import it.cri.demo.service.VolunteerService;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VolunteerDetails extends JDialog {
    private JPanel detailPanel;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField birthDateField;
    private JTextField birthPlaceField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField residenceField;
    private JTextField registrationNumberField;
    private JTextField feeField;
    private JTextField rankField;
    private JTextField lastRecallField;
    private JTextField activityField;
    private JTextField lastVisitField;
    private JTextField nextVisitField;
    private JLabel nameLabel;
    private JLabel surnameLabel;
    private JLabel birthDateLabel;
    private JLabel birthPlaceLabel;
    private JLabel phoneLabel;
    private JLabel emailLabel;
    private JLabel residenceLabel;
    private JLabel registrationNumberLabel;
    private JLabel feeLabel;
    private JLabel rankLabel;
    private JLabel lastRecallLabel;
    private JLabel activityLabel;
    private JLabel lastVisitLabel;
    private JLabel nextVisitLabel;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton saveUpdateButton;
    private JButton cancelUpdateButton;
    private Volunteer volunteer;
    private final VolunteerService service;

    public VolunteerDetails(Frame owner, VolunteerService service, Volunteer v) throws HeadlessException {
        super(owner, "Dettagli arruolato", true);
        this.volunteer = v;
        this.service = service;
        updateFields();
        setContentPane(detailPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setTitle("Dettagli arruolato");
        setVisible(true);
        updateButton.addActionListener(e -> enableFormEdit());
        cancelUpdateButton.addActionListener(e -> disableFormEdit());
        saveUpdateButton.addActionListener(e -> {
            updateVolunteer();
            updateFields();
            disableFormEdit();
        });
        deleteButton.addActionListener(e -> {
            int res = JOptionPane.showConfirmDialog(deleteButton, "Eliminare arruolato " + volunteer.getName() + " " + volunteer.getSurname() + "?", "Elimina record", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            // 0 = OK, 2 = CANCEL, -1 CLOSE DIALOG
            System.out.println(res);
            if (res == 0) {
                service.delete(volunteer);
                dispose();
            }
        });
    }

    private void updateVolunteer() {
        volunteer.setName(nameField.getText());
        volunteer.setSurname(surnameField.getText());
        volunteer.setBirthDate(LocalDate.parse(birthDateField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))); //TODO check format
        volunteer.setBirthPlace(birthPlaceField.getText());
        volunteer.setPhoneNumber(phoneField.getText());
        volunteer.setEmailAddress(emailField.getText());
        volunteer.setResidenceAddress(residenceField.getText());
        volunteer.setRegistrationNumber(registrationNumberField.getText());
        volunteer.setFee(Double.valueOf(feeField.getText()));
        volunteer.setRankValue(rankField.getText());
        volunteer.setLastRecall(LocalDate.parse(lastRecallField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))); //TODO check format
        volunteer.setLastActivity(activityField.getText());
        volunteer.setLastMedicalVisit(LocalDate.parse(lastVisitField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))); //TODO check format
        volunteer.setNextMedicalVisit(LocalDate.parse(nextVisitField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))); //TODO check format
        this.volunteer = service.save(volunteer);
    }

    private void updateFields() {
        this.nameField.setText(volunteer.getName() == null ? "-" : volunteer.getName());
        this.surnameField.setText(volunteer.getSurname() == null ? "-" : volunteer.getSurname());
        this.birthDateField.setText(volunteer.getBirthDate() == null ? "-" : volunteer.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.birthPlaceField.setText(volunteer.getBirthPlace() == null ? "-" : volunteer.getBirthPlace());
        this.phoneField.setText(volunteer.getPhoneNumber() == null ? "-" : volunteer.getPhoneNumber());
        this.emailField.setText(volunteer.getEmailAddress() == null ? "-" : volunteer.getEmailAddress());
        this.residenceField.setText(volunteer.getResidenceAddress() == null ? "-" : volunteer.getResidenceAddress());
        this.registrationNumberField.setText(volunteer.getRegistrationNumber() == null ? "-" : volunteer.getRegistrationNumber());
        this.feeField.setText(volunteer.getFee() == null ? "-" : String.valueOf(volunteer.getFee()));
        this.rankField.setText(volunteer.getRankValue() == null ? "-" : volunteer.getRankValue());
        this.lastRecallField.setText(volunteer.getLastRecall() == null ? "-" : volunteer.getLastRecall().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.activityField.setText(volunteer.getLastActivity() == null ? "-" : volunteer.getLastActivity());
        this.lastVisitField.setText(volunteer.getLastMedicalVisit() == null ? "-" : volunteer.getLastMedicalVisit().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.nextVisitField.setText(volunteer.getNextMedicalVisit() == null ? "-" : volunteer.getNextMedicalVisit().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    private void enableFormEdit() {
        nameField.setEditable(true);
        surnameField.setEditable(true);
        birthDateField.setEditable(true);
        birthPlaceField.setEditable(true);
        phoneField.setEditable(true);
        emailField.setEditable(true);
        residenceField.setEditable(true);
        registrationNumberField.setEditable(true);
        feeField.setEditable(true);
        rankField.setEditable(true);
        lastRecallField.setEditable(true);
        activityField.setEditable(true);
        lastVisitField.setEditable(true);
        nextVisitField.setEditable(true);
        saveUpdateButton.setVisible(true);
        cancelUpdateButton.setVisible(true);
        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);
    }

    private void disableFormEdit() {
        nameField.setEditable(false);
        surnameField.setEditable(false);
        birthDateField.setEditable(false);
        birthPlaceField.setEditable(false);
        phoneField.setEditable(false);
        emailField.setEditable(false);
        residenceField.setEditable(false);
        registrationNumberField.setEditable(false);
        feeField.setEditable(false);
        rankField.setEditable(false);
        lastRecallField.setEditable(false);
        activityField.setEditable(false);
        lastVisitField.setEditable(false);
        nextVisitField.setEditable(false);
        saveUpdateButton.setVisible(false);
        cancelUpdateButton.setVisible(false);
        updateButton.setEnabled(true);
        deleteButton.setEnabled(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        detailPanel = new JPanel();
        detailPanel.setLayout(new FormLayout("fill:d:grow,fill:d:grow", "fill:max(d;4px):noGrow,fill:max(d;4px):noGrow,fill:max(d;4px):noGrow,fill:max(d;4px):noGrow,fill:max(d;4px):noGrow,fill:max(d;4px):noGrow,fill:max(d;4px):noGrow,fill:max(d;4px):noGrow,fill:14px:noGrow,fill:max(d;4px):noGrow,fill:34px:noGrow,fill:max(d;4px):noGrow,fill:max(d;4px):noGrow,fill:max(d;4px):noGrow,fill:max(d;4px):noGrow,fill:max(d;4px):noGrow,fill:max(d;4px):noGrow,center:max(d;4px):noGrow,center:max(d;4px):noGrow"));
        detailPanel.setAlignmentX(0.5f);
        detailPanel.setAlignmentY(0.5f);
        detailPanel.setAutoscrolls(true);
        detailPanel.setName("Dettagli arruolato");
        detailPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Dettagli arruolato", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        nameField = new JTextField();
        nameField.setEditable(false);
        nameField.setPreferredSize(new Dimension(100, 30));
        CellConstraints cc = new CellConstraints();
        detailPanel.add(nameField, cc.xy(1, 2, CellConstraints.FILL, CellConstraints.CENTER));
        nameLabel = new JLabel();
        nameLabel.setText("Nome");
        detailPanel.add(nameLabel, cc.xy(1, 1));
        surnameLabel = new JLabel();
        surnameLabel.setText("Cognome");
        detailPanel.add(surnameLabel, cc.xy(2, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
        surnameField = new JTextField();
        surnameField.setEditable(false);
        surnameField.setPreferredSize(new Dimension(100, 30));
        detailPanel.add(surnameField, cc.xy(2, 2, CellConstraints.FILL, CellConstraints.CENTER));
        birthDateField = new JTextField();
        birthDateField.setEditable(false);
        birthDateField.setPreferredSize(new Dimension(100, 30));
        detailPanel.add(birthDateField, cc.xy(1, 4, CellConstraints.FILL, CellConstraints.CENTER));
        birthPlaceField = new JTextField();
        birthPlaceField.setEditable(false);
        birthPlaceField.setPreferredSize(new Dimension(100, 30));
        detailPanel.add(birthPlaceField, cc.xy(2, 4, CellConstraints.FILL, CellConstraints.CENTER));
        birthDateLabel = new JLabel();
        birthDateLabel.setText("Data di nascita");
        detailPanel.add(birthDateLabel, cc.xy(1, 3));
        birthPlaceLabel = new JLabel();
        birthPlaceLabel.setText("Luogo di nascita");
        detailPanel.add(birthPlaceLabel, cc.xy(2, 3, CellConstraints.LEFT, CellConstraints.DEFAULT));
        phoneField = new JTextField();
        phoneField.setEditable(false);
        phoneField.setPreferredSize(new Dimension(100, 30));
        detailPanel.add(phoneField, cc.xy(1, 6, CellConstraints.FILL, CellConstraints.CENTER));
        emailField = new JTextField();
        emailField.setEditable(false);
        emailField.setPreferredSize(new Dimension(100, 30));
        detailPanel.add(emailField, cc.xy(2, 6, CellConstraints.FILL, CellConstraints.CENTER));
        phoneLabel = new JLabel();
        phoneLabel.setText("Cellulare");
        detailPanel.add(phoneLabel, cc.xy(1, 5));
        emailLabel = new JLabel();
        emailLabel.setText("e-mail");
        detailPanel.add(emailLabel, cc.xy(2, 5, CellConstraints.LEFT, CellConstraints.DEFAULT));
        residenceField = new JTextField();
        residenceField.setEditable(false);
        residenceField.setPreferredSize(new Dimension(100, 30));
        detailPanel.add(residenceField, cc.xy(1, 8, CellConstraints.FILL, CellConstraints.CENTER));
        residenceLabel = new JLabel();
        residenceLabel.setText("Residenza");
        detailPanel.add(residenceLabel, cc.xy(1, 7, CellConstraints.LEFT, CellConstraints.FILL));
        final JSeparator separator1 = new JSeparator();
        detailPanel.add(separator1, cc.xy(1, 9, CellConstraints.FILL, CellConstraints.FILL));
        final JSeparator separator2 = new JSeparator();
        detailPanel.add(separator2, cc.xy(2, 9, CellConstraints.FILL, CellConstraints.FILL));
        registrationNumberField = new JTextField();
        registrationNumberField.setEditable(false);
        registrationNumberField.setPreferredSize(new Dimension(100, 30));
        detailPanel.add(registrationNumberField, cc.xy(1, 11, CellConstraints.FILL, CellConstraints.CENTER));
        registrationNumberLabel = new JLabel();
        registrationNumberLabel.setText("Num. Matricola");
        detailPanel.add(registrationNumberLabel, cc.xy(1, 10, CellConstraints.LEFT, CellConstraints.DEFAULT));
        feeField = new JTextField();
        feeField.setEditable(false);
        feeField.setPreferredSize(new Dimension(100, 30));
        detailPanel.add(feeField, cc.xy(2, 11, CellConstraints.FILL, CellConstraints.CENTER));
        feeLabel = new JLabel();
        feeLabel.setText("Quota ass.");
        detailPanel.add(feeLabel, cc.xy(2, 10, CellConstraints.LEFT, CellConstraints.DEFAULT));
        rankField = new JTextField();
        rankField.setEditable(false);
        rankField.setPreferredSize(new Dimension(100, 30));
        detailPanel.add(rankField, cc.xy(1, 13, CellConstraints.FILL, CellConstraints.CENTER));
        rankLabel = new JLabel();
        rankLabel.setText("Grado");
        detailPanel.add(rankLabel, cc.xy(1, 12, CellConstraints.LEFT, CellConstraints.FILL));
        lastRecallField = new JTextField();
        lastRecallField.setEditable(false);
        lastRecallField.setPreferredSize(new Dimension(100, 30));
        detailPanel.add(lastRecallField, cc.xy(2, 13, CellConstraints.FILL, CellConstraints.CENTER));
        lastRecallLabel = new JLabel();
        lastRecallLabel.setText("Ultimo richiamo");
        detailPanel.add(lastRecallLabel, cc.xy(2, 12));
        activityField = new JTextField();
        activityField.setEditable(false);
        activityField.setPreferredSize(new Dimension(100, 30));
        detailPanel.add(activityField, cc.xy(1, 15, CellConstraints.FILL, CellConstraints.DEFAULT));
        activityLabel = new JLabel();
        activityLabel.setText("Attività");
        detailPanel.add(activityLabel, cc.xy(1, 14));
        lastVisitField = new JTextField();
        lastVisitField.setEditable(false);
        lastVisitField.setPreferredSize(new Dimension(100, 30));
        detailPanel.add(lastVisitField, cc.xy(1, 17, CellConstraints.FILL, CellConstraints.DEFAULT));
        lastVisitLabel = new JLabel();
        lastVisitLabel.setText("Ultima visita");
        detailPanel.add(lastVisitLabel, cc.xy(1, 16));
        nextVisitField = new JTextField();
        nextVisitField.setEditable(false);
        nextVisitField.setPreferredSize(new Dimension(100, 30));
        detailPanel.add(nextVisitField, cc.xy(2, 17, CellConstraints.FILL, CellConstraints.CENTER));
        nextVisitLabel = new JLabel();
        nextVisitLabel.setText("Prox. visita");
        detailPanel.add(nextVisitLabel, cc.xy(2, 16));
        deleteButton = new JButton();
        deleteButton.setText("Elimina record");
        detailPanel.add(deleteButton, cc.xy(2, 18));
        updateButton = new JButton();
        updateButton.setText("Modifica");
        detailPanel.add(updateButton, cc.xy(1, 18));
        cancelUpdateButton = new JButton();
        cancelUpdateButton.setText("Annulla");
        cancelUpdateButton.setVisible(false);
        detailPanel.add(cancelUpdateButton, cc.xy(2, 19));
        saveUpdateButton = new JButton();
        saveUpdateButton.setText("Salva");
        saveUpdateButton.setVisible(false);
        detailPanel.add(saveUpdateButton, cc.xy(1, 19));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return detailPanel;
    }

}
