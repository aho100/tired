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
		//Enemy a = new Enemy();
		
		//world.add(a); //do this 24 more times
		
		/*for (int i = 0; i < 20; i++)
		{
			world.add(new Enemy());
		}
		*/
		Bullet b = new Bullet();
		world.add(new Location(8,2),b);
		Flower flow = new Flower();
		world.add(new Location(10,2),flow);
		
		Bullet tracker1 = new Bullet(); //tracks bullets and lives on the side of the grid
		Bullet tracker2 = new Bullet();
		Bullet tracker3 = new Bullet();
		Bullet tracker4 = new Bullet();
		Bullet tracker5 = new Bullet();
		
		ExLives life1 = new ExLives();
		ExLives life2 = new ExLives(); 
		
		world.add(new Location (1,13),life1);
		world.add(new Location (2,13),life2);
		world.add(new Location (1,14), tracker1);
		world.add(new Location (2,14), tracker2);
		world.add(new Location (3,14), tracker3);
		world.add(new Location (4,14), tracker4);
		world.add(new Location (5,14), tracker5);
		
		/*Flower c = new Flower();
		world.add(new Location(11,0),c);
		Flower d = new Flower();
		world.add(new Location(11,1),d);
		Flower e = new Flower();
		world.add(new Location(11,2),e);
		Flower f = new Flower();
		world.add(new Location(11,3),f);		
		Flower g = new Flower();
		world.add(new Location(11,4),g);
		Flower h = new Flower();
		world.add(new Location(11,5),h);		
		Flower i = new Flower();
		world.add(new Location(11,6),i);
		Flower j = new Flower();
		world.add(new Location(11,7),j);		
		Flower k = new Flower();
		world.add(new Location(11,8),k);
		Flower l = new Flower();
		world.add(new Location(11,9),l);		
		Flower m = new Flower();
		world.add(new Location(11,10),m);
		Flower n = new Flower();
		world.add(new Location(11,11),n);		
		Flower o = new Flower();
		world.add(new Location(11,12),o);
		Flower p = new Flower();
		world.add(new Location(11,13),p);
		Flower q = new Flower();
		world.add(new Location(11,14),q);*/
		LastTile gg = new LastTile();    //represents the exit
		world.add(new Location(11,11),gg);
		
		
	
		world.show();
	}
}
