/**
 * 
 */
package starwars;

import edu.monash.fit2099.simulator.matter.EntityManager;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.space.Location;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.entities.actors.DarthVader;
import starwars.entities.actors.Player;
import starwars.entities.actors.Stormtrooper;

/**
 * @author visvamba
 *
 */
public class DeathStar extends SWWorld {

	private DeathStarGrid myGrid;
	
	public DeathStarGrid getGrid() {
		return this.myGrid;
	}

	
	/**
     * The entity manager of the world which keeps track of
     * <code>SWEntities</code> and their <code>SWLocation</code>s
     */
    private static final EntityManager<SWEntityInterface, SWLocation> entityManager = new EntityManager<SWEntityInterface, SWLocation>();

	
	/**
	 * 
	 */
	public DeathStar() {
		SWLocation.SWLocationMaker factory = SWLocation.getMaker();
		this.myGrid = new DeathStarGrid(factory);
		this.space = this.myGrid;
	}

	/* (non-Javadoc)
	 * @see edu.monash.fit2099.simulator.space.World#getEntityManager()
	 */
	
	
	public static EntityManager<SWEntityInterface, SWLocation> getEntitymanager() {
		return entityManager;    
	}
  public EntityManager<SWEntityInterface, SWLocation> getEntityManager() {
		return DeathStar.getEntitymanager();
	}
	@Override

	public void initializeWorld(MessageRenderer iface) {
		messageRenderer = iface;
		SWLocation loc;
		EntityManager<SWEntityInterface, SWLocation> entityManager = this.getEntityManager();
		// Set default location string
		for (int row = 0; row < height(); row++) {
		    for (int col = 0; col < width(); col++) {
			loc = myGrid.getLocationByCoordinates(col, row);
			loc.setLongDescription("DeathStar (" + col + ", " + row + ")");
			loc.setShortDescription("DeathStar (" + col + ", " + row + ")");
			loc.setSymbol('.');
		    }
		}
		
		//Vader
		loc = myGrid.getLocationByCoordinates(9, 4);
		DarthVader darthVader = new DarthVader(10000,messageRenderer,this);
		entityManager.setLocation(darthVader, loc);
		
		//Falcon
		loc = myGrid.getLocationByCoordinates(0, 4);
		loc.setLongDescription("Millenium Falcon (0, 4)");
		loc.setShortDescription("Millenium Falcon (0, 4)");
		loc.setSymbol('F');
		
		// Stormtroopers
		for (int i = 0; i<5; i++) {
			loc = myGrid.getLocationByCoordinates(4, i);
			Stormtrooper newTrooper = new Stormtrooper(100, messageRenderer, this);
			entityManager.setLocation(newTrooper, loc);
		}
		
		this.isInitialized = true;
	}
	
	public void removePlayer(Player player) {
		getEntityManager().remove(player);
	}
	
	public void insertPlayer(Player player) {
		//int newCol = (int) (Math.random()*myGrid.getWidth());
		//int newRow = (int) (Math.random()*myGrid.getHeight());
		player.changeWorld(this);
		
		getEntityManager().setLocation(player, myGrid.getLocationByCoordinates(4, 3));
	}
	
	public boolean canMove(SWActor a, Direction whichDirection) {
		SWLocation where = (SWLocation) getEntityManager().whereIs(a); // requires a
																	// cast for
																	// no reason
																	// I can
																	// discern.
																	// Probably
																	// type
																	// erasure.
		return where.hasExit(whichDirection);
	}
	public void moveEntity(SWActor a, Direction whichDirection) {

		// get the neighboring location in whichDirection
		Location loc = getEntityManager().whereIs(a).getNeighbour(whichDirection);

		// Base class unavoidably stores superclass references, so do a checked
		// downcast here
		if (loc instanceof SWLocation)
			// perform the move action by setting the new location to the the
			// neighboring location
			getEntityManager().setLocation(a, (SWLocation) getEntityManager().whereIs(a).getNeighbour(whichDirection));
	}
	
	public Location find(SWEntityInterface e) {
		return getEntityManager().whereIs(e); // cast and return a SWLocation?
	}

}
