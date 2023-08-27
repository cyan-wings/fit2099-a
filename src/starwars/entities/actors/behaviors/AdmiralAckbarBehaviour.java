package starwars.entities.actors.behaviors;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWWorld;

public class AdmiralAckbarBehaviour extends BehaviourInterface {
	
	private MessageRenderer messageRenderer;
	
	/**
     * AdmiralAckbar is in <code>YavinIV</code>.
     * AdmiralAckbar will say "It’s a trap!" when <code>Player</code> arrves without <code>PrincessLeia</code> and <code>Artoo</code>
     * 
     * @param the <code>SWActor</code> having this behaviour
     * @param world the <code>SWWorld</code> world to which this <code>SWActor</code> belongs
     * @param m <code>MessageRenderer</code> to display messages.
     * 
     */
	public AdmiralAckbarBehaviour(SWActor actor, SWWorld world, MessageRenderer m) {
		super(actor, world);
		this.messageRenderer = m;
	}
	
	@Override
	public boolean ExecuteBehaviour() {
		if (Math.random() > 0.9) {
			actor.say(String.format("%s says: It’s a trap!", actor.getShortDescription()));
			return true;
		}
		else {
			return false;
		}
	}
}
