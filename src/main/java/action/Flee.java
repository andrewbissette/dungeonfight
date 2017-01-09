package action;

import player.Player;

/**
 * Created by andrewbissette on 09/01/2017.
 *
 * For running from combat
 *
 */
public class Flee implements Action {
    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public boolean execute(Player target, int modifier) {
        // probably not necessary
        boolean result = this.execute();
        return result;
    }
}
