package action;

import player.Player;

/**
 * Created by andrewbissette on 09/01/2017.
 *
 * An action which increases defense for a round of combat
 *
 */
public class Defend implements Action {

    @Override
    public boolean execute() {
        // todo implement defend action
        return false;
    }

    @Override
    public boolean execute(Player target, int modifier) {
        // not needed
        boolean result = this.execute();
        return result;
    }
}
