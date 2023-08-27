package starwars.entities.actors.behaviors;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWWorld;

public class MonMothmaBehaviour extends BehaviourInterface {
	
	private MessageRenderer messageRenderer;
	
	/**
     * MonMothma is in <code>YavinIV</code>.
     * MonMothma will say "What are you doing here, farmboy? Bring us General Organa and the plans!" when <code>Player</code> arrves without <code>PrincessLeia</code> and <code>Artoo</code>
     * 
     * @param the <code>SWActor</code> having this behaviour
     * @param world the <code>SWWorld</code> world to which this <code>SWActor</code> belongs
     * @param m <code>MessageRenderer</code> to display messages.
     * 
     */
	public MonMothmaBehaviour(SWActor actor, SWWorld world, MessageRenderer m) {
		super(actor, world);
		this.messageRenderer = m;
	}
	
	@Override
	public boolean ExecuteBehaviour() {
		actor.say(String.format("%s says: What are you doing here, farmboy? Bring us General Organa and the plans!", actor.getShortDescription()));
		return true;
	}
}
