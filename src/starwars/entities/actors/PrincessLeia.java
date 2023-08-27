package starwars.entities.actors;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWEntityInterface;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.Attack;
import starwars.actions.MindControl;
import starwars.entities.actors.behaviors.FollowBehaviour;

public class PrincessLeia extends SWOrganicActor{
	
	private static PrincessLeia leia = null;
	
	/**
     * Princess Leia is from Team Good. She doesn't move and initialy on <code>DeathStar</code> since she is a prisoner.
     * <code>MindControl</code> cannot be used on her.
     * She cannot be attacked when she is in prison.
     * She follows Luke when he arrives at her location.
     * As <code>PrincessLeia</code> takes damage, if his hitpoints go below 0, game is loss.
     * 
     * @param m <code>MessageRenderer</code> to display messages.
     * @param world the <code>SWWorld</code> world to which <code>Princess Leia</code> belongs
     * 
     */
	public PrincessLeia(MessageRenderer m, SWWorld world) {
		super(Team.GOOD, 100, m, world);
		this.setSymbol("L");
		this.setShortDescription("Princess Leia");
		this.setLongDescription("The beautiful Princess Leia");
		
		this.removeAffordance(Attack.class);
		this.removeAffordance(MindControl.class);
		//TODO follow affordance 
	}
	
	public static PrincessLeia getPrincessLeia(MessageRenderer m, SWWorld world) {

		if (leia == null) {
		    leia = new PrincessLeia(m, world);
		}
		return leia;
	}
	public void act() {
		SWLocation loc = this.world().getEntityManager().whereIs(this);
		for (SWEntityInterface a : this.world().getEntityManager().contents(loc)) {
			if (a instanceof Player) {
				this.behaviours.add(new FollowBehaviour((SWActor) this, this.world(), (SWActor) a));
			}
		}
	}
	@Override
	public void takeDamage(int damage) {
		super.takeDamage(damage);
		if (this.getHitPoints() <= 0) {
			scheduler.loseGame();
		}
	}

}
