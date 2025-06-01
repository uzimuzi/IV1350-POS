package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.dto.SaleDTO;

/**
 * The accountinginformation class handles the financial data genereated from sales, such as discounts
 * from sales.
 * 
 */
public class AccountingInformation {


    /**
     * Updates the accounting information based on the data from sales. 
     * @param priceAfterDiscount The final SaleDTO of a sale after all disocunts are applied.
     */
    public void updateAccountingInformation(SaleDTO priceAfterDiscount){
        System.out.println("Updated the accounting information...");
    }
}
