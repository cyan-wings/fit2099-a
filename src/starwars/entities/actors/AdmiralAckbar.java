package starwars.entities.actors;

import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.MindControl;
import starwars.entities.actors.behaviors.AdmiralAckbarBehaviour;

public class AdmiralAckbar extends SWOrganicActor{
	
	private static AdmiralAckbar ackbar = null;
	
	/**
     * <code>AdmiralAckbara</code> is from Team Good. He doesn't move and initialy on <code>YavinIV</code>.
     * <code>AdmiralAckbar</code> will perform <code>AdmiralAckbarBehaviour</code>
     * 
     * @param m <code>MessageRenderer</code> to display messages.
     * @param world the <code>SWWorld</code> world to which <code>AdmiralAckba</code> belongs
     * 
     */
	public AdmiralAckbar(MessageRenderer m, SWWorld world) {
		super(Team.GOOD, 100, m, world);
		this.setSymbol("A");
		this.setShortDescription("Admiral Ackbar");
		this.setLongDescription(this.getShortDescription());

		this.removeAffordance(MindControl.class);
		behaviours.add(new AdmiralAckbarBehaviour(this, world, m));
	}
	
	public static AdmiralAckbar getAdmiralAckbar(MessageRenderer m, SWWorld world, Direction[] moves) {

		if (ackbar == null) {
		    ackbar = new AdmiralAckbar(m, world);
		}
		return ackbar;
	    }

}
