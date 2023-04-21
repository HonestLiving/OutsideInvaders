package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.tools.CollisionRect;

public class Ship {
    public int lives =5;
    public double xSpeed = 250;
    public int fireSpeed;

    public static final int SHIP_WIDTH = 55;
    public static final int SHIP_HEIGHT = 50;
    Texture texture;
    CollisionRect rect;
    public float x,y;
    final float DEFAULT_Y = 8;

    public Ship(float x){
        this.x = x;
        this.y = DEFAULT_Y;

        this.rect = new CollisionRect(x,y,SHIP_WIDTH,SHIP_HEIGHT);
        texture= new Texture("Ship.png");

    }

    public void update (float delta){
        this.rect.move(x,y);
    }

    public void render(SpriteBatch batch){
        batch.draw (texture,x,y,SHIP_WIDTH, SHIP_HEIGHT) ;
    }

    public CollisionRect getCollisionRect(){
        return rect;
    }
}
