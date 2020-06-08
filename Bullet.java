import info.gridworld.actor.Bug;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.world.World;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.actor.ExLives;
import java.awt.Color;

public class Bullet extends Actor
{	private Actor selected = null;
	//private Player chosen = null;

	public Bullet()
	{
		setColor(Color.YELLOW);
			setDirection(315);
	}
	
	public Bullet(int dir)
	{
		setDirection(dir);
	}
	
	public void act()
	{
		Location loc = null;
		Grid grid = getGrid();
		
		if(checkEnemies())
		{
			 loc = selected.getLocation();
			 selected.removeSelfFromGrid();
			 removeSelfFromGrid();
			 double r = Math.random()*4; // 0 - 3
			 if((int)r == 1)
			 { //25% chance
				Actor e = new ExLives(); //change to ammo pack
				e.putSelfInGrid(grid, loc);
			 }
		}
		else
			removeSelfFromGrid();


	}
	
	public boolean checkEnemies()   //checks if the column, the bullet is in, has an enemy
	{
		Grid<Actor> gr = getGrid();
		Actor a = null;

		Location loc = getLocation();

		for (Location l = loc.getAdjacentLocation(getDirection()); gr.isValid(l); l = l.getAdjacentLocation(getDirection()))
		{
			a = gr.get(l);

			if (a != null && !(a instanceof Rock))
			{
				selected = gr.get(l);
				return true;
			}
		}

		return false;
	}
	
}
	
