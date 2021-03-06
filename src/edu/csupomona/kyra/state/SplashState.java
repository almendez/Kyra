/**************************************************************
 *	file:		SplashState.java
 *	author:		Andrew King, Anthony Mendez
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	04/28/2011
 *
 *	purpose: This class defines the splash screen game state.
**************************************************************/

package edu.csupomona.kyra.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.Kyra;
import edu.csupomona.kyra.state.level.Kiosk;
import edu.csupomona.kyra.state.level.Level1;
import edu.csupomona.kyra.state.level.Level2;


public class SplashState extends BasicGameState {

	private Image background = null;
	private int stateID = 0;
	
	public SplashState(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
    public int getID() {
        return stateID;
    }
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("img/splash_background.png");
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics gc1) throws SlickException {
    	background.draw(0, 0);
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {    	
    	sbg.addState(new MenuState(Kyra.MENUSTATE));
    	sbg.getState(Kyra.MENUSTATE).init(gc, sbg);
		sbg.addState(new PlayerSelectState(Kyra.PLAYERSELECTSTATE));
		sbg.getState(Kyra.PLAYERSELECTSTATE).init(gc, sbg);
		sbg.addState(new OptionState(Kyra.OPTIONSTATE));
		sbg.getState(Kyra.OPTIONSTATE).init(gc, sbg);
		sbg.addState(new Level1());
		sbg.getState(Kyra.GAMESTATEONE).init(gc, sbg);
		sbg.addState(new Level2());
		sbg.getState(Kyra.GAMESTATETWO).init(gc, sbg);
		sbg.addState(new GameOverState(Kyra.GAMEOVERSTATE));
		sbg.getState(Kyra.GAMEOVERSTATE).init(gc, sbg);
		sbg.addState(new CreditsState(Kyra.CREDITSSTATE));
		sbg.getState(Kyra.CREDITSSTATE).init(gc, sbg);
		sbg.addState(new Kiosk(Kyra.KIOSKSTATE));
    	sbg.getState(Kyra.KIOSKSTATE).init(gc, sbg);
		
    	sbg.getCurrentState().leave(gc, sbg);
		sbg.getState(Kyra.MENUSTATE).enter(gc, sbg);
		sbg.enterState(Kyra.MENUSTATE);
    }
}

