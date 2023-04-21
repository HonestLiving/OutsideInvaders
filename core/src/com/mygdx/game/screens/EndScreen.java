package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.OutsideInvaders;

import static com.mygdx.game.OutsideInvaders.APP_HEIGHT;

public class EndScreen implements Screen {
    private static final int TITLE_WIDTH = 300;
    private static final int TITLE_HEIGHT = 400;
    private static final int TITLE_X = OutsideInvaders.APP_WIDTH/2 - TITLE_WIDTH/2;
    private static final int TITLE_Y = 200;
    Texture title;

    OutsideInvaders game ;

    public EndScreen(OutsideInvaders game){
        this.game=game;
        title = new Texture("end_title.png");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(title,TITLE_X, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT);
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
