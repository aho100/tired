import info.gridworld.actor.Bug;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;

public class Enemy extends Bug {
  private Player player;
  private Location loc;


  public Enemy(Player player1)
  {
    setColor(Color.BLUE);
    player = player1;
  }

  public void act()
  {
    if (!(player.getGrid() == null))
    {
      getL();
      move();
    }
  }

  public void move()
  {
    Grid<Actor> gr = getGrid();
    int dir = getLocation().getDirectionToward(loc);
    setDirection(dir);
    Location next = getLocation().getAdjacentLocation(getDirection());
    if (gr.isValid(next))
      moveTo(next);
  }

  public void getL()
  {
    loc = player.getLocation();
  }

}
