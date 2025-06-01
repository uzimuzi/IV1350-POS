package se.kth.iv1350.pos.integration;

import java.util.ArrayList;
/**
 * The class that handles revenue logging.
 */
public class RevenueLog {
    private final ArrayList<Double> completeRevenue = new ArrayList<>();
    /**
     * Adds the revenue after a sale to the log
     * @param revenue the revenue to be added
     */
    public void addRevenue(double revenue){
        completeRevenue.add(revenue);
    }

    

}
