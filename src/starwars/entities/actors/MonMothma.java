package starwars.entities.actors;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.MindControl;
import starwars.entities.actors.behaviors.MonMothmaBehaviour;

public class MonMothma extends SWOrganicActor{
	
	private static MonMothma mothma = null;
	
	/**
     * <code>MonMothma</code> is from Team Good. He doesn't move and initialy on <code>YavinIV</code>.
     * <code>MonMothma</code> will perform <code>MonMothmaBehaviour</code>
     * 
     * @param m <code>MessageRenderer</code> to display messages.
     * @param world the <code>SWWorld</code> world to which <code>MonMothma</code> belongs
     * 
     */
	public MonMothma(MessageRenderer m, SWWorld world) {
		super(Team.GOOD, 100, m, world);
		this.setSymbol("M");
		this.setShortDescription("Mon Mothma");
		this.setLongDescription(this.getShortDescription());

		this.removeAffordance(MindControl.class);
		behaviours.add(new MonMothmaBehaviour(this, world, m));
	}
	
	public static MonMothma getMonMothma(MessageRenderer m, SWWorld world) {

		if (mothma == null) {
			mothma = new MonMothma(m, world);
		}
		return mothma;
	    }


}
