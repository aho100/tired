import info.gridworld.grid.Grid;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Player;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

public class Alien extends Critter {
	public boolean boss;
	public Player player; 
	public int steps;
	public static final Location FIRST_LIVE = Location(2, 13);
	public static final Location SECOND_LIVE = Location(1, 13);

	//Constructs an Alien
    //@param rocks: determines if the Alien can place rocks or not
    public Alien(boolean canPlaceRocks, Player player1) { 
    	setColor(Color.GREEN);
    	boss = canPlaceRocks;
    	player = player1;
    	steps = 0;
    }

    public void act() {
        if (getGrid() == null)
            return;
        ArrayList<Actor> actors = getActors();  //Gets actors that occupy neighboring grid locations
        processActors(actors);
        ArrayList<Location> moveLocs = getMoveLocations();  //Implemented to return the empty neighboring locations.
        Location loc = selectMoveLocation(moveLocs);
        makeMove(loc);
    }

    //Implemented to kill (i.e. remove) the Player and remove a life from the Player if they are killed
    //If the Alien kills the Player, it will be removed from the grid. 
	//@param actors: the actors to be processed
	public void processActors(ArrayList<Actor> actors) {
		Grid<Actor> gr = getGrid();

		for (Actor a : actors) {
            if (a instanceof Player) { 
            	removeSelfFromGrid();
                if (get(FIRST_LIVE) != null)
                	gr.remove(FIRST_LIVE);
                else if (get(SECOND_LIVE) != null)
                	gr.remove(SECOND_LIVE);
                //The Player loses the third time they die and "Game Over" is shown on the middle of the screen.
                gr.put(new Location(5,7), new GameOver());
            }
        }
    }

    //Selects the location for the next move
    /* 
      If the Alien did not eat a Player, there is a 50% chance the Alien moves on its next turn; if
      the Alien moves, it will move one step towards the Player. 
    */
    //@param locs: the possible locations for the next move
    public Location selectMoveLocation(ArrayList<Location> locs) {
        Location playerLoc = locatePlayer();
        int dir = getLocation().getDirectionToward(playerLoc);
        Location nextMove = getLocation().getAdjacentLocation(dir);

        if (!(locs.contains(nextMove)) || (chanceFunc()))
            return getLocation();
        return nextMove;
    }

    //Makes the move
    //If the Alien is a Boss, it can spawn a rock randomly every 6 turns near itself on the grid.
    //@param loc: the location to move to
    public void makeMove(Location loc) {
    	Grid<Actor> gr = getGrid();

        moveTo(loc);
        steps++;

        if ((boss) && (steps % 6 == 0)) {
        	ArrayList<Location> moveLocs = getMoveLocations();
            int r = (int) (Math.random() * moveLocs.size());
            Rock rock = new Rock();
            rock.putSelfInGrid(gr, moveLocs.get(r));
        }
    }

    //Helper method to locate the player
    private Location locatePlayer() {
    	if (!(player.getGrid() == null))
    		return player.getLocation();
    	return getLocation();
    }

    //Helper method for 50-50 chance
    private boolean chanceFunc() {
	    Random r = new Random();
	    int chance = r.nextInt(2);
	    if (chance == 1) 
	    	return true;
	    return false;
	}
}