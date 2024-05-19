package com.aps3.jogo.Entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Mochila extends SpriteBatch {
    private Texture textura = new Texture("img/mochila.png");
    private int x = 100;
    private int y = 100;
    private int qtd;
    private boolean cheio;
    public Mochila(){

    }
    public Texture getImagem() {
        return this.textura;
    }
}
