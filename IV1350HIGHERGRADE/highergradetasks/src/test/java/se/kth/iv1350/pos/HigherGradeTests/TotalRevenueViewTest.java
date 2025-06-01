package se.kth.iv1350.pos.HigherGradeTests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.pos.view.TotalRevenueView;

public class TotalRevenueViewTest {
    

    @Test
    void testRevenuePrintout() {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(out));

    TotalRevenueView view = new TotalRevenueView();
    view.newRevenue(1541.38);

    System.setOut(originalOut);

    String output = out.toString();
    assertTrue(output.contains("Total revenue"));
    assertTrue(output.contains("1541.38"));
}
}
