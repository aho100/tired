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
		Location loc=null;
		Grid grid = getGrid();
		//System.out.println(checkEnemies());
		if(checkEnemies()){
			 loc = selected.getLocation();
			 selected.removeSelfFromGrid();
			 this.removeSelfFromGrid();
			 double r = Math.random()*4; // 0 - 3
			 if((int)r == 1){ //25% chance
				Actor e = new ExLives(); //change to ammo pack
				e.putSelfInGrid(grid, loc);
			 }
		}
		else
			this.removeSelfFromGrid();
			
		
	}
	
	public boolean checkEnemies()   //checks if the column, the bullet is in, has an enemy
	{	Grid<Actor> gr = getGrid();
		boolean isThere = false;
		Actor a = null;
		int bulletCol = this.getLocation().getCol();
		int bulletRow = this.getLocation().getRow();
		//System.out.println(bulletCol + " " + bulletRow);
		Location loc;
		//if (getDirection() == 180)
		for (int i = bulletRow+1; i < gr.getNumRows(); i++)
		{
			//System.out.println(i + " " + bulletCol);
			loc = new Location(i, bulletCol);
			a = gr.get(loc);
			//System.out.println(a);
			if (a != null && !(a instanceof ExLives)) //add if its not a rock or an ammo pack
			{	selected = gr.get(loc);
				return true;
			}
		}
		
		/*if (this.getDirection() == 90)                                           accounts for all four directions
			for (int j = bulletCol +1; j < gr.getNumCols(); j++)
			{
				loc = new Location(bulletRow, j);
				a = gr.get(loc);
				if (a != null && !(a instanceof ExLives))
				{
					selected = gr.get(loc);
					return true;
				}
			}
		if (this.getDirection() ==0)
			for (int k = bulletRow -1; k > 0; k--)
			{
				loc = new Location(k, bulletCol);
				a = gr.get(loc);
				if (a != null && !(a instanceof ExLives))
				{
					selected = gr.get(loc);
					return true;
				}
			}
		if (this.getDirection() == 270)
			for (int j = bulletCol -1; j > 0; j--)
			{
				loc = new Location(bulletRow, j);
				a = gr.get(loc);
				if (a != null && !(a instanceof ExLives))
				{
					selected = gr.get(loc);
					return true;
				}
			}	
		*/
		return isThere;
}
}
	