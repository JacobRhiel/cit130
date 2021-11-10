package edu.ccac.courses.cit130.assignments.third.tuitioninformation;

/**
 * Represents a part time student.
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jul 27, 2021
 */
public class PartTimeStudent extends Student {

    public PartTimeStudent(int number, String name, String address, String city, String state, int zipcode, ResidentStatus residentStatus, String emailAddress, int totalSemesterCredits) {
        super(number, name, address, city, state, zipcode, residentStatus, emailAddress, totalSemesterCredits);
    }

    @Override
    public double tuitionRate() {
        int credits = getTotalSemesterCredits();
        double rate;
        switch (getResidentStatus()) {
            case C:
                rate = 110.00 * credits;
                break;
            case OC:
                rate = 220.00 * credits;
                break;
            case OS:
                rate = 330.00 * credits;;
                break;
            default:
                rate = 0.00;
                break;
        }
        return rate;
    }

    @Override
    public double capitalFee() {
        int credits = getTotalSemesterCredits();
        switch (getResidentStatus()) {
            case OC:
                return 6.50 * credits;
            case OS:
                return 7.50 * credits;
            case C:
            default:
                return 0.00;
        }
    }

    @Override
    public double collegeFee() {
        double totalFee = getTotalSemesterCredits() * 6.00;
        return Math.min(totalFee, 72.00);
    }

    @Override
    public double malpracticeInsuranceFee() {
        return 7.00;
    }

    @Override
    public double accidentInsuranceFee() {
        return 0.00;
    }

    @Override
    public double studentServicesFee() {
        return 4.00 * getTotalSemesterCredits();
    }
}
