import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Critter;
import info.gridworld.actor.ExLives;



import java.awt.Color;

public class GalaxyRunner{
	public static void main(String[] args)
	{
		ActorWorld world = new ActorWorld();
		Location goal = new Location(11,11);
		Player player = new Player(goal);
		world.playerLocation(player.getLocation());
		
		world.add(new Location(0,0), player);
		for (int i = 0; i < 19; i++)
		{
			double w = Math.random() * 9 + 2;
			double y = Math.random() * 12;
			world.add(new Location ((int)w, (int)y), new Alien(false, player));
		}
		for (int k =0; k < 1;k++)
		{
			double w = Math.random() * 9 + 2;
			double y = Math.random() * 12;
			world.add(new Location ((int)w, (int)y), new Alien(true, player));
		}
		
		for (int j =0; j < 3; j++)
		{
			world.add(new Ammo());
		}
	
		
		
		BulletSupply tracker1 = new BulletSupply(); //tracks bullets and lives on the side of the grid
		BulletSupply tracker2 = new BulletSupply();
		BulletSupply tracker3 = new BulletSupply();
		BulletSupply tracker4 = new BulletSupply();
		BulletSupply tracker5 = new BulletSupply();
		
		ExLives life1 = new ExLives();
		ExLives life2 = new ExLives(); 
		
		world.add(new Location (1,13),life1);
		world.add(new Location (2,13),life2);
		world.add(new Location (1,14), tracker1);
		world.add(new Location (2,14), tracker2);
		world.add(new Location (3,14), tracker3);
		world.add(new Location (4,14), tracker4);
		world.add(new Location (5,14), tracker5);
		
		
		LastTile gg = new LastTile();    //represents the exit
		world.add(new Location(11,11),gg);
		
		for (int r = 0; r < 12; r++)
		{
			world.add(new Location(r, 12),new Rock());
		
		}
		world.add(new Location(0,13), new Rock());
		world.add(new Location(0,14), new Rock());
		for (int p = 3; p <12; p++)
		{
			world.add(new Location(p, 13), new Rock());
		}
		for (int q = 6; q < 12; q++)
		{
			world.add(new Location(q,14),new Rock());
		}
	
		world.show();
	}
}
