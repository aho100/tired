import info.gridworld.actor.Bug;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.world.World;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

import java.awt.Color;


public class Traps extends Actor
{
	private Player player;
	private Alien enemy;
	private Location loc;
	private Location loc1;
	private Location loc2;
	
	public Traps()
	{
		setColor(Color.YELLOW);
	}
	
	public void act() //do nothing until player steps in it and trap disappears/alien spawns
	{	
		Grid g = getGrid();
		
		loc1 = player.getLocation();
		loc2 = getLocation();
		if (loc1.equals(loc2))
		{
			removeSelfFromGrid();
			enemy.putSelfInGrid(g, loc2);
		}
	}
}
	
