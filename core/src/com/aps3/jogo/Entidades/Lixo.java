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
    private Rectangle rectangle;
    private String nome;

    public Lixo(tipoLixo tipo, Vector2 posicao){

        this.tipo = tipo;
        //this.posicao = posicao;
        this.posicao.x = (posicao.x*128)+46;
        this.posicao.y = (posicao.y*128)+46;
        iniciar();
        rectangle = new Rectangle(this.posicao.x, this.posicao.y, 36, 36);
    }
    private void iniciar(){
        Texture metal1 = new Texture("img/tipo-lixo/metal1.png");
        Texture metal2 = new Texture("img/tipo-lixo/metal2.png");
        Texture papel1 = new Texture("img/tipo-lixo/papel1.png");
        Texture papel2 = new Texture("img/tipo-lixo/papel2.png");
        Texture plastico1 = new Texture("img/tipo-lixo/plastico1.png");
        Texture plastico2 = new Texture("img/tipo-lixo/plastico2.png");
        Texture vidro1 = new Texture("img/tipo-lixo/vidro1.png");
        Texture vidro2 = new Texture("img/tipo-lixo/vidro2.png");
        Texture organico1 = new Texture("img/tipo-lixo/organico1.png");
        Texture organico2 = new Texture("img/tipo-lixo/organico2.png");
        //Random aleatorio = new Random();
        switch (tipo) {
            case METAL:
                if (new Random().nextInt(2) == 0) {
                    this.texturaLixo = metal1;
                    this.nome = "Lata de refrigerante";
                }else {
                    this.texturaLixo = metal2;
                    this.nome = "Lata de milho";
                }
                break;
            case PAPEL:
                if (new Random().nextInt(2) == 0) {
                    this.texturaLixo = papel1;
                    this.nome = "Jornais";
                }else {
                    this.texturaLixo = papel2;
                    this.nome = "Embalagem de isopor";// É UM TIPO DE PLASTICO
                }
                break;
            case PLASTICO:
                if (new Random().nextInt(2) == 0) {
                    this.texturaLixo = plastico1;
                    this.nome = "Sacola de mercado";
                }else {
                    this.texturaLixo = plastico2;
                    this.nome = "Garrafa pet";
                }
                break;
            case VIDRO:
                if (new Random().nextInt(2) == 0) {
                    this.texturaLixo = vidro1;
                    this.nome = "Garrafa de vidro 1";
                }else {
                    this.texturaLixo = vidro2;
                    this.nome = "Garrafa de vidro 2";
                }
                break;
            case ORGANICO:
                if (new Random().nextInt(2) == 0) {
                    this.texturaLixo = organico1;
                    this.nome = "Maça";
                }else {
                    this.texturaLixo = organico2;
                    this.nome = "Osso";
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
    public tipoLixo getTipo() {
        return tipo;
    }
    public Rectangle getRectangle() {
        return this.rectangle;
    }
    public String getNome() {
        return this.nome;
    }
}
