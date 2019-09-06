package model;

public abstract class Indicator {

    /**
     * Instance variable which stores the year of the indicator data
     */
    private int year;

    /**
     * Since the "Indicator" type may hold more than one data**, we need a way to keep track of
     * which data value is valid. Use this as a default value for any class that extends the
     * abstract class Indicator.
     *
     */
    public final int INVALID_DATA = -1;


    /**
     * A constructor which initializes the instance variable "year".
     *
     * @param year: an int for the year of the current data
     */
    Indicator(int year) {
        this.year = year;
    }

    /**
     * Accessor method getYear() receives no arguments and returns the instance variable "year"
     * @return: Data Type int
     */
    public int getYear() {
        return this.year;
    }

    /**
     * A mutator abstract method which will be implemented by the child class to initialize one
     * or more instance variables.
     *
     * @param: receives one argument:
     *          - a single dimensional array of double values.
     * @Note: We are restricting the values used in the data to be of type double.
     */
    abstract void setData(double[] doubleArray);

    /**
     * getData() will receive no arguments and returns a single-dimensional double array.
     * This method is complimentary to the mutator method.
     */
    public abstract double[] getData();

    /**
     * Receives no arguments and returns a String object representing the instance.
     */
    public abstract String toString();




}