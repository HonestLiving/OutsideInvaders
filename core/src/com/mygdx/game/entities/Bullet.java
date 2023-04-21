package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.tools.CollisionRect;

/**
 * Name: Matthew Chen
 * Date: 06/21/2022
 * Description: This is the bullet super class, most bullets will use these attributes. It makes bullets that collides with
 * enemies / the ship.
 */

public class Bullet {
    /**
     * The speed that the bullet travels at
     */
    public final static int SPEED = 500;

    /**
     * The default Y that the bullet spawns at (The ship that constantly shoots)
     */
    public final static int DEFAULT_Y = 40;

    /**
     * The texture of the bullet
     */
    public Texture texture;

    /**
     * The width of the bullet
     */
    public final static int WIDTH =3;

    /**
     * The height of the bullet
     */
    public final static int HEIGHT =12;

    /**
     * The X co-ord of the bullet
     */
    float x;

    /**
     * The Y co-ord of the bullet
     */
    float y;

    /**
     * The hitbox of the bullet
     */
    CollisionRect rect;

    /**
     * Checker to see if bullet should be removed
     */
    public boolean remove =false;

    /**
     * Default constructor for a bullet
     */
    public Bullet(){
    }

    /**
     * Constructor to make a bullet
     * @param x the x co-ordinate of the bullet
     */
    public Bullet(float x){
        this.x = x;
        this.y = DEFAULT_Y;

        this.rect = new CollisionRect(x,y,WIDTH,HEIGHT);

        this.texture= new Texture("bullet.png");
    }

    /**
     *Draws the bullet
     * @param batch A batch is used by lib gdx to draw textures.
     */
    public void render(SpriteBatch batch){
        batch.draw (texture,x,y) ;
    }

    /**
     * updates the bullet's position and it's hitbox
     * @param delta the time between frames
     */
    public void update (float delta){
        y+= SPEED *delta;
        if(y> Gdx.graphics.getHeight()){
            remove = true;
        }
        rect.move(x,y);
    }

    /**
     * returns the collisionRect (hitbox)
     * @return the collisionRect (hitbox)
     */
    public CollisionRect getCollisionRect(){
        return rect;
    }
}
