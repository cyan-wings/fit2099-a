package starwars.entities;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWEntity;
//import starwars.*;

public class DroidPart extends SWEntity {

	protected DroidPart(MessageRenderer m) {
		super(m);

		this.shortDescription = "Droid part";
		this.longDescription = "This can be used to repair or build droids";
	}

}
