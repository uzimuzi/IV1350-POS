package se.kth.iv1350.pos.controller;

import java.util.ArrayList;

import se.kth.iv1350.pos.dto.ItemDTO;
import se.kth.iv1350.pos.dto.ReceiptDTO;
import se.kth.iv1350.pos.dto.SaleDTO;
import se.kth.iv1350.pos.dto.ShoppingCartDTO;
import se.kth.iv1350.pos.integration.*;
import se.kth.iv1350.pos.model.*;

/**
 * The Controller class.
 */

public class Controller {

    private AccountingInformation accountingInformation;
    private InventoryInformation inventoryInformation;
    private DiscountInformation discountInformation;
    private SalesHistory salesHistory;
    private Sale sale;
    private Printer printer;
    
     /**
      * The controller class acts as the main point of control in the MVC architecture of the point
      * sale system. It handles input and output between the model and view layers using the different
      * information systems.
      * @param printer Handles the printing of the receipts.
      * @param salesHistory Stores the past sales made.
      */
     public Controller(Printer printer, SalesHistory salesHistory){
        this.printer = printer;
        this.salesHistory = salesHistory;
        /**
         * Handles the inventory data.
         */
        inventoryInformation = new InventoryInformation();
        /**
         * Handles the financial data.
         */
        accountingInformation = new AccountingInformation();
        /**
         * Handles the discount data for customers based on their ID.
         */
        discountInformation = new DiscountInformation();

     }

     /**
      * Starts a new sale by creating a new sale instance.
      */
     public void startSale(String cashierName, String storeID){
        this.sale = new Sale(cashierName, storeID);
     }

     /**
      * Adds the item to the current sale, giving both ID and amount of the item.

      * @param itemID   The item to be added to the sale.
      * @param quantity The amount of the item to be added to the sale.
      */
     public void scanItem(int itemID, int quantity){
        if (quantity <= 0){
            return;
        }
        ItemDTO itemInformation = retrieveItem(itemID);
        if (itemInformation != null){
            sale.addNewItemToSale(itemInformation, quantity);
        }
     }

     private ItemDTO retrieveItem(int itemID){
        return inventoryInformation.findItemInformation(itemID);
    }

     /**
      *  Method which returns the final sale cart.
      */
     public ArrayList<ShoppingCartDTO> getFinalCart(){
        if (sale == null){
            return new ArrayList<>();
        }
        return sale.getFinalCart();
     }

      /**
      * Processes the customers payment and calculates change to be given backl.
      * @param amountPaid The amount the customer has paid.
      * 
      */
     public double customerPayment(double amountPaid){
        double changeAmount = 0;
        if (sale != null){
            changeAmount = processTransaction(amountPaid);
        }
        return changeAmount;
     }

     private double processTransaction(double amountPaid){
        double changeAmount = sale.calculateChangeAmount(amountPaid);
        ReceiptDTO receipt = sale.getReceipt(amountPaid, changeAmount);
        if (receipt != null){
            printer.printReceipt(receipt);
        }
        return changeAmount;
    }

     /**
      * Requests the discount information by ID, and applies the discount to the customers cart.
      * @param customerID Used to derive the eligibility of discounts for the customer.
      * 
      */

      public SaleDTO discountRequest(int customerID){
        ArrayList<ShoppingCartDTO> finalCart = getFinalCart();
        double discountAmount = getDiscount(customerID, finalCart);
        SaleDTO priceAfterDiscount = sale.applyDiscount(finalCart, discountAmount);
        updateAccounting(priceAfterDiscount);
        return priceAfterDiscount;
     }

     private double getDiscount(int customerID, ArrayList<ShoppingCartDTO> finalCart){
        return discountInformation.lookUpDiscountInformation(customerID, finalCart);
    }

    private void updateAccounting(SaleDTO priceAfterDiscount){
        accountingInformation.updateAccountingInformation(priceAfterDiscount);
    }

     /**
      * Completes the sale by documenting it in SalesHistory and updates the
      * inventory of the item.
      */
     public double completeSale(){
        if (sale == null){
            return 0;
        }
        ArrayList<ShoppingCartDTO> finalCart = getFinalCart();
        updateInfo(finalCart);
        return sale.getTotalPrice();
     }

     private void updateInfo(ArrayList<ShoppingCartDTO> finalCart){
        salesHistory.documentSale(finalCart);
        inventoryInformation.updateItemInventory(finalCart);
    }
   


 
}
