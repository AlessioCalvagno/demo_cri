package it.cri.demo.ui.detaildialogs;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import it.cri.demo.entity.Recall;
import it.cri.demo.service.RecallService;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RecallDetails extends JDialog {
    private JPanel mainPanel;
    private JButton updateButton;
    private JButton deleteButton;
    private JFormattedTextField startDateField;
    private JLabel startDateLabel;
    private JTextField activityField;
    private JLabel activityLabel;
    private JButton confermaButton;
    private JButton annullaButton;
    private JButton downloadButton;
    private JButton uploadButton;
    private JFormattedTextField endDateField;
    private JLabel endDateLabel;
    private JLabel fileLabel;

    private Recall recall;
    private final RecallService recallService;
    private File tmpFile;

    public RecallDetails(Frame owner, Recall recall, RecallService recallService) {
        super(owner, "Dettagli richiamo", true);
        $$$setupUI$$$();
        updateFields();
        setContentPane(mainPanel);
        setSize(160, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.recall = recall;
        this.recallService = recallService;
        this.tmpFile = null;

        updateButton.addActionListener(e -> enableFormEdit());
        annullaButton.addActionListener(e -> {
            disableFormEdit();
            updateFields();
        });

        confermaButton.addActionListener(e -> {
            try {
                updateRecall();
                updateFields();
                disableFormEdit();
            } catch (IOException ex) {
                System.out.println("Error in updateRecall(): " + ex.getMessage());
                JOptionPane.showMessageDialog(confermaButton, "Errore nell'aggiornare il record, riprova: " + ex.getMessage(),
                        "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteButton.addActionListener(e -> {
            int res = JOptionPane.showConfirmDialog(deleteButton, "Eliminare richiamo? ", "Elimina record", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            // 0 = OK, 2 = CANCEL, -1 CLOSE DIALOG
            if (res == 0) {
                recallService.delete(recall);
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

                String fileName = "richiamo_" + recall.getStartDate().format(DateTimeFormatter.ofPattern("dd_MM_yyyy")) + ".pdf"; // Replace with your actual file name
                File outputFile = new File(selectedDir, fileName);

                try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                    fos.write(recall.getFile());

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

    private void updateRecall() throws IOException {
        recall.setStartDate(LocalDate.parse(startDateField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        recall.setEndDate(LocalDate.parse(endDateField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        recall.setActivity(activityField.getText());
        if (tmpFile != null)
            recall.setFile(Files.readAllBytes(tmpFile.toPath()));
        this.recall = recallService.save(recall);
    }

    private void updateFields() {
        this.startDateField.setText(recall.getStartDate() == null ? "" : recall.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.endDateField.setText(recall.getEndDate() == null ? "" : recall.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.activityField.setText(recall.getActivity() == null ? "" : recall.getActivity());
    }

    private void enableFormEdit() {
        startDateField.setEnabled(true);
        endDateField.setEnabled(true);
        activityField.setEnabled(true);
        uploadButton.setVisible(true);
        downloadButton.setVisible(false);

        confermaButton.setVisible(true);
        annullaButton.setVisible(true);
        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);
    }

    private void disableFormEdit() {
        startDateField.setEnabled(false);
        endDateField.setEnabled(false);
        activityField.setEnabled(false);
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
        mainPanel.setLayout(new FormLayout("fill:max(d;4px):grow,fill:max(d;4px):grow", "fill:max(d;4px):noGrow,center:d:noGrow,fill:max(d;4px):noGrow,center:max(d;4px):noGrow,fill:max(d;4px):noGrow,center:d:noGrow,fill:d:noGrow,center:max(d;4px):noGrow,center:max(d;4px):noGrow,fill:max(d;4px):grow,center:max(d;4px):noGrow,center:max(d;4px):noGrow"));
        updateButton = new JButton();
        updateButton.setPreferredSize(new Dimension(80, 30));
        updateButton.setText("Modifica");
        CellConstraints cc = new CellConstraints();
        mainPanel.add(updateButton, cc.xy(1, 12));
        deleteButton = new JButton();
        deleteButton.setPreferredSize(new Dimension(80, 30));
        deleteButton.setText("Elimina record");
        mainPanel.add(deleteButton, cc.xy(2, 12));
        final JSeparator separator1 = new JSeparator();
        mainPanel.add(separator1, cc.xyw(1, 10, 2, CellConstraints.FILL, CellConstraints.FILL));
        mainPanel.add(startDateField, cc.xyw(1, 2, 2, CellConstraints.FILL, CellConstraints.DEFAULT));
        startDateLabel = new JLabel();
        startDateLabel.setText("Data inizio");
        mainPanel.add(startDateLabel, cc.xyw(1, 1, 2));
        activityField = new JTextField();
        mainPanel.add(activityField, cc.xyw(1, 6, 2, CellConstraints.FILL, CellConstraints.DEFAULT));
        activityLabel = new JLabel();
        activityLabel.setText("Attività");
        mainPanel.add(activityLabel, cc.xyw(1, 5, 2));
        confermaButton.setPreferredSize(new Dimension(80, 30));
        confermaButton.setText("Conferma");
        mainPanel.add(confermaButton, cc.xy(1, 11));
        annullaButton.setText("Annulla");
        mainPanel.add(annullaButton, cc.xy(2, 11));
        fileLabel = new JLabel();
        fileLabel.setText("File");
        mainPanel.add(fileLabel, cc.xyw(1, 7, 2));
        downloadButton = new JButton();
        downloadButton.setText("Scarica");
        mainPanel.add(downloadButton, cc.xyw(1, 8, 2, CellConstraints.FILL, CellConstraints.DEFAULT));
        uploadButton.setText("Carica");
        mainPanel.add(uploadButton, cc.xyw(1, 9, 2));
        mainPanel.add(endDateField, cc.xyw(1, 4, 2, CellConstraints.FILL, CellConstraints.DEFAULT));
        endDateLabel = new JLabel();
        endDateLabel.setText("Data fine");
        mainPanel.add(endDateLabel, cc.xyw(1, 3, 2, CellConstraints.DEFAULT, CellConstraints.FILL));
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
        endDateField = new JFormattedTextField(dateFormatter);
        endDateField.setColumns(10);
        startDateField = new JFormattedTextField(dateFormatter);
        startDateField.setColumns(10);
    }
}
