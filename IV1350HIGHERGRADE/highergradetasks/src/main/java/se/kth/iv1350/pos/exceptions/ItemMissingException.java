package se.kth.iv1350.pos.exceptions;

public class ItemMissingException extends Exception {

    private final String itemID;

    /**
     * Creates an exception for a missing item.
     * @param itemID the id of the missing item.
     */
    public ItemMissingException(String itemID){
        super("Item ID was not found: " + itemID);

        this.itemID = itemID;
    }

    /**
     * returns the missing item id
     * 
     */
    public String getItemID(){
        return itemID;
    }
}
