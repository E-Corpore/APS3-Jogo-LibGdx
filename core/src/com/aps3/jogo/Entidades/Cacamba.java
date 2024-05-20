package com.aps3.jogo.Entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Cacamba extends SpriteBatch {
    private int x,y;
    private tipoLixo tipo;
    private Texture texturaCacamba;
    private Rectangle rectangle;

    public Cacamba(tipoLixo tipo,int x,int y) {
        this.tipo = tipo;
        this.x = x*128;
        this.y = y*128;
        rectangle = new Rectangle(this.x, this.y, 128, 128);
        Texture metal = new Texture("img/cacamba/metal.png");
        Texture papel = new Texture("img/cacamba/papel.png");
        Texture plastico = new Texture("img/cacamba/plastico.png");
        Texture vidro = new Texture("img/cacamba/vidro.png");
        Texture organico = new Texture("img/cacamba/organico.png");
        switch (tipo) {
            case METAL:
                this.texturaCacamba = metal;
                break;
            case PAPEL:
                this.texturaCacamba = papel;
                break;
            case PLASTICO:
                this.texturaCacamba = plastico;
                break;
            case VIDRO:
                this.texturaCacamba = vidro;
                break;
            case ORGANICO:
                this.texturaCacamba = organico;
                break;
            default:
        }
    }
    public Texture getImagem() {
        return texturaCacamba;
    }

    public void setImagem(Texture textura) {
        texturaCacamba = textura;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public tipoLixo getTipo() {
        return tipo;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
