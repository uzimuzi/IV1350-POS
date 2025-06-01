package se.kth.iv1350.pos.observer;

/**
 * Obersver that tracks new revenue from sales.
 */
public interface RevenueObserver {

    /**
     * Called when a new sale is complete
     * @param revenue the revenue to be added.
     */
    void newRevenue(double revenue);
    
}
