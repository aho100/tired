import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location; 
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;  //**Only included for testing purposes

import java.awt.Color;

public class AlienRunner {
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();
		/* Commented for testing purposes:
		Player x = new Player();
		*/
		Flower x = new Flower();  //**Only included for testing purposes
		world.add(new Location(0, 0), x);
		world.add(new Alien(false, x));
		world.playerLocation(x.getLocation());  //**Ask Yoshi what this does
        world.show();
	}
}