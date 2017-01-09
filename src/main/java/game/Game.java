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
import action.*;

public class Game {

    public static void main(String[] args) {

        // all of this is just testing code
        PlayerCharacter newPC = new PlayerCharacter(10,15,16,10,10,10);
        PlayerCharacter newTarget = new PlayerCharacter();

        newTarget.setName("bob");
        newTarget.setArmourClass(10);

        Action oneAttack = new SingleAttack(2,6,0);

        // add to newPc list
        newPC.addAction("attack", oneAttack);

        // do eet
        newPC.perform(oneAttack, newTarget);
        newTarget.getCurrentHP();




    }

}
