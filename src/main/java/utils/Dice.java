package utils;

/**
 * Created by andrewbissette on 08/01/2017.
 *
 * A collection of die rolling methods
 *
 */

import java.util.concurrent.ThreadLocalRandom;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Dice {

    // basic die roller
    public static int roll(int numOfSides) {
        int roll = ThreadLocalRandom.current().nextInt(1, numOfSides + 1);
        utils.Text.log("roll: " + roll);

        return roll;
    }

    // roll multiple dice
    public static int roll(int numOfDice, int numOfSides) {
        int sum = 0;
        for (int i = 0; i < numOfDice; i++) {
            sum += roll(numOfSides);
        }
        return sum;
    }

    // roll multiple dice with a modifier
    public static int roll(int numOfDice, int numOfSides, int modifier) {
        return roll(numOfDice, numOfSides) + modifier;
    }

    // roll XdY, drop lowest
    public static int dropLowest(int numOfDice, int numOfSides) {
        List<Integer> rolls = new ArrayList<Integer>();
        for (int i = 0; i < numOfDice; i++) {
            rolls.add(roll(numOfSides));
        }
        // delete lowest
        int minIndex = rolls.indexOf(Collections.min(rolls));
        rolls.remove(minIndex);

        // sum and return
        int sum = 0;
        for (Integer i : rolls) {
            sum += i;
        }
        return sum;
    }

    // advantage and disadvantage
    public static int advantage() {
        return advantage(20);
    }

    public static int advantage(int numOfSides) {
        int roll1 = roll(numOfSides);
        int roll2 = roll(numOfSides);

        if (roll1 > roll2) {
            return roll1;
        } else {
            return roll2;
        }
    }

    public static int disadvantage() {
        return disadvantage(20);
    }

    public static int disadvantage(int numOfSides) {
        int roll1 = roll(numOfSides);
        int roll2 = roll(numOfSides);

        if (roll1 < roll2) {
            return roll1;
        } else {
            return roll2;
        }
    }

}
