import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Actor;
import java.awt.Color;


public class PlayerRunner
{
    public static void main(String[] args)
    {
      ActorWorld world = new ActorWorld();
      world.add(new Location(3, 3), new Rock());
      world.add(new Location(2, 8), new Flower(Color.BLUE));
      Actor act = new Actor();
      world.add(new Location(0,9), act);
      Player alice = new Player(act.getLocation());
      world.add(new Location(7, 8), alice);
      Enemy john = new Enemy(alice);
      world.add(new Location(1,1), john);
      world.playerLocation(alice.getLocation());
      world.show();
    }
}
