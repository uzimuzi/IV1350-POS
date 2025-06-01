package se.kth.iv1350.pos.exceptions;


public class DatabaseIssueException extends Exception {

    /**
     * Creates an exception that handles database issues.
     * @param message the message displayed in the exception
     */
    public DatabaseIssueException(String message){
        super("Database error " + message);
    }

    
}
