package com.aps3.jogo.Controles;

import com.aps3.jogo.Entidades.Direcao;
import com.aps3.jogo.Entidades.Player;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class Entrada implements InputProcessor {
    public boolean cima;
    public boolean baixo;
    public boolean direita;
    public boolean esquerda;
    public boolean esc = false;
    private Player player;

    public Entrada(Player player){
        this.player = player;
    }
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.UP || keycode == Input.Keys.W) {
            cima = true;
        }
        if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
            baixo = true;
        }
        if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
            direita = true;
        }
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A) {
            esquerda = true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.ESCAPE) {
            esc = !esc;
        }
        if (keycode == Input.Keys.UP || keycode == Input.Keys.W) {
            cima = false;
        }
        if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
            baixo = false;
        }
        if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
            direita = false;
        }
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A) {
            esquerda = false;
        }

        return false;
    }
    public void limparTeclas(){
        cima = false;
        baixo = false;
        direita = false;
        esquerda = false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
