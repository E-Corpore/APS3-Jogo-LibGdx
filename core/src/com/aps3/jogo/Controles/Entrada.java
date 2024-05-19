package com.aps3.jogo.Controles;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class Entrada implements InputProcessor {
    public boolean cima;
    public boolean baixo;
    public boolean direita;
    public boolean esquerda;
    public boolean esc = false;
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.UP) {
            cima = true;
        }
        if (keycode == Input.Keys.DOWN) {
            baixo = true;
        }
        if (keycode == Input.Keys.RIGHT) {
            direita = true;
        }
        if (keycode == Input.Keys.LEFT) {
            esquerda = true;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.ESCAPE) {
            esc = !esc;
        }
        if (keycode == Input.Keys.UP) {
            cima = false;
        }
        if (keycode == Input.Keys.DOWN) {
            baixo = false;
        }
        if (keycode == Input.Keys.RIGHT) {
            direita = false;
        }
        if (keycode == Input.Keys.LEFT) {
            esquerda = false;
        }

        return false;
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
