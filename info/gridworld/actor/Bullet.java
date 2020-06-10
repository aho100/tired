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


/**
 * Kills the enemy (if any), and removes itself from grid as well.
 */
	public void act()
	{
		Location loc=null;
		Grid<Actor> grid = getGrid();
		//System.out.println(checkEnemies());
		if(checkEnemies()){
			 loc = selected.getLocation();
			 selected.removeSelfFromGrid();
			 removeSelfFromGrid();
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
			 else
			 {
				 Explosion explode = new Explosion();
				 explode.putSelfInGrid(grid,loc);
			 }
		}
		else
			removeSelfFromGrid();


	}

/**
 * Checks if enemy is in line of direction of bullet.
 * Makes sure it detects enemy and not Rock, ExLives, Traps or Ammo.
 * @return true if enemy, false otherwise.
 */
	public boolean checkEnemies()
	{
		Grid<Actor> gr = getGrid();
		Actor a = null;

		Location loc = getLocation();

		for (Location l = loc.getAdjacentLocation(getDirection()); gr.isValid(l); l = l.getAdjacentLocation(getDirection()))
		{
			a = gr.get(l);
			if (a instanceof Rock)
				return false;
			if (a != null && !(a instanceof Rock) && !(a instanceof ExLives) && !(a instanceof Ammo) && !(a instanceof Traps)
					&& !(a instanceof BulletSupply) && !(a instanceof GameOver) && !(a instanceof YouWin))
			{
				selected = gr.get(l);
				return true;
			}
		}

		return false;
	}
}
