package model;




import java.text.DecimalFormat;

public class GDPIndicator extends Indicator {

    /**
     * Instance variable of type double, which is updated when the IndicatorType
     * is GDP_PER_CAPITA
     */
    private double gdpPerCapita;


    /**
     * A constructor which initializes the year using a call to the super constructor. It also
     * initializes the instance variable gdpPerCapita to the inherited INVALID_DATA
     * @param year: receives one argument of type int
     */
    public GDPIndicator(int year) {
        super(year);
        this.gdpPerCapita = super.INVALID_DATA;
    }

    /**
     * A constructor which initializes the instance variables year, gdpPerCapita. For now, use this method
     * for testing purposes. Receives two-arguments
     * @param year: an int for the year instance
     * @param gdpPerCapita: a double for the gap per capita
     */
    GDPIndicator(int year, double gdpPerCapita) {
        super(year);
        this.gdpPerCapita = gdpPerCapita;
    }



    /**
     * The mutator method setData() which initializes the gdpPerCapita instance variable. Receives  one
     * argument. Variable instance gdpPerCapita is set to index zero of the single-dimensional array of
     * type double that is passed to the method.
     * @param gdpPerCapita: a single-dimensional array of type double
     */
    @Override
    public void setData(double[] gdpPerCapita) {
        this.gdpPerCapita = gdpPerCapita[0];
    }

    /**
     * Getter method that returns a single dimensional array of type double that possesses the data held
     * for the Indicator type
     * @return
     */
    @Override
    public double[] getData() {
        return new double[]{gdpPerCapita};
    }

    /**
     * Method below to return a String representation of the object. The output can be
     *    formatted similar to the previous project
     * @return
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        if (this.gdpPerCapita == -1) return "()";
        return String.format("(%s)", df.format(this.gdpPerCapita));
    }
}