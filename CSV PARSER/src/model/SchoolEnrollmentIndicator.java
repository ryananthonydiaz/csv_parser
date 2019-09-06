package model;

import model.Indicator;

import java.text.DecimalFormat;

/**
 * A class that extends the abstract class "Indicator" and represents the two categories of net percentage of
 * children enrolled in primary school and net percentage of children in secondary school.
 */
public class SchoolEnrollmentIndicator extends Indicator {

    /**
     * Instance variable of type double, which is updated when the IndicatorType
     * is "SCHOOL_ENROLLMENT_PRIMARY
     */
    private double netPrimary;


    /**
     * Instance variable of type double, which is updated when the IndicatorType
     * is "SCHOOL_ENROLLMENT_SECONDARY
     */
    private double netSecondary;




    /**
     * A constructor which initializes the year and sets the instance variables
     * "netPrimary to inherited "INVALID_DATA". Receives one argument, an
     * int for the year of this instance
     * @param year: an int for the year of this instance
     */
    public SchoolEnrollmentIndicator(int year) {
        super(year);
        this.netPrimary = super.INVALID_DATA;
        this.netSecondary = super.INVALID_DATA;
    }

    /**
     * A constructor which initializes the instance variables "year" and "netPrimary".
     * Receives three arguments: an int for the year of this instance, a double
     * for the primary enrollment and a double for the secondary enrollment.
     * @param year: type int
     * @param netPrimary: type double
     * @param netSecondary: type double
     */
    SchoolEnrollmentIndicator(int year, double netPrimary, double netSecondary) {
        super(year);
        this.netPrimary = netPrimary;
        this.netSecondary = netSecondary;
    }

    /**
     * A mutator method setData() which initializes the "netPrimary" and the "netSecondary" and receives
     * one argument: a single dimensional array of type double. "netPrimary" is set to the firts index
     * of the single dimensional array and netSecondary is set to the second index of the single
     * dimensional array.
     * @param: single dimensional array of type double
     */
    @Override
    void setData(double[] net) {
        this.netPrimary = net[0];
        this.netSecondary = net[1];
    }

    /**
     * Accessor method getData() which does not receive an argument and returns
     * array of double primitives. The first index will be set to the instance
     * variable netPrimary and the second index will be set to the instance
     * variable netSecondary
     *
     *
     */
    @Override
    public double[] getData() {
        return new double[] {this.netPrimary, this.netSecondary};
    }


    /**
     * Returns a String representation of the object with both primary and secondary data.
     * @return type String
     *
     * @TODO: This logic may have to be changed! Re-Visit*************************************
     */
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        if (this.netPrimary == -1 && this.netSecondary == -1) return "()";
        if (this.netPrimary == -1 && this.netSecondary != -1) return String.format("( ,"+"%s)", df.format(this.netSecondary));
        if (this.netSecondary == -1 && this.netPrimary != -1) return String.format("(%s"+ ", )", df.format(this.netPrimary));
        return String.format("(%s"+", %s)", df.format(this.netPrimary), df.format(this.netSecondary));
    }

    /**
     * A mutator method which updates the primary enrollment. Receives one argument:
     * a double value which we use to update netPrimary. Does not return anything.
     * @param netPrimary: type double
     */
    public void setPrimaryEnrollment(double netPrimary){
        this.netPrimary = netPrimary;
    }

    /**
     * A mutator method which updates the secondary enrollment. This mutator method receives
     * one argument of type double and does not return anything.
     * @param netSecondary: type double
     */
    public void setSecondaryEnrollment(double netSecondary) {
        this.netSecondary = netSecondary;
    }

    /**
     * Accessor method getPrimaryEnrollment() which does not receive arguments and
     * return the instance variables netPrimary.
     */
    double getPrimaryEnrollment() {
        return netPrimary;
    }

    /**
     * Accessor method getSecondaryEnrollment() which does not receive arguments and returns
     * the instance variables netSecondary
     */
    double getSecondaryEnrollment() {
        return netSecondary;
    }




}
