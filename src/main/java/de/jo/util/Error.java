package de.jo.util;

import de.jo.aurora.lexer.tokens.TokenPosition;

/**
 * @author Johannes Hans 19.05.2023
 * @Project AuroraScript
 */
public class Error {

    /**
     * This function prints an error message to the console.
     *
     * @param error The parameter "error" is a String variable that represents the error message that occurred.
     */
    public static void softCall(String error) {
        System.err.println("An error occurred: ");
        System.err.println(error);
    }

    /**
     * This function prints an error message and stack trace for a given exception.
     *
     * @param error The "error" parameter is an object of the Exception class, which represents an error or exception that
     * occurred during the execution of a program. The method "softCall" takes this parameter and prints out an error
     * message along with the stack trace of the exception. It also throws the exception back to the
     */
    public static void softCall(Exception error) {
        System.err.println("An error occurred: ");
        error.printStackTrace();
    }

    /**
     * This function prints an error message and the stack trace of an exception.
     *
     * @param error A String variable that contains a message describing the error that occurred.
     * @param exception The "exception" parameter is an object of the Exception class, which represents an error or
     * exceptional condition that has occurred during the execution of a program. It contains information about the type of
     * error, the location where it occurred, and the stack trace of the program at the time of the error. The
     */
    public static void softCall(String error, Exception exception) {
        System.err.println("An error occurred: ");
        System.err.println(error);
        exception.printStackTrace();
    }

    /**
     * The function prints an error message and exits the program with a status code of -1.
     *
     * @param error The parameter "error" is a String variable that represents the error message that will be printed to
     * the console when the "call" method is called.
     */
    public static void call(String error) {
        System.err.println("An error occurred: ");
        System.err.println(error);
        System.exit(-1);
    }

    /**
     * This function prints an error message and stack trace for an exception and exits the program with a status code of
     * -1.
     *
     * @param error The parameter "error" is an object of the Exception class, which represents an error or exceptional
     * condition that has occurred during the execution of a program. It is used to pass the error information to the
     * method "call" so that it can handle the error and print the error message along with the stack
     */
    public static void call(Exception error) {
        System.err.println("An error occurred: ");
        error.printStackTrace();
        System.exit(-1);
    }

    /**
     * This function prints an error message and stack trace for an exception and exits the program with a status code of
     * -1.
     *
     * @param error A string that describes the error that occurred.
     * @param exception The "exception" parameter is an object of the Exception class, which represents an error or
     * exceptional condition that has occurred during the execution of a program. It contains information about the type of
     * error, the location where it occurred, and the stack trace of the program at the time of the error. The
     */
    public static void call(String error, Exception exception) {
        System.err.println("An error occurred: ");
        System.err.println(error);
        exception.printStackTrace();
        System.exit(-1);
    }

    public static void callToken(String error, TokenPosition pos) {
        System.err.println("An error occurred: ");
        System.err.println(error +" at position: ");
        System.err.println(pos.toString()+"!");
        System.exit(-1);
    }

}
