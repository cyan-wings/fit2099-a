/**
 * 
 */
package starwars.entities;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWEntity;

/**
 * <code>MilleniumFalcon</code> acts like a portal to transport <code>Player</code> to different worlds.
 * @author visvamba
 *
 */
public class MilleniumFalcon extends SWEntity {

	/**
	 * @param m
	 */
	public MilleniumFalcon(MessageRenderer m) {
		super(m);
		
		this.shortDescription = "the Millenium Falcon";
		this.longDescription = "the Millenium Falcon: Made the Kessel Run in less than 12 parsecs";
		this.setSymbol("M");
	}

}
