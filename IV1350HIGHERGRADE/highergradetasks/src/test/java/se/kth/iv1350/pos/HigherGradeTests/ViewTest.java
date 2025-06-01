package se.kth.iv1350.pos.HigherGradeTests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.integration.SalesHistory;
import se.kth.iv1350.pos.view.View;

public class ViewTest {
    
    @Test
    void testSaleStartOutput() {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(out));
    
    SalesHistory salesHistory = new SalesHistory();
    Printer printer = new Printer();
    Controller controller = new Controller(printer, salesHistory);
    View view = new View(controller);
    view.simulateAFakeSale();

    System.setOut(originalOut);

    String output = out.toString();
    assertTrue(output.contains("NEW SALE STARTED"));
    assertTrue(output.contains("Sale complete"));
    assertTrue(output.contains("RECEIPT: START"));
    assertTrue(output.contains("Items Bought"));

}

}
