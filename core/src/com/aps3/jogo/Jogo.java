package com.aps3.jogo;

import com.aps3.jogo.screens.Play;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.swing.*;

public class Jogo extends ApplicationAdapter {
	private static Jogo instance;
	public static final boolean DEBUG = true;
	SpriteBatch batch;
	Texture img;

	protected Viewport viewport;

	public Jogo() {
	}
	
	@Override
	public void create () {
		setScreen(new Play());
	}

	@Override
	public void render () {
        super.render();
	}
	@Override
	public void resize(int width, int height) {
        super.resize(width, height);
	}
	
	@Override
	public void dispose () {
        super.dispose();
	}

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    public static Jogo getInstance() {
		return instance;
	}
}
