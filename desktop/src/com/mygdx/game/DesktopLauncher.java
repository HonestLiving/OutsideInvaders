package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.OutsideInvaders;


public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		//Sets the window title & size
		config.setTitle(OutsideInvaders.APP_TITLE);
		config.setForegroundFPS(OutsideInvaders.APP_FPS);
		config.setResizable(false);
		config.setWindowedMode(OutsideInvaders.APP_WIDTH,OutsideInvaders.APP_HEIGHT);

		new Lwjgl3Application(new OutsideInvaders(), config);
	}
}
