package se.kth.iv1350.pos.controller;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import se.kth.iv1350.pos.dto.SaleDTO;
import se.kth.iv1350.pos.dto.ShoppingCartDTO;
import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.integration.SalesHistory;

public class ControllerTest {
    private Controller controller;

    @BeforeEach
    public void setUp(){
        SalesHistory salesHistory = new SalesHistory();
        Printer printer = new Printer();
        controller = new Controller(printer, salesHistory);
        controller.startSale("John", "Store22");
    }

    @AfterEach
    public void cleanUp(){
        controller = null;
    }

    @Test
    public void testItemScanAddsCorrectAmount(){
        controller.scanItem(1, 1);
        controller.scanItem(2, 2);
        controller.scanItem(1, 3);

        ArrayList<ShoppingCartDTO> cart = controller.getFinalCart();

        boolean foundMilk = false;

        for(ShoppingCartDTO item: cart){
            if(item.getItem().getItemID() == 1){
                assertEquals(4, item.getItemQuantity(), "Milk quantity should be 4");
                foundMilk = true;

            }
        }
        assertTrue(foundMilk, "Milk should be in the cart");
    }

    @Test
    void totalPriceShouldBeCorrect(){
        controller.scanItem(2, 1);
        controller.scanItem(3, 1);
        double total = controller.completeSale();
        assertTrue(total > 0, "Total must be positive");
        assertEquals(166, Math.round(total), "Unexpected total for items");
    }

    @Test
    void paymentShouldReturnCorrectChangeAmount(){
        controller.scanItem(3, 1);
        double total = controller.completeSale();
        double amountPaid = 100;
        double changeAmount = controller.customerPayment(amountPaid);
        assertEquals(amountPaid - total, changeAmount, 0.01, "Incorrect change calculation");
    }

    @Test
    void requestDiscountShouldNotCrash(){
        controller.scanItem(1, 2);
        controller.scanItem(2, 1);
        SaleDTO discount = controller.discountRequest(99);
        assertNotNull(discount, "Discounted sale should not be null");
        assertTrue(discount.getPriceAfterDiscount() >= 0, "Price after discount must be non negative");
    }

    


}
