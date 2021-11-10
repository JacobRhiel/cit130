package edu.ccac.courses.cit130.assignments.third.tuitioninformation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jul 30, 2021
 */
public class StudentDisplayPanel extends JPanel {

    public static StudentTableModel STUDENT_TABLE_MODEL = new StudentTableModel();

    public StudentDisplayPanel() {
        setLayout(new BorderLayout());
        JTable table = new JTable(STUDENT_TABLE_MODEL);
        JScrollPane studentTableScrollPane = new JScrollPane(table);
        add(studentTableScrollPane, BorderLayout.CENTER);
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    Student student = STUDENT_TABLE_MODEL.getStudentAtRow(row);
                    StudentInformationDisplay displayPanel = new StudentInformationDisplay(student);
                    JOptionPane.showMessageDialog(table.getParent(), displayPanel);
                }
            }
        });
    }

    static {

        STUDENT_TABLE_MODEL.insertStudentEntry(new FullTimeStudent(
                1, "Joshua", "Carpenter", "Dallas", "TX",
                12345, ResidentStatus.C, "joshua.carpenter@fake.com", 14));

        STUDENT_TABLE_MODEL.insertStudentEntry(new FullTimeStudent(
                2, "Dan", "Green", "Cleveland", "OH",
                12345, ResidentStatus.OC, "greenmandan@fake.com", 12));
        STUDENT_TABLE_MODEL.insertStudentEntry(new PartTimeStudent(
                3, "Eric", "Vought", "Atlanta", "Georgia",
                12345, ResidentStatus.C, "evaught@fake.com", 4));
        STUDENT_TABLE_MODEL.insertStudentEntry(new FullTimeStudent(
                4, "Forest", "Gump", "Lockport", "LA",
                12345, ResidentStatus.OS, "forest.gump@fake.com", 15));

    }

}
