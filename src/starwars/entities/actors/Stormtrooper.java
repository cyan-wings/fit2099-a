package starwars.entities.actors;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.Team;
import starwars.entities.Blaster;
import starwars.entities.actors.behaviors.*;

public class Stormtrooper extends SWOrganicActor {
	
	 /**
     * Stormtroopers are from team evil. Stormtroopers randomly moves around (on any given turn, there is a 50% probability
     * that he will move) and they will execute <code>AttackNeighboursBehaviourStormtrooper</code> when they encounter any actor. 
     * Stormtroopers have a <code>Blaster</code>.
     * Stormtroopers also has the <code>RadioBackup</code> behaviour.
     * 
     * @param hitpoints the number of hit points of Stormtrooper. If this decreases to below zero, the Stormtrooper will die.
     * @param m <code>MessageRenderer</code> to display messages.
     * @param world the <code>SWWorld</code> world to which <code>DarthVader</code> belongs
     * 
     */
	public Stormtrooper (int hitpoints, MessageRenderer m, SWWorld world) {
		super(Team.EVIL, hitpoints, m, world);
		this.setSymbol("S");
		
		behaviours.add(new PickUpItem(this, world, Blaster.class));
		behaviours.add(new AttackNeighboursBehaviourStormtrooper(this, world, m));
		behaviours.add(new WanderBehaviour(this, world));
		behaviours.add(new RadioBackup(this, world));
	 }
	
	 @Override
	 public void executeBehaviours() {
		 
		 super.executeBehaviours();
		 say(describeLocation());
	 }
	
	 @Override
	 public String getShortDescription() {
		return " a stormtrooper";
	 }

	 @Override
	 public String getLongDescription() {
		return this.getShortDescription();
	 }
	 
	 private String describeLocation() {
		SWLocation location = this.world.getEntityManager().whereIs(this);
		return this.getShortDescription() + " [" + this.getHitPoints() + "] is at " + location.getShortDescription();
	 }
}
