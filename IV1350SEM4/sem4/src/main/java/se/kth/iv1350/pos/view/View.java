package se.kth.iv1350.pos.view;

import java.io.IOException;

import se.kth.iv1350.pos.controller.*;
import se.kth.iv1350.pos.exceptions.DatabaseIssueException;
import se.kth.iv1350.pos.exceptions.ItemMissingException;
import se.kth.iv1350.pos.utilities.Logger;
import se.kth.iv1350.pos.utilities.TotalRevenueFileOutput;
/**
 * The view class represents the user interface layer in the MVC model.
 * It interacts with controller to mimic user input in order to see how the system behaves.
 */
public class View {

    private Controller controller;
    private Logger logger;

    /**
     * Creates a new View using the controller.
     * @param controller The controller used to interact with this View object.
     * 
     */
    public View(Controller controller){
        this.controller = controller;
        this.logger = new Logger("errorlogtextfile.txt");
        controller.addRevenueObserver(new TotalRevenueView());
        try{
            controller.addRevenueObserver(new TotalRevenueFileOutput("revenuelog.txt"));
        } catch (IOException error){
            System.out.println("Could not print: " + error);
        }
        
    }
    /**
     * Simulates a fake sale to test the system and check to see how it behaves. 
     */
    public void simulateAFakeSale(){

        beginSale();
        simulateItemScanning();
        double totalPrice = completeCurrentSale();
        double changeAmount = finishSale(1, 5000, totalPrice);
        printChange(changeAmount);
        endSale();
        logger.close();
    }

    private void beginSale(){
        controller.startSale("Anna", "Store 7");
        System.out.println("                NEW SALE STARTED                    \n");

    }

    private void simulateItemScanning(){
        addItemToSale(1, 1);
        addItemToSale(999, 1);
        addItemToSale(2, 5);
        addItemToSale(3, 10);
        addItemToSale(4, 15);
        addItemToSale(16, 100);
    }

    private void addItemToSale(int itemID, int quantity){
        try{
            controller.scanItem(itemID, quantity);
            System.out.println("Amount " + quantity + " added of Item " + itemID);
        } catch (ItemMissingException error){
            System.out.println("Item scanned was not found, check ID");
            logger.log("Missing item exception: " + error.getMessage());
        } catch(DatabaseIssueException error){
            System.out.println("There was a server issue, contact support");
            logger.log("Database Issue: " + error.getMessage());
        }catch(Exception error){
            System.out.println("There was an error!");
            logger.log("Unexpected issue: " + error.getMessage());
        }
        
    }

    private double completeCurrentSale(){
        double totalPrice = controller.completeSale();
        System.out.println("Sale complete. Final price: " + totalPrice);
        return totalPrice;
    }

    private double finishSale(int customerID, double paymentAmount, double totalPrice){
        controller.discountRequest(customerID);
        System.out.println("Change due...");
        return controller.customerPayment(paymentAmount);
    }

    private void printChange(double changeAmount){
        System.out.println("Customer gets " + changeAmount + " SEK back");
    }

    private void endSale(){
        System.out.println("                  SALE COMPLETE                     ");

    }

}   

