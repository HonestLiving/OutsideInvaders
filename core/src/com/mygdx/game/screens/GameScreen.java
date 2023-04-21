package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.game.OutsideInvaders;
import com.mygdx.game.entities.*;

import java.util.ArrayList;
import java.util.Random;

import static com.mygdx.game.OutsideInvaders.APP_HEIGHT;
import static com.mygdx.game.OutsideInvaders.APP_WIDTH;
import static com.mygdx.game.entities.Ship.SHIP_WIDTH;

public class GameScreen implements Screen {


    public static final int ENEMY_SHUFFLE = 30;
    int enemySpawnX = 80;
    int enemySpawnY = 600;

    public static final int HEART_WIDTH = 40;
    public static final int HEART_HEIGHT = 40;

    int waveNum =0;

    Ship ship;
    int heartSpawnX = APP_WIDTH-200;
    int heartSpawnY = APP_HEIGHT-100;

    //To count time (Need 2 different ones to keep track of things)
    private float enemyTimeSeconds = 0f;
    private float enemyShootTracker = 0f;
    private float timeSeconds = 0f;

    //1f means 1 second
    private float bulletSpawnTime = .4f;
    private float enemyBulletSpawnTime = 0.8f;
    private float enemyShuffleTime = 1f;

    private boolean alternatingCheck =false;

    OutsideInvaders game;

    Texture life;
    //Array of bullets
    ArrayList<Bullet> bullets;
    ArrayList<EnemyBullet> enemyBullets;

    //Array of boosters
    ArrayList<Boosters> boosters;

    //Array of enemies, aka a wave
    ArrayList<Enemies> wave;

    BitmapFont scoreFont;
    Random rand;
    Random spawnerRand;
    int upperbound = 16;

    public GameScreen (OutsideInvaders game){
        this.game=game;

        rand = new Random();
        spawnerRand = new Random();
        //Makes a ship and puts it in the middle of the screen
        ship = new Ship(APP_WIDTH/2 - SHIP_WIDTH/2);
        bullets = new ArrayList<Bullet>();
        boosters = new ArrayList<Boosters>();
        enemyBullets = new ArrayList<EnemyBullet>() ;
        wave = new ArrayList<Enemies>();
        scoreFont = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
        life = new Texture("heart.png") ;

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        //Adding new bullets into the list constantly
        timeSeconds += Gdx.graphics.getRawDeltaTime();
        if (timeSeconds > bulletSpawnTime) {
            timeSeconds -= bulletSpawnTime;
            bullets.add(new Bullet(ship.x + 4));
            bullets.add(new Bullet(ship.x + SHIP_WIDTH - 4));
        }

        //Updating bullets (Need another array or else concurrent modification exception)
        ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();

        for (Bullet bullet : bullets) {
            bullet.update(delta);
            if (bullet.remove == true) {
                bulletsToRemove.add(bullet);
            }
        }

        if (wave.size() == 0) {
            waveNum += 1;
            enemyBulletSpawnTime -=0.1f ;
            enemySpawnY = 600 - waveNum*10;
            enemySpawnX = 80;
            upperbound -= 2;

            if (upperbound<0){
                upperbound=0;
            }

            for (int i = 0; i < waveNum * 5; i++) {

                //If the row ends, plus 50 cuz that's enemy width
                if (enemySpawnX + 50 > APP_WIDTH) {
                    enemySpawnX = 50;
                    enemySpawnY -= 50;
                }

                wave.add(new Enemies(enemySpawnX, enemySpawnY));
                enemySpawnX += 80;
            }
        }

        //Adding enemies to remove
        ArrayList<Enemies> enemiesToRemove = new ArrayList<Enemies>();
        //Adding boosters to remove
        ArrayList<Boosters> boostersToRemove = new ArrayList<Boosters>();

        //Movement
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            ship.x += ship.xSpeed * Gdx.graphics.getDeltaTime();
            if (ship.x > APP_WIDTH - SHIP_WIDTH) {
                ship.x = APP_WIDTH - SHIP_WIDTH;
            }
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            ship.x -= ship.xSpeed * Gdx.graphics.getDeltaTime();
            if (ship.x < 0) {
                ship.x = 0;
            }
        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        game.batch.begin();


        GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "wave: "+ waveNum);
        scoreFont.draw(game.batch, scoreLayout, APP_WIDTH/2 - scoreLayout.width/2, game.APP_HEIGHT - scoreLayout.height - 5);

