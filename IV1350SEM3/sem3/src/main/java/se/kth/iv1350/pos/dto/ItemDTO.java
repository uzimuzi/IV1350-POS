package se.kth.iv1350.pos.dto;

/**
 * The itemdto is a data transer object that contains the
 * details of an item in a sale.
 * It is passed as a object containing the attributes of an item.
 */
public class ItemDTO {
    private int itemID;
    private String itemName;
    private double itemPrice;
    private int itemQuantity;
    private double itemVatRate;
    private String category;
    private String unit;
    


    /**
     * 
     * @param itemID The unique ID for an item
     * @param itemName  The name of the item
     * @param itemPrice The cost of the item
     * @param quantity  The amount of the item
     * @param vatRate   The vat rate for the item
     * @param category The category the item belongs to
     * @param unit The unit the item has
     */
    public ItemDTO(int itemID, String itemName, double itemPrice, int quantity, double vatRate, String category, String unit){
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = quantity;
        this.itemVatRate = vatRate;
        this.category = category;
        this.unit = unit;
    }
    /**
     * Gets the itemID
     * 
     */
    public int getItemID(){
        return this.itemID;
    }
    /**
     * Gets the item price
     * 
     */
    public double getItemPrice(){
        return this.itemPrice;
    }
    /**
     * Gets the item name
     * 
     */
    public String getItemName(){
        return this.itemName;
    }

    /**
     * Gets the item category
     * 
     */
    public String getCategory(){
        return this.category;
    }

    /**
     * Gets the item unit
     * 
     */
    public String getUnit(){
        return this.unit;
    }



    /**
     * Gets the item amount
     * 
     */
    public int getItemQuantity(){
        return this.itemQuantity;
    }

    /**
     * Gets the item vatrate
     * 
     */
    public double getItemVatRate(){
        return this.itemVatRate;
    }

    
}
