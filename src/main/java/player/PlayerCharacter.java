package player;

import action.*;
import utils.Dice;

import java.util.Map;
import java.util.HashMap;

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
    private Map<String, Attribute> statBlock = new HashMap<String,Attribute>();

    // PCs have health
    private int currentHP;
    private int maxHP;

    // PCs have armour
    private int armourClass;

    // PCs have a list of actions
    private Map<String, Action> actionList = new HashMap<String,Action>();

    //=======================
    // constructors

    public PlayerCharacter(int str, int dex, int con, int intel, int wis, int cha) {
        this(new int[]{str, dex, con, intel, wis, cha});
    }

    public PlayerCharacter(int[] stats) {
        // set stat array
        this.statBlock.put("Strength", new Attribute(stats[0]));
        this.statBlock.put("Dexterity", new Attribute(stats[1]));
        this.statBlock.put("Constitution", new Attribute(stats[2]));
        this.statBlock.put("Intelligence", new Attribute(stats[3]));
        this.statBlock.put("Wisdom", new Attribute(stats[4]));
        this.statBlock.put("Charisma", new Attribute(stats[5]));
    }

    public PlayerCharacter() { }

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

    public boolean perform(Action action) {
        // check the action list for the chosen action
        Action chosenAction;
        boolean result = false;
        for(Map.Entry<String,Action> playerAction : this.actionList.entrySet()) {
            if (playerAction.getValue().equals(action)) {
                // do action
                utils.Text.log("executing");
                action.execute();
                result = true;
            }
        }
        // return success/failure
        return result;
    }

    public boolean perform(Action action, Player target) {
        return false;
    }

    public boolean perform(Action action, Player target, int modifier) {
        return false;
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
    public void setName(String name) {
        this.name = name;
    }
}
