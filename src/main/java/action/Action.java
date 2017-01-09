package action;

/**
 * Created by andrewbissette on 08/01/2017.
 *
 * an interface for all actions
 *
 * initially attack, defend, flee
 *
 * actions should return true/false reporting success of execution
 * i.e. did the action succeed, not simply did the method run
 * todo result object to return more information about action resolution for strategies
 * todo figure out how to handle encounter information
 *
 */

import player.Player;
import player.Attribute;

public interface Action {

    boolean execute();

    // more information is needed by some actions particularly attacks
    // where this is redundant the implementation can ignore it
    boolean execute(Player target);

}
