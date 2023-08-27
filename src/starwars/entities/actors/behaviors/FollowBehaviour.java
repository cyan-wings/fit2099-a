package starwars.entities.actors.behaviors;

import edu.monash.fit2099.gridworld.Grid;
import starwars.SWActor;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.actions.Move;

public class FollowBehaviour extends BehaviourInterface {

    private SWActor owner;

    public FollowBehaviour(SWActor actor, SWWorld world, SWActor owner) {
	super(actor, world);
	this.owner = owner;
    }

    public boolean hasOwner() {
	return owner != null;
    }

    public void setOwner(SWActor newOwner) {
	owner = newOwner;
    }

    @Override
    public boolean ExecuteBehaviour() {
	if (owner == null)
	    return false;

	//TODO go the full A* 
	SWLocation a = entityManager.whereIs(actor);
	SWLocation b = entityManager.whereIs(owner);
	if (a == b)
	    return true;

	for (Grid.CompassBearing heading : Grid.CompassBearing.values()) {
	    if (a.getNeighbour(heading) == b) {
		Move myMove = new Move(heading, world.getMessageRenderer(), world);
		actor.schedule(myMove);
		return true;
	    }
	}
	return false;
    }
}
