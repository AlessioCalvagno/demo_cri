package it.cri.demo.ui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import it.cri.demo.entity.Volunteer;
import it.cri.demo.service.VolunteerService;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VolunteerDetails extends JDialog {
    private JPanel detailPanel;
    private JTextField nameField;
    private JTextField surnameField;
    private JFormattedTextField birthDateField;
    private JTextField birthPlaceField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField residenceField;
    private JTextField registrationNumberField;
    private JLabel nameLabel;
    private JLabel surnameLabel;
    private JLabel birthDateLabel;
    private JLabel birthPlaceLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JLabel registrationNumberLabel;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton saveUpdateButton;
    private JButton cancelUpdateButton;
    private JLabel residenceLabel;
    private JLabel pecLabel;
    private JTextField pecField;
    private JButton visiteMedicheButton;
    private JButton associativeFeeButton;
    private JButton brevetButton;
    private JButton recallButton;
    private JButton qualificationButton;
    private JButton promotionButton;
    private JSeparator bottomSeparator;
    private Volunteer volunteer;
    private final VolunteerService service;

    public VolunteerDetails(Frame owner, VolunteerService service, Volunteer v) throws HeadlessException {
        super(owner, "Dettagli arruolato", true);
        this.volunteer = v;
        this.service = service;
        $$$setupUI$$$();
        updateFields();
        setContentPane(detailPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 500);
//        pack();
        setTitle("Dettagli arruolato");
//        setVisible(true);
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
            if (res == 0) {
                service.delete(volunteer);
                dispose();
            }
        });
        visiteMedicheButton.addActionListener(e -> openMedicalVisitUI());
        associativeFeeButton.addActionListener(e -> openAssociativeFeeUI());
        brevetButton.addActionListener(e -> openBrevetUI());
        recallButton.addActionListener(e -> openRecallUI());
        qualificationButton.addActionListener(e -> openQualificationUI());
        promotionButton.addActionListener(e -> openPromotionUI());
    }


    private void openMedicalVisitUI() {
        new MedicalVisitUI(null, volunteer, service).setVisible(true);
    }

    private void openAssociativeFeeUI() {
        new AssociativeFeeUI(null, volunteer, service).setVisible(true);
    }

    private void openBrevetUI() {
        new BrevetUI(null, volunteer, service).setVisible(true);
    }

    private void openRecallUI() {
        new RecallUI(null, volunteer, service).setVisible(true);
    }

    private void openQualificationUI() {
        new QualificationUI(null, volunteer, service).setVisible(true);
    }

    private void openPromotionUI() {
        new PromotionUI(null, volunteer, service).setVisible(true);
    }

    private void updateVolunteer() {
        volunteer.setName(nameField.getText());
        volunteer.setSurname(surnameField.getText());
        volunteer.setBirthDate(LocalDate.parse(birthDateField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))); //TODO check format
        volunteer.setBirthPlace(birthPlaceField.getText());
        volunteer.setPhoneNumber(phoneField.getText());
        volunteer.setEmailAddress(emailField.getText());
        volunteer.setPecAddress(pecField.getText());
        volunteer.setResidenceAddress(residenceField.getText());
        volunteer.setRegistrationNumber(registrationNumberField.getText());
        this.volunteer = service.save(volunteer);
    }

    private void updateFields() {
        this.nameField.setText(volunteer.getName() == null ? "-" : volunteer.getName());
        this.surnameField.setText(volunteer.getSurname() == null ? "-" : volunteer.getSurname());
        this.birthDateField.setText(volunteer.getBirthDate() == null ? "" : volunteer.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.birthPlaceField.setText(volunteer.getBirthPlace() == null ? "-" : volunteer.getBirthPlace());
        this.phoneField.setText(volunteer.getPhoneNumber() == null ? "-" : volunteer.getPhoneNumber());
        this.emailField.setText(volunteer.getEmailAddress() == null ? "-" : volunteer.getEmailAddress());
        this.pecField.setText(volunteer.getPecAddress() == null ? "-" : volunteer.getPecAddress());
        this.residenceField.setText(volunteer.getResidenceAddress() == null ? "-" : volunteer.getResidenceAddress());
        this.registrationNumberField.setText(volunteer.getRegistrationNumber() == null ? "-" : volunteer.getRegistrationNumber());
    }

    private void enableFormEdit() {
        nameField.setEditable(true);
        surnameField.setEditable(true);
        birthDateField.setEditable(true);
        birthPlaceField.setEditable(true);
        phoneField.setEditable(true);
        emailField.setEditable(true);
        pecField.setEditable(true);
        residenceField.setEditable(true);
        registrationNumberField.setEditable(true);
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
        pecField.setEditable(false);
        residenceField.setEditable(false);
        registrationNumberField.setEditable(false);
        saveUpdateButton.setVisible(false);
        cancelUpdateButton.setVisible(false);
        updateButton.setEnabled(true);
        deleteButton.setEnabled(true);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        detailPanel = new JPanel();
        detailPanel.setLayout(new FormLayout("fill:d:grow,fill:d:grow", "fill:max(d;4px):noGrow,fill:max(d;4px):noGrow,fill:max(d;4px):noGrow,fill:max(d;4px):noGrow,fill:max(d;4px):noGrow,fill:d:noGrow,fill:max(d;4px):noGrow,fill:d:noGrow,fill:max(d;4px):noGrow,fill:34px:noGrow,fill:d:noGrow,fill:d:noGrow,fill:d:noGrow,fill:d:noGrow,fill:d:grow,center:max(d;4px):noGrow,fill:d:noGrow"));
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
        pecField = new JTextField();
        pecField.setEditable(false);
        pecField.setPreferredSize(new Dimension(100, 30));
        detailPanel.add(pecField, cc.xy(1, 6, CellConstraints.FILL, CellConstraints.CENTER));
        emailField = new JTextField();
        emailField.setEditable(false);
        emailField.setPreferredSize(new Dimension(100, 30));
        detailPanel.add(emailField, cc.xy(2, 6, CellConstraints.FILL, CellConstraints.CENTER));
        pecLabel = new JLabel();
        pecLabel.setText("PEC");
        detailPanel.add(pecLabel, cc.xy(1, 5));
        emailLabel = new JLabel();
        emailLabel.setText("e-mail");
        detailPanel.add(emailLabel, cc.xy(2, 5, CellConstraints.LEFT, CellConstraints.DEFAULT));
        phoneField = new JTextField();
        phoneField.setEditable(false);
        phoneField.setPreferredSize(new Dimension(100, 30));
        detailPanel.add(phoneField, cc.xy(1, 8, CellConstraints.FILL, CellConstraints.CENTER));
        phoneLabel = new JLabel();
        phoneLabel.setText("Cellulare");
        detailPanel.add(phoneLabel, cc.xy(1, 7, CellConstraints.LEFT, CellConstraints.FILL));
        final JSeparator separator1 = new JSeparator();
        detailPanel.add(separator1, cc.xy(1, 11, CellConstraints.FILL, CellConstraints.FILL));
        final JSeparator separator2 = new JSeparator();
        detailPanel.add(separator2, cc.xy(2, 11, CellConstraints.FILL, CellConstraints.FILL));
        deleteButton = new JButton();
        deleteButton.setPreferredSize(new Dimension(100, 30));
        deleteButton.setText("Elimina record");
        detailPanel.add(deleteButton, cc.xy(2, 17));
        updateButton = new JButton();
        updateButton.setPreferredSize(new Dimension(100, 30));
        updateButton.setText("Modifica");
        detailPanel.add(updateButton, cc.xy(1, 17));
        registrationNumberField = new JTextField();
        registrationNumberField.setEditable(false);
        registrationNumberField.setPreferredSize(new Dimension(100, 30));
        detailPanel.add(registrationNumberField, cc.xy(2, 8, CellConstraints.FILL, CellConstraints.CENTER));
        registrationNumberLabel = new JLabel();
        registrationNumberLabel.setText("Num. Matricola");
        detailPanel.add(registrationNumberLabel, cc.xy(2, 7, CellConstraints.LEFT, CellConstraints.DEFAULT));
        residenceField = new JTextField();
        residenceField.setEditable(false);
        residenceField.setPreferredSize(new Dimension(200, 30));
        detailPanel.add(residenceField, cc.xyw(1, 10, 2, CellConstraints.FILL, CellConstraints.DEFAULT));
        residenceLabel = new JLabel();
        residenceLabel.setText("Residenza");
        detailPanel.add(residenceLabel, cc.xy(1, 9, CellConstraints.LEFT, CellConstraints.DEFAULT));
        qualificationButton = new JButton();
        qualificationButton.setPreferredSize(new Dimension(100, 30));
        qualificationButton.setText("Qualifiche");
        detailPanel.add(qualificationButton, cc.xy(1, 14));
        visiteMedicheButton = new JButton();
        visiteMedicheButton.setPreferredSize(new Dimension(100, 30));
        visiteMedicheButton.setText("Visite mediche");
        detailPanel.add(visiteMedicheButton, cc.xy(2, 12));
        associativeFeeButton = new JButton();
        associativeFeeButton.setPreferredSize(new Dimension(100, 30));
        associativeFeeButton.setText("Quota associativa");
        detailPanel.add(associativeFeeButton, cc.xy(1, 12));
        recallButton = new JButton();
        recallButton.setPreferredSize(new Dimension(100, 30));
        recallButton.setText("Richiami");
        detailPanel.add(recallButton, cc.xy(1, 13));
        brevetButton = new JButton();
        brevetButton.setPreferredSize(new Dimension(100, 30));
        brevetButton.setText("Brevetti");
        detailPanel.add(brevetButton, cc.xy(2, 14));
        promotionButton = new JButton();
        promotionButton.setPreferredSize(new Dimension(100, 30));
        promotionButton.setText("Promozioni");
        detailPanel.add(promotionButton, cc.xy(2, 13));
        bottomSeparator = new JSeparator();
        bottomSeparator.setPreferredSize(new Dimension(200, 30));
        detailPanel.add(bottomSeparator, cc.xyw(1, 15, 2, CellConstraints.FILL, CellConstraints.FILL));
        saveUpdateButton.setPreferredSize(new Dimension(100, 30));
        saveUpdateButton.setText("Conferma");
        detailPanel.add(saveUpdateButton, cc.xy(1, 16));
        cancelUpdateButton.setPreferredSize(new Dimension(100, 30));
        cancelUpdateButton.setText("Annulla");
        detailPanel.add(cancelUpdateButton, cc.xy(2, 16));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return detailPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        cancelUpdateButton = new JButton();
        cancelUpdateButton.setVisible(false);

        saveUpdateButton = new JButton();
        saveUpdateButton.setVisible(false);

        MaskFormatter dateFormatter = null;
        try {
            dateFormatter = new MaskFormatter("##/##/####");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        dateFormatter.setPlaceholderCharacter('_');

        birthDateField = new JFormattedTextField(dateFormatter);
        birthDateField.setColumns(10);
    }
}
