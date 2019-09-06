package model;



public class Country {

    /**
     * Stores the name of the current instance
     * Type String
     */
    private String name;


    /**
     * instance variable of type int which contains the minimum year for the Country object
     */
    private int minYear;

    /**
     * instance variable of type int which contains the maximum year for the Country object
     */
    private int maxYear;



    /**
     * An instance variable linked list of Indicator objects called indicators
     *
     */
    private LinkedList<Indicator> indicators = new LinkedList<>();


    /**
     * A constructor that initializes the instance variable name which we will use object for comparison.
     * @param name: type String
     */
    public Country(String name) {
        this.name = name;
        this.maxYear = 2017;
    }

    /**
     * Accessor Method which does not take any arguments and returns the name of the current instance
     *
     * @return: type String
     */
    public String getName() {
        return this.name;
    }

    /**
     * The method getStartYear() which does not receive any arguments and returns an int for the first year that we
     * have indicator data for. This method assumes that the data is stored in chronological order.
     *
     * @return
     */
    public int getStartYear() {
        return this.minYear;
    }

    /**
     * The method getEndYear() which does not receive any arguments and returns an int for the last year that we have
     * indicator data for. This method assumes that the data is stored in chronological order.
     *
     * @return
     */
    public int getEndYear() {
        return this.maxYear;
        }



    /**
     * The accessor method getIndicatorForYear() which returns the data if the requested year is valid. Otherwise, it
     * will throw an exception. Receives one argument which is an int for the requested year. Returns an object of
     * type Indicator if the requested year exists in the indicators instance variable. Throws an IllegalArgumentException
     * when the year does not exist.
     *
     * @param requestedYear: type int
     * @return an object of type Indicator
     * @throws IllegalArgumentException
     */
    public Indicator getIndicatorForYear(int requestedYear) throws IllegalArgumentException {
//        if (this.indicators.getIndex(0) == null) {
//            return null;
//        }
//        if (requestedYear < this.getStartYear() || requestedYear > this.getEndYear()) throw new IllegalArgumentException();
        for (int i = 0; i < this.indicators.size(); i++){
            if (this.indicators.getIndex(i) != null && this.indicators.getIndex(i).getYear() == requestedYear) {
                return this.indicators.getIndex(i);
            }
        }
        return null;
    }

    /**
     * The mutator method setIndicatorForYear() which will update the array indicators with new data. This method will
     * add the data for the requested year. Receives two arguments: an int for the requested year to update and an
     * object of type Indicator. Set this data to the year in the attribute "indicators". This method does not return
     * anything. If the year does not exist in "indicators", an IllegalArgumentException is thrown.
     * <p>
     * - Assume the indicators array is stored in chronological order
     *
     * @param requestedYearToUpdate
     * @param data
     * @throws IllegalArgumentException
     *
     * @TODO: implement method .insertAtIndex() within LinkedList<T>()
     */
    void setIndicatorForYear(int requestedYearToUpdate, Indicator data) throws IllegalArgumentException {
        if (this.indicators.getIndex(0) == null) {
            this.indicators.insertAtIndex(data, 0);
            return;
        }
        if (requestedYearToUpdate < this.getStartYear() || requestedYearToUpdate > this.getEndYear()) {
            throw new IllegalArgumentException(String.format("%d is not within the year range of %d - %d for the data available", requestedYearToUpdate, this.getStartYear(), this.getEndYear()));
        }
        for (int i = 0; i < this.indicators.size(); i++) {
            if (this.getEndYearHelper()[i] == requestedYearToUpdate) {
                this.indicators.add(data);
                return;
            }
        }

    }


