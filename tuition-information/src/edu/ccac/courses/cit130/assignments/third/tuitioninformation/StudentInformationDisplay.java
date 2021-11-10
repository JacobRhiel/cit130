package edu.ccac.courses.cit130.assignments.third.tuitioninformation;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jul 31, 2021
 */
public class StudentInformationDisplay extends JPanel {

    public StudentInformationDisplay(Student student) {
        setLayout(new GridLayout(2, 2));
        JPanel studentInfo = new JPanel();
        studentInfo.setLayout(new BoxLayout(studentInfo, BoxLayout.Y_AXIS));
        studentInfo.setBorder(BorderCreator.createBoldTitledBorder("Student Details - " +
                (student.isFullTime() ? "Full Time" : "Part Time")));
        studentInfo.add(new JLabel("\tNumber: " + student.getNumber()));
        studentInfo.add(new JLabel("\tName: " + student.getName()));
        studentInfo.add(new JLabel("\tAddress: " + student.getAddress()));
        studentInfo.add(new JLabel("\tCity: " + student.getCity()));
        studentInfo.add(new JLabel("\tState: " + student.getState()));
        studentInfo.add(new JLabel("\tZipcode: " + student.getZipCode()));
        studentInfo.add(new JLabel("\tResident Status: " + student.getResidentStatus()));
        studentInfo.add(new JLabel("\tEmail Address: " + student.getEmailAddress()));
        studentInfo.add(new JLabel("\tTotal Semester Credits: " + student.getTotalSemesterCredits()));
        JPanel feeInfo = new JPanel();
        feeInfo.setLayout(new BoxLayout(feeInfo, BoxLayout.Y_AXIS));
        feeInfo.setBorder(BorderCreator.createBoldTitledBorder("Fee Details"));
        feeInfo.add(new JLabel("\tTuition Rate: " + student.tuitionRate()));
        feeInfo.add(new JLabel("\tCapital Fee: " + student.capitalFee()));
        feeInfo.add(new JLabel("\tCollege Fee: " + student.collegeFee()));
        feeInfo.add(new JLabel("\tMalpractice Insurance Fee: " + student.malpracticeInsuranceFee()));
        feeInfo.add(new JLabel("\tAccident Insurance Fee: " + student.accidentInsuranceFee()));
        feeInfo.add(new JLabel("\tStudent Services Fee: " + student.studentServicesFee()));

        JPanel overallInfo = new JPanel();
        overallInfo.setLayout(new BoxLayout(overallInfo, BoxLayout.Y_AXIS));
        overallInfo.setBorder(BorderCreator.createBoldTitledBorder("Overall Information"));
        overallInfo.add(new JLabel("\tTotal Student Fees: " + student.totalCollegeFees()));
        overallInfo.add(new JLabel("\tTotal College Cost: " + student.totalCollegeCost()));
        add(studentInfo);
        add(feeInfo);
        add(overallInfo);
    }
}
