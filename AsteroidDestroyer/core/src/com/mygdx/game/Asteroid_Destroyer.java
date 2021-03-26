package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static sun.misc.Version.println;

public class Asteroid_Destroyer extends ApplicationAdapter {
	SpriteBatch batch;
	Texture ship, stars;
	float shipH, shipW, shipX, shipY;
	float velocity = 0.0f;
	float gravity = 0.6f;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		ship = new Texture("ship.png");
		stars = new Texture("star.png");
		shipW = Gdx.graphics.getWidth()/6;
		shipH = Gdx.graphics.getHeight()/10;
		shipX = Gdx.graphics.getWidth()/2;
		shipY = Gdx.graphics.getWidth()/10;


	}

	@Override
	public void render () {
		batch.begin();

		batch.draw(stars, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(ship, shipX, shipY, shipW, shipH);
		shipMove();


		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		stars.dispose();
		ship.dispose();
	}

	public void shipMove(){
		float accelerometerX  = Gdx.input.getAccelerometerX();
		if (accelerometerX  > 0){
			shipX = shipX -2;
			if (shipX == 0){
				shipX = shipX +2;
			}
		} else if(accelerometerX  < 0){
			shipX = shipX +2;
			if (shipX == Gdx.graphics.getWidth() - shipW){
				shipX = shipX -2;
			}
		}
	}


}
