package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class customdrawing extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture mario;
	Ball ball;

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("background.png");
		mario = new Texture("mario.png");

		ball = new Ball();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (Gdx.input.justTouched())
			ball.velocity.y += 100;
		ball.update();
		batch.begin();

		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(mario, ball.position.x + 140, ball.position.y + 140, mario.getWidth() , mario.getHeight()  );

		batch.end();
	}
	
	@Override
	public void dispose () {

		batch.dispose();
		background.dispose();
		mario.dispose();
	}
}