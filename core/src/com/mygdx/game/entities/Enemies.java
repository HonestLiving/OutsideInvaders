package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.tools.CollisionRect;

public class Enemies {
    public final static int SPEED = 500;
    public static final int ENEMY_WIDTH = 30;
    public static final int ENEMY_HEIGHT = 30;
    public static Texture texture;

    CollisionRect rect;
    public float x,y;

    public Enemies(float x,float y){
        this.x = x;
        this.y = y;

        this.rect = new CollisionRect(x,y,ENEMY_WIDTH,ENEMY_HEIGHT);
        texture= new Texture("default_enemy.png");

    }

    public void render(SpriteBatch batch){
        batch.draw (texture,x,y,ENEMY_WIDTH, ENEMY_HEIGHT) ;
    }


    public void update (float delta){
        rect.move(x,y);
    }

    public CollisionRect getCollisionRect(){
        return rect;
    }

}
