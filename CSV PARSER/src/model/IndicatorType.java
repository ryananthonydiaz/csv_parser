package model;

/**
 * Class IndicatorType possesses and indicates constants that represent what Indicator the parsed file is.
 */
public enum IndicatorType {

    INVALID ("Invalid Data"),
    GDP_PER_CAPITA ("GDP per capita(current US$)"),
    SCHOOL_ENROLLMENT_PRIMARY ("School Enrollment In Primary (% net)"),
    SCHOOL_ENROLLMENT_SECONDARY("School Enrollment In Secondary (% net)"),
    SCHOOL_ENROLLMENT("School Enrollment In Primary and Secondary");


    /**
     * instance variable label of type String which the caller will use to get a description of the constant
     */
    private String label;

    /**
     * A constructor which accepts one parameter of type String which is used to instantiate the instance variable
     * "label"
     * @param label
     */
    IndicatorType(String label) {
        this.label = label;
    }

    /**
     * Getter/Accessor method which returns the instance variable "label"
     * @return: type String
     */
    public String getLabel() {
        return this.label;
    }

}
