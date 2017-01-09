package action.attack;

/**
 * Created by andrewbissette on 09/01/2017.
 *
 * A simple type for all attacks
 * Later this may need to be an interface
 *
 * Attacks consist of the attack roll and damage roll and are passed to targets to resolve
 *
 */
public class Attack {

    // attacks have an attack and damage roll
    private int attackRoll;
    private int damageRoll;

    // simple constructor
    public Attack(int attackRoll, int damageRoll) {
        this.attackRoll = attackRoll;
        this.damageRoll = damageRoll;
    }

}
