import info.gridworld.actor.Bug;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;

public class YouWin extends Actor
{
  public YouWin()
  {
    setColor(Color.YELLOW);
    setDirection(45);
  }

  public void act()
  {
    setDirection(getDirection()*-1);
  }


}
