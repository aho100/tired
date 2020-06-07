import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Critter;


import java.awt.Color;

public class PredatorRunner
{
	public static void main(String[] args)
	{
		ActorWorld world = new ActorWorld();
		Predator a = new Predator();
		Predator f = new Predator();
		Predator g = new Predator();
		Predator h = new Predator();
		Predator i = new Predator();
		
		Bug b = new Bug();
		Bug c = new Bug();
		//Critter d = new Critter();
		//d.setColor(Color.PINK);
		Rock r = new Rock();
		Flower e = new Flower();


		world.add(new Location(3,3),b);
				world.add(new Location(3,5),c);
						//world.add(new Location(4,4),d);
								world.add(new Location(5,4),e);
								world.add(new Location(1,2),r);
								
		world.add(new Location(4,5),a);
		world.show();

	}
}