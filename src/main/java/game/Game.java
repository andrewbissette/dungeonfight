package game;

/**
 * Created by andrewbissette on 08/01/2017.
 *
 * A temporary class to run the game
 * Later it may need to have a game state, controller, etc in other modules
 * For now a place to run a main method
 *
 */

import player.*;
import utils.*;

public class Game {

    public static void main(String[] args) {

        // all of this is just testing code
        Text.log("rolling 1dX");
        Text.read("enter X: ");
        Text.log(Integer.toString(Dice.roll(2,10,5)));

    }

}
