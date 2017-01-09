package action;

/**
 * Created by andrewbissette on 09/01/2017.
 *
 * An action that executes a single attack
 * May be extended by multiattack
 *
 */

import action.attack.Attack;
import player.*;
import utils.*;

public class SingleAttack implements Action {

    public boolean execute() {
        // not valid without a target and attribute
        // todo remove this temp code
        // todo handle invalid attacks properly
        Player temp1 = new PlayerCharacter();
        int temp2 = 0;
        this.execute(temp1,temp2);
        return false;
    }

    public boolean execute(Player target, int modifier) {
        // generate an attack using a given modifier

        // roll an attack roll
        int attackRoll = Dice.roll(1,20,modifier);

        // roll a damage roll
        // todo weapons and proficiency for variable damage
        // for now just 1d8
        int damageRoll = Dice.roll(1,6,modifier);

        // log
        Text.log("Attacking " + target.getName());
        Text.log("Attack: " + attackRoll);
        Text.log("Damage: " + damageRoll);

        // generate an attack
        Attack newAttack = new Attack(attackRoll, damageRoll);

        // todo pass to the target and ask for resolution
        boolean result = false;

        return result;
    }

}
