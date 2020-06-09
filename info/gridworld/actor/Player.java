package info.gridworld.actor;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;

public class Player extends Bug
{
  private Location loc;
  public int steps = 100;

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

  public void step()
  {
    steps -= 1;
  }

  public int getSteps()
  {
    return steps;
  }

  public void act()
  {
    Grid<Actor> gr = getGrid();
    if (getLocation().equals(loc))
    {
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
    if (getColor().equals(Color.GREEN)) {
      for (int y1=0; y1<12; y1++) {
          for (int x1=0; x1<12; x1++) {
              GameOver gover = new GameOver();
              gover.putSelfInGrid(gr, new Location(y1,x1));
          }
      }
    }
  }



}
