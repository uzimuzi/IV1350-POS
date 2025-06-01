package se.kth.iv1350.pos.integration;

import java.util.ArrayList;

import se.kth.iv1350.pos.dto.ShoppingCartDTO;

/**
 * The saleshistory class handles the documenting of previous sales and transactions
 * It contains the record of completed sales.
 */
public class SalesHistory {

    private ArrayList<ArrayList<ShoppingCartDTO>> saleCheckout;

    /**
     * The saleshistory constructor.
     */

    public SalesHistory(){
        this.saleCheckout = new ArrayList<ArrayList<ShoppingCartDTO>>();
    }
    
    /**
     * Stores the completed sale into the history.
     * @param saleCheckout The shopping cart of the completed sale that is stored.
     */
    public void documentSale (ArrayList<ShoppingCartDTO> saleCheckout){
        this.saleCheckout.add(saleCheckout);
    }
}
