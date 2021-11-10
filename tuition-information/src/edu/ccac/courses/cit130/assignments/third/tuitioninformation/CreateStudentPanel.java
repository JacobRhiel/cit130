package edu.ccac.courses.cit130.assignments.third.tuitioninformation;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static edu.ccac.courses.cit130.assignments.third.tuitioninformation.StudentDisplayPanel.STUDENT_TABLE_MODEL;

/**
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jul 27, 2021
 */
public class CreateStudentPanel extends JPanel {

    private JPanel inputArea;

    private final JButton createStudentButton = new JButton("Submit New Student");
    private final JButton clearStudentDataButton = new JButton("Clear Student Data");
    private static final JButton generateIdButton = new JButton("Generate ID");

    private static final StudentStatusCheckbox studentStatusCheckbox = new StudentStatusCheckbox();
    private static final StudentEntryTextField studentIdTextField = new StudentEntryTextField(InputType.NUMERIC);
    private static final StudentEntryTextField totalSemesterCreditsTextField = new StudentEntryTextField(InputType.NUMERIC);

    private final ResidentialStatusSelector residentialStatusSelector = new ResidentialStatusSelector();
    private static final Border defaultBorder = studentIdTextField.getBorder();
    private static final Map<Integer, StudentEntryTextField> textFields = new HashMap<>();
    public static JLabel statusLabel = new JLabel(" ");

