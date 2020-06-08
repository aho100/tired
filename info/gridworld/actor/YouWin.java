package info.gridworld.actor;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;

public class YouWin extends Actor
{
  public YouWin(Color col)
  {
    setColor(col);
    setDirection(45);
  }

  public void act()
  {
    setDirection(getDirection()*-1);
  }


}
