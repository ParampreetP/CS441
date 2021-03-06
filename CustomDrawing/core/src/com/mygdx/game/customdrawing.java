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
	Texture browser;
	Ball ball;

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("background.png");
		mario = new Texture("mario.png");
		browser = new Texture("browser.png");
		ball = new Ball();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (Gdx.input.justTouched()) {
			ball.velocity.y += 50;
			ball.position.x +=50;
		}
		ball.update();
		batch.begin();

		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(mario, ball.position.x + 140, ball.position.y + 140, mario.getWidth()/6 , mario.getHeight()/6  );
		batch.draw(browser,900,140, browser.getWidth(), browser.getHeight() );


		batch.end();
		if (((ball.position.x >= 700) && (ball.position.x <= 950)) && ((ball.position.y >= 0) && (ball.position.y <= 400))){
			ball.position.x = 140;
			ball.position.y = 140;
		}
	}
	
	@Override
	public void dispose () {

		batch.dispose();
		background.dispose();
		mario.dispose();
		browser.dispose();
	}
}
