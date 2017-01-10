package player;

import action.*;
import action.attack.Attack;
import utils.Dice;
import utils.Text;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Created by andrewbissette on 08/01/2017.
 *
 * A generic player character
 * This may later become abstract to allow character classes to be implemented via inheritance
 * but I think this is not the best way to do that
 *
 */
public class PlayerCharacter implements Player {

    // PCs have names
    private String name = new String();

    // PCs have core attributes
    private Map<String, Attribute> statBlock = new LinkedHashMap<String,Attribute>();

    // PCs have health
    private int currentHP;
    private int maxHP;

    // PCs have armour
    private int armourClass;

    // PCs have a list of actions
    private Map<String, Action> actionList = new HashMap<String,Action>();

    //=======================
    // constructors
    // these call on the chargen functions below

    public PlayerCharacter(int str, int dex, int con, int intel, int wis, int cha) {
        ArrayList<Integer> stats = new ArrayList<Integer>();
        stats.add(str);
        stats.add(dex);
        stats.add(con);
        stats.add(intel);
        stats.add(wis);
        stats.add(cha);
        this.charGen(stats);
    }


    public PlayerCharacter() {
        this.charGen();
    }

    //=======================
    // interface methods
    public int getModifier(String attributeName) {

        int modifier = -99;
        // todo - handle incorrect name entry

        // convert input string to lowercase 3-letter code
        String attributeShort = attributeName.substring(0,2).toLowerCase();

        // search statblock map for this key
        for (Map.Entry<String,Attribute> playerAttribute : this.statBlock.entrySet()) {
            // convert key to code
            String currentAttributeShort = playerAttribute.getKey().substring(0,2).toLowerCase();
            if (attributeShort.equals(currentAttributeShort)) {
                modifier = playerAttribute.getValue().getModifier();
            }
        }

        // return
        return modifier;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public int initiative() {
        // roll initiative
        return Dice.roll(1,20,this.getModifier("dex"));
    }

    public boolean perform(String actionName) {
        return this.perform(actionName, null);
    }

    public boolean perform(String actionName, Player target) {
        // find the action from a string in the action list
        for(Map.Entry<String,Action> playerAction : this.actionList.entrySet()) {
            if (playerAction.getKey().equals(actionName)) {
                // do it
                return this.perform(playerAction.getValue(), target);
            }
        }
        return false;
    }

    public boolean perform(Action action) {
        return this.perform(action, null);
    }

    public boolean perform(Action action, Player target) {
        // check the action list for the chosen action
        boolean result = false;
        for(Map.Entry<String,Action> playerAction : this.actionList.entrySet()) {
            if (playerAction.getValue().equals(action)) {
                // do action
                utils.Text.log("executing");
                action.execute(target);
                result = true;
            }
        }
        // return success/failure
        return result;
    }

    @Override
    public boolean resolve(Attack incomingAttack) {
        // unpack the attack and see if it hit
        boolean result = false;
        if (incomingAttack.getAttackRoll() >= this.armourClass) {
            // hit!
            result = true;
            // update PC state
            this.currentHP -= incomingAttack.getDamageRoll();
        }

        // tell the attacker if they hit or miss
        return result;
    }

    //======================
    // private methods

    // populate action list
    // private for now - currently actions will be generated at PC creation, not mid-encounter

    public void addAction(String name, Action action) {
        this.actionList.put(name, action);
    }

    //=======================
    // getters and setters
    // mostly temporary
    public void setName(String name) {
        this.name = name;
    }

    public void setArmourClass(int armourClass) {
        this.armourClass = armourClass;
    }

    public int getCurrentHP() {
        Text.log(this.currentHP + " / " + this.maxHP + " hp.");
        return currentHP;
    }

    //======================
    // character generator
    // methods to be called by constructors
    // the aim is to allow random generation using prompts (if PlayerCharacter() is called) or
    // somewhat controlled generation if attributes are provided
    // todo add character classes and races

    private void charGen() {
        // no stats provided
        // start from scratch then pass it onto the next method
        Text.log("Generating ability scores (4d6 drop lowest)...");

        // generate scores and prepare to send to the next generator
        ArrayList<Integer> stats = new ArrayList<Integer>();
        for (int i = 0; i < 6; i++) {
            stats.add(Dice.dropLowest(4,6));
        }

        this.charGen(stats);
    }

    private void charGen(ArrayList<Integer> stats) {
        // generate stat block
        this.statBlock.put("Strength", new Attribute(stats.get(0)));
        this.statBlock.put("Dexterity", new Attribute(stats.get(1)));
        this.statBlock.put("Constitution", new Attribute(stats.get(2)));
        this.statBlock.put("Intelligence", new Attribute(stats.get(3)));
        this.statBlock.put("Wisdom", new Attribute(stats.get(4)));
        this.statBlock.put("Charisma", new Attribute(stats.get(5)));

        // print scores
        for (Map.Entry<String, Attribute> playerStat : this.statBlock.entrySet()) {
            Text.log(playerStat.getKey().substring(0, 3) + ": " + playerStat.getValue().getValue());
        }

        // calculate hp
        // todo do this properly
        int hitDice = Integer.parseInt(Text.read("How many sides do your hit dice have? "));
        this.maxHP = hitDice + this.getModifier("con");
        this.currentHP = this.maxHP;

        // calculate AC
        this.armourClass = 10 + this.getModifier("dex");

        // give the PC a basic attack
        // todo do this properly
        int weaponsize = Integer.parseInt(Text.read("How many sides do your attack dice have? "));
        SingleAttack playerAttack = new SingleAttack(weaponsize, this.getModifier("str"));
        this.addAction("attack", playerAttack);

        // give the PC a name
        this.name = Text.read("Name your hero! ");

    }

}