    /**
     * getIndicatorForPeriod() determines if the requested year range is valid. If the request is invalid:
     *          - It will display a message specifying which request is invalid.
     *          - Then adjust the start and end year to the overlap between the requested and valid start and end
     *              years.
     *          - Then return a one-dimensional array of Indicator for the enrollment data of the given country name
     *              between the requested start year and end year inclusive.
     * Takes two arguments:
     *          - An int for the requested starting year.
     *          - An int for the requested ending year (inclusive)
     * Returns:
     *          - One-dimensional array of type Indicator
     * Throws:
     *          - An IllegalArgumentException in case a valid period does not exist between the requested start and
     *              end years.
     * @param strYearQuery
     * @param endYearQuery
     * @return
     * @throws IllegalArgumentException
     *
     *
     */
    public Indicator[] getIndicatorForPeriod(int strYearQuery, int endYearQuery) throws IllegalArgumentException {
        int yearLabLen = this.indicators.size();

        if (endYearQuery > this.getEndYear() && strYearQuery < this.getStartYear()) {
            System.out.println(String.format("Invalid request of start and end year %d, %d. Using valid sub-period for %s is %d to %d.", strYearQuery, endYearQuery, this.name, this.getStartYear(), this.getEndYear()));
            endYearQuery = this.getEndYear();
            strYearQuery = this.getStartYear();
        }

        if (endYearQuery < this.getStartYear() && strYearQuery < this.getStartYear()) {
            throw new IllegalArgumentException(String.format("\t\tInvalid request of start and end year %d, %d. Valid period for %s is %d to %d", strYearQuery, endYearQuery, this.name, this.getStartYear(), this.getEndYear()));
        }
        if (endYearQuery > this.getEndYear() && strYearQuery > this.getEndYear()) {
            throw new IllegalArgumentException(String.format("\t\tInvalid request of start and end year %d, %d. Valid period for %s is %d to %d", strYearQuery, endYearQuery, this.name, this.getStartYear(), this.getEndYear()));
        }

        if ((strYearQuery >= this.getStartYear() && strYearQuery <= this.getEndYear()) && (endYearQuery > this.getEndYear())) {
            System.out.println(String.format("Invalid request of start and end year %d, %d. Using valid sub-period for %s is %d to %d.", strYearQuery, endYearQuery, this.name, strYearQuery, this.getEndYear()));
            endYearQuery = this.getEndYear();
        }
        if ((endYearQuery >= this.getStartYear() && endYearQuery <= this.getEndYear()) && (strYearQuery < this.getStartYear())) {
            System.out.println(String.format("Invalid request of start and end year %d, %d. Using valid sub-period for %s is %d to %d.", strYearQuery, endYearQuery, this.name, this.getStartYear(), endYearQuery));
            strYearQuery = this.getStartYear();
        }

        Indicator[] dataToReturn = new Indicator[endYearQuery - strYearQuery + 1];
        int strColToReturn = 0;
        int endColToReturn = 0;
        for (int j = 0; j < yearLabLen; j++) {
            if (this.getEndYearHelper()[j] == strYearQuery) {
                strColToReturn = j;
            }
            if (this.getEndYearHelper()[j] == endYearQuery) {
                endColToReturn = j;
            }
        }
        int counter = 0;
        for (int k = 0; k < this.indicators.size(); k++) {
            if (k >= strColToReturn && k <= endColToReturn) {
                dataToReturn[counter++] = this.indicators.getIndex(k);
            }
        }
        return dataToReturn;
    }



    /**
     * The method equals() returns true if the argument matches the name of this instance. Receives one argument:
     * a String for the name of the country we are searching for
     *
     * @param: type String
     * @return: true if the name of this instance matches the argument and false otherwise
     *
     */

    public boolean equals(Object obj) {
        if(obj instanceof String) {
            return this.getName().equalsIgnoreCase((String) obj);
        }
        if (obj instanceof Country) {
            return this.getName().equalsIgnoreCase(((Country) obj).getName());
        }
        return false;
    }

    /**
     * This accessor method returns the current objects "indicators" instance variable which is a single-dimensional
     * array of type Indicator
     * @return: single-dimensional of type Indicator
     */
    public LinkedList<Indicator> getIndicators() {
        return this.indicators;
    }

    /**
     * The method ".toString()" which returns String object representing this instance. The return value
     * includes the name of this Country instance and the data stored in the one dimensional indicators array.
     * In order to improve readability, when a Country object is displayed, year labels are not included.
     * @return: type String
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("%30s", this.name));

        for (Indicator ind : this.indicators) {
            str.append(String.format("%20s", ind != null ? ind.toString() : "()"));
        }
        return str.toString();
    }

    /**
     * This method is used to help the CountryList.toString() method in order to properly generate year labels. It is
     * implemented in Country Class so now matter which Country Object the head of Countrylist is referencing,
     * CountryList may call this method on CountryList instance variable "head". This method returns a formatted
     * String object.
     * @return: type String
     */
    public String generateYears() {
        StringBuilder yearLabels = new StringBuilder();
        int startYear = this.getStartYear();

        for (int i = 0; i <= this.maxYear - this.minYear; i++) {
                yearLabels.append(String.format("%20s", Integer.toString(startYear + i)));
        }
        return yearLabels.toString();
    }

    /**
     * This private access method returns a single dimensional array of type int. Each element respresents the
     * proper year labels of this Country and is used through the Class to avoid nullpointers when calling
     * the ".getYear()" method on this.indicators indices.
     * @return: single-dimensional array of type int
     */
    private int[] getEndYearHelper() {
        int[] yearList = new int [this.indicators.size()];
        int startYear = this.getStartYear();
        for (int i = 0; i < this.indicators.size(); i++) {
            yearList[i] = startYear + i;
        }
        return yearList;
    }

    /**
     * The method addIndicator() which adds an Indicator object to the end of "indicators" list.
     * Receives one argument: An indicator object for the new data to add.
     * Does not return anything.
     * @param dataForOneYear of type Indicator
     */
    public void addIndicator(Indicator dataForOneYear) {
        this.indicators.add(dataForOneYear);
        this.minYear = this.indicators.size() > 15 ? 1960 : 2006;


    }
}
