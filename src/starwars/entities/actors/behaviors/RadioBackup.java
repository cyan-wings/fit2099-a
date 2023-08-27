package starwars.entities.actors.behaviors;

import starwars.SWActor;
import starwars.SWWorld;
import starwars.entities.actors.Stormtrooper;

public class RadioBackup extends BehaviourInterface{

	/**
     * This is <code>Stormtrooper</code>s' behaviour when encountering a <code>SWActor</code>.
     * There is a 5% chance they will call another Stormtrooper at the same location they are at.
     * 
     * @param the <code>SWActor</code> having this behaviour
     * @param world the <code>SWWorld</code> world to which this <code>SWActor</code> belongs
     * @param m <code>MessageRenderer</code> to display messages.
     * 
     */
	public RadioBackup(SWActor actor, SWWorld world) {
		super(actor, world);
	}

	@Override
	public boolean ExecuteBehaviour() {
		if (actor.isDead()) {
			return false;
		}
		if (Math.random() > 0.95 && actor instanceof Stormtrooper) {
			
			world.stormtrooperBackup((Stormtrooper) actor);
			return true;
		}
		return false;
	}
	

}
