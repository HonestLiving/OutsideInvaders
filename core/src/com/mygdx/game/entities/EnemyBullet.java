package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.tools.CollisionRect;

public class EnemyBullet extends Bullet{

    public EnemyBullet(float x, float y){
        this.x= x;
        this.y = y;

        this.rect = new CollisionRect(x,y,WIDTH,HEIGHT);
        this.texture = new Texture("enemy_bullet.png");
    }

    @Override
    public void update (float delta){
        y-= SPEED *delta;
        if(y< 0){
            remove = true;
        }
        rect.move(x,y);
    }
}
