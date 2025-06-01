package se.kth.iv1350.pos.integration;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.dto.ItemDTO;
import se.kth.iv1350.pos.dto.ShoppingCartDTO;

public class InventoryInformationTest {

    private InventoryInformation inventory;

    @BeforeEach
    void setUp(){
        inventory = new InventoryInformation();
    }

    @AfterEach
    void cleanUp(){
        inventory = null;
    }

    @Test
    void findExistingItemReturnsCorrectItem(){
        ItemDTO item = inventory.findItemInformation(1); // Whole Milk
        assertNotNull(item, "Item should be found.");
        assertEquals(1, item.getItemID(), "Item ID should match.");
        assertEquals("Whole Milk", item.getItemName(), "Item name should match.");
    }
    @Test
    void findNonExistingItemReturnsNull(){
        ItemDTO item = inventory.findItemInformation(999);
        assertNull(item, "Non-existent item should return null.");
    }


    @Test
    void updateInventoryDecreasesQuantity(){
        ItemDTO originalItem = inventory.findItemInformation(2); // Cat Food
        int originalQuantity = originalItem.getItemQuantity();
        int soldQuantity = 3;

        ArrayList<ShoppingCartDTO> cart = new ArrayList<>();
        cart.add(new ShoppingCartDTO(originalItem, soldQuantity));
        inventory.updateItemInventory(cart);

        ItemDTO updatedItem = inventory.findItemInformation(2);
        int expectedQuantity = originalQuantity - soldQuantity;
        assertEquals(expectedQuantity, updatedItem.getItemQuantity(), "Inventory not reduced correctly.");
    }
    @Test
    void updateInventoryRemovesCorrectItem(){
        ItemDTO itemToSell = inventory.findItemInformation(4); // Bread
        int qtyBefore = itemToSell.getItemQuantity();
        ArrayList<ShoppingCartDTO> finalCart = new ArrayList<>();
        finalCart.add(new ShoppingCartDTO(itemToSell, 10));
        inventory.updateItemInventory(finalCart);

        ItemDTO result = inventory.findItemInformation(4);
        assertEquals(qtyBefore - 10, result.getItemQuantity(), "Bread quantity was not updated correctly.");
    }

    @Test
    void replaceItemCreatesNewItem(){
        ItemDTO original = inventory.findItemInformation(5); // Yogurt
        int oldQuantity = original.getItemQuantity();
        ArrayList<ShoppingCartDTO> cart = new ArrayList<>();
        cart.add(new ShoppingCartDTO(original, 1));
        inventory.updateItemInventory(cart);

        ItemDTO updated = inventory.findItemInformation(5);
        assertNotSame(original, updated, "Updated item should be a new object.");
        assertEquals(oldQuantity - 1, updated.getItemQuantity(), "Quantity should be updated correctly.");
    }
}
