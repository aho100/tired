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
	
	public void act() //do nothing until player steps in it and trap disappears/enemy spawns
	{	
		Grid<Actor> g = getGrid();
		int rand = (int)(Math.random() * 10);
		int randx = (int)(Math.random() * 11) + 1;
		int randy = (int)(Math.random() * 11) + 1;
		loc = new Location(randy, randx);
		if ((rand == 1) && (g.isValid(loc))) //10% chance to spawn			
		{
			Actor spawn = new Traps();
			spawn.putSelfInGrid(g, loc);
		}
		
		loc1 = selected.getLocation();
		loc2 = getLocation();
		if (loc1.equals(loc2))
		{
			removeSelfFromGrid();
			enemy.putSelfInGrid(g, loc2);
		}
	}
}
	
