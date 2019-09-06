package model;

import java.io.FileNotFoundException;

import csv.CSVParser;
import csv.InvalidFileFormatException;
import view.CountrySelector;

/**
 * Provides access to CSV data.
 * @author Foothill College, Ryan Diaz
 */
public class DataModel
{
	/**
	 * data parsed from file
	 */
	private Country[] model;

	/**
	 * name of x-axis
	 */
	private String xAxisName;

	/**
	 * name of y-axis
	 */
	private String yAxisName;

	/**
	 * The indicator type.
	 */
	private IndicatorType indicatorType;

	/**
	 * Creates a model given an input file.
	 * @param filenames
	 */
	public DataModel(final String [] filenames)
	{
		// Holds the data for one or more indicators
		model = null;

		for (int currentFileIndex = 0; currentFileIndex < filenames.length; currentFileIndex++)
		{
			// For debugging purposes
			System.out.println("\nParsing filename " + filenames[currentFileIndex]);

			parseCSVFile(filenames[currentFileIndex]);
		} // end of the for loop parsing the CSV file(s)

		switch(indicatorType)
		{
		case GDP_PER_CAPITA:
		case SCHOOL_ENROLLMENT_PRIMARY:
		case SCHOOL_ENROLLMENT_SECONDARY:
			this.xAxisName = "Year";
			this.yAxisName = indicatorType.getLabel();
			break;
		default:
			// file is not a known type
			return;
		}
	}

	private void parseCSVFile(final String filename)
	{
		CSVParser parser;
		try
		{
			parser = new CSVParser(filename);
		}
		catch (FileNotFoundException e)
		{
			System.out.printf("File name %s not found.", filename);
			return;
		}
		catch (InvalidFileFormatException e)
		{
			System.out.printf("Invalid file format %s\n", e.getMessage());
			return;
		}

		// Accessor methods should only return values of instance variables.
		indicatorType = parser.getIndicatorType();

		// NOTE: You can assume that the format of all CSV input files are the same with the same:
		//       Number of countries
		//       Name of countries
		//       Year labels
		String [] countryNames = parser.getCountryNames();
		int [] yearLabels = parser.getYearLabels();
		double [][] parsedTable = parser.getParsedTable();

		// Check if the array of countries has been initialized by a previous iteration.
		if (model == null)
		{
			// An array of Country objects.
			model = new Country[countryNames.length];
		}

		// Reference to a Country object
		Country foundCountry;
		boolean initializingCountry;

		// Go through each country name parsed from the CSV file.
		for (int countryIndex = 0; countryIndex < model.length; countryIndex++)
		{
			initializingCountry = false;
			foundCountry = findCountry(countryNames[countryIndex], model);

			if (foundCountry == null)
			{
				// Create a Country object
				foundCountry = new Country(countryNames[countryIndex]);
				initializingCountry = true;
			}

			// Get the parsed array of doubles for the current Country object
			double [] dataForAllYears = parsedTable[countryIndex];

			// Go through each year of data read from the CSV file.
			for (int yearIndex = 0; yearIndex < dataForAllYears.length; yearIndex++)
			{
				// TODO: Create the abstract class Indicator to hold the year portion of the data.
				Indicator dataForOneYear = null;
				if (!initializingCountry)
				{
					// TODO: Define the accessor method such that it returns the Indicator
					//       data for a specific year.
					// Note: Assume the CSV file is well formed and the years are consecutive.
					dataForOneYear = foundCountry.getIndicatorForYear(yearLabels[yearIndex]);
				}

				// Recall that each CSV input file we read will provide us one data value.
				// TODO: Create mutator methods that will initialize the appropriate instance variable
				switch(indicatorType)
				{
				case GDP_PER_CAPITA:
					// if true then we have not seen this Country before
					if (dataForOneYear == null)
					{
						// TODO: Create the child class GDPIndicator to hold the data.
						dataForOneYear = new GDPIndicator(yearLabels[yearIndex]);
					}
					// The overriden method of the abstact class requires an one dimensional array
					double data[] = {dataForAllYears[yearIndex]};
					// Uses the overriden method to set the data
					((GDPIndicator)dataForOneYear).setData(data);
					break;
				case SCHOOL_ENROLLMENT_PRIMARY:
					if (dataForOneYear == null)
					{
						// TODO: Create the child class SchoolEnrollmentIndicator to hold the data.
						dataForOneYear = new SchoolEnrollmentIndicator(yearLabels[yearIndex]);
					}
					((SchoolEnrollmentIndicator)dataForOneYear).setPrimaryEnrollment(dataForAllYears[yearIndex]);
					break;
				case SCHOOL_ENROLLMENT_SECONDARY:
					if (dataForOneYear == null)
					{
						// TODO: Create the child class SchoolEnrollmentIndicator to hold the data.
						dataForOneYear = new SchoolEnrollmentIndicator(yearLabels[yearIndex]);
					}
					((SchoolEnrollmentIndicator)dataForOneYear).setSecondaryEnrollment(dataForAllYears[yearIndex]);
					break;
				default:
					break;
				}
				// TODO: Define the addIndicator to add to the end of the linked list.
				//       For extra credit opportunity determine if the year consecutively follows
				//       the previously added year. If not, call setIndicatorForYear() method
				//       to find the appropriate spot.
				// initialize the year
				foundCountry.addIndicator(dataForOneYear);
			}

			// add the newly created country to the 1D array
			model[countryIndex] = foundCountry;
		} // end of for loop traversing the array of Country objects
	}

	/**
	 * Performs a linear search of the array of Country objects based on the name of the Country.
	 * @param requestedCountryName  Name of the Country object to search for
	 * @param countries	One-dimensional array of Country objects.
	 * @return	The Country object if found. Otherwise, null.
	 */
	private static Country findCountry(String requestedCountryName, Country[] countries)
	{
		for (int i = 0; i < countries.length; i++)
		{
			if (countries[i] != null && countries[i].equals(requestedCountryName))
			{
				return countries[i];
			}
		}
		return null;
	}

	/**
	 * Returns the parsed model.
	 * @return parsed model
	 */
	public Country[] getModel() {
		return model;
	}

	/**
	 * Returns the indicator type.
	 * @return the indicator type
	 */
	public IndicatorType getIndicatorType()
	{	return this.indicatorType; }

	/**
	 * Returns x-axis name.
	 * @return x-axis name
	 */
	public String getXAxisName() {
		return this.xAxisName;
	}

	/**
	 * Returns y-axis name.
	 * @return y-axis name
	 */
	public String getYAxisName() {
		return this.yAxisName;
	}

}
