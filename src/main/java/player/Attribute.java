package player;

/**
 * Created by andrewbissette on 08/01/2017.
 *
 * Details player attributes
 * Each attribute has a number and a modifier
 * An attribute can be constructed from a flat number to give the modifier directly
 * Later this will also handle saving throws
 * todo handle saving throws
 */

public class Attribute {

    private int value;
    private int modifier;

    public Attribute() {};

    public Attribute(int attributeValue) {
        this(attributeValue, null);

    }

    public Attribute (int attributeValue, Integer attributeModifier) {
        this.value = attributeValue;
        if (attributeModifier == null) {
            this.modifier = calculateModifier(attributeValue);
        } else {
            this.modifier = attributeModifier;
        }
    }

    private int calculateModifier(int score) {
        int modifier;
        if (score >= 10) {
            modifier = (score - 10) / 2;
        } else {
            float temp = (float) score;
            modifier = (int) Math.floor((temp - 10) / 2);
        }
        return modifier;
    }

    public int getModifier() {
        return modifier;
    }

    public int getValue() {
        return value;
    }


}
