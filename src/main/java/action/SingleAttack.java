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

    // variables
    // attacks have info about the dice and attributes they use here which are specified during construction
    int numOfDice;
    int typeOfDice;
    int modifier;

    // constructors
    public SingleAttack(int numOfDice, int typeOfDice, int modifier) {
        this.numOfDice = numOfDice;
        this.typeOfDice = typeOfDice;
        this.modifier = modifier;
    }

    public SingleAttack(int numOfDice, int typeOfDice) {
        this(numOfDice,typeOfDice,0);
    }

    public SingleAttack(int typeOfDice) {
        this(1, typeOfDice);
    }

    public boolean execute() {
        // not valid without a target and attribute
        // todo remove this temp code
        // todo handle invalid attacks properly
        Player temp1 = new PlayerCharacter();
        this.execute(temp1);
        return false;
    }

    public boolean execute(Player target) {
        // generate an attack using a given modifier
        // todo advantage/disadvantage

        // roll an attack roll
        int attackRoll = Dice.roll(1,20,this.modifier);

        // roll a damage roll
        // todo weapons and proficiency
        // for now just 1d8
        int damageRoll = Dice.roll(this.numOfDice,this.typeOfDice,this.modifier);
        // log
        Text.log("Attacking " + target.getName() + " with " + this.numOfDice + "d" + this.typeOfDice + "+" + this.modifier);
        Text.log("Attack: " + attackRoll);
        Text.log("Damage: " + damageRoll);

        // generate an attack
        Attack newAttack = new Attack(attackRoll, damageRoll);

        // pass to the target and ask for resolution
        boolean result = target.resolve(newAttack);

        return result;
    }

}
