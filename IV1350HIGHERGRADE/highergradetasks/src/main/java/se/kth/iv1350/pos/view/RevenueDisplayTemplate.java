package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.observer.RevenueObserver;

public abstract class RevenueDisplayTemplate implements RevenueObserver {

    private double totalRevenue;

    public void newRevenue(double amount){
        accumulateRevenue(amount);
        displayRevenue();
    }

    private void accumulateRevenue(double amount){
        totalRevenue += amount;
    }

    private void displayRevenue(){
        try{
            showRevenue();
        }catch (Exception e ){
            handleException(e);
        }
    }

    protected double getTotalRevenue(){
        return totalRevenue;
    }

    protected abstract void showRevenue() throws Exception;

    protected abstract void handleException(Exception e);



    
}
