package com.aps3.jogo.Entidades;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Player extends SpriteBatch{
    private final TextureAtlas atlasPlayer;

    private Animation animation;
    private float x=1432,y=1965;
    private Direcao direcao;
    private boolean correndo = false;
    private int velocidade;

    public Player(){
        atlasPlayer = new TextureAtlas(Gdx.files.internal("personagem/player.atlas"));
        direcao = Direcao.BAIXO;
        setDirecao(direcao);
    }
    public int getAltura() {
        return 68;
    }
    public int getLargura() {
        return 36;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public void mover(){
        switch (this.direcao){
            case BAIXO:
                this.y -= velocidade;
                break;
            case CIMA:
                this.y += velocidade;
                break;
            case ESQUERDA:
                this.x -= velocidade;
                break;
            case DIREITA:
                this.x += velocidade;
                break;
        }
    }
    public void recuar(){
        animation.setFrameDuration(0f);
        switch (this.direcao){
            case BAIXO:
                this.y ++;
                break;
            case CIMA:
                this.y --;
                break;
            case ESQUERDA:
                this.x ++;
                break;
            case DIREITA:
                this.x --;
                break;
        }
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
        this.correndo = correndo;
    }
}
