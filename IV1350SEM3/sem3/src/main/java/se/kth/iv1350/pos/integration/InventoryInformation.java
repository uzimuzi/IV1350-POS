package se.kth.iv1350.pos.integration;
import java.util.ArrayList;

import se.kth.iv1350.pos.dto.ItemDTO;
import se.kth.iv1350.pos.dto.ShoppingCartDTO;

/**
 * The inventory class handles the inventory data.
 * It is responsible for collecting data in the inventory as well as uppdating as sales happen.
 */
public class InventoryInformation {
    private ArrayList<ItemDTO> items;

        /**
         * The constructor containing a few items.
         */
        public InventoryInformation(){
            items = new ArrayList<ItemDTO>();
            items.add(new ItemDTO(1, "Whole Milk", 21.50,500, 12, "Dairy", "liters"));
            items.add(new ItemDTO(2, "Cat Food", 99.90, 200, 12, "Animal", "ml"));
            items.add(new ItemDTO(3, "Newspaper", 50.90, 100, 6, "Media", "pcs"));
            items.add(new ItemDTO(4, "Bread", 24.90, 500, 12, "Bakery", "grams"));
            items.add(new ItemDTO(5, "Yogurt", 15.50, 500, 6, "Dairy", "liters"));

        }
        /**
         * Updates the inventory depending on the items sold during a completed sale. Items are
         * removed if they are sold.
         * @param finalCart The list of the items sold and the amount.
         */
        public void updateItemInventory(ArrayList<ShoppingCartDTO> finalCart){
            for(ShoppingCartDTO itemInFinalCart: finalCart){
                ItemDTO itemInformation = itemInFinalCart.getItem();
                int itemQuantity = itemInFinalCart.getItemQuantity();
                int updatedQuantity = itemInformation.getItemQuantity() - itemQuantity;
                replaceItem(itemInformation, updatedQuantity);
            }
        }

        private void replaceItem(ItemDTO itemInformation, int quantity){
            ItemDTO updatedItem = new ItemDTO(
                itemInformation.getItemID(),
                itemInformation.getItemName(),
                itemInformation.getItemPrice(),
                quantity,
                itemInformation.getItemVatRate(),
                itemInformation.getCategory(),
                itemInformation.getUnit()
            );
            items.remove(itemInformation);
            items.add(updatedItem);   
        }

        /**
         * Gets the item from the inventory information based on the itemID,
         * such as name, price and amount.
         * @param itemID The ID for the item being searched for.
         * 
         */
        public ItemDTO findItemInformation(int itemID){
            for (ItemDTO item : this.items){
                if(itemID == item.getItemID()){
                    return item;
                }
            }
            return null;
        }

}
