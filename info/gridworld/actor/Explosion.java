package info.gridworld.actor;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;

public class Explosion
{
  public Explosion()
  {
    setColor(Color.RED);
    setDirection(0);
  }

  public void act()
  {
    removeSelfFromGrid();
  }
}
