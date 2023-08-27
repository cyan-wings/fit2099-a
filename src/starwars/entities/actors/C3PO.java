package starwars.entities.actors;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWWorld;
import starwars.Team;

/**
 * Class representing protocol droid C3PO. He usually just says useless things.
 * 
 * @author visvamba
 *
 */
public class C3PO extends Droid {

/**
 * Constructor which automatically configures <code>Team</code>, hitpoints, and model name.
 * 
 * @param m
 * @param world
 */
	public C3PO(MessageRenderer m, SWWorld world) {
		super(Team.GOOD, 200, m, world, "C3PO");
		this.droidOwner = null;
		
		this.setSymbol("C");
		this.setShortDescription("C3PO");
		this.setLongDescription("Protocol droid and chronic pessimist");
	}
	
	public void act() {
		super.act();
		
		// Setting up Threepio's speech
		double randVal = Math.random();
		if (randVal<=0.1) {
			double newRand = Math.random();
			if (newRand <0.33) {
				say(this.shortDescription+": We are doomed!");
			} else if (newRand < 0.66) {
				say(this.shortDescription+": Thank the maker!");
				
			} else {
				say(this.shortDescription+": I've got a bad feeling about this");
			}
			
		}
	}
}
