package se.kth.iv1350.pos.dto;

/**
 * The receiptdto class is a data transfer objject that represents the data
 *  found in the sale receipt.
 * 
 */
public class ReceiptDTO {
    
    private SaleDTO saleInformation;
    private double amountPaid;
    private double changeAmount;
    private String cashierName;
    private String storeID;

    /**
     * 
     * @param saleInformation A SaleDTO containing the information about the sale
     * @param amountPaid The amount of money the customer paid
     * @param changeAmount  The change amount the customer is to receive
     * @param cashierName The name of the cashier
     * @param storeID The ID of the store
     */
    public ReceiptDTO(SaleDTO saleInformation, double amountPaid, double changeAmount, String cashierName, String storeID){
        this.saleInformation = saleInformation;
        this.changeAmount = changeAmount;
        this.amountPaid = amountPaid;
        this.cashierName = cashierName;
        this.storeID = storeID;
    }
    
    /**
     * Gets the sale information
     * 
     */
    public SaleDTO getSaleInformation(){
        return this.saleInformation;
    }
    /**
     * Gets the amount paid by the customer
     * 
     */
    public double getAmountPaid(){
        return this.amountPaid;
    }
    /**
     * Gets the amount of change for the customer
     * 
     */
    public double getChangeAmount(){
        return this.changeAmount;
    }

    /**
     * Gets the name of the cashier
     * 
     */
    public String getCashierName(){
        return this.cashierName;
    }

     /**
     * Gets the id of the store.
     * 
     */

    public String getStoreID(){
        return this.storeID;
    }
    
}
