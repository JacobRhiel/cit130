package edu.ccac.courses.cit130.assignments.third.tuitioninformation;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Override of DefaultTableModel to allow custom features.
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jul 04, 2021
 */
public class StudentTableModel extends DefaultTableModel {

    /**
     * Fields found by reflection.
     */
    private final Field[] fields;
    /**
     * Products of the model.
     */
    private final Map<Integer, Student> students;

    public StudentTableModel() {
        this.students = new HashMap<>();
        this.fields = Student.class.getDeclaredFields();
        // find fields reflectively and format the naming from camel case to add spaces in between humps
        // and capitalize the first letter.
        for (Field field : fields) {
            String formattedFieldName = field.getName().replaceAll(
                    String.format("%s|%s|%s",
                            "(?<=[A-Z])(?=[A-Z][a-z])",
                            "(?<=[^A-Z])(?=[A-Z])",
                            "(?<=[A-Za-z])(?=[^A-Za-z])"
                    ),
                    " "
            );
            addColumn(formattedFieldName.substring(0, 1).toUpperCase()
                    + formattedFieldName.substring(1));
        }
    }

    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }

    /**
     * Checks if the supplied id is available.
     * @param id The supplied id.
     * @return If the id is available.
     */
    public boolean isIdAvailable(int id) {
        for (Student student : students.values()) {
            int studentNumber = student.getNumber();
            if(studentNumber == id) return false;
        }
        return true;
    }

    /**
     * Gets the next available id.
     * @return The id.
     */
    public int getNextAvailableId() {
        int maxStudentNumber = 0;
        for (Student student : students.values()) {
            maxStudentNumber = Math.max(maxStudentNumber, student.getNumber());
        }
        return maxStudentNumber + 1;
    }

    /**
     * Checks if the student exists.
     * @param id The id.
     * @param email The email.
     * @return If the student exists.
     */
    public boolean studentExists(int id, String email) {
        if(!isIdAvailable(id)) return true;
        for (Student student : students.values()) {
            if(student.getEmailAddress().equals(email)) return true;
        }
        return false;
    }

    /**
     * Inserts student to student model.
     * @param student The student to insert.
     */
    public void insertStudentEntry(Student student) {
        int row = getRowCount();
        students.put(row, student);
        insertRow(row, new Object[] {
                student.getNumber(), student.getName(), student.getAddress(),
                student.getCity(), student.getState(),
                student.getZipCode(), student.getResidentStatus(),
                student.getEmailAddress(),
                student.getTotalSemesterCredits()
        });
    }

    /**
     * Gets the student at row index.
     * @param rowIndex The index of the row.
     * @return The student at the row index.
     */
    public Student getStudentAtRow(int rowIndex) {
        return students.getOrDefault(rowIndex, null);
    }
}
