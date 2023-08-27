/**
 * 
 */
package starwars;

import edu.monash.fit2099.simulator.matter.EntityManager;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.space.Location;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.entities.actors.Player;

/**
 * @author vnat5
 *
 */
public class YavinIV extends SWWorld {

	protected YavinIVGrid myGrid;
	
	public YavinIVGrid getGrid() {
		return this.myGrid;
	}
	
	/**
     * The entity manager of the world which keeps track of
     * <code>SWEntities</code> and their <code>SWLocation</code>s
     */
    private static final EntityManager<SWEntityInterface, SWLocation> entityManager = new EntityManager<SWEntityInterface, SWLocation>();

    public static EntityManager<SWEntityInterface, SWLocation> getEntitymanager() {
		return entityManager;    
	}
  public EntityManager<SWEntityInterface, SWLocation> getEntityManager() {
		return YavinIV.getEntitymanager();
	}
	/**
	 * 
	 */
	public YavinIV() {
		// TODO Auto-generated constructor stub
		SWLocation.SWLocationMaker factory = SWLocation.getMaker();
		myGrid = new YavinIVGrid(factory);
		space = myGrid;
	}

	public void initializeWorld(MessageRenderer iface) {
		messageRenderer = iface;
		this.isInitialized = true;
	}
	/* (non-Javadoc)
	 * @see edu.monash.fit2099.simulator.space.World#getEntityManager()
	 */
	public void removePlayer(Player player) {
		getEntityManager().remove(player);
	}
	
	public void insertPlayer(Player player) {
		//int newCol = (int) (Math.random()*myGrid.getWidth());
		//int newRow = (int) (Math.random()*myGrid.getHeight());
		player.changeWorld(this);
		
		getEntityManager().setLocation(player, myGrid.getLocationByCoordinates(0, 1));
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
