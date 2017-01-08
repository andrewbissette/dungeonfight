package player;

/**
 * Created by andrewbissette on 08/01/2017.
 *
 * An interface for all players/NPCs
 *
 */

import action.*;

public interface Player {

    // any agent should report an attribute if asked
    int getModifier(String attributeName);

    // any agent should perform an action if asked
    // reports successful action as boolean
    // may optionally have a target
    boolean perform(Action action);
    boolean perform(Action action, Player target);
    // todo search by string?
    // todo strategies

    // any agent should be able to resolve an attack if asked
    // this reports a hit or miss
    //boolean resolve(Attack incomingAttack);
    // todo resolve other actions e.g. spells

}
