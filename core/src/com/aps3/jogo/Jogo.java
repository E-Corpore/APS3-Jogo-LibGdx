package com.aps3.jogo;

import com.aps3.jogo.Telas.Play;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Jogo extends Game {
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
