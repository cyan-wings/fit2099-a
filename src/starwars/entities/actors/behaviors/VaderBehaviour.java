package starwars.entities.actors.behaviors;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWActor;
import starwars.SWEntityInterface;
import starwars.SWWorld;
import starwars.actions.Attack;
import starwars.actions.ForceChoke;
import starwars.entities.actors.DarthVader;
import starwars.entities.actors.Player;
import starwars.entities.actors.Stormtrooper;

public class VaderBehaviour extends BehaviourInterface{
	
	private MessageRenderer messageRenderer;
	
	/**
     * This is <code>DarthVader</code>'s behaviour when encountering a <code>SWActor</code>.
     * There is a 50% chance he will <code>ForceChoke</code> anyone.
     * If he encounters Luke, he will try turning Luke to the dark side. Luke has 75% chance to resist it if he is trained.
     * If actions fails, he will <code>Attack</code> Luke instead.
     * If he doesn't ForceChoke the <code>SWActor</code>, he will attack them with his <code>LightSabre</code>. This excludes <code>Stormtroopers</code> which are on the same team
     * 
     * @param the <code>SWActor</code> having this behaviour
     * @param world the <code>SWWorld</code> world to which this <code>SWActor</code> belongs
     * @param m <code>MessageRenderer</code> to display messages.
     * 
     */
	public VaderBehaviour(SWActor actor, SWWorld world, MessageRenderer m) {
		super(actor, world);
		this.messageRenderer = m;
	}
	
	@Override
    public boolean ExecuteBehaviour() {
		for (SWEntityInterface entity : getLocalEntites()) {
			if (!(entity instanceof DarthVader) && (entity instanceof SWActor) && entity.isDead() == false) {
				if (Math.random() > 0.5) {
					actor.schedule(new ForceChoke(entity, messageRenderer));
					actor.say(String.format("%s says, feel the power of the dark side.", actor.getShortDescription()));
					entity.say(entity.getShortDescription() + " shouts ARGGHHH!!");
						
					if (entity instanceof Player) {
						actor.say(String.format("%s says, come to the dark side.", actor.getShortDescription()));
						if (Math.random() > 0.5) {
							if (entity.hasCapability(Capability.RESIST_DARK_SIDE)) {
								if (Math.random() > 0.75) {
									((Player)entity).turnToDarkSide();
								}
							}
							else {
								((Player)entity).turnToDarkSide();
							}
						}
						else {
							actor.schedule(new Attack(entity, messageRenderer));
							actor.say(String.format("%s says, you are weak Skywalker.", actor.getShortDescription()));
						}
					}
					
					return true;
				}
				else {
					if (!(entity instanceof Stormtrooper)) {
						actor.schedule(new Attack(entity, messageRenderer));
						actor.say("Bzzz!");
					}
				}
			}
		}
		return false;
    }

}
