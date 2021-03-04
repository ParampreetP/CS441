package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
//https://stackoverflow.com/questions/42071541/how-to-make-a-texture-jump-up-and-return-to-starting-position-android-java-lib
public class Ball {
    public static final float GRAVITY = -50;
    public static final float BOUNCE_DAMPENING = 0.4f;

    public final Vector2 position = new Vector2();
    public final Vector2 velocity = new Vector2();
    public final Vector2 acceleration = new Vector2(0, GRAVITY);

    public void update (){
        float dt = Gdx.graphics.getDeltaTime();
        velocity.add(acceleration.x * dt, acceleration.y * dt);
        position.add(velocity.x * dt, velocity.y * dt);

        if (position.y <= 0){ // hit ground, so bounce
            position.y = -position.y * BOUNCE_DAMPENING;
            velocity.y = -velocity.y * BOUNCE_DAMPENING;
        }

        if (position.y >= 900){//mario will not be able to fly to high out of screen
            position.y = -position.y;

        }
    }
}
