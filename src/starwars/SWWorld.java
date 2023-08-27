package starwars;

import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.gridworld.Grid.CompassBearing;
import edu.monash.fit2099.simulator.matter.EntityManager;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.space.Location;
import edu.monash.fit2099.simulator.space.World;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.entities.*;
import starwars.entities.actors.*;

/**
 * Class representing a world in the Star Wars universe.
 * 
 * @author ram
 */
/*
 * Change log 2017-02-02: Render method was removed from Middle Earth Displaying
 * the Grid is now handled by the TextInterface rather than by the Grid or
 * MiddleWorld classes (asel)
 */
public class SWWorld extends World {

	/**
	 * <code>SWGrid</code> of this <code>SWWorld</code>
	 */
	private SWGrid myGrid;
	
	// So we don't initialize twice
	protected boolean isInitialized;
	public boolean isIntialized() {
		return isInitialized;
	}

	protected MessageRenderer messageRenderer;

	/**
	 * The entity manager of the world which keeps track of
	 * <code>SWEntities</code> and their <code>SWLocation</code>s
	 */
	private static final EntityManager<SWEntityInterface, SWLocation> entityManager = new EntityManager<SWEntityInterface, SWLocation>();

	/**
	 * Constructor of <code>SWWorld</code>. This will initialize the
	 * <code>SWLocationMaker</code> and the grid.
	 */
	public SWWorld() {
		SWLocation.SWLocationMaker factory = SWLocation.getMaker();
		myGrid = new SWGrid(factory);
		space = myGrid;

	}

	/**
	 * Returns the height of the <code>Grid</code>. Useful to the Views when
	 * rendering the map.
	 * 
	 * @author ram
	 * @return the height of the grid
	 */
	public int height() {
		return space.getHeight();
	}

	/**
	 * Returns the width of the <code>Grid</code>. Useful to the Views when
	 * rendering the map.
	 * 
	 * @author ram
	 * @return the height of the grid
	 */
	public int width() {
		return space.getWidth();
	}

	public MessageRenderer getMessageRenderer() {
		return messageRenderer;
	}

