package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.OutsideInvaders;
import jdk.tools.jmod.Main;

import java.awt.*;

public class MainMenuScreen implements Screen {
    private static final int EXIT_BUTTON_WIDTH = 300;
    private static final int EXIT_BUTTON_HEIGHT = 100;
    private static final int EXIT_BUTTON_Y = 100;

    private static final int PLAY_BUTTON_WIDTH = 300;
    private static final int PLAY_BUTTON_HEIGHT = 100;
    private static final int PLAY_BUTTON_Y = 200;

    private static final int TITLE_WIDTH = 400;
    private static final int TITLE_HEIGHT = 300;
    private static final int TITLE_X = OutsideInvaders.APP_WIDTH/2 - TITLE_WIDTH/2;
    private static final int TITLE_Y = 300;

    private static final int BUTTON_X = OutsideInvaders.APP_WIDTH/2 - EXIT_BUTTON_WIDTH/2;


    OutsideInvaders game ;

    Texture exitButtonActive;
    Texture exitButtonInactive;
    Texture playButtonActive;
    Texture playButtonInactive;
    Texture title;

    public MainMenuScreen(OutsideInvaders game){
        this.game=game;
        title = new Texture("title.png");
        playButtonActive = new Texture("play_button_active.png");
        playButtonInactive= new Texture("play_button_inactive.png");
        exitButtonActive = new Texture("exit_button_active.png");
        exitButtonInactive = new Texture("exit_button_inactive.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.batch.draw(title,TITLE_X, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT);

        if(Gdx.input.getX()<BUTTON_X  + EXIT_BUTTON_WIDTH && Gdx.input.getX()>BUTTON_X  && OutsideInvaders.APP_HEIGHT - Gdx.input.getY()<EXIT_BUTTON_Y+EXIT_BUTTON_HEIGHT && OutsideInvaders.APP_HEIGHT - Gdx.input.getY()>EXIT_BUTTON_Y){
            game.batch.draw(exitButtonActive,BUTTON_X ,EXIT_BUTTON_Y,EXIT_BUTTON_WIDTH,EXIT_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        }
        else{
            game.batch.draw(exitButtonInactive,BUTTON_X ,EXIT_BUTTON_Y,EXIT_BUTTON_WIDTH,EXIT_BUTTON_HEIGHT);
        }

        if(Gdx.input.getX()<BUTTON_X  + PLAY_BUTTON_WIDTH && Gdx.input.getX()>BUTTON_X  && OutsideInvaders.APP_HEIGHT - Gdx.input.getY()<PLAY_BUTTON_Y+PLAY_BUTTON_HEIGHT && OutsideInvaders.APP_HEIGHT - Gdx.input.getY()>PLAY_BUTTON_Y){
            game.batch.draw(playButtonActive,BUTTON_X ,PLAY_BUTTON_Y,PLAY_BUTTON_WIDTH,PLAY_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(new GameScreen(game));
            }
        }
        else{
            game.batch.draw(playButtonInactive,BUTTON_X ,PLAY_BUTTON_Y,PLAY_BUTTON_WIDTH,PLAY_BUTTON_HEIGHT);
        }

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
