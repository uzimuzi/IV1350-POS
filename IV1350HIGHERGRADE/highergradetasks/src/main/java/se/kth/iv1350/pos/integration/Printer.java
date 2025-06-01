package se.kth.iv1350.pos.integration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import se.kth.iv1350.pos.dto.ReceiptDTO;
import se.kth.iv1350.pos.dto.SaleDTO;
import se.kth.iv1350.pos.dto.ShoppingCartDTO;
/**
 * The printer class handles the output for the receipt in a sale.
 * It formats the data from a sale after it has completed.
 */
public class Printer {

    /**
     * Prints the receipt of the sale, using the data generated during the sale. It is
     * formatted and displayed as a summary of the sale.
     * @param receipt The object containing all the information needed for the receipt.
     */
public void printReceipt(ReceiptDTO receipt){

        SaleDTO saleInformation = receipt.getSaleInformation();
        if(saleInformation != null){
            printReceiptStart(receipt);
            printReceiptTimestamp(saleInformation.getSaleTime());
            printReceiptItemSection(saleInformation);
            printReceiptSummary(saleInformation, receipt);
            printReceiptEnding();
        }
    }

    private void printReceiptStart(ReceiptDTO receipt) {
    System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::");
    System.out.println("                    RECEIPT: START                  ");
    System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::");
    System.out.println("Cashier: " + receipt.getCashierName());
    System.out.println("Store ID: " + receipt.getStoreID());
    System.out.println();
}

    private void printReceiptTimestamp(LocalDateTime saleTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        String formattedTime = saleTime.format(formatter);
        System.out.println("Timestamp: " + formattedTime + "\n");
    }

    private void printReceiptItemSection(SaleDTO saleInformation){
        System.out.println("Items Bought:");
        System.out.println("------------------------------------------------------");
        System.out.println("Qty | Item Name          | Unit Price | Unit Total");
        System.out.println("------------------------------------------------------");

        ArrayList<ShoppingCartDTO> cart = saleInformation.getFinalCart();
        for (int i = 0; i < cart.size(); i++){
            printItem(cart.get(i));
        }
        System.out.printf("Items Scanned: %d\n", saleInformation.getTotalItems());


        System.out.println("------------------------------------------------------");
    }

    private void printItem(ShoppingCartDTO item){
        String name = item.getItem().getItemName();
        int amount = item.getItemQuantity();
        double unitPrice = item.getItem().getItemPrice();
        double lineTotal = unitPrice * amount;

        System.out.printf("%-3d | %-18s | %10.2f | %10.2f\n", amount, name, unitPrice, lineTotal);
    }

    private void printReceiptSummary(SaleDTO saleInformation, ReceiptDTO receipt){
        System.out.printf("Total (incl. VAT):                 %.2f SEK\n", saleInformation.getTotalPrice());
        System.out.printf("Total VAT:                         %.2f SEK\n", saleInformation.getTotalVat());
        System.out.printf("Amount Paid:                       %.2f SEK\n", receipt.getAmountPaid());
        System.out.printf("Discount Used:                     %.2f SEK\n", saleInformation.getTotalDiscountAmount());
    }

    private void printReceiptEnding(){
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("                    RECEIPT: END                    ");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
    }
}
