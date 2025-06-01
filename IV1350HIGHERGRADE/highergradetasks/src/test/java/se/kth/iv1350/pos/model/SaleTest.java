package se.kth.iv1350.pos.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pos.dto.ItemDTO;
import se.kth.iv1350.pos.dto.ReceiptDTO;
import se.kth.iv1350.pos.dto.SaleDTO;
import se.kth.iv1350.pos.dto.ShoppingCartDTO;

public class SaleTest {
    
    private Sale sale;

    @BeforeEach
    void setUp(){
        sale = new Sale("John", "Store22");

    }
    @AfterEach
    void cleanUp(){
        sale = null;
    }
    @Test
    void addingItemAppearInCart(){
        ItemDTO item = new ItemDTO(1, "Banana", 5.99, 1, 12, "Fruit", "kgs");
        sale.addNewItemToSale(item, 2);

        ArrayList<ShoppingCartDTO> cart = sale.getFinalCart();
        assertEquals(1, cart.size(), "Cart should contain 1 item");
        assertEquals(2, cart.get(0).getItemQuantity(), "Item quantity should be 2");
        assertEquals("Banana", cart.get(0).getItem().getItemName(), "Item name should be banana");

    }
    @Test
    void getTotalCorrectAmountforItems(){
        sale.addNewItemToSale(new ItemDTO(1, "Apple", 5, 1, 12, "Fruit", "kgs"), 3);
        sale.addNewItemToSale(new ItemDTO(2, "Juice", 20, 1, 12, "Drinks", "liters"), 1);

        double expectedPrice = ((3*5 + 20)*1.12);
        assertEquals(expectedPrice, sale.getTotalPrice(), 0.001, "Total is not calculated correctly");

    }
    @Test
    void sameItemIncreaseQuantity(){
        ItemDTO item = new ItemDTO(1, "Milk", 20, 1, 12,  "Dairy", "liters");
        sale.addNewItemToSale(item, 2);
        sale.addNewItemToSale(item, 3);

        ShoppingCartDTO resultAmount = sale.getItemInCart(1);
        assertEquals(5, resultAmount.getItemQuantity(), "Item quantity should add up");
    }
    @Test
    void discountLowerPrice(){
        ItemDTO item = new ItemDTO(10, "Cheese" , 100, 1, 12, "Dairy", "grams");
        sale.addNewItemToSale(item, 1);

        ArrayList<ShoppingCartDTO> cart = sale.getFinalCart();
        SaleDTO discountedPrice = sale.applyDiscount(cart, 50);
        assertEquals(62, discountedPrice.getPriceAfterDiscount(), 0.01, "Discount not applied correctly");

    }

    @Test
    void getReceiptReturnObject(){
        ItemDTO item = new ItemDTO(8, "Soda", 25, 1, 12, "Drinks", "liters");
        sale.addNewItemToSale(item, 1);
        double amountPaid = 100;
        double changeAmount = sale.calculateChangeAmount(amountPaid);
        ReceiptDTO receipt = sale.getReceipt(amountPaid, changeAmount);
        assertNotNull(receipt, "ReceiptDTO should not be null");
        assertEquals(amountPaid, receipt.getAmountPaid(), "Amount paid not matching");
    }

    @Test
    void getTotalItemsScannedReturnsCorrectValue() {
    ItemDTO apple = new ItemDTO(1, "Apple", 5, 1, 12, "Fruit", "kgs");
    sale.addNewItemToSale(apple, 2);
    sale.addNewItemToSale(apple, 3);

    ArrayList<ShoppingCartDTO> cart = sale.getFinalCart();
    SaleDTO saleDTO = sale.applyDiscount(cart, 0);
    int expectedTotalItems = 5;

    assertEquals(expectedTotalItems, saleDTO.getTotalItems(), "Scanned item count should be passed to DTO correctly");
}


    @Test
    void getTotalPriceReturnRightSum(){
        sale.addNewItemToSale(new ItemDTO(6, "Chicken", 100, 1, 12, "Meat", "kgs"), 2);
        sale.addNewItemToSale(new ItemDTO(10, "Fish", 20, 1, 12, "Meat", "kgs"), 3);
        double totalPrice = sale.getTotalPrice();
        assertEquals(291.2, totalPrice, 0.01, "Incorrect total price");
    }

    @Test
void receiptShouldContainCorrectStoreMetadata(){
    ItemDTO item = new ItemDTO(9, "Yogurt", 15, 1, 12, "Dairy", "grams");
    sale.addNewItemToSale(item, 2);
    double amountPaid = 100;
    double changeAmount = sale.calculateChangeAmount(amountPaid);

    ReceiptDTO receipt = sale.getReceipt(amountPaid, changeAmount);
    assertEquals("John", receipt.getCashierName(), "Cashier name in receipt should match");
    assertEquals("Store22", receipt.getStoreID(), "Store ID in receipt should match");
}

    
}
