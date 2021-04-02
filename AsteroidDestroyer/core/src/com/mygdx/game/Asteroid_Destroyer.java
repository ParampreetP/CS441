package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureArrayData;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Random;

import static sun.misc.Version.println;

public class Asteroid_Destroyer extends ApplicationAdapter {
	SpriteBatch batch;
	Texture ship, stars, gameOver;
	Texture ast1, ast2, ast3, ast4, ast5, ast6;
	float shipH, shipW, shipX, shipY;
	float astY, ast1X, ast2X, ast3X, ast4X, ast5X, ast6X;

	int nAst = 6;
	float[] astsY = new float[nAst];
	float[][] astsX = new float[6][nAst];

	ShapeRenderer shape;
	Circle c_ship, c_ast1[], c_ast2[], c_ast3[], c_ast4[], c_ast5[], c_ast6[];

	enum Screen{
		TITLE, MAIN_GAME, GAME_OVER, INSTR_SCREEN;
	}

	Screen currentScreen = Screen.TITLE;


	private Stage stage;
	private Texture playTexture;
	private Texture InstrTexture;
	private Texture backTexture;

	private TextureRegion myTextureRegion;
	private TextureRegion myTextureRegion2;
	private TextureRegion myTextureRegion3;

	private TextureRegionDrawable myTexRegionDrawable;
	private TextureRegionDrawable myTexRegionDrawable2;
	private TextureRegionDrawable myTexRegionDrawable3;
	private ImageButton gameButton;
	private ImageButton instrButton;
	private ImageButton backButton;




	boolean flag = false;

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
		c_ship = new Circle();
		c_ast1 = new Circle[nAst];
		c_ast2 = new Circle[nAst];
		c_ast3 = new Circle[nAst];
		c_ast4 = new Circle[nAst];
		c_ast5 = new Circle[nAst];
		c_ast6 = new Circle[nAst];

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

			c_ast1[i] = new Circle();
			c_ast2[i] = new Circle();
			c_ast3[i] = new Circle();
			c_ast4[i] = new Circle();
			c_ast5[i] = new Circle();
			c_ast6[i] = new Circle();

		}

		playTexture = new Texture(Gdx.files.internal("play.png"));
		InstrTexture = new Texture(Gdx.files.internal("instruction.png"));
		backTexture = new Texture(Gdx.files.internal("back.png"));
		myTextureRegion = new TextureRegion(playTexture);
		myTextureRegion2 = new TextureRegion(InstrTexture);
		myTextureRegion3 = new TextureRegion(backTexture);
		myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
		myTexRegionDrawable2 = new TextureRegionDrawable(myTextureRegion2);
		myTexRegionDrawable3 = new TextureRegionDrawable(myTextureRegion3);
		gameButton = new ImageButton(myTexRegionDrawable); //Set the button up
		instrButton = new ImageButton(myTexRegionDrawable2);
		backButton = new ImageButton(myTexRegionDrawable3);
		gameButton.setPosition(Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()/2);
		instrButton.setPosition(Gdx.graphics.getWidth()/7, Gdx.graphics.getHeight()/4);
		backButton.setPosition(0,0);
		backButton.setSize(shipW, shipH);
		stage = new Stage(new ScreenViewport()); //Set up a stage for the ui
		stage.addActor(gameButton); //Add the button to the stage to perform rendering and take input.
		stage.addActor(instrButton);
		Gdx.input.setInputProcessor(stage); //Start taking input from the ui


	}

	@Override
	public void render () {
		batch.begin();


		if (currentScreen == Screen.TITLE) {
			stage.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
			stage.draw();

			gameButton.addListener(new EventListener() {//start button
				@Override
				public boolean handle(Event event) {

					if (flag == true){
						shipX = Gdx.graphics.getWidth()/2;
						shipY = Gdx.graphics.getWidth()/10;

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

							c_ast1[i] = new Circle();
							c_ast2[i] = new Circle();
							c_ast3[i] = new Circle();
							c_ast4[i] = new Circle();
							c_ast5[i] = new Circle();
							c_ast6[i] = new Circle();

						}

					}

					currentScreen = Screen.MAIN_GAME;
					return false;
				}
			});

			instrButton.addListener(new EventListener() {//instruction button
				@Override
				public boolean handle(Event event) {
					currentScreen = Screen.INSTR_SCREEN;
					return false;
				}
			});


		} else if (currentScreen == Screen.MAIN_GAME) {


			batch.draw(stars, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			batch.draw(ship, shipX, shipY, shipW, shipH);
			shipMove();
			for (int i = 0; i < nAst; i++) {
				if (astsY[i] < shipY) {
					astsY[i] = Gdx.graphics.getHeight() + i * shipH;
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
			c_ship.set(shipX + shipW / 2, shipY + shipH / 2, shipW / 2);
			//shape.begin(ShapeRenderer.ShapeType.Filled);
			for (int i = 0; i < nAst; i++) {

				/*
			shape.setColor(Color.BLUE);
			shape.circle(astsX[0][i] + shipW/2, astsY[i] + shipH/2, shipW / 2);
			shape.circle(astsX[1][i] + shipW/2, astsY[i]+ shipH/2, shipW / 2);
			shape.circle(astsX[2][i] + shipW/2, astsY[i]+ shipH/2, shipW / 2);
			shape.circle(astsX[3][i] + shipW/2, astsY[i]+ shipH/2, shipW / 2);
			shape.circle(astsX[4][i] + shipW/2, astsY[i]+ shipH/2, shipW / 2);
			shape.circle(astsX[5][i] + shipW/2, astsY[i]+ shipH/2, shipW / 2);
			shape.circle(shipX + shipW/2, shipY + shipH/2, shipW/2 );*/

				c_ast1[i].set(astsX[0][i] + shipW / 2, astsY[i] + shipH / 2, shipW / 2);
				c_ast2[i].set(astsX[1][i] + shipW / 2, astsY[i] + shipH / 2, shipW / 2);
				c_ast3[i].set(astsX[2][i] + shipW / 2, astsY[i] + shipH / 2, shipW / 2);
				c_ast4[i].set(astsX[3][i] + shipW / 2, astsY[i] + shipH / 2, shipW / 2);
				c_ast5[i].set(astsX[4][i] + shipW / 2, astsY[i] + shipH / 2, shipW / 2);
				c_ast6[i].set(astsX[5][i] + shipW / 2, astsY[i] + shipH / 2, shipW / 2);

				if (Intersector.overlaps(c_ship, c_ast1[i]) || Intersector.overlaps(c_ship, c_ast2[i]) || Intersector.overlaps(c_ship, c_ast3[i]) || Intersector.overlaps(c_ship, c_ast4[i]) || Intersector.overlaps(c_ship, c_ast5[i]) || Intersector.overlaps(c_ship, c_ast6[i])) {
					currentScreen = Screen.GAME_OVER;
					flag = true;

				}

			}
			//shape.end();

		} else if (currentScreen == Screen.GAME_OVER){
				gameOver = new Texture("gameover.png");
				batch.draw(gameOver,0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

				if (Gdx.input.justTouched()){
					gameOver.dispose();
					currentScreen = Screen.TITLE;

				}



		} else if (currentScreen == Screen.INSTR_SCREEN){
			batch.draw(stars, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			instrButton.remove();
			gameButton.remove();
			stage.addActor(backButton);
			stage.draw();
			backButton.draw(batch, 1);

			backButton.addListener(new EventListener() {
				@Override
				public boolean handle(Event event) {
					//todo
					//go back to main menu
					return false;
				}
			});


		}
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		stars.dispose();
		ship.dispose();
		stage.dispose();
		gameOver.dispose();
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