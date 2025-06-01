package se.kth.iv1350.pos.HigherGradeTests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.pos.dto.ItemDTO;
import se.kth.iv1350.pos.dto.ReceiptDTO;
import se.kth.iv1350.pos.dto.SaleDTO;
import se.kth.iv1350.pos.dto.ShoppingCartDTO;
import se.kth.iv1350.pos.integration.Printer;

public class ReceiptPrinterTest {

    @Test
    void testPrintReceiptOutput(){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));

        ItemDTO item = new ItemDTO(9, "Chocolate", 30.0, 1, 0.12, "Sweets", "pcs");
        ShoppingCartDTO cartItem = new ShoppingCartDTO(item, 2);
        ArrayList<ShoppingCartDTO> cart = new ArrayList<>();
        cart.add(cartItem);

        SaleDTO saleInfo = new SaleDTO (LocalDateTime.now(),
            cart,60.0,    
            5.0,      
            55.0,     
            10.0,     
            true,     
            2      
        );

        ReceiptDTO receipt = new ReceiptDTO(saleInfo, 70.0, 15.0, "Cashier 1", "Store 99");

        Printer printer = new Printer();
        printer.printReceipt(receipt);

        System.setOut(originalOut);
        String output = out.toString();

        assertTrue(output.contains("RECEIPT: START"));
        assertTrue(output.contains("Cashier: Cashier 1"));
        assertTrue(output.contains("Store ID: Store 99"));
        assertTrue(output.contains("Chocolate"));
        assertTrue(output.contains("Total (incl. VAT):"));
        assertTrue(output.contains("RECEIPT: END"));
    }
}
