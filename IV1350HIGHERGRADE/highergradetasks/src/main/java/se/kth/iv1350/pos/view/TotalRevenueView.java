package se.kth.iv1350.pos.view;

/**
 * The class that displays the total revenue to the view.
 */
public class TotalRevenueView extends RevenueDisplayTemplate{
    
    protected void showRevenue() throws Exception{
        System.out.println("Total revenue: " + getTotalRevenue());
    }

    protected void handleException(Exception e){
        System.err.println("Could not display total revenue: " + e.getMessage());
    }
}
