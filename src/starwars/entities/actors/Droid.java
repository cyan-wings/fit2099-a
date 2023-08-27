package starwars.entities.actors;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWWorld;
import starwars.Team;
import starwars.entities.DroidPart;

import java.util.*;

/**
 * Abstract class representing a droid. Basic functionality provided includes checking if the droid is in Badlands.
 * <p>
 * Droids with <=0 hitpoints become immobile but can be healed with OilCan.
 * 
 * @author visvamba
 *
 */

public abstract class Droid extends SWActor {
	
	private HashSet<DroidPart> droidParts;
	protected SWActor droidOwner;
	private String droidModel;
	
	public Droid(Team team, int hitpoints, MessageRenderer m, SWWorld world, String model) {
		super(team, hitpoints, m, world);
		// TODO Auto-generated constructor stub
		
		this.droidModel = model;
		
		
	}

	// To add owner
	public Droid(Team team, int hitpoints, MessageRenderer m, SWWorld world, String model, SWActor owner) {
		this(team, hitpoints, m, world, model);
		this.droidOwner = owner;	
	}
	
	public void act() {
		//
	}
	
	/**
	 * Checks droid's location to see if in Badlands. This would result in a loss of hitpoints.
	 * @return
	 */
	// Checks location to see if droid is in Badlands
	private boolean isInBadlands() {
		//int x_min=4, x_max=7, y_min=5, y_max=8;
		/*
		SWLocation droidLoc = SWWorld.getEntitymanager().whereIs(this);
		//if ()
		for (i=4; i<=7; i++) {
			for (j= 5; j<=8; j++) {
				SWGrid
			}
		}
		*/
		return false;
	}
	
}
