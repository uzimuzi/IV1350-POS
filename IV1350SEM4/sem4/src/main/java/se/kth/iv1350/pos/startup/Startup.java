package se.kth.iv1350.pos.startup;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.*;
import se.kth.iv1350.pos.view.View;

/**
 * The startup class handles the launch of the main components of the sale system.
 * It triggers all dependencies needed for a sale, controller, view, printer and model classes.
 */
public class Startup {
    private View view;
    private Controller controller;
    private SalesHistory salesHistory;   
    private Printer printer;
    /**
     * The startup object that initialized all the components needed for the POS.
     */
    public Startup(){
   
        salesHistory = new SalesHistory();
        printer = new Printer();
        controller = new Controller(printer, salesHistory);
        view = new View(controller);
       

    }
    /**
     * Creates the view layer for the system.
     * @return
     */
    public View getView(){
        return this.view;
    }

    public static void main(String[] args) {
        Startup startup = new Startup();
        View view = startup.getView();
        view.simulateAFakeSale();
        view.simulateAFakeSale();
    }

}
