package starwars.entities.actors;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.Team;
import starwars.entities.LightSaber;
import starwars.entities.actors.behaviors.PickUpItem;
import starwars.entities.actors.behaviors.VaderBehaviour;
//import starwars.entities.actors.behaviors.VaderBehaviour;
import starwars.entities.actors.behaviors.WanderBehaviour;

public class DarthVader extends SWOrganicActor{

    /**
     * Darth Vader is from team evil. Darth Vader randomly moves around (on any given turn, there is a 50% probability
     * that he will move) and Vader will execute <code>VaderBehaviour</code> when he encounters any actor. 
     * Vader has a <code>LightSaber</code>
     * As <code>DarthVader</code> takes damage, if his hitpoints go below 0, game is won.
     * 
     * @param hitpoints the number of hit points of Darth Vader. If this decreases to below zero, the Vader will die.
     * @param m <code>MessageRenderer</code> to display messages.
     * @param world the <code>SWWorld</code> world to which <code>DarthVader</code> belongs
     * 
     */
    public DarthVader(int hitpoints, MessageRenderer m, SWWorld world) {
	super(Team.EVIL, hitpoints, m, world);
	this.setSymbol("V");

	behaviours.add(new PickUpItem(this, world, LightSaber.class));
	behaviours.add(new VaderBehaviour(this, world, m));
	behaviours.add(new WanderBehaviour(this, world));
	
    }

    @Override
    protected void executeBehaviours() {
	say(describeLocation());
	super.executeBehaviours();
    }

    @Override
    public String getShortDescription() {
	return "Darth Vader";
    }

    @Override
    public String getLongDescription() {
	return this.getShortDescription();
    }

    private String describeLocation() {
	SWLocation location = this.world.getEntityManager().whereIs(this);
	return this.getShortDescription() + " [" + this.getHitPoints() + "] is at " + location.getShortDescription();
    }
    
    @Override
	public void takeDamage(int damage) {
		super.takeDamage(damage);
		if (this.getHitPoints() <= 0) {
			scheduler.wonGame();
		}
	}

}
