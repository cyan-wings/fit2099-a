package starwars.entities.actors.behaviors;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWEntityInterface;
import starwars.SWWorld;
import starwars.actions.Attack;
import starwars.entities.actors.DarthVader;
import starwars.entities.actors.Stormtrooper;

public class AttackNeighboursBehaviourStormtrooper extends BehaviourInterface{
	
    private MessageRenderer messageRenderer;

    /**
     * This is <code>Stromtrooper</code>s' behaviour when encountering a <code>SWActor</code>.
     * There is a 25% chance they will <code>Attack</code> anyone seccessfully with the <code>Blaster</code> they are carrying.
     * Else, they would shoot wildly.
     *
     * @param the <code>SWActor</code> having this behaviour
     * @param world the <code>SWWorld</code> world to which this <code>SWActor</code> belongs
     * @param m <code>MessageRenderer</code> to display messages.
     * 
     */
    public AttackNeighboursBehaviourStormtrooper(SWActor attacker, SWWorld world, MessageRenderer m) {

	super(attacker, world);
	this.messageRenderer = m;
    }

    @Override
    public boolean ExecuteBehaviour() {
    	for (SWEntityInterface entity : getLocalEntites()) {
			if (!(entity instanceof Stormtrooper) && !(entity instanceof DarthVader) && (entity instanceof SWActor) && entity.isDead() == false) {
				if (Math.random() > 0.75) {
					this.actor.schedule(new Attack(entity, messageRenderer));
					this.actor.say("PEW PEW");
					return true;
				}
				else {
					this.actor.say("Stormtrooper shoots wildly!");
					return false;
				}
			}
		}
		return false;
	}
}