    public CreateStudentPanel() {
        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout());
        add(rootPanel, BorderLayout.CENTER);
        setupInputArea();
    }

    private void setupInputArea() {
        JPanel parent = new JPanel();
        parent.setLayout(new BorderLayout());
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        TitledBorder border = BorderCreator.createBoldTitledBorder("New Student");
        border.setTitleColor(Color.BLACK);
        border.setTitleFont(border.getTitleFont().deriveFont(Font.BOLD));
        container.setBorder(border);

        textFields.put(0, studentIdTextField);
        textFields.put(1, new StudentEntryTextField(InputType.ALPHA_SPACE));
        textFields.put(2, new StudentEntryTextField(InputType.STRING));
        textFields.put(3, new StudentEntryTextField(InputType.ALPHA_SPACE));
        textFields.put(4, new StudentEntryTextField(InputType.ALPHA_SPACE));
        textFields.put(5, new StudentEntryTextField(InputType.NUMERIC));
        textFields.put(6, new StudentEntryTextField(InputType.STRING));
        textFields.put(7, totalSemesterCreditsTextField);

        createInputAreaData();

        generateIdButton.addActionListener(e -> {
           studentIdTextField.setText(String.valueOf(STUDENT_TABLE_MODEL.getNextAvailableId()));
        });

        parent.add(this.inputArea, BorderLayout.NORTH);
        statusLabel.setForeground(Color.RED);
        statusLabel.setFont(statusLabel.getFont().deriveFont(Font.BOLD));
        parent.add(statusLabel, BorderLayout.CENTER);
        parent.add(createButtonGroup(), BorderLayout.SOUTH);
        container.add(parent);
        add(container, BorderLayout.CENTER);
    }

    /**
     * Constructs the input area data predefined by the textfield entries.
     */
    private void createInputAreaData() {
        this.inputArea = new JPanel();
        // + 2 because of non text field entries in the grid.
        this.inputArea.setLayout(new GridLayout(textFields.size() + 2, 3));
        this.inputArea.add(new JLabel("Student Number: "));
        this.inputArea.add(textFields.get(0));
        this.inputArea.add(generateIdButton);
        this.inputArea.add(new JLabel("Student Name: "));
        this.inputArea.add(textFields.get(1));
        this.inputArea.add(new JLabel());
        this.inputArea.add(new JLabel("Residential Status: "));
        this.inputArea.add(residentialStatusSelector);
        this.inputArea.add(new JLabel());
        this.inputArea.add(new JLabel("Student Address: "));
        this.inputArea.add(textFields.get(2));
        this.inputArea.add(new JLabel());
        this.inputArea.add(new JLabel("City: "));
        this.inputArea.add(textFields.get(3));
        this.inputArea.add(new JLabel());
        this.inputArea.add(new JLabel("State: "));
        this.inputArea.add(textFields.get(4));
        this.inputArea.add(new JLabel());
        this.inputArea.add(new JLabel("Zipcode: "));
        this.inputArea.add(textFields.get(5));
        this.inputArea.add(new JLabel());
        this.inputArea.add(new JLabel("Student Email: "));
        this.inputArea.add(textFields.get(6));
        this.inputArea.add(new JLabel());
        this.inputArea.add(new JLabel("Semester Credits: "));
        this.inputArea.add(textFields.get(7));
        this.inputArea.add(new JLabel());
        this.inputArea.add(new JLabel("Status: "));
        this.inputArea.add(studentStatusCheckbox);
    }

    /**
     * Creates the button group.
     * @return The button group JPanel.
     */
    private JPanel createButtonGroup() {
        JPanel buttonGroupPanel = new JPanel();
        buttonGroupPanel.setLayout(new GridLayout(1, 2));
        createStudentButton.addActionListener(e -> onCreate());
        clearStudentDataButton.addActionListener(e -> clearAllData());
        buttonGroupPanel.add(clearStudentDataButton);
        buttonGroupPanel.add(createStudentButton);
        return buttonGroupPanel;
    }

    /**
     * Clears all data from text fields.
     * Sends an alert message, that disappears after 5 seconds.
     */
    private void clearAllData() {
        for (StudentEntryTextField field : textFields.values()) {
            field.setText("");
        }
        residentialStatusSelector.setSelectedIndex(0);
        for (StudentEntryTextField field : textFields.values()) {
            field.setBorder(defaultBorder);
        }
        studentStatusCheckbox.clearErrors();
        statusLabel.setText("Cleared student form data.");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText(" ");
            }
        }, TimeUnit.SECONDS.toMillis(5));
    }

    /**
     * Sends a field update based on the component, if the listener is notified of a change.
     * @param textField The component that fired an update.
     */
    public static void pushTextFieldUpdate(StudentEntryTextField textField) {
        for (StudentEntryTextField field : textFields.values()) {
            if(field != textField) continue;
            if(field == totalSemesterCreditsTextField) {
                updateStudentStatusCheckbox(totalSemesterCreditsTextField.getText());
                return;
            }
            if(field == studentIdTextField) {
                updateExistingNumberCheck(studentIdTextField.getText());
                return;
            }
        }
    }

    /**
     * Checks if the entered student number is already in use.
     * @param idString The student number.
     */
    private static void updateExistingNumberCheck(String idString) {
        if(idString.isEmpty()) return;
        boolean isIdAvailable = STUDENT_TABLE_MODEL.isIdAvailable(Integer.parseInt(idString));
        if(!isIdAvailable) {
            studentIdTextField.setBorder(BorderFactory.createLineBorder(Color.RED));
            statusLabel.setText("Student ID is in use, use a new one or select generate to create an available one.");
        } else {
            studentIdTextField.setBorder(defaultBorder);
            statusLabel.setText(" ");
        }
    }

    /**
     * Updates the status radio buttons based on entered credit value.
     * @param creditsString The amount of credits.
     */
    private static void updateStudentStatusCheckbox(String creditsString) {
        if(creditsString.isEmpty()) return;
        int credits = Integer.parseInt(creditsString);
        studentStatusCheckbox.updateStatusForCredits(credits);
    }

    /**
     * Verifies that the text fields are not empty. (Pre submission check)
     * @return If text fields are not all empty.
     */
    public boolean verifyTextFieldsNotEmpty() {
        boolean hasError;
        for (StudentEntryTextField value : textFields.values()) {
            if (value.getText().isEmpty()) {
                value.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            }
        }
        hasError = studentStatusCheckbox.checkAndDisplayError();
        if (hasError)
            statusLabel.setText("Missing required fields, please fix fields outlined in red.");
        else statusLabel.setText("");
        return hasError;
    }

    /**
     * Sets the student exists error.
     */
    private void setStudentExistsError() {
        setStudentExistsError(false);
    }

    /**
     * Sets the student exists error.
     * @param clear To clear the error or not.
     */
    private void setStudentExistsError(boolean clear) {
        if(!clear)
            statusLabel.setText("Student already exists, unable to create. Please check student number or email address.");
        else statusLabel.setText(" ");
    }

    /**
     * Sends a success alert for an action.
     * @param message The message of the alert to send.
     */
    private void updateSuccessAlert(String message) {
        statusLabel.setForeground(Color.MAGENTA);
        statusLabel.setText(message);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText(" ");
            }
        }, TimeUnit.SECONDS.toMillis(5));
    }

    /**
     * Submits creation event for student.
     */
    public void onCreate() {
        if(verifyTextFieldsNotEmpty()) return;
        if(textFields.get(0).getText().isEmpty()) return;
        int studentNumber = Integer.parseInt(textFields.get(0).getText());
        String email = textFields.get(6).getText();
        if(STUDENT_TABLE_MODEL.studentExists(studentNumber, email)) {
            setStudentExistsError();
            return;
        }
        int credits = textFields.get(7) == null ? 0 : Integer.parseInt(textFields.get(7).getText());
        boolean isFullTime = credits >= 12;
        String studentName = textFields.get(1).getText();
        String studentAddress = textFields.get(2).getText();
        String city = textFields.get(3).getText();
        String state = textFields.get(4).getText();
        int zipcode = Integer.parseInt(textFields.get(5).getText());
        ResidentStatus status = (ResidentStatus)residentialStatusSelector.getSelectedItem();
        if (studentStatusCheckbox.isFullTimeStudent() && isFullTime) {
            FullTimeStudent student = new FullTimeStudent(
                    studentNumber, studentName, studentAddress, city, state, zipcode, status, email, credits
            );
            STUDENT_TABLE_MODEL.insertStudentEntry(student);
        } else if (!studentStatusCheckbox.isFullTimeStudent() || !isFullTime) {
            PartTimeStudent student = new PartTimeStudent(
                    studentNumber, studentName, studentAddress, city, state, zipcode, status, email, credits
            );
            STUDENT_TABLE_MODEL.insertStudentEntry(student);
        }
        updateSuccessAlert("Successfully added new student.");
    }
}
