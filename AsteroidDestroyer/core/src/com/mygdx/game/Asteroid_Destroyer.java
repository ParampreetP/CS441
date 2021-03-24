package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Asteroid_Destroyer extends ApplicationAdapter {
	SpriteBatch batch;
	Texture ship;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		ship = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		batch.begin();



		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		ship.dispose();
	}
}
