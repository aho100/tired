/*
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * @author Cay Horstmann
 */

package info.gridworld.actor;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;

import java.awt.Color;

import java.util.ArrayList;

import java.lang.Math;

/**
 * An <code>ActorWorld</code> is occupied by actors. <br />
 * This class is not tested on the AP CS A and AB exams.
 */

public class ActorWorld extends World<Actor>
{
    private static final String DEFAULT_MESSAGE = "Press WASD to move, M to shoot! Press \"RUN\" to start the game!";

    private Location selected;		//new variable to move individual actors
    private Location player = null;
    private int steps = 100;
    private int kills = 0;
    private int score;


    /**
     * Constructs an actor world with a default grid.
     */
    public ActorWorld()
    {
	selected = null;
    }

    /**
     * Constructs an actor world with a given grid.
     * @param grid the grid for this world.
     */
    public ActorWorld(Grid<Actor> grid)
    {
        super(grid);
	selected = null;
    }

    public void show()
    {
        if (getMessage() == null)
            setMessage(DEFAULT_MESSAGE);
        super.show();
    }



	//NEW method to do something
    public void stuff()
    {
	Grid<Actor> g = getGrid();

	Actor obj;

	ArrayList<Actor> sorted = new ArrayList<Actor>();
	for(int i = 0; i < g.getNumRows(); i++)
	{
		for(int j = 0; j < g.getNumCols(); j++)
		{
			obj = g.get(new Location(i, j));
			if(obj != null)
				sorted.add(obj);
		}
	}


        for (int k = 0; k < sorted.size(); k++)
        {
	    Actor a = sorted.get(k);
            Location loc = a.getLocation();
	    Location next = new Location(loc.getRow()-1, loc.getCol());
	    if(g.isValid(next) && g.get(next) == null)	//check to make sure it's in the grid & not overlapping another Actor
	    {
		a.moveTo(next);
		setMessage(""+sorted.size());
	    }
        }




    }

/**
 * Counts how many lives you have in vertical bar on the right of the world.
 * @return  Returns number of lives left.
 */
    public int countLives()
    {
      Grid<Actor> g = getGrid();
      int y = 2;
      for(int ind = 2; ind > 0; ind--)
      {
        Location loc = new Location(ind, 13);
        if (g.get(loc) != null)
          break;
        else
          y = ind - 1;
      }
      return y;
    }

/**
 * Counts Ammo left on vertical bar on right of world.
 * @return Returns number of bullets.
 */
    public int countAmmo()
    {
      Grid<Actor> g = getGrid();
      int y = 5;
      for(int ind = 5; ind > 0; ind--)
      {
        Location loc = new Location(ind, 14);
        if (g.get(loc) != null)
          break;
        else
          y = ind - 1;
      }
      return y;
    }

/**
 * Checks if player has ammo left to shoot.
 * @return Returns true if there are bullets left in bar,
 *                 false otherwise.
 */
    public boolean isAmmo()
    {
      Grid<Actor> g = getGrid();
      for(int ind = 5; ind > 0; ind--)
      {
        Location loc = new Location(ind, 14);
        if (g.get(loc) != null)
        {
          g.get(loc).removeSelfFromGrid();
          return true;
        }
      }
      return false;
    }

/**
 * Gets the Location 'in front' of the Player,
 * or where the player is trying to move into.
 * @return Location where Player is trying to move into.
 */
    public Location getLocationFront()
    {
      Grid<Actor> g = getGrid();
      Location loc = player.getAdjacentLocation( g.get(player).getDirection() + 90 );
      return loc;
    }

/**
 * Figures out if num is in between two numbers.
 * @param  num Your main number.
 * @param  i   Lower bound (exclusive).
 * @param  ii  Uppder bound (inclusive).
 * @return     True if in between, False otherwise.
 */
    public boolean isBetween(int num, int i, int ii)
    {
      if (num <= ii && num > i)
        return true;
      else
        return false;
    }


/**
 * Detects what key was pressed and acts accordingly.
 * When WASD is pressed, player is moved.
 * It makes sure it only moves the player by taking in the initial location of the player
 * in the Runner, and then updating the location as it moves, and furthermore
 * checking it is instanceof Player.
 *
 * It shoots bullet when you press M.
 * You cannot spawn Bullet ontop of anything,
 * if you try for Rock, Ammo, or Extra Lives it will not do anything,
 * if you try for Alein it will kill the Alien ahead.
 * The Bullet will kill enemy ahead.
 * You lose Ammo when you shoot from the verticl bar on the right.
 *
 * If you move into an Ammo pack, you get all 5 bullets.
 * If you move into an Extra Life tile, you get one more life.
 * If you move into a Bullet that an Alein drops, you get one more bullet.
 * Max ammo is 5 bullets.
 * If you move into a Trap an Alien will spawn somewhere randomly.
 *
 * @param  description   automatically detects
 * @param  loc            which key is pressed when key is pressed
 * @return               returns false because we use GUI
 */
    public boolean keyPressed(String description, Location loc)
    {
	//setMessage(description);   //Changes the message to display the 'keypressed', use to debug

  	Grid<Actor> g = getGrid();

	int x = 0;
	int y = 0;
	if(description.equals("W"))
		y = -1;
	if(description.equals("A"))
		x = -1;
	if(description.equals("D"))
		x = 1;
	if(description.equals("S"))
		y = 1;
  if(description.equals("M") && g.isValid(getLocationFront()) && !(g.get(getLocationFront()) instanceof Ammo)
      && !(g.get(getLocationFront()) instanceof ExLives) && !(g.get(getLocationFront()) instanceof Rock)
      && !(g.get(getLocationFront()) instanceof LastTile) && !(g.get(getLocationFront()) instanceof YouWin)
      && !(g.get(getLocationFront()) instanceof GameOver) && isAmmo())
  {
    if (g.get(getLocationFront()) instanceof Alien)
    {
      Explosion explode = new Explosion();
      explode.putSelfInGrid(g, getLocationFront());
      kills += 1;
    }
    else
    {
      int dir = g.get(player).getDirection() + 90;
      Bullet bull = new Bullet( dir );
      bull.setDirection( dir );
      bull.putSelfInGrid(g, getLocationFront());
      if (bull.checkEnemies())
        kills += 1;
    }
  }
  if(description.equals("P"))
  {
    kills = 0;
    steps = 100;
    Restart res = new Restart();
    res.restart(this);
  }

// Messages depending on how much you moved.
  if (isBetween(steps, 90, 100))
    setMessage(steps+": Wait for me Grandpa! I'm coming to save you!");
  else
  {
    if (isBetween(steps, 80, 90))
      setMessage(steps+": To avenge Mother's death!");
    else if (isBetween(steps, 70, 80))
      setMessage(steps+": Grandpa!! Where are you!?");
    else if (isBetween(steps, 60, 70))
      setMessage(steps+": SHOOT THE ALIENS!");
    else if (isBetween(steps, 50, 60))
      setMessage(steps+": Almost.. There... !");
    else if (isBetween(steps, 30, 50))
      setMessage(steps+": Stop running around and start shooting the ALIENS!");
    else if (isBetween(steps, 10, 30))
      setMessage(steps+": Poor Grandpa, waiting to get saved... while you mope around! SHOOT!");
    else if (isBetween(steps, 0, 10))
      setMessage(steps+": great...");
// The only way to affect the player by using .get() was through methods in Actor
// So i got clever and changed the color and then made the Player react to its color!
// BIG BRAIN
    else if (steps == 0)
    {
      g.get(player).setColor(Color.GREEN);
    }
  }

// random game over messages
  if (g.get(player) instanceof GameOver)
  {
    int mess = (int) Math.floor(Math.random()*4);
    switch(mess){
      case 0:
        setMessage("GAME OVER: Sorry Grandpa :( \nPress 'P' to play again!");
        break;
      case 1:
        setMessage("GAME OVER: Grandpa, I'm Never gonna give you up, Never gonna let you down, Never gonna turn around, and DESERT YOU! \nPress 'P' to play again!");
        break;
      case 2:
        setMessage("GAME OVER: Play again!... or not haha just kidding... unless?\nPress 'P' to play again!");
        break;
      case 3:
        setMessage("GAME OVER: GET BETTER NEXT TIME \nPress 'P' to play again!");
        break;
    }
  }
// random you win messages
  if (g.get(player) instanceof YouWin)
  {
    score = (100*steps) + (200*kills) + (500*countLives());
    int mess = (int) Math.floor(Math.random()*5);
    switch(mess){
      case 0:
        setMessage("YOUR SCORE: "+score+". YOU WIN: FINALLY! I GET TO SEE GRANDPA! \nPress 'P' to play again!");
        break;
      case 1:
        setMessage("YOUR SCORE: "+score+". YOU WIN: GRANDPA? WHERE ARE YOU? HELLO? \nPress 'P' to play again!");
        break;
      case 2:
        setMessage("YOUR SCORE: "+score+". YOU WIN: OH GRANDPA!... wait, NO THATS NOT GRANDPA! WHO ARE YOU? ->>>> to be continued... \nPress 'P' to play again!");
        break;
      case 3:
        setMessage("YOUR SCORE: "+score+". YOU WIN: OR DID YOU...? NO, YOU WIN! \nPress 'P' to play again!");
        break;
      case 4:
        setMessage("YOUR SCORE: "+score+". YOU WIN: BUT GRANDPA WAS NOWHERE TO BE SEEN... \"No... Father, is that you?\" ->>>> to be continued... \nPress 'P' to play again!");
        break;
    }
  }


	if((x != 0 || y != 0) && player != null && g.isValid(player) && (g.get(player) instanceof Player))
	{
		Actor obj = g.get(player);
		if (obj != null )
		{
	    Location player1 = new Location(player.getRow() + y, player.getCol() + x);
      if (g.isValid(player1) && !(g.get(player1) instanceof Rock)
          && !(g.get(player1) instanceof Alien) && !(g.get(player1) instanceof Bullet) && (steps > 0))
      {
          player = player1;
          steps -= 1;
      }


      switch(x){
        case 1:
          obj.setDirection(0);
          break;
        case -1:
          obj.setDirection(180);
          break;
      }
      switch(y){
        case 1:
          obj.setDirection(90);
          break;
        case -1:
          obj.setDirection(270);
          break;
      }

      if(g.get(player) instanceof ExLives)
      {
        int count = countLives();
        ExLives life = new ExLives();
        if (count < 2)
        {
          life.putSelfInGrid(g, new Location(count+1, 13));
        }
      }

      if(g.get(player) instanceof Ammo)
      {
        for(int index = 1; index <= 5; index++)
        {
          BulletSupply amm = new BulletSupply();
          amm.putSelfInGrid(g, new Location(index, 14));
        }
      }

      if(g.get(player) instanceof BulletSupply)
      {
        int count = countAmmo();
        BulletSupply amm = new BulletSupply();
        if (count < 5)
        {
          amm.putSelfInGrid(g, new Location(count+1, 14));
        }
      }

      if (g.get(player) instanceof Traps)
      {
        Alien ali = new Alien(false, obj);
        int y1 = (int) Math.floor(Math.random() * 11) + 1;
        int x1 = (int) Math.floor(Math.random() * 11) + 1;
        Location loc1 = new Location(y1, x1);

        while (!(g.get(loc1) == null) || player.equals(loc1))
        {
          int y2 = (int) Math.floor(Math.random() * 11) + 1;
          int x2 = (int) Math.floor(Math.random() * 11) + 1;
          loc1 = new Location(y2, x2);
        }
        add(loc1, ali);
      }


			obj.moveTo(player);
		}
	}
        return false;
    }


