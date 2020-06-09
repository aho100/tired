package info.gridworld.actor;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;

public class Player extends Bug
{
  private Location loc;

  public Player()
  {
    setColor(Color.RED);
    setDirection(0);
  }

  public Player(Location l)
  {
    setColor(Color.RED);
    setDirection(0);
    loc = l;
  }

  public void act()
  {
    if (getLocation().equals(loc))
    {
      Grid<Actor> gr = getGrid();
      removeSelfFromGrid();
      for (int indy = 0; indy < 12; indy++)
      {
        for (int indx = 0; indx < 12; indx++)
        {
          YouWin message = new YouWin(Color.WHITE);
          Location loc = new Location(indy, indx);
          message.putSelfInGrid(gr, loc);
        }
      }
    }
  }



}
