package se.kth.iv1350.pos.utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * The class that handles exception logging
 */
public class Logger {
    
    private PrintWriter logWriter;
    /**
     * New logger that writes to a file.
     * @param file the file to be written to
     */
    public Logger(String file){
        try{
            logWriter = new PrintWriter(new FileWriter(file, true));
        } catch (IOException error){
            System.out.println("Logger error: " + error.getMessage());
        }
    }
    /**
     * Logs a new message to the file
     * @param logText the message to be logged.
     */
    public void log(String logText){
        logWriter.println(LocalDateTime.now() + " : " + logText);
        logWriter.flush();
    }
    /**
     * Closes the writer.
     */
    public void close(){
        logWriter.close();
    }
}
