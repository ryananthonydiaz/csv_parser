package csv;

import java.io.IOException;


/**
 * Extends java.io.IOException. An object of this type is thrown when a class wants to indicate that the format
 * of the file is not valid
 */
public class InvalidFileFormatException extends IOException{

    /**
     * Instance variable that holds the String which contains the message describing the nature
     * of the error
     */
    private String message;

    /**
     * Instance variable that holds the String which contains the filename
     */
    private String filename;


    /**
     * When the CSVParser constructor creates an object of type InvalidFileFormatException to throw, include the
     * invalid line read as part of the message
     * @param message: Of type String for the message to output.
     * @param filename: Of type String for the name of the file
     */
    InvalidFileFormatException(String message, String filename) {
        this.message = message;
        this.filename = filename;
    }

    /**
     * Accessor method getMessage() which receives no arguments and returns a String which includes the filename and the
     * message describing the nature of the error.
     * @return
     */
    @Override
    public String getMessage() {
        return String.format("at: %s\nExpecting: %s", this.filename, this.message);
    }

}
