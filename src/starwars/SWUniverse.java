/**
 * 
 */
package starwars;

import edu.monash.fit2099.simulator.time.Scheduler;
import starwars.entities.actors.Player;
import starwars.swinterfaces.SWGridController;

/**
 * @author visvamba
 *
 */
public class SWUniverse {

	private static SWUniverse universe = null;
	private static SWWorld tatooine = new SWWorld();
	private static YavinIV yavinIV = new YavinIV();
	private static DeathStar deathStar = new DeathStar();
	private SWWorld currentWorld = tatooine;
	
	protected SWGridController uiController;
	protected Scheduler scheduler;
	private SWUniverse() {
				
	}
	
	// There is only one universe (screw Marvel)
	public static SWUniverse getUniverse() {
		if (universe == null) {
			universe = new SWUniverse();
			
		}
		return universe;
	}
	public Scheduler getScheduler() {
		return scheduler;
	}
	public SWGridController getUIController() {
		return uiController;
	}
	// Initialise tatooine
	public void start() {
		uiController = new SWGridController(tatooine);
		scheduler = new Scheduler(1, tatooine);
		SWActor.setScheduler(scheduler);
		tatooine.initializeWorld(uiController);
	}
	
	public void goToYavin() {
		
		uiController.setWorld(yavinIV);
		scheduler.changeWorld(yavinIV);
		currentWorld = yavinIV;
		if (!yavinIV.isIntialized()) {
			yavinIV.initializeWorld(uiController);
		}
	}
	public void goToYavin(Player player) {
		this.goToYavin();
		player.world.removePlayer(player);
		yavinIV.insertPlayer(player);
	}
	public void goToDeathStar() {
		
		uiController.setWorld(deathStar);
		scheduler.changeWorld(deathStar);
		currentWorld = deathStar;
		if (!deathStar.isIntialized()) {
			deathStar.initializeWorld(uiController);
		}
		
		
	}
	//@SuppressWarnings("static-access")
	public void goToDeathStar(Player player) {
		this.goToDeathStar();
		player.world.removePlayer(player);
		deathStar.insertPlayer(player);
		
		
	}
	public SWWorld getCurrentWorld() {
		return currentWorld;
	}
	
	
	
	public void goToTatooine() {
		scheduler.changeWorld(tatooine);
	}
}
