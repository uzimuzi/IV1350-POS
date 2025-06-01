package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.observer.RevenueObserver;
/**
 * The class that displays the total revenue to the view.
 */
public class TotalRevenueView implements RevenueObserver{
    private double totalRevenue;
    /**
     * Prints the new total revenue to the output.
     * @param revenue the revenue to be outputted.
     */
    
    public void newRevenue(double revenue){
        totalRevenue += revenue;
        System.out.println("Observer notified");
        System.out.println("Current total revenue: " + totalRevenue + "SEK");
    }
}
