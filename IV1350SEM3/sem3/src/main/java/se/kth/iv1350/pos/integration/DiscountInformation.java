package se.kth.iv1350.pos.integration;
import java.util.ArrayList;

import se.kth.iv1350.pos.dto.ShoppingCartDTO;

/**
 * The discountinformation handles the retrieval of discount information for a
 * specific customer based on ID and applies the discounts available for all the items
 * in their current shopping cart.
 */
public class DiscountInformation {


    /**
     * Searches foe discounts available for items in the cart.
     * @param customerID The ID of the customer who requests the discount.
     * @param saleInformation The current item list in the customers cart.
     * 
     */
    public double lookUpDiscountInformation(int customerID, ArrayList<ShoppingCartDTO> saleInformation){
        return 0;
    }
}