	/**
	 * Set up the world, setting descriptions for locations and placing items
	 * and actors on the grid.
	 * 
	 * @author ram
	 * @param iface
	 *            a MessageRenderer to be passed onto newly-created entities
	 */
	public void initializeWorld(MessageRenderer iface) {
		messageRenderer = iface;
		SWLocation loc;
		// Set default location string
		for (int row = 0; row < height(); row++) {
			for (int col = 0; col < width(); col++) {
				loc = myGrid.getLocationByCoordinates(col, row);
				loc.setLongDescription("SWWorld (" + col + ", " + row + ")");
				loc.setShortDescription("SWWorld (" + col + ", " + row + ")");
				loc.setSymbol('.');
			}
		}

		// BadLands
		for (int row = 5; row < 8; row++) {
			for (int col = 4; col < 7; col++) {
				loc = myGrid.getLocationByCoordinates(col, row);
				loc.setLongDescription("Badlands (" + col + ", " + row + ")");
				loc.setShortDescription("Badlands (" + col + ", " + row + ")");
				loc.setSymbol('b');
			}
		}

		// Ben's Hut
		loc = myGrid.getLocationByCoordinates(5, 6);
		loc.setLongDescription("Ben's Hut");
		loc.setShortDescription("Ben's Hut");
		loc.setSymbol('H');

		// Old Ben
		Direction[] patrolmoves = { CompassBearing.EAST, CompassBearing.EAST, CompassBearing.SOUTH, CompassBearing.WEST,
				CompassBearing.WEST, CompassBearing.SOUTH, CompassBearing.EAST, CompassBearing.EAST,
				CompassBearing.NORTHWEST, CompassBearing.NORTHWEST };
		loc = myGrid.getLocationByCoordinates(4, 5);
		entityManager.setLocation(BenKenobi.getBenKenobi(iface, this, patrolmoves), loc);
		entityManager.setLocation(BenKenobi.getBenKenobi(iface, this, patrolmoves),
				myGrid.getLocationByCoordinates(4, 6));
		entityManager.setLocation(BenKenobi.getBenKenobi(iface, this, patrolmoves),
				myGrid.getLocationByCoordinates(5, 6));
		// R2D2
		Direction[] r2d2Patrol = { CompassBearing.EAST, CompassBearing.EAST, CompassBearing.EAST, CompassBearing.EAST,
				CompassBearing.EAST, CompassBearing.WEST, CompassBearing.WEST, CompassBearing.WEST, CompassBearing.WEST,
				CompassBearing.WEST };
		loc = myGrid.getLocationByCoordinates(3, 7);
		Artoo r2d2 = new Artoo(200, r2d2Patrol, iface, this);
		entityManager.setLocation(r2d2, loc);

		loc = myGrid.getLocationByCoordinates(5, 7);
		SWDroidActor red = new SWDroidActor(Team.GOOD, 200, iface, this);
		red.setShortDescription("Red");
		red.setLongDescription("Red, the R2 unit with a bad motivator. I know how he feels.");
		red.setSymbol("r");
		red.takeDamage(200);
		entityManager.setLocation(red, loc);

		loc = myGrid.getLocationByCoordinates(6, 7);
		Threepio c3po = new Threepio(200, iface, this);
		c3po.takeDamage(200);
		entityManager.setLocation(c3po, loc);

		// Luke
		loc = myGrid.getLocationByCoordinates(5, 9);

	

		Player luke = new Player(Team.GOOD, 100, iface, this);

		luke.setShortDescription("Luke");
		entityManager.setLocation(luke, loc);
		luke.resetMoveCommands(loc);

		// Beggar's Canyon
		for (int col = 3; col < 8; col++) {
			loc = myGrid.getLocationByCoordinates(col, 8);
			loc.setShortDescription("Beggar's Canyon (" + col + ", " + 8 + ")");
			loc.setLongDescription("Beggar's Canyon  (" + col + ", " + 8 + ")");
			loc.setSymbol('C');
			loc.setEmptySymbol('='); // to represent sides of the canyon
		}

		// Moisture Farms
		for (int row = 0; row < 10; row++) {
			for (int col = 8; col < 10; col++) {
				loc = myGrid.getLocationByCoordinates(col, row);
				loc.setLongDescription("Moisture Farm (" + col + ", " + row + ")");
				loc.setShortDescription("Moisture Farm (" + col + ", " + row + ")");
				loc.setSymbol('F');

				// moisture farms have reservoirs
				entityManager.setLocation(new Reservoir(iface), loc);
			}
		}

		// Scatter some other entities and actors around

		// a empty canteen
		loc = myGrid.getLocationByCoordinates(3, 1);
		entityManager.setLocation(new Canteen(iface, 10, 0), loc);

		// a full canteen
		loc = myGrid.getLocationByCoordinates(6, 6);
		entityManager.setLocation(new Canteen(iface, 10, 10), loc);

		// an oil can
		loc = myGrid.getLocationByCoordinates(1, 5);
		entityManager.setLocation(new OilCan(iface), loc);

		// a lightsaber
		loc = myGrid.getLocationByCoordinates(5, 5);
		entityManager.setLocation(new LightSaber(iface), loc);

		// A blaster
		loc = myGrid.getLocationByCoordinates(3, 4);
		entityManager.setLocation(new Blaster(iface), loc);

		// Tuskens
		TuskenRaider raider = new TuskenRaider(100, "Bilbo", iface, this);
		loc = myGrid.getLocationByCoordinates(4, 3);
		entityManager.setLocation(raider, loc);
		entityManager.setLocation(new GaffiStick(iface), myGrid.getLocationByCoordinates(4, 3));
		entityManager.setLocation(new TuskenRaider(100, "Urrorr'ukr'arhr", iface, this),
				myGrid.getLocationByCoordinates(2, 3));
		entityManager.setLocation(new TuskenRaider(100, "Rgur'tlr'rauk", iface, this),
				myGrid.getLocationByCoordinates(2, 5));
		entityManager.setLocation(new TuskenRaider(100, "Gkrror'ukurr", iface, this),
				myGrid.getLocationByCoordinates(3, 3));

		// Aunt Beru
		SWActor beru = new VanillaActor(Team.GOOD, 50, "Aunt Beru", "b", iface, this);
		entityManager.setLocation(beru, myGrid.getLocationByCoordinates(8, 8));

		// Uncle Owen
		SWActor owen = new VanillaActor(Team.GOOD, 50, "Uncle Owen", "o", iface, this);
		entityManager.setLocation(owen, myGrid.getLocationByCoordinates(8, 8));
		
		// Millenium Falcon
		MilleniumFalcon falcon = new MilleniumFalcon(iface);
		entityManager.setLocation(falcon, myGrid.getLocationByCoordinates(0, 4));
		
		this.isInitialized = true;
		
	}

