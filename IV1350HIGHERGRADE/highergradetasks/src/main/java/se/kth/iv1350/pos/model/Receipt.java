package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.dto.ReceiptDTO;
import se.kth.iv1350.pos.dto.SaleDTO;
import se.kth.iv1350.pos.dto.StoreDataDTO;

/**
 * The receipt class handles the generating of a receipt object after a sale is complete.
 * It contains all the sale data.
 */
public class Receipt {

    /**
     * Creats a receipt object based on the final sale data, after all the 
     * data of the sale is finalized. 
     * @param priceAfterDiscount The final price of the sale after discounts are applied
     * @param amountPaid    The amount of money paid by the customer
     * @param changeAmount The amount of change to be given to the customer.
     * @param cashierName The name of the cashier of the sale.
     * @param storeID   The ID of the store the sale is made in.
     * @return
     */
    public ReceiptDTO createReceipt(SaleDTO priceAfterDiscount, double amountPaid, double changeAmount, StoreDataDTO storeInformation){
        return new ReceiptDTO(priceAfterDiscount, amountPaid, changeAmount, storeInformation.getCashierName(), storeInformation.getStoreID());
    }
}
