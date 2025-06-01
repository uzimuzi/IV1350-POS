package se.kth.iv1350.pos.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import se.kth.iv1350.pos.dto.ItemDTO;
import se.kth.iv1350.pos.dto.ReceiptDTO;
import se.kth.iv1350.pos.dto.SaleDTO;
import se.kth.iv1350.pos.dto.ShoppingCartDTO;
import se.kth.iv1350.pos.dto.StoreDataDTO;
import se.kth.iv1350.pos.observer.RevenueObserver;

/**
 * The sale class handles the sale transaction and contains all the information about
 * the entire sale. It takes care of all of the things that happen during a sale such as adding 
 * and removing items etc.
 */
public class Sale {
    private ArrayList<ShoppingCartDTO> shoppingCart;
    private SaleDTO priceAfterDiscount;
    private double totalPrice;
    private double totalVat;
    private double currentCartPrice;
    private Receipt receipt;
    private int totalItemsScanned;
    private LocalDateTime saleTime;
    private final String cashierName;
    private final String storeID;
    private ArrayList<RevenueObserver> observers = new ArrayList<>();

    
    /*
     * The constructor for the sale class.
     */
    public Sale(String cashierName, String storeID){
        saleTime = setSaleTime();
        shoppingCart = new ArrayList<ShoppingCartDTO>();
        receipt = new Receipt();
        totalPrice = 0;
        totalVat = 0;
        currentCartPrice = 0;
        this.cashierName = cashierName;
        this.storeID = storeID;
    }

    /**
     * Adds a revenue observer
     * @param observer the observer that is added
     */
    public void addRevenueObserver(RevenueObserver observer){
        observers.add(observer);
    }

    /**
     * Notifies all revenue observers of the completed sale
     */
    public void notifyObservers(){
        for(RevenueObserver observer: observers){
            observer.newRevenue(this.priceAfterDiscount.getPriceAfterDiscount());
        }
    }


    /**
     * Gets the total price of the sale
     * 
     */
    public double getTotalPrice(){
        return totalPrice;
    }
    /**
     * Gets the local time of the sale
     * 
     */
    private LocalDateTime setSaleTime(){
        return LocalDateTime.now();
    }
    
    /**
     * Retreives a specific item from the shopping cart by ID.
     * @param itemID ID of the item to be accessed in the cart
     * @return
     */
    public ShoppingCartDTO getItemInCart(int itemID){
        return findItemByID(itemID);
    }

    private ShoppingCartDTO findItemByID(int itemID){
        for (int i = 0; i < shoppingCart.size(); i++){
            ShoppingCartDTO item = shoppingCart.get(i);
            if (item.getItemID() == itemID){
                return item;
            }
        }
        return null;
    }

    /**
     * Checks to see if the item examined exists.
     * @param itemID ID of the item to be accessed in the cart
     * @return
     */
    public boolean itemLookup(int itemID){
        return findItemByID(itemID) != null;
    }

    private void removeItemFromCart(ItemDTO itemInformation){
        int indexToRemove = -1;
        for (int i = 0; i < shoppingCart.size(); i++){
            if (shoppingCart.get(i).getItemID() == itemInformation.getItemID()){
                indexToRemove = i;
                break;
            }
        }
        if (indexToRemove != -1){
            shoppingCart.remove(indexToRemove);
        }
    }
    

    private void updateCart(ItemDTO itemInformation, int quantity){
        double itemPrice = itemInformation.getItemPrice();
        double vatRate = itemInformation.getItemVatRate();
        double totalPriceForItem = itemPrice*quantity;
        double itemVat = (itemPrice * vatRate/100) * quantity;
        
        calculateTotalsForCart(totalPriceForItem, itemVat);
    }

    private void calculateTotalsForCart(double totalPriceForItem, double itemVat){
        this.currentCartPrice += totalPriceForItem;
        this.totalVat += itemVat;
        this.totalPrice = this.currentCartPrice + this.totalVat;
    }

    /**
     * Adds the new item to the sale as well as the amount of it. If an item exists, just update quantity.
     * @param itemInformation Information about the item to be added.
     * @param quantity  The amount of the item to be added.
     * @return
     */
    public ArrayList<ShoppingCartDTO> addNewItemToSale(ItemDTO itemInformation, int quantity){
        if (itemLookup(itemInformation.getItemID())){
            itemExists(itemInformation, quantity);
        } else {
            processNewItem(itemInformation, quantity);
        }
        updateCart(itemInformation, quantity);
        totalItemsScanned += quantity;
        return this.shoppingCart;
    }

    private void itemExists(ItemDTO itemInformation, int quantity){
        ShoppingCartDTO existingItem = getItemInCart(itemInformation.getItemID());
        int newQuantity = existingItem.getItemQuantity() + quantity;
        removeItemFromCart(itemInformation);
        shoppingCart.add(new ShoppingCartDTO(itemInformation, newQuantity));
    }

    private void processNewItem(ItemDTO itemInformation, int quantity){
        shoppingCart.add(new ShoppingCartDTO(itemInformation, quantity));
    }

    /**
     * Creates a receipt for the entire sale, inclucing financial data.
     * @param amountPaid The amount paid by the customer
     * @param changeAmount The amount of change to be given to the customer
     * @return
     */
    public ReceiptDTO getReceipt(double amountPaid, double changeAmount){
        StoreDataDTO storeInformation = new StoreDataDTO(cashierName, storeID);
        return receipt.createReceipt(priceAfterDiscount, amountPaid, changeAmount, storeInformation);
    }

    /**
     * Calculates the amount of money to be given back to the customer.
     * @param amountPaid The amount of money the customer gives.
     * @return
     */
    public double calculateChangeAmount(double amountPaid){
        return amountPaid - this.totalPrice;
    }

    /**
     * Applies discounts to the sale by the amount of discount the customer is eligible for.
     * @param saleCheckout  The final price after discounts.
     * @param discountAmount The amount of discount to be applied to the sale.
     * @return
     */
    public SaleDTO applyDiscount(ArrayList<ShoppingCartDTO> saleCheckout, double discountAmount){
        double finalPrice = totalPrice - discountAmount;
        priceAfterDiscount = new SaleDTO(this.saleTime, saleCheckout, this.totalPrice, discountAmount, finalPrice, this.totalVat, true, totalItemsScanned);
        return priceAfterDiscount;
    }

    /**
     * Gets the cashiers name
     */

    public String getCashierName() {
        return cashierName;
    }
    /**
     * Gets the ID of the store.
     */

    public String getStoreID() {
        return storeID;
    }
    /**
     * Gets the final shopping cart of the customer for the current sale.
     * @return
     */
    public ArrayList<ShoppingCartDTO> getFinalCart(){
        return this.shoppingCart;
    }
}
