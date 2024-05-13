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
    private TextureAtlas textureAtlasFrente;
    private TextureAtlas textureAtlasCosta;
    private TextureAtlas textureAtlasDireita;
    private TextureAtlas textureAtlasEsquerda;
    public Animation animation;
    public int largura=48,altura=90;


    public Player(){
        //batch = new SpriteBatch();
        textureAtlasFrente = new TextureAtlas(Gdx.files.internal("frente.atlas"));
        textureAtlasCosta = new TextureAtlas(Gdx.files.internal("costa.atlas"));
        textureAtlasDireita = new TextureAtlas(Gdx.files.internal("direita.atlas"));
        textureAtlasEsquerda = new TextureAtlas(Gdx.files.internal("esquerda.atlas"));
        frente();

    }
    public void frente(){
        animation = new Animation(0f, textureAtlasFrente.getRegions());
    }
    public void costa(){
        animation = new Animation(0f, textureAtlasCosta.getRegions());
    }
    public void direita(){
        animation = new Animation(0f, textureAtlasDireita.getRegions());
    }
    public void esquerda(){
        animation = new Animation(0f, textureAtlasEsquerda.getRegions());
    }

}
