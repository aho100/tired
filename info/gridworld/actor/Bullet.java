package info.gridworld.actor;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;

public class Bullet extends Actor
{
	private Actor selected = null;
	//private Player chosen = null;

	public Bullet()
	{
		setColor(Color.BLACK);
		setDirection(0);
	}

	public Bullet(int dir)
	{
		setColor(Color.BLACK);
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
			 double p = Math.random()*2;
			 if((int)r == 1)
			 { //25% chance of getting either ammo or exlives
				if (p ==0)
				{
					Actor g = new BulletSupply();
					g.putSelfInGrid(grid,loc);
				}

				else
				{
					Actor e = new ExLives();
				e.putSelfInGrid(grid, loc);
				}
			 }
		}
		else
			this.removeSelfFromGrid();


	}

	public boolean checkEnemies()   //checks if the column, the bullet is in, has an enemy
	{
		Grid<Actor> gr = getGrid();
		Actor a = null;

		Location loc = getLocation();

		for (Location l = loc.getAdjacentLocation(getDirection()); gr.isValid(l); l = l.getAdjacentLocation(getDirection()))
		{
			a = gr.get(l);

			if (a != null && !(a instanceof Rock) && !(a instanceof ExLives) && !(a instanceof Ammo))
			{
				selected = gr.get(l);
				return true;
			}
		}

		return false;
	}
}
