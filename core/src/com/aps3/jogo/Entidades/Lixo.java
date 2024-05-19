package com.aps3.jogo.Entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Lixo extends SpriteBatch{
    private Texture texturaLixo;
    private int x, y;

    public Lixo(int tipo){

    }
    private void iniciar(){
        texturaLixo = new Texture("img/lixo.png");

    }

}