    public void step()
    {
        Grid<Actor> gr = getGrid();
        ArrayList<Actor> actors = new ArrayList<Actor>();
        for (Location loc : gr.getOccupiedLocations())
            actors.add(gr.get(loc));

        for (Actor a : actors)
        {
            // only act if another actor hasn't removed a
            if (a.getGrid() == gr)
                a.act();
        }
    }

    /**
     * Adds an actor to this world at a given location.
     * @param loc the location at which to add the actor
     * @param occupant the actor to add
     */
    public void add(Location loc, Actor occupant)
    {
        occupant.putSelfInGrid(getGrid(), loc);
    }

    /**
     * Adds an occupant at a random empty location.
     * @param occupant the occupant to add
     */
    public void add(Actor occupant)
    {
        Location loc = getRandomEmptyLocation();
        if (loc != null)
            add(loc, occupant);
    }

    /**
     * Removes an actor from this world.
     * @param loc the location from which to remove an actor
     * @return the removed actor, or null if there was no actor at the given
     * location.
     */
    public Actor remove(Location loc)
    {
        Actor occupant = getGrid().get(loc);
        if (occupant == null)
            return null;
        occupant.removeSelfFromGrid();
        return occupant;
    }

	//overrided
    public boolean locationClicked(Location loc)
    {
	setMessage(loc.toString());     //Changes the message to the 'location' clicked.

	selected = loc;

        return false;
    }

    public void playerLocation(Location loc)
    {
      player = loc;
    }

    public Location getPlayerLocation()
    {
      return player;
    }

}
