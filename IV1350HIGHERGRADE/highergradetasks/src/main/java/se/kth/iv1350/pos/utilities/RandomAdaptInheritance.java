package se.kth.iv1350.pos.utilities;

import java.util.Random;

/**
 * Adapts random using inheritance.
 */
public class RandomAdaptInheritance extends Random {

    /**
     * returns an even number between 0 and max.
     * @param max the max value of the random number generation.
     * @return
     */
    public int nextEvenNumber(int max){
        return super.nextInt(max / 2) * 2;
    }
}
