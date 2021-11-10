package edu.ccac.courses.cit130.assignments.third.tuitioninformation;

/**
 * Represents a full time student.
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jul 27, 2021
 */
public class FullTimeStudent extends Student {

    public FullTimeStudent(int number, String name, String address, String city, String state, int zipcode, ResidentStatus residentStatus, String emailAddress, int totalSemesterCredits) {
        super(number, name, address, city, state, zipcode, residentStatus, emailAddress, totalSemesterCredits);
    }

    @Override
    public double tuitionRate() {
        int exceedingSemesterCredits = 18 - getTotalSemesterCredits();
        boolean exceedingCredits = exceedingSemesterCredits > 0;
        double exceedingCreditCost = (exceedingCredits ? exceedingSemesterCredits * 110.00 : 0.00);
        double rate;
        switch (getResidentStatus()) {
            case C:
              rate = 1650.00;
              break;
            case OC:
                rate = 3300.00;
                break;
            case OS:
                rate = 4950.00;
                break;
            default:
                rate = 0.00;
                break;
        }
        return rate + exceedingCreditCost;
    }

    @Override
    public double capitalFee() {
        switch (getResidentStatus()) {
            case OC:
                return 78.00;
            case OS:
                return 85.00;
            case C:
            default:
                return 0.00;
        }
    }

    @Override
    public double collegeFee() {
        return 72.00;
    }

    @Override
    public double malpracticeInsuranceFee() {
        return 7.70;
    }

    @Override
    public double accidentInsuranceFee() {
        return 5.40;
    }

    @Override
    public double studentServicesFee() {
        return 4.25;
    }
}
