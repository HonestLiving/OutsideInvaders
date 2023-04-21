package com.mygdx.game.entities;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.tools.CollisionRect;

public class Boosters {
    CollisionRect rect;
    float x;
    float y;
    public final static int WIDTH =50;
    public final static int HEIGHT =50;
    float xSpeed=4;
    float ySpeed=4;
    Texture texture;


    public Boosters(float x, float y) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.rect = new CollisionRect(x,y,WIDTH,HEIGHT);
        this.texture = new Texture("life_booster.png");
    }

    public void update(float delta) {
        x += xSpeed;
        y += ySpeed;
        if (x-WIDTH < 0 || x+WIDTH > Gdx.graphics.getWidth()) {
            xSpeed = -xSpeed;
        }
        if (y-HEIGHT < 0 || y+HEIGHT > Gdx.graphics.getHeight()) {
            ySpeed = -ySpeed;
        }
        this.rect.move(x,y);
    }
    public void render(SpriteBatch batch){
        batch.draw (texture,x,y,WIDTH,HEIGHT) ;
    }

    public CollisionRect getCollisionRect(){
        return rect;
    }
}