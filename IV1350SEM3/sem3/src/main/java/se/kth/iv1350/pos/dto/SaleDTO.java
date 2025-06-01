package se.kth.iv1350.pos.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
/**
 * The saledto is a data tranfer object which stores a completed sale.
 * It takes price, discount, vat and time into details.
 */
public class SaleDTO {
    private double totalPrice;
    private double totalDiscountAmount;
    private double priceAfterDiscount;
    private double totalVat;
    private LocalDateTime saleTime;
    private ArrayList<ShoppingCartDTO> finalCart;
    private boolean appliedDiscount;
    private int totalItemsScanned;


    /**
     * 
     * @param saleTime The time of a sale
     * @param finalCart The final shopping cart of a sale
     * @param totalPrice The total price of a sale before discounts
     * @param totalDiscountAmount  The total amount of discount
     * @param priceAfterDiscount   The total price after discounts are applied
     * @param totalVat The amount of vat of a sale.
     * @param appliedDiscount Chepck to see if a discount is applied or not.
     * @param totalItemsScanned The amount of items scanned in a sale
     */

    public SaleDTO(LocalDateTime saleTime, ArrayList<ShoppingCartDTO> finalCart, double totalPrice, double totalDiscountAmount, double priceAfterDiscount, double totalVat, boolean appliedDiscount, int totalItemsScanned){
        this.totalPrice = totalPrice;
        this.totalDiscountAmount = totalDiscountAmount;
        this.priceAfterDiscount = priceAfterDiscount;
        this.totalVat = totalVat;
        this.saleTime = saleTime;
        this.finalCart = finalCart;
        this.appliedDiscount = appliedDiscount;
        this.totalItemsScanned = totalItemsScanned;

    }
    /**
     * Gets the total price
     * 
     */
    public double getTotalPrice(){
        return this.totalPrice;
    }
    
    /**
     * Gets the discount amount
     * 
     */
    public double getTotalDiscountAmount(){
        return this.totalDiscountAmount;
    }
    /**
     * Gets the total price after discount
     * 
     */
    public double getPriceAfterDiscount(){
        return this.priceAfterDiscount;
    }
    /**
     * Gets the total vat amount
     * 
     */
    public double getTotalVat(){
        return this.totalVat;
    }

    /**
     * Gets the discount status for a sale
     * 
     */

    public boolean hasAppliedDiscount(){
        return appliedDiscount;
    }

    /**
     * Gets the time of the sale
     * 
     */
     public LocalDateTime getSaleTime(){
        return this.saleTime;
    }

    /**
     * Gets the total number of items in the sale
     * 
     */

    public int getTotalItems(){
        return this.totalItemsScanned;
    }
    /**
     * Gets the final shopping cart of the sale.
     * 
     */
    public ArrayList<ShoppingCartDTO> getFinalCart(){
        return this.finalCart;
    }

}
