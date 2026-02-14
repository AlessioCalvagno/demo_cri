package it.cri.demo.ui.detaildialogs;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import it.cri.demo.entity.MedicalVisit;
import it.cri.demo.service.MedicalVisitService;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MedicalVisitDetails extends JDialog {
    private JPanel mainPanel;
    private JButton updateButton;
    private JButton deleteButton;
    private JFormattedTextField dateField;
    private JLabel dateLabel;
    private JTextField doctorField;
    private JLabel doctorLabel;
    private JButton confermaButton;
    private JButton annullaButton;
    private JButton downloadButton;
    private JButton uploadButton;
    private JLabel fileLabel;

    private MedicalVisit medicalVisit;
    private final MedicalVisitService medicalVisitService;
    private File tmpFile;

    public MedicalVisitDetails(Frame owner, MedicalVisit medicalVisit, MedicalVisitService medicalVisitService) {
        super(owner, "Dettagli visita medica", true);
        this.medicalVisit = medicalVisit;
        this.medicalVisitService = medicalVisitService;
        this.tmpFile = null;
        $$$setupUI$$$();
        updateFields();
        setContentPane(mainPanel);
        setSize(160, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        updateButton.addActionListener(e -> enableFormEdit());
        annullaButton.addActionListener(e -> {
            disableFormEdit();
            updateFields();
        });

        confermaButton.addActionListener(e -> {
            try {
                updateMedicalVisit();
                updateFields();
                disableFormEdit();
            } catch (IOException ex) {
                System.out.println("Error in updateMedicalVisit(): " + ex.getMessage());
                JOptionPane.showMessageDialog(confermaButton, "Errore nell'aggiornare il record, riprova: " + ex.getMessage(),
                        "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteButton.addActionListener(e -> {
            int res = JOptionPane.showConfirmDialog(deleteButton, "Eliminare visita medica? ", "Elimina record", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            // 0 = OK, 2 = CANCEL, -1 CLOSE DIALOG
            if (res == 0) {
                medicalVisitService.delete(medicalVisit);
                dispose();
            }
        });

        uploadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(uploadButton) == JFileChooser.APPROVE_OPTION) {
                tmpFile = fileChooser.getSelectedFile();
            }
        });

        downloadButton.addActionListener(e -> {
            JFileChooser dirChooser = new JFileChooser();
            dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (dirChooser.showOpenDialog(uploadButton) == JFileChooser.APPROVE_OPTION) {
                File selectedDir = dirChooser.getSelectedFile();

                String fileName = "visita_medica_" + medicalVisit.getDate().format(DateTimeFormatter.ofPattern("dd_MM_yyyy")) + ".pdf"; // Replace with your actual file name
                File outputFile = new File(selectedDir, fileName);

                try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                    fos.write(medicalVisit.getFile());

                    // Display success message
                    JOptionPane.showMessageDialog(
                            null,
                            "File salvato in: " + outputFile.getAbsolutePath(),
                            "Download Coompletato",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } catch (IOException ex) {
                    System.out.println("Error in file download: " + ex.getMessage());
                    JOptionPane.showMessageDialog(downloadButton, "Errore nel scaricare il file, riprova: " + ex.getMessage(),
                            "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void updateMedicalVisit() throws IOException {
        medicalVisit.setDate(LocalDate.parse(dateField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        medicalVisit.setDoctor(doctorField.getText());
        if (tmpFile != null)
            medicalVisit.setFile(Files.readAllBytes(tmpFile.toPath()));
        this.medicalVisit = medicalVisitService.save(medicalVisit);
    }

    private void updateFields() {
        this.dateField.setText(medicalVisit.getDate() == null ? "" : medicalVisit.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.doctorField.setText(medicalVisit.getDoctor() == null ? "" : medicalVisit.getDoctor());
    }

    private void enableFormEdit() {
        dateField.setEnabled(true);
        doctorField.setEnabled(true);
        uploadButton.setVisible(true);
        downloadButton.setVisible(false);

        confermaButton.setVisible(true);
        annullaButton.setVisible(true);
        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);
    }

    private void disableFormEdit() {
        dateField.setEnabled(false);
        doctorField.setEnabled(false);
        uploadButton.setVisible(false);
        downloadButton.setVisible(true);

        confermaButton.setVisible(false);
        annullaButton.setVisible(false);
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
        mainPanel = new JPanel();
        mainPanel.setLayout(new FormLayout("fill:max(d;4px):grow,fill:max(d;4px):grow", "fill:max(d;4px):noGrow,center:d:noGrow,fill:max(d;4px):noGrow,center:31px:noGrow,fill:d:noGrow,center:max(d;4px):noGrow,center:max(d;4px):noGrow,fill:max(d;4px):grow,center:max(d;4px):noGrow,center:max(d;4px):noGrow"));
        updateButton = new JButton();
        updateButton.setPreferredSize(new Dimension(80, 30));
        updateButton.setText("Modifica");
        CellConstraints cc = new CellConstraints();
        mainPanel.add(updateButton, cc.xy(1, 10));
        deleteButton = new JButton();
        deleteButton.setPreferredSize(new Dimension(80, 30));
        deleteButton.setText("Elimina record");
        mainPanel.add(deleteButton, cc.xy(2, 10));
        final JSeparator separator1 = new JSeparator();
        mainPanel.add(separator1, cc.xyw(1, 8, 2, CellConstraints.FILL, CellConstraints.FILL));
        mainPanel.add(dateField, cc.xyw(1, 2, 2, CellConstraints.FILL, CellConstraints.DEFAULT));
        dateLabel = new JLabel();
        dateLabel.setText("Data");
        mainPanel.add(dateLabel, cc.xyw(1, 1, 2));
        doctorField = new JTextField();
        mainPanel.add(doctorField, cc.xyw(1, 4, 2, CellConstraints.FILL, CellConstraints.DEFAULT));
        doctorLabel = new JLabel();
        doctorLabel.setText("Dottore");
        mainPanel.add(doctorLabel, cc.xyw(1, 3, 2));
        confermaButton.setPreferredSize(new Dimension(80, 30));
        confermaButton.setText("Conferma");
        mainPanel.add(confermaButton, cc.xy(1, 9));
        annullaButton.setText("Annulla");
        mainPanel.add(annullaButton, cc.xy(2, 9));
        fileLabel = new JLabel();
        fileLabel.setText("File");
        mainPanel.add(fileLabel, cc.xyw(1, 5, 2));
        downloadButton = new JButton();
        downloadButton.setText("Scarica");
        mainPanel.add(downloadButton, cc.xyw(1, 6, 2, CellConstraints.FILL, CellConstraints.DEFAULT));
        uploadButton.setText("Carica");
        mainPanel.add(uploadButton, cc.xyw(1, 7, 2));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        confermaButton = new JButton();
        confermaButton.setVisible(false);

        annullaButton = new JButton();
        annullaButton.setVisible(false);

        uploadButton = new JButton();
        uploadButton.setVisible(false);

        MaskFormatter dateFormatter = null;
        try {
            dateFormatter = new MaskFormatter("##/##/####");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        dateFormatter.setPlaceholderCharacter('_');
        dateField = new JFormattedTextField(dateFormatter);
        dateField.setColumns(10);
    }
}
