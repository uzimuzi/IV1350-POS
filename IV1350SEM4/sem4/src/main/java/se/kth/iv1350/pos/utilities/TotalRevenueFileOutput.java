package se.kth.iv1350.pos.utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import se.kth.iv1350.pos.observer.RevenueObserver;
/**
 * The file that writes the revenue to a file.
 */
public class TotalRevenueFileOutput implements RevenueObserver{
    
    private double totalRevenue;
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
     * Updates the total revenue and writes to the file.
     */
    public void newRevenue(double revenue){
        totalRevenue += revenue;
        revenueWriter.println("Total revenue: " + totalRevenue + " SEK");
        revenueWriter.flush();
    }
    /**
     * Closes the writer.
     */
    public void close(){
        revenueWriter.close();
    }
}
