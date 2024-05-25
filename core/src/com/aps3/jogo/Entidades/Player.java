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
    private TextureAtlas atlasPlayer;

    private Animation animation;
    private int largura=36,altura=68;
    private float x=2328,y=1965;
    private float anteriorX=2328,anteriorY=1965;
    private Direcao direcao;
    private boolean correndo = false;
    private int velocidade;

    public Player(){
        atlasPlayer = new TextureAtlas(Gdx.files.internal("player.atlas"));
        direcao = Direcao.BAIXO;
        setDirecao(direcao);
    }
    public int getAltura() {
        return altura;
    }
    public int getLargura() {
        return largura;
    }
    public float getX() {
        return x;
    }
    public void andarX(float v) {
        this.anteriorX = this.x;
        this.x = x+v;
    }
    public float getY() {
        return y;
    }

    public void andarY(float v) {
        this.anteriorY = this.y;
        this.y = y+ v;
    }
    public void recuar(){
        //this.x = anteriorX;
        //this.y = anteriorY;
    }
    public Animation getAnimation() {
        return animation;
    }
    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
        float velocidadeAnimacaoAndando = 0.16f;
        float velocidadeAnimacaoCorrendo = 0.08f;
        switch (this.direcao){
            case BAIXO:
                if(correndo){
                    animation = new Animation(velocidadeAnimacaoCorrendo, atlasPlayer.findRegions("correrFrente"));
                }else {
                    animation = new Animation(velocidadeAnimacaoAndando, atlasPlayer.findRegions("frente"));
                }
                break;
            case CIMA:
                if(correndo){
                    animation = new Animation(velocidadeAnimacaoCorrendo, atlasPlayer.findRegions("correrCosta"));
                }else {
                    animation = new Animation(velocidadeAnimacaoAndando, atlasPlayer.findRegions("costa"));
                }
                break;
            case ESQUERDA:
                if(correndo){
                    animation = new Animation(velocidadeAnimacaoCorrendo, atlasPlayer.findRegions("correrEsquerda"));
                }else {
                    animation = new Animation(velocidadeAnimacaoAndando, atlasPlayer.findRegions("esquerda"));
                }
                break;
            case DIREITA:
                if(correndo){
                    animation = new Animation(velocidadeAnimacaoCorrendo, atlasPlayer.findRegions("correrDireita"));
                }else {
                    animation = new Animation(velocidadeAnimacaoAndando, atlasPlayer.findRegions("direita"));
                }
                break;
        }
    }
    public int getVelocidade() {
        return velocidade;
    }
    public void setCorrendo(boolean correndo) {
        this.velocidade = correndo?4:2;
        //this.animation.setFrameDuration(correndo?0.08f:0.16f);
        this.correndo = correndo;
    }
}
