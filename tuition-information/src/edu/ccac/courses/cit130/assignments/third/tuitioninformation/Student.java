package edu.ccac.courses.cit130.assignments.third.tuitioninformation;

/**
 * Abstract class for handling student data.
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jul 09, 2021
 */
public abstract class Student {
    private final int number;
    private final String name;
    private final String address;
    private final String city;
    private final String state;
    private final int zipCode;
    private final ResidentStatus residentStatus;
    private final String emailAddress;
    private final int totalSemesterCredits;

    public Student(int number, String name, String address, String city, String state, int zipcode,
                   ResidentStatus residentStatus, String emailAddress, int totalSemesterCredits) {
        this.number = number;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipcode;
        this.residentStatus = residentStatus;
        this.emailAddress = emailAddress;
        this.totalSemesterCredits = totalSemesterCredits;
    }

    public abstract double tuitionRate();

    public abstract double capitalFee();

    public abstract double collegeFee();

    public abstract double malpracticeInsuranceFee();

    public abstract double accidentInsuranceFee();

    public abstract double studentServicesFee();

    public double totalCollegeFees() {
        return capitalFee() + collegeFee()
                + malpracticeInsuranceFee() + accidentInsuranceFee()
                + studentServicesFee();
    }

    public double totalCollegeCost() {
        return tuitionRate() + totalCollegeFees();
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public ResidentStatus getResidentStatus() {
        return residentStatus;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public int getTotalSemesterCredits() {
        return totalSemesterCredits;
    }

    public boolean isFullTime() {
        return getTotalSemesterCredits() >= 12;
    }
}
