/**
 * 
 */
package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActionInterface;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.SWUniverse;
import starwars.entities.actors.Player;

/**
 * @author visvamba
 *
 */
public class TravelToDeathStar extends SWAffordance {

	public TravelToDeathStar(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);
		
		
	}

	@Override
	public boolean canDo(SWActor actor) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void act(SWActor actor) {
		// TODO Auto-generated method stub
		SWUniverse.getUniverse().goToDeathStar((Player) actor);
		actor.removeAction(this);
		for (SWActionInterface a: actor.getActions()) {
			if (a instanceof TravelToYavin) {
				actor.removeAction(a);
			}
		}
		
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Travel to the Death Star";
	}

}
