package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.controller.*;
/**
 * The view class represents the user interface layer in the MVC model.
 * It interacts with controller to mimic user input in order to see how the system behaves.
 */
public class View {

    private Controller controller;
    

    /**
     * Creates a new View using the controller.
     * @param controller The controller used to interact with this View object.
     */
    public View(Controller controller){
        this.controller = controller;
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
        addItemToSale(5, 100);
    }

    private void addItemToSale(int itemID, int quantity){
        controller.scanItem(itemID, quantity);
        System.out.println("Amount " + quantity + " added of Item " + itemID);

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

