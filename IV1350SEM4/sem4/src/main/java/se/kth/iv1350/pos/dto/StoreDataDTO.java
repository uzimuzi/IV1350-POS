package se.kth.iv1350.pos.dto;

/**
 * DTO that contains information about a receipt,
 * such as the cashier and the store where the sale took place.
 */
public class StoreDataDTO{
    private final String cashierName;
    private final String storeID;
    /**
     * Creates a new StoreDataDtO.
     *
     * @param cashierName The name of the cashier handling the sale.
     * @param storeID     The ID of the store where the sale is made.
     */
    
    public StoreDataDTO(String cashierName, String storeID) {
        this.cashierName = cashierName;
        this.storeID = storeID;
    }

    /**
     * Returns the cashier name.
     * @return The name of the cashier.
     */
    public String getCashierName(){
        return cashierName;
    }
    /**
     * Returns the store ID.
     * @return The ID of the store.
     */
    public String getStoreID(){
        return storeID;
    }
}
