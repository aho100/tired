import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location; 
import info.gridworld.actor.Rock;

import java.awt.Color;

public class AlienRunner {
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();
		Player x = new Player();
		world.add(new Location(0, 0), x);
		world.add(new Alien(false, x));
		world.playerLocation(x.getLocation()); 
        world.show();
	}
}