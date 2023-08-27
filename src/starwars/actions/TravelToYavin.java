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
 * @author Asus K401UB
 *
 */
public class TravelToYavin extends SWAffordance {

	/**
	 * @param theTarget
	 * @param m
	 */
	public TravelToYavin(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see starwars.SWActionInterface#canDo(starwars.SWActor)
	 */
	@Override
	public boolean canDo(SWActor actor) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void act(SWActor actor) {
		// TODO Auto-generated method stub
		SWUniverse.getUniverse().goToYavin((Player) actor);
		actor.removeAction(this);
		for (SWActionInterface a: actor.getActions()) {
			if (a instanceof TravelToDeathStar) {
				actor.removeAction(a);
			}
		}
		
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Travel to the Yavin IV (Rebel HQ)";
	}

}
