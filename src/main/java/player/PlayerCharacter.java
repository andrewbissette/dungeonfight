package player;

import action.Action;
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

    // PCs have core attributes
    Map<String, Attribute> statBlock = new HashMap<String,Attribute>();

    // PCs have health
    int currentHP;
    int maxHP;

    // PCs have armour
    int armourClass;

    // PCs have a list of actions
    Map<String, Action> actionList = new HashMap<String,Action>();

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

    public boolean perform(Action action) {
        return false;
    }

    public boolean perform(Action action, Player target) {
        return false;
    }

}
