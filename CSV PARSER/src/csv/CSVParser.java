package csv;

import model.IndicatorType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class CSVParser {



    /**
     * A one-dimensional array of type String, which holds the names of all countries read from CSV file
     */
    private String[] countryNames;

    /**
     * A one-dimensional array of type int, which holds year number read from CSV file
     */
    private int[] yearLabels;

    /**
     * A two-dimensional array of type double, where the row represents a country, and the column represents the
     * data for a specific year
     */
    private double[][] dataTable;

    /**
     * This String contains the line where a possible validity check failed and will be sent back to the user.
     */
    String invalidLine;

    /**
     * This String returns the line that the CSVParser expected at the specific line the
     * InvalidFileFormatException is thrown
     */
    String expected;

    /**
     * Instance variable that holds the type of "Indicator" the file in reference possesses.
     */
    IndicatorType indicatorType;


    /**
     * @param filePath: String - Will be the given path to our CSV file we are meant to parse
     * @throws FileNotFoundException
     * @throws InvalidFileFormatException
     */
    public CSVParser(String filePath) throws FileNotFoundException, InvalidFileFormatException {
        scanIndicator(filePath);
        if (validityChecker(filePath)) {
            countryCounter(filePath);
            occupyCountryNames(filePath);
            extractAllYearLabels(filePath);
            populateDataTable(filePath);
        } else {
            System.err.println(String.format("Invalid Line at: \n%s\n", this.invalidLine));
            throw new InvalidFileFormatException(this.expected, filePath);
        }
    }

    /**
     * getter: public access to retrieve all the names of the countries according to the CSV file in reference
     * @return: One-Dimensional String Array
     */
    public String[] getCountryNames() {
        return countryNames;
    }

    /**
     * getter: public access to retrieve all the years for recorded and non-recorded/non-surveyed enrollment data
     *          according to the CSV file in reference
     * @return: One-dimensional int Array
     */
    public int[] getYearLabels() {
        return yearLabels;
    }

    /**
     * getter: public access to retrieve all the data excluded CSV format header (first five lines) country names
     *          and year labels and any other meta data for that matter.
     * @return: two-dimensional double Array
     */
    public double[][] getParsedTable() {
        return dataTable;
    }

    /**
     * getter: public access to retrieve the numbers of years surveyed in reference to the CSV file passed to the
     *          CSVParser constructor
     * @return: int
     */
    public int getNumberOfYears() {
        return yearLabels.length;
    }

    /**
     * This method will receive no arguments and returns the IndicatorType set when
     * you parsed the input file in the constructor.
     *
     */
    public IndicatorType getIndicatorType() {
        return this.indicatorType;
    }

    /**
     * This function is a private function to check whether or not the CSV file passed to the CSVParser
     *          constructor is valid according to the validity format stated; specifically in reference to the first
     *          five lines of the CSV files. If the CSV file is not in proper format, validityChecker(String filePath)
     *          will return a boolean of false within the CSVParser constructor before any of the other constructor
     *          logic is reached and it'll throw a new InvalidFileFormatException() sending a message to the user
     *          of the invalid file format.
     * @param filePath: file path to the CSV file to be parsed and validated
     * @return: boolean
     * @throws FileNotFoundException
     */
    private boolean validityChecker(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        int lineCounter = 0;
        int truthCounter = 0;
        while (scanner.hasNextLine()) {
            String currentLineFormat = scanner.nextLine();
            if (validityHelper(lineCounter, currentLineFormat)) truthCounter++;
            lineCounter++;
            if (lineCounter > 5) break;
        }
        if (truthCounter == 5) {
            return true;
        }
        return false;
    }



    /**
     * This is a private access function to specifically help the validityChecker() in an attempt to modularize
     *  code for possible collaboration and overall more efficient logic flow
     * @param lineCounter
     * @param currentLineFormat
     * @return: boolean
     */
    private boolean validityHelper(int lineCounter, String currentLineFormat) {
        boolean lineOneFormat, lineTwoFormat, lineThreeFormat, lineFourFormat, lineFiveFormat;
        lineOneFormat = false;
        lineTwoFormat = false;
        lineThreeFormat = false;
        lineFourFormat = false;
        lineFiveFormat = false;
        switch (lineCounter) {
            case 0:
                if (currentLineFormat.contains("Data Source,World Development Indicators")) {
                    lineOneFormat = true;
                }
                else {
                    this.invalidLine = currentLineFormat;
                    this.expected = "\"Data Source,World Development Indicators\"";
                }
                break;
            case 1:
                if (currentLineFormat.contains("Indicator,")) {
                    lineTwoFormat = true;
                }
                else {
                    this.invalidLine = currentLineFormat;
                    this.expected = "\"Indicator,\"";
                }
                break;
            case 2:
                if (currentLineFormat.contains("Last Updated Date")) {
                    lineThreeFormat = true;
                }
                else {
                    this.invalidLine = currentLineFormat;
                    this.expected = "\"Last Updated Date\"";
                }
                break;
            case 3:
                if (currentLineFormat.contains("Number of Countries")) {
                    lineFourFormat = true;
                }
                else {
                    this.invalidLine = currentLineFormat;
                    this.expected = "\"Number of Countries\"";
                }
                break;
            case 4:
                if (currentLineFormat.contains("Country Name")) {
                    lineFiveFormat = true;
                }
                else {
                    this.invalidLine = currentLineFormat;
                    this.expected = "\"Country Name\"";
                }
                break;
        }
        if (lineOneFormat || lineTwoFormat || lineThreeFormat || lineFourFormat || lineFiveFormat) return true;
        return false;
    }

    /**
     * This method is private and counts the how many countries there are referenced in the csv file, which
     * ultimately determines the size of our "countryNames" array. The "-5" is based on the logic of counting
     * all the lines in the entire csv file and then subtracting the lines in the before any contries are
     * referenced which in a properly formatted file is: 5.
     *
     * @param fileName: The CSV file that is being parsed.
     */
    private void countryCounter(String fileName) throws FileNotFoundException {
        Scanner lineInput = new Scanner(new File(fileName));
        int cnt = 0;
        while (lineInput.hasNextLine()) {
            cnt++;
            lineInput.nextLine();
        }
        this.countryNames = new String[cnt - 5];
    }

    /**
     * This method will be a helper method and placed within "countryNameCounter", so once the numbers of countries
     * within the CSV file is determined we can then populate the array with the actual names (Strings) using this
     * method.
     *
     * @param: The Scanner object that took in the CSV file in countryNameCounter
     */
    private void occupyCountryNames(String filePath) throws FileNotFoundException {
        String tempStr;
        Scanner oneLine = new Scanner(new File(filePath));
        int lineCounter = 0; int arrayCounter = 0;
        String[] strArr = new String[this.countryNames.length];
        while (oneLine.hasNextLine()) {
            tempStr = oneLine.nextLine();
            if (lineCounter > 4) {
                StringBuilder strBuild = new StringBuilder(tempStr);
                if (Character.toString(strBuild.charAt(0)).equalsIgnoreCase("\"")) {
//                    System.out.println(strBuild.toString());
                    strArr = strBuild.toString().replace("\"", "").split(",");
                    this.countryNames[arrayCounter++] = strArr[0] + strArr[1];
                } else {
                    strArr = strBuild.toString().split(",");
                    this.countryNames[arrayCounter++] = strArr[0];
                }
            }
            lineCounter++;
        }
    }

    /**
     * This private access function is to support the logic within this.objects constructor. This specific
     *          function extracts all the years excluding any descriptive text in the same line, parses the year Strings
     *          into int and populates an int[] array in sequential order.
     * @param filePath
     * @throws FileNotFoundException
     */
    private void extractAllYearLabels(String filePath) throws FileNotFoundException {
        String tempStr;
        Scanner oneLine = new Scanner(new File(filePath));
        int lineCounter = 0;
        while (oneLine.hasNextLine()) {
            tempStr = oneLine.nextLine();
            if (lineCounter == 4) {
                StringBuilder strBuild = new StringBuilder(tempStr);
                String[] yearArray = strBuild.toString().split(",");
                this.yearLabels = new int[yearArray.length - 1];
                int index = 1;
                for (int i = 0; i < this.yearLabels.length; i++) {
                    this.yearLabels[i] = Integer.parseInt(yearArray[index++]);
                }
                break;
            }
            lineCounter++;
        }
    }


    /**
     * This private access function is to support the logic within this.objects constructor. This specific
     *          function extracts all the data from the CSV file in reference excluding any descriptive or metadata
     *          within the same file including but not limited to: listed years, country names and file header.
     * @param filePath: path to the CSV File to be parsed
     * @throws FileNotFoundException
     */
    private void populateDataTable(String filePath) throws FileNotFoundException {
        String tempStr;
        Scanner oneLine = new Scanner(new File(filePath));
        int lineCounter = 0;
        this.dataTable = new double[countryNames.length][yearLabels.length];
        int index = 0;
        while (oneLine.hasNextLine()) {
            tempStr = oneLine.nextLine();
            if (lineCounter > 4) {
                tempStr = getRidOfNamesWithQuotes(tempStr);
                this.dataTable[index++] = getRidOfCountryNames(tempStr);
            }
            lineCounter++;
        }
    }

    /**
     * This private access function is a helper function that was specifically created to help populateDataTable() to
     * extract the data requested by solely getting rid of country names that are surrounded by quotations
     * @param data
     * @return
     */
    private String getRidOfNamesWithQuotes(String data) {
        StringBuilder strB = new StringBuilder(data);
        int counter = 0;
        int start = 0;
        int end = 0;
        for (int i = 0; i < data.length(); i++) {
            if (Character.toString(data.charAt(i)).equals("\"") && counter == 0) {
                start = i;
                counter++;
                continue;
            }
            if (Character.toString(data.charAt(i)).equals("\"") && counter > 0) {
                end = i + 1;
                data = strB.delete(start, end + 1).toString();
                break;
            }
        }
        return data;
    }

    /**
     * This private access function is a helper function specifically created to help populateDataTable() to remove
     * country names after country names that were surrounded with quotations were already removed. This would be the
     * final stage of parsing/removing country names within the populateDataTable() function
     * @param myData
     * @return
     */
    private double[] getRidOfCountryNames(String myData) {
        String[] myDataArr = myData.split(",");
        int counter = 0;
        if (myDataArr.length == 0) {
            double[] myDoubleData = new double[this.yearLabels.length];
            return myDoubleData;
        }
        if(!myDataArr[0].isEmpty()) {
            if (checkForCountryName(myDataArr[0])) {
                double[] myDoubleData = new double[myDataArr.length - 1];
                for (int i = 1; i < myDataArr.length; i++) {
                    if (myDataArr[i].isEmpty()) {
                        myDoubleData[counter++] = -1;
                    } else {
                        myDoubleData[counter++] = Double.parseDouble(myDataArr[i]);
                    }
                }
                return myDoubleData;
            }
        }

        double[] myDoubleData = new double[myDataArr.length];
        for (
                int i = 0;
                i < myDoubleData.length; i++) {
            if (myDataArr[i].isEmpty()) {
                myDoubleData[i] = -1;
            } else {
                myDoubleData[i] = Double.parseDouble(myDataArr[i]);
            }
        }
        return myDoubleData;
    }


    /**
     * This helper function is specifically created to help populateDataTable() function. After removing country names
     * that were surrounded with quotations, some lines of data began with a country name and some did not. This function
     * determines if a line of data does in fact still have a country name and if it does, that line of data is then parsed
     * and removes that country name. This function returns a boolean.
     * @param str
     * @return
     */
    private boolean checkForCountryName(String str) {
        str = str.toLowerCase();
        return str.charAt(0) >= 97 && str.charAt(0) <= 122;
    }


    /**
     * This private access function scans the CSV File in reference for line two
     * where a properly formatted file has the "Indicator" line
     * @param filePath: String
     * @throws FileNotFoundException
     */
    private void scanIndicator(String filePath) throws FileNotFoundException {
        String tempStr;
        int counter = 0;
        Scanner scan = new Scanner(new File(filePath));

        while (scan.hasNextLine()) {
            tempStr = scan.nextLine();
            counter++;
            if (counter == 2) {
                this.indicatorType = checkIndicator(tempStr);
            }
        }
    }


    /**
     * This private access helper function receives the string at which the proper
     * line scanIndicator is looking for (the indicator line) and this function
     * determines what type of indicator the file possesses and then returns
     * the IndicatorType enum accordingly
     * @param lineToCheck: String
     * @return: IndicatorType enum
     */
    private IndicatorType checkIndicator(String lineToCheck) {
        if (lineToCheck.contains("Indicator,School Enrollment In Primary")) {
            return IndicatorType.SCHOOL_ENROLLMENT_PRIMARY;
        }
        if (lineToCheck.contains("Indicator,GDP per capita")) {
            return IndicatorType.GDP_PER_CAPITA;
        }
        if (lineToCheck.contains("Indicator,School Enrollment In Secondary")) {
            return IndicatorType.SCHOOL_ENROLLMENT_SECONDARY;
        }
        return null;
    }


}
