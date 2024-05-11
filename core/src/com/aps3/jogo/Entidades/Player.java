package com.aps3.jogo.Entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite {
    private Vector2 velocidade = new Vector2();
    private float speed = 60 * 2, gravity =60 * 1.8f;

    public Player(Sprite sprite) {
        super(sprite);
    }

    public void draw(SpriteBatch spriteBatch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }
    public void update(float delta){
        velocidade.x -= gravity * speed;


        if (velocidade.y > speed){
            velocidade.y = speed;
        } else if (velocidade.x < -speed){
            velocidade.x = -speed;
        }

        setX(getX() + velocidade.x * speed);
        setY(getY() + velocidade.y * speed);
    }
}