	/**
	 * Determine whether a given <code>SWActor a</code> can move in a given
	 * direction <code>whichDirection</code>.
	 * 
	 * @author ram
	 * @param a
	 *            the <code>SWActor</code> being queried.
	 * @param whichDirection
	 *            the <code>Direction</code> if which they want to move
	 * @return true if the actor can see an exit in <code>whichDirection</code>,
	 *         false otherwise.
	 */
	public boolean canMove(SWActor a, Direction whichDirection) {
		SWLocation where = (SWLocation) entityManager.whereIs(a); // requires a
																	// cast for
																	// no reason
																	// I can
																	// discern.
																	// Probably
																	// type
																	// erasure.
		return where.hasExit(whichDirection);
	}

	/**
	 * Accessor for the grid.
	 * 
	 * @author ram
	 * @return the grid
	 */
	public Grid<SWLocation> getGrid() {
		return myGrid;
	}

	/**
	 * Move an actor in a direction.
	 * 
	 * @author ram
	 * @param a
	 *            the actor to move
	 * @param whichDirection
	 *            the direction in which to move the actor
	 */
	public void moveEntity(SWActor a, Direction whichDirection) {

		// get the neighboring location in whichDirection
		Location loc = entityManager.whereIs(a).getNeighbour(whichDirection);

		// Base class unavoidably stores superclass references, so do a checked
		// downcast here
		if (loc instanceof SWLocation)
			// perform the move action by setting the new location to the the
			// neighboring location
			entityManager.setLocation(a, (SWLocation) entityManager.whereIs(a).getNeighbour(whichDirection));
	}

	/**
	 * Returns the <code>Location</code> of a <code>SWEntity</code> in this
	 * grid, null if not found. Wrapper for
	 * <code>entityManager.whereIs()</code>.
	 * 
	 * @author ram
	 * @param e
	 *            the entity to find
	 * @return the <code>Location</code> of that entity, or null if it's not in
	 *         this grid
	 */
	public Location find(SWEntityInterface e) {
		return entityManager.whereIs(e); // cast and return a SWLocation?
	}

	/**
	 * This is only here for compliance with the abstract base class's interface
	 * and is not supposed to be called.
	 */

	@SuppressWarnings("unchecked")
	public EntityManager<SWEntityInterface, SWLocation> getEntityManager() {
		return SWWorld.getEntitymanager();
	}

	/**
	 * Returns the <code>EntityManager</code> which keeps track of the
	 * <code>SWEntities</code> and <code>SWLocations</code> in
	 * <code>SWWorld</code>.
	 * 
	 * @return the <code>EntityManager</code> of this <code>SWWorld</code>
	 * @see {@link #entityManager}
	 */
	public static EntityManager<SWEntityInterface, SWLocation> getEntitymanager() {
		if (SWUniverse.getUniverse().getCurrentWorld() instanceof DeathStar) {
			return DeathStar.getEntitymanager();
		}
		return entityManager;
	}
	
	public void removePlayer(Player player) {
		entityManager.remove(player);
	}
	
	public void insertPlayer(Player player) {
		int newCol = (int) (Math.random()*myGrid.getWidth());
		int newRow = (int) (Math.random()*myGrid.getHeight());
		entityManager.setLocation(player, myGrid.getLocationByCoordinates(newCol, newRow));
	}
	
	public void stormtrooperBackup(Stormtrooper trooper) {
		SWLocation loc = getEntityManager().whereIs(trooper);
		Stormtrooper newTrooper = new Stormtrooper(100, messageRenderer, this);
		getEntitymanager().setLocation(newTrooper, loc);
	}
}
