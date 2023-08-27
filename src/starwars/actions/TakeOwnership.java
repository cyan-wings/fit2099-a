package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.entities.actors.SWDroidActorInterface;

public class TakeOwnership extends SWAffordance {

    public TakeOwnership(SWEntityInterface theTarget, MessageRenderer m) {
	super(theTarget, m);
    }

    @Override
    public boolean canDo(SWActor actor) {
	assert(target instanceof SWDroidActorInterface);
	SWDroidActorInterface droid = (SWDroidActorInterface)target;
	return !droid.hasOwner();
    }

    @Override
    public void act(SWActor actor) {
	((SWDroidActorInterface)target).setOwner(actor);
	actor.say(String.format("%s owns %s now.", actor.getShortDescription(), target.getShortDescription()));
    }

    @Override
    public String getDescription() {
	return "Take ownership of " + target.getShortDescription();
    }
}
