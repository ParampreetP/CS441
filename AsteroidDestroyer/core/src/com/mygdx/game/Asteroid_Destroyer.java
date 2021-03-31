package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

import static sun.misc.Version.println;

public class Asteroid_Destroyer extends ApplicationAdapter {
	SpriteBatch batch;
	Texture ship, stars;
	Texture ast1, ast2, ast3, ast4, ast5, ast6;
	float shipH, shipW, shipX, shipY;
	float astY, ast1X, ast2X, ast3X, ast4X, ast5X, ast6X;

	int nAst = 6;
	float[] astsY = new float[nAst];
	float[][] astsX = new float[6][nAst];

	ShapeRenderer shape;

	@Override
	public void create () {
		batch = new SpriteBatch();
		ship = new Texture("ship.png");
		stars = new Texture("star.png");

		ast1 = new Texture("asteroid.png");
		ast2 = new Texture("asteroid.png");
		ast3 = new Texture("asteroid.png");
		ast4 = new Texture("asteroid.png");
		ast5 = new Texture("asteroid.png");
		ast6 = new Texture("asteroid.png");

		shape = new ShapeRenderer();

		shipW = Gdx.graphics.getWidth()/6;
		shipH = Gdx.graphics.getHeight()/10;
		shipX = Gdx.graphics.getWidth()/2;
		shipY = Gdx.graphics.getWidth()/10;

		astY = Gdx.graphics.getHeight() - shipW;
		ast1X = Gdx.graphics.getWidth()/2;
		ast2X = Gdx.graphics.getWidth()/2 + 200;
		ast3X = Gdx.graphics.getWidth()/2 + 400;
		ast4X = Gdx.graphics.getWidth()/2 - 200;
		ast5X = Gdx.graphics.getWidth()/2 - 450;
		ast6X = Gdx.graphics.getWidth()/2 - 650;

		for (int i = 0; i < nAst; i++){
			astsY[i] = Gdx.graphics.getHeight()+i * shipH;
			//Gdx.graphics.getHeight()+i * Gdx.graphics.getHeight()/2;
			Random r1 = new Random();
			Random r2 = new Random();
			Random r3 = new Random();
			Random r4 = new Random();
			Random r5 = new Random();
			Random r6 = new Random();

			astsX[0][i] = r1.nextFloat() * Gdx.graphics.getHeight();
			astsX[1][i] = r2.nextFloat() * Gdx.graphics.getHeight();
			astsX[2][i] = r3.nextFloat() * Gdx.graphics.getHeight();
			astsX[3][i] = r4.nextFloat() * Gdx.graphics.getHeight();
			astsX[4][i] = r5.nextFloat() * Gdx.graphics.getHeight();
			astsX[5][i] = r6.nextFloat() * Gdx.graphics.getHeight();

		}

	}

	@Override
	public void render () {
		batch.begin();

		batch.draw(stars, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(ship, shipX, shipY, shipW, shipH);


		shipMove();

		for (int i = 0; i <nAst; i++){
			if (astsY[i] < shipY){
				astsY[i] = Gdx.graphics.getHeight()+i * shipH;
				Random r1 = new Random();
				Random r2 = new Random();
				Random r3 = new Random();
				Random r4 = new Random();
				Random r5 = new Random();
				Random r6 = new Random();

				astsX[0][i] = r1.nextFloat() * Gdx.graphics.getHeight();
				astsX[1][i] = r2.nextFloat() * Gdx.graphics.getHeight();
				astsX[2][i] = r3.nextFloat() * Gdx.graphics.getHeight();
				astsX[3][i] = r4.nextFloat() * Gdx.graphics.getHeight();
				astsX[4][i] = r5.nextFloat() * Gdx.graphics.getHeight();
				astsX[5][i] = r6.nextFloat() * Gdx.graphics.getHeight();
			}

			astsY[i] = astsY[i] - 5;
			batch.draw(ast1, astsX[0][i], astsY[i], shipW, shipH);
			batch.draw(ast2, astsX[1][i], astsY[i], shipW, shipH);
			batch.draw(ast3, astsX[2][i], astsY[i], shipW, shipH);
			batch.draw(ast4, astsX[3][i], astsY[i], shipW, shipH);
			batch.draw(ast5, astsX[4][i], astsY[i], shipW, shipH);
			batch.draw(ast6, astsX[5][i], astsY[i], shipW, shipH);


		}
		shape.begin(ShapeRenderer.ShapeType.Filled);
		for (int i = 0; i <nAst; i++) {
			shape.setColor(Color.BLUE);
			shape.circle(astsX[0][i] + shipW/2, astsY[i] + shipH/2, shipW / 2);
			shape.circle(astsX[1][i] + shipW/2, astsY[i]+ shipH/2, shipW / 2);
			shape.circle(astsX[2][i] + shipW/2, astsY[i]+ shipH/2, shipW / 2);
			shape.circle(astsX[3][i] + shipW/2, astsY[i]+ shipH/2, shipW / 2);
			shape.circle(astsX[4][i] + shipW/2, astsY[i]+ shipH/2, shipW / 2);
			shape.circle(astsX[5][i] + shipW/2, astsY[i]+ shipH/2, shipW / 2);
			shape.circle(shipX + shipW/2, shipY + shipH/2, shipW / 2);
		}
		shape.end();


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
		int speed = 4;
		if (accelerometerX  > 0){
			shipX = shipX - speed;
			if (shipX == 0){
				shipX = shipX + speed;
			}
		} else if(accelerometerX  < 0){
			shipX = shipX + speed;
			if (shipX == Gdx.graphics.getWidth() - shipW){
				shipX = shipX - speed;
			}
		}
	}






}
