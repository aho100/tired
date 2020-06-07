import info.gridworld.actor.Bug;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Critter;
import info.gridworld.world.World;

import java.awt.Color;
import java.util.ArrayList;

public class Predator extends Critter               
{	private int steps;
	private int lifespan = 200;
	private int cycle;
	private boolean baby;
	private Actor other = null;
	
	public Predator()
	{
		steps =0;
		cycle=0;
		setColor(Color.BLUE);
		baby = false;
		other = null; //refers to what is going to be eaten
	}
	
	private void changeColor(int cycle){ //changes colors via counter 
		if (cycle == 0)
			setColor(Color.BLUE);
		else if(cycle == 1)
			setColor(Color.GREEN);
		else if(cycle == 2)
			setColor(Color.RED);
		else if (this.cycle == 3){
			setColor(Color.BLACK);
			if(!this.baby)
				addBabies(); //black predator will give birth to baby
		}
		else if(!this.baby){ //if it is a NOT a baby then it will turn blue and continue the cycle again
			setColor(Color.BLUE);
			this.cycle = 0;
		}
	}
	
	public void addBabies(){ 
		Grid<Actor> grid = getGrid();
		ArrayList<Location> emptylocations = grid.getEmptyAdjacentLocations(getLocation());
		int emptyspaces = emptylocations.size();
		int randomspace = (int)(Math.random()*emptyspaces);
		Predator p = new Predator();
		p.baby=true;
		p.putSelfInGrid(grid, emptylocations.get(randomspace));
	}
	
	
	public void processActors(ArrayList<Actor> actors)
	{	
		int n = actors.size();
		if (n == 0)
            return;
		
		int r = (int) (Math.random() * n);
        this.other = actors.get(r); 
		
	}
	
	public ArrayList<Location> getMoveLocations()
   {
		ArrayList<Location> temp = new ArrayList();
		
		if ((this.other != null) && this.other.getLocation() != null && !(this.other instanceof Predator) && !(this.other instanceof Rock) ){//handle nullpointer {
			temp.add(this.other.getLocation());
			cycle=cycle+1;
		changeColor(cycle);
			
		}
		else {
			temp = getGrid().getEmptyAdjacentLocations(getLocation());
		}
        return temp;
    }


	public Location selectMoveLocation(ArrayList<Location> locs) 
    {
        int n = locs.size();
        if (n == 0)
            return getLocation();
        int r = (int) (Math.random() * n);
		
        return locs.get(r);
    }
	

	public void makeMove(Location loc)
    {
		if(loc != null){
			setDirection(getLocation().getDirectionToward(loc));
			super.makeMove(loc);
		}
		else{ //if everything is empty around it, just random number
			ArrayList<Location> empty = getGrid().getEmptyAdjacentLocations(getLocation());
			int n = empty.size();
			int r = (int) (Math.random() * n);
			if (empty.get(r) == null)
				removeSelfFromGrid();
			else
				moveTo(empty.get(r));
		}
		steps++;
		if(steps == 200)
			gameOver(); //handle when reach 200 steps
    }
	
	public void gameOver() //death
	{	Grid grid = getGrid();
		Flower flow = new Flower();
		Location a = getLocation();
		
			removeSelfFromGrid();
			flow.putSelfInGrid(grid, a);
		
	}
}

		
			