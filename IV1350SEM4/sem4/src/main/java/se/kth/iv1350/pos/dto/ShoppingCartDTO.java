package se.kth.iv1350.pos.dto;

/**
 * The shoppingcartdto is a data transfer object representing an item in the shopping cart
 * of a customer.
 * It contains the item data and the amount in the cart.
 */
public class ShoppingCartDTO {
    final private ItemDTO itemInformation;
    final private int quantity;

    /**
     * 
     * @param itemInformation ItemDTO containing all the information for the item.
     * @param quantity The amount of the item in the cart.
     */
    public ShoppingCartDTO(ItemDTO itemInformation, int quantity){
        this.itemInformation = itemInformation;
        this.quantity = quantity;
    }

    /**
     * Gets the itemID
     * 
     */
     public int getItemID(){
        return this.itemInformation.getItemID();
    }
    /**
     * Gets the item information
     * 
     */
    public ItemDTO getItem(){
        return this.itemInformation;
    }

    /**
     * Gets the item amount-
     * 
     */
    public int getItemQuantity(){
        return this.quantity;
    }

   
}
