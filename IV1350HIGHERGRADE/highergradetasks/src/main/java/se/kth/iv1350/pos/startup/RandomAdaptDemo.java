package se.kth.iv1350.pos.startup;

import se.kth.iv1350.pos.utilities.RandomAdaptComposition;
import se.kth.iv1350.pos.utilities.RandomAdaptInheritance;

/**
 * Prints the random numbers from both adaptations.
 */
public class RandomAdaptDemo {
    
    public static void main(String[] args) {
        

        RandomAdaptInheritance randinh = new RandomAdaptInheritance();
        System.out.println("Inheritance even number: " + randinh.nextEvenNumber(50));

        RandomAdaptComposition randcomp = new RandomAdaptComposition();
        System.out.println("Composition even number: " + randcomp.nextEvenNumber(50));
        


    }
}
