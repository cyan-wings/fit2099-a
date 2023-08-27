package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActionInterface;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.entities.actors.DarthVader;

public class ForceChoke extends SWAffordance implements SWActionInterface {
	
	
	/**
     * ForceChoke action reduces <code>SWActor</code> hitpoints by 50
     * 
     * @param the <code>SWEntityInterface</code> target
     * @param m <code>MessageRenderer</code> to display messages.
     * 
     */
	public ForceChoke(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);
		priority = 1;
	    }
	
	@Override
	public String getDescription() {
		return "force choke " + this.target.getShortDescription();
    	}
	
	 @Override
	 public boolean canDo(SWActor actor) {
		return !(getTarget().isDead()) && actor instanceof DarthVader;
	    }

	 @Override
	 public void act(SWActor choker) {
		SWEntityInterface target = getTarget();
		
		choker.say(choker.getShortDescription() + " is force choking " + target.getShortDescription() + "!");
		
		target.takeDamage(50);
		choker.takeDamage(0); //actor uses no energy	
		
		if (choker.isDead()) { //the actor who use force choke is dead after the action
		    choker.setLongDescription(choker.getLongDescription() + ", that died of exhaustion while attacking someone");
		    choker.removeAffordance(ForceChoke.class); //remove the force choke affordance of the dead actor so it can no longer be attacked
		}
		
		if (target.isDead()) {
		    target.removeAffordance(ForceChoke.class); //remove the force choke affordance of the dead actor so it can no longer be attacked
		    if (target instanceof SWActor)
			target.setLongDescription(target.getLongDescription() + ", that was force choked in a fight");
		    else
			target.setLongDescription(target.getLongDescription() + ", that was destroyed in a fight");
		}
	 }
}
