package edu.csupomona.kyra.state.level;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.Kyra;
import edu.csupomona.kyra.component.sound.PlayerSoundsLevel2;

public class Level2 extends Level {
	final static Vector2f P1_POS = new Vector2f(33, 1216);
	final static Vector2f P2_POS = new Vector2f(83, 1216);
	
	public Level2() throws SlickException {
		super(Kyra.GAMESTATETWO, "lvl/level2map.tmx", P1_POS, P2_POS, false);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		super.init(gc, sbg);
		player1.addComponent(new PlayerSoundsLevel2("player1_SFX_lvl2"));
		if (Kyra.vs)
			player2.addComponent(new PlayerSoundsLevel2("player2_SFX_lvl2"));
		addZombie(new Vector2f(130, 1216));
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		super.update(gc, sbg, delta);
		if (gc.isPaused() && gc.getInput().isKeyPressed(Input.KEY_P)) {
			nextLevel(gc, sbg);
		}
	}
}
