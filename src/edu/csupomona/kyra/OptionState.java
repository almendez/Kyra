/**************************************************************
 *	file:		OptionState.java
 *	author:		Andrew King, Anthony Mendez
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	04/28/2011
 *
 *	purpose: This class defines the game state where you select
 *	various game options.
**************************************************************/

package edu.csupomona.kyra;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class OptionState extends BasicGameState {

	int stateID = 4;
	Controls con;
	
	public OptionState(int stateID, Controls con) {
		this.stateID = stateID;
		this.con = con;
	}
	
	@Override
    public int getID() {
        return stateID;
    }
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
	
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics gc1) throws SlickException {
    	
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
 
    }
}
