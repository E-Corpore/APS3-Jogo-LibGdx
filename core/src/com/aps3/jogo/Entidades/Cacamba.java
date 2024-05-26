package com.aps3.jogo.Entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Cacamba extends SpriteBatch {
    private final int x;
    private final int y;
    private tipoLixo tipo;
    private Texture texturaCacamba;
    private final Rectangle rectangle;
    private String nome;
    private int lixosRecebidos;

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
                this.nome = "Container de lixo de Metal";
                break;
            case PAPEL:
                this.texturaCacamba = papel;
                this.nome = "Container de lixo de Papel";
                break;
            case PLASTICO:
                this.texturaCacamba = plastico;
                this.nome = "Container de lixo de Plastico";
                break;
            case VIDRO:
                this.texturaCacamba = vidro;
                this.nome = "Container de lixo de Vidro";
                break;
            case ORGANICO:
                this.texturaCacamba = organico;
                this.nome = "Container de lixo Organico";
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
    public Rectangle getRectangle() {
        return rectangle;
    }
    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public tipoLixo getTipo() {
        return tipo;
    }
    public void setTipo(tipoLixo tipo) {
        this.tipo = tipo;
    }
    public void setLixosRecebidos() {
        this.lixosRecebidos++;
    }
    public int getLixosRecebidos() {
        return lixosRecebidos;
    }
}
