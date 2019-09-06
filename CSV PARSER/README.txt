project folder:
ryananthonydiaz-cs1b-project05/


Brief description of submitted files:

src/model/Country.java
    - This class represents data for each country within a given time period indicative of the file that's parsed and
    the time period the user requests.
    Attributes:
        - Instance variable called name of type String, which stores the name of the current instance.
        - Instance variable called indicators, a Linked List of type Indicator, which holds all the data for
        this instance.
    Methods:
        - A constructor that initializes the instance variables.
            Receives two arguments:
                a String for the name of the current instance.
        - The method equals() which returns true if the argument matches the name of this instance.
            Receives one argument:
                a String for the name of the country we are searching for.
            Returns true if the name of this instance matches the argument.
        - The accessor method getName() which does not take any arguments and returns the name of the current instance.
        - The method getStartYear() which does not receive any arguments and returns an int for the first year that we have indicator data for.
        - The method getEndYear() which does not receive any arguments and returns an int for the last year that we have indicator data for.
        - The accessor method getIndicatorForYear() which return the data if the requested year is valid. Otherwise it will throw an exception.
            Receives one argument:
                an int for the requested year.
            Returns an object of type Indicator if the requested year exists in the indicators instance variable.
            Throws an IllegalArgumentException when the year does not exist.
        -  The mutator method setIndicatorForYear() which will update the array indicators with new data. This method will add the data for the requested year.
               Receives two arguments:
                   a int for the requested year to update.
                   an object of type Indicator. Set this data to the year in the attribute indicators.
           Does not return anything.
           Throws an IllegalArgumentException if the year does not exist.
        - The method getIndicatorForPeriod() which determines if the requested year range is valid. If not, first adjust the start and end year to overlap between the requested and valid start and end years. Then return a one dimensional array of type Indicator for the data between the requested start year and end year (inclusive).
            Receives two arguments:
                An int for the requested starting year (inclusive).
                An int for the requested ending year (inclusive).
                Returns the data for the requested years between start and end year, inclusive. Throw an IllegalArgumentException(String message) if:
                    - both requested start year and end year are out of range, or
                    - the requested start and end year are in inverted order.
        - The method toString() which returns String object representing this instance. The return value should include the name of this Country instance and the data stored in the one dimensional indicators array.




src/csv/CSVParser.java
    - This class will read the *.csv file one line at a time and set various attributes
    - UPDATES: instance variable "indicatorType" to retain the indicator type of the file in reference and private
    accessor method checkIndicator() which accurately determines which indicator the CSV file in reference possesses

src/csv/InvalidFileFormatException.java
    - Extends java.io.IOException. An object of this type is thrown when a class wants
    to indicate that the format of the file is not valid.
    - Designed to indicate whether or not a CSV File that is passed within CSVParser is properly
    formatted or not.

src/model/DataModel.java
    - This class provides access to the parsed CSV data in reference to our LineChart and adding series data for our
    GraphView Class.

src/model/GDPIndicator.java
    - GDP per capita is gross domestic product divided by midyear population. GDP is the sum of gross value added
    by all resident producers in the economy plus any product taxes and minus any subsidies not included in the
    value of the products. It is calculated without making deductions for depreciation of fabricated assets or for
    depletion and degradation of natural resources. This class (when IndicatorType is GDP_PER_CAPITA) will represent
    the data in relevance to GDP per capita.
    - Attributes:
        - Instance variable gdpPerCapita of type double, which is updated when the IndicatorType is GDP_PER_CAPITA

src/model/SchoolEnrollmentIndicator.java
    - The enrollment data breaks down in two categories of net percentage of children enrolled in primary school
    and net percentage of children in secondary school.
    - Child class of abstract class Indicator
    - Attributes:
        - Instance variable "netPrimary" of type double which is updated when the IndicatorType is
        SCHOOL_ENROLLMENT_PRIMARY
        - Instance variable "netSecondary" of type double, which is updated when the IndicatorType is
        SCHOOL_ENROLLMENT_SECONDARY (This is not set within this project and/or tests)