        //Renders the ship
        ship.render(game.batch);
        ship.update(delta);

        //Renders the bullets
        for (Bullet bullet : bullets) {
            bullet.render(game.batch);
        }

        //Shuffles the enemies

        enemyShootTracker += Gdx.graphics.getRawDeltaTime();
        if (enemyShootTracker > enemyShuffleTime) {
            if (alternatingCheck) {
                for (Enemies enemies : wave) {
                    enemies.x += ENEMY_SHUFFLE;
                    enemies.update(delta);
                }
                enemyShootTracker -= enemyShuffleTime;
                alternatingCheck = false;
            } else {
                for (Enemies enemies : wave) {
                    enemies.x -= ENEMY_SHUFFLE;
                    enemies.update(delta);
                }
                enemyShootTracker -= enemyShuffleTime;
                alternatingCheck = true;
            }
        }

        //Adding new bullets into the enemy bullet list constantly
        enemyTimeSeconds += Gdx.graphics.getRawDeltaTime();
        if (enemyTimeSeconds > enemyBulletSpawnTime) {
            enemyTimeSeconds -= enemyBulletSpawnTime;

            for(Enemies enemies : wave){
                if (upperbound==0){
                    enemyBullets.add(new EnemyBullet(enemies.x + enemies.ENEMY_WIDTH/2, game.APP_HEIGHT - (game.APP_HEIGHT - enemies.y)));
                }
                else{
                    if(rand.nextInt(upperbound)==1){
                        enemyBullets.add(new EnemyBullet(enemies.x + enemies.ENEMY_WIDTH/2, game.APP_HEIGHT - (game.APP_HEIGHT - enemies.y)));
                    }
                }


            }
        }

        //Updating bullets (Need another array or else concurrent modification exception)
        ArrayList<EnemyBullet> enemyBulletsToRemove = new ArrayList<EnemyBullet>();

        for (EnemyBullet bullet : enemyBullets) {
            bullet.update(delta);
            if (bullet.remove == true) {
                enemyBulletsToRemove.add(bullet);
            }
        }

        //Updating bullets (Need another array or else concurrent modification exception)

        for (Boosters booster : boosters) {
            booster.update(delta);
        }

        //Checking for collision after all positions are updated
        for(Bullet bullet : bullets){
            for(Enemies enemies : wave){
                if(bullet.getCollisionRect().collidesWith(enemies.getCollisionRect())){
                    bulletsToRemove.add(bullet);
                    enemiesToRemove.add(enemies);
                    //Chance of spawning a booster
                    if(spawnerRand.nextInt(5)==1){
                        boosters.add(new Boosters(enemies.x,enemies.y));
                    }
                }
            }
        }

        //Checking for collision after all positions are updated
        for(EnemyBullet enemyBullet : enemyBullets){
            if(enemyBullet.getCollisionRect().collidesWith(ship.getCollisionRect())){
                enemyBulletsToRemove.add(enemyBullet);
                ship.lives-=1;
            }
        }

        //Checking for collision after all positions are updated
        for(Boosters booster : boosters){
            if(booster.getCollisionRect().collidesWith(ship.getCollisionRect())){
                boostersToRemove.add(booster);
                ship.lives+=1;
            }
        }

        //updates the ship lives (heart pictures))

        for(int i=0; i<ship.lives;i++){

            game.batch.draw(life,heartSpawnX,heartSpawnY,HEART_WIDTH,HEART_HEIGHT);
            heartSpawnX +=10;

            if (i== ship.lives-1){
                heartSpawnX =APP_WIDTH-200;
                break;
            }
        }

        if (ship.lives==0){
            this.dispose();
            game.setScreen(new EndScreen(game));
        }

        //Removes all the bullets that need to be removed from the original bullet arraylist.
        bullets.removeAll(bulletsToRemove);
        //Removes all the bullets that need to be removed from the original bullet arraylist.
        enemyBullets.removeAll(enemyBulletsToRemove);
        wave.removeAll(enemiesToRemove);
        boosters.removeAll(boostersToRemove);

        //Renders the enemies
        for(Enemies enemies : wave) {
            enemies.render(game.batch);
        }

        //Renders enemy bullets
        for (EnemyBullet bullet : enemyBullets) {
            bullet.render(game.batch);
        }

        //Renders boosters
        for (Boosters booster : boosters) {
            booster.render(game.batch);
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
