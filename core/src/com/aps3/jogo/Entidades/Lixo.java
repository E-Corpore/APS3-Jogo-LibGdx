package com.aps3.jogo.Entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Lixo extends SpriteBatch{
    private Texture texturaLixo;
    private tipoLixo tipo;
    private Vector2 posicao= new Vector2();
    private Texture metal1 = new Texture("img/tipo-lixo/metal1.png");
    private Texture metal2 = new Texture("img/tipo-lixo/metal2.png");
    private Texture papel1 = new Texture("img/tipo-lixo/papel1.png");
    private Texture papel2 = new Texture("img/tipo-lixo/papel2.png");
    private Texture plastico1 = new Texture("img/tipo-lixo/plastico1.png");
    private Texture plastico2 = new Texture("img/tipo-lixo/plastico2.png");
    private Texture vidro1 = new Texture("img/tipo-lixo/vidro1.png");
    private Texture vidro2 = new Texture("img/tipo-lixo/vidro2.png");
    private Texture organico1 = new Texture("img/tipo-lixo/organico1.png");
    private Texture organico2 = new Texture("img/tipo-lixo/organico2.png");

    private Rectangle rectangle;

    private Random aleatorio = new Random();

    public Lixo(tipoLixo tipo, Vector2 posicao){
        this.tipo = tipo;
        //this.posicao = posicao;
        this.posicao.x = (posicao.x*128)+46;
        this.posicao.y = (posicao.y*128)+46;
        iniciar();
        rectangle = new Rectangle(this.posicao.x, this.posicao.y, 36, 36);
    }
    private void iniciar(){
        switch (tipo) {
            case METAL:
                if (aleatorio.nextInt(2) == 0) {
                    this.texturaLixo = metal1;
                }else {
                    this.texturaLixo = metal2;
                }
                break;
            case PAPEL:
                if (aleatorio.nextInt(2) == 0) {
                    this.texturaLixo = papel1;
                }else {
                    this.texturaLixo = papel2;
                }
                break;
            case PLASTICO:
                if (aleatorio.nextInt(2) == 0) {
                    this.texturaLixo = plastico1;
                }else {
                    this.texturaLixo = plastico2;
                }
                break;
            case VIDRO:
                if (aleatorio.nextInt(2) == 0) {
                    this.texturaLixo = vidro1;
                }else {
                    this.texturaLixo = vidro2;
                }
                break;
            case ORGANICO:
                if (aleatorio.nextInt(2) == 0) {
                    this.texturaLixo = organico1;
                }else {
                    this.texturaLixo = organico2;
                }
                break;
            default:

        }
        //System.out.println("Tipo: "+tipo+" X: "+posicao.x+" Y: "+posicao.y);
    }
    public Texture getImagem(){
        return this.texturaLixo;
    }
    public float getX() {
        return this.posicao.x;
    }
    public float getY() {
        return this.posicao.y;
    }
    public void setX(int x) {
        this.posicao.x = x;
    }
    public void setY(int y) {
        this.posicao.y = y;
    }
    public Rectangle getRectangle() {
        return this.rectangle;
    }
}