src/model/Indicator.java
    - Abstract class of type Indicator
    - Attributes:
        - instance variable "year" of type int, which stores the year of the indicator data
        - instance variable called INVALID_DATA which is a final int that is set to "-1". Since the
        indicator type may hold more than one type of data, this final int allows a form of keeping track
        of which data value is valid. This is a default value for any class that extends the abstract class
        Indicator
        - A constructor which initializes the instance variable year. Only receives one argument which is an int
        for the year of the current data.
        - An accessor method getYear() which received no arguments and returns the instance variable year.
        - A mutator abstract method setData() which will be implemented by the child class to initialize one or more
        instance variables. Only receives one argument which is a single dimensional array of type double.
        - An abstract method getData() which will receive no arguments and returns a single dimensional double
        array. This method is a complimentary to the mutator method.
        - An abstract method toString() which receives no arguments and returns a String object representing the
        instance.

src/model/LinkedList.java

       - The LinkedList from the previous assignment is now modified to implement a generic iterable interface and contain
       two nested clases: Class Node Class Node which is to contain the data of interest and class ListIterator
       which implements the generic interface Iterator. The purpose of this class is to traverse the collection of
       objects within the list.

src/model/IndicatorType.java

    - A previously designed and implemented enum that is updated so that depending on the constant, the caller may specify
    a different label.


src/view/ChartGraph.java

    - Instantiates an JavaFX application which creates a model of the data.
     Uses the model to instantiate an object of type  javafx.scene.chart.LineChart
     via the GraphView class. Then sets up the scene with basic modification to
     the stage.

src/view/CountrySelector.java

    - Randomly builds a LinkedList of Country objects to be used in the graph

src/view/GraphView.java

    - The class GraphView which extends the parameterized LineChart from javafx.scene package. GraphView will create a
    data series for each Country object.



resources/childrenEnrolledInPrimary.csv
    - A CSV (Comma Separated Value) file.
    - Indicator Type: SCHOOL_ENROLLMENT_PRIMARY
    - Lines 6 to EOF (end of file) contain values for each year for its corresponding country in relevance to school
    enrollment

resources/childrenEnrolledInPrimary_short_12years.csv
    - A CSV (Comma Separated Value) file.
    - Indicator Type: SCHOOL_ENROLLMENT_PRIMARY
    - Lines 6 to EOF (end of file) contain values for each year for its corresponding country in relevance to school
    enrollment

resources/childrenEnrolledInSecondary.csv
    - A CSV (Comma Separated Value) file.
    - Indicator Type: SCHOOL_ENROLLMENT_SECONDARY
    - Lines 6 to EOF (end of file) contain values for each year for its corresponding country

resources/childrenEnrolledInSecondary_short_12years.csv
    - A CSV (Comma Separated Value) file.
    - Indicator Type: SCHOOL_ENROLLMENT_SECONDARY
    - Lines 6 to EOF (end of file) contain values for each year for its corresponding country

resources/childrenEnrolled_invalid.csv
    - A CSV (Comma Separated Value) File.
    - Lines 4 to EOF (end of file) contain values for each year for its corresponding country
    - Missing the properly formatted line four and five that would read Number of countries and country name. Designed
    to test our "InvalidFileFormatException.java" Class/File

resources/gdpPerCapita.csv
    - A CSV (Comma Separated Value) file.
    - Indicator Type: GDP_PER_CAPITA
    - Lines 6 to EOF (end of file) contain values for each year for its corresponding country in relevance to GDP per
    capita


resources/gdpPerCapita_short_12years.csv
    - A CSV (Comma Separated Value) file.
    - Indicator Type: GDP_PER_CAPITA
    - Lines 6 to EOF (end of file) contain values for each year for its corresponding country in relevance to GDP per
    capita


resources/RUN1.jpg

    - A screen shot of the GUI generated when ChartGraph.java is being ran with the
    "private static final IndicatorType DEFAULT_INDICATOR = IndicatorType.SCHOOL_ENROLLMENT;" being uncommented

resources/RUN2.jpg

    - A screen shot of the GUI generated when ChartGraph.java is being ran with the
    "private static final IndicatorType DEFAULT_INDICATOR = IndicatorType.GDP_PER_CAPITA;" being uncommented

README.txt
    - description of submitted files