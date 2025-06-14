package se.kth.iv1350.pos.utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import se.kth.iv1350.pos.view.RevenueDisplayTemplate;
/**
 * The file that writes the revenue to a file.
 */
public class TotalRevenueFileOutput extends RevenueDisplayTemplate{
    
    private PrintWriter revenueWriter;

    /**
     * Creates a new file output.
     * @param file the file to be written to.
     * @throws IOException
     */
    public TotalRevenueFileOutput(String file) throws IOException{
        revenueWriter = new PrintWriter(new FileWriter(file, true));
    }

    /**
     * Writes total to the file.
     */
    @Override
    protected void showRevenue() throws IOException{
        revenueWriter.println("Total revenue: " + getTotalRevenue() + " SEK");
        revenueWriter.flush();
    }

    /**
     * Handles exceptions that when writing to the file.
     */
    @Override
    protected void handleException(Exception error){
        System.out.println("Error writing revenue to file: " + error.getMessage());
    }
    

    /**
     * Closes the writer.
     */
    public void close(){
        revenueWriter.close();
    }
}
