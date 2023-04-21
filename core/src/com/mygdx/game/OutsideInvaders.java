package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.MainMenuScreen;

import java.util.Random;

public class OutsideInvaders extends Game {

	//Application variables
	public static String APP_TITLE = "Outside Invaders" ;
	public static int APP_WIDTH = 480 ;
	public static int APP_HEIGHT = 720 ;
	public static int APP_FPS = 60 ;


	public SpriteBatch batch;

	@Override
	public void create() {

		batch = new SpriteBatch() ;
		this.setScreen(new MainMenuScreen(this));

	}

	//Like a loop, runs 60 times per second
	@Override
	public void render() {
		super.render();
	}
}
