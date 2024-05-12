package com.aps3.jogo.Entidades;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;


public class Player extends SpriteBatch{
    //public SpriteBatch batch;
    private TextureAtlas textureAtlas;
    public Animation animation;


    public Player(){
        //batch = new SpriteBatch();
        textureAtlas = new TextureAtlas(Gdx.files.internal("frente.atlas"));

        animation = new Animation(0f, textureAtlas.getRegions());

    }

}
