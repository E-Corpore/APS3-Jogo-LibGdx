package com.aps3.jogo;

import com.aps3.jogo.Entidades.Player;
import com.aps3.jogo.Telas.Play;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Jogo extends Game {
	//private static Jogo instance;
	//public static final boolean DEBUG = true;

	//private TextureAtlas textureAtlas;
	//public Animation animation;
	private float elapsedTime = 0f ;
	Player player;

	protected Viewport viewport;
	SpriteBatch batch;

	public Jogo() {
	}
	
	@Override
	public void create () {
		setScreen(new Play());
		//setScreen(new Player());
		//batch = new SpriteBatch();
		//player = new Player();
		//textureAtlas = new TextureAtlas(Gdx.files.internal("frente.atlas"));

		//animation = new Animation(1f/5f, textureAtlas.getRegions());
	}

	@Override
	public void render () {
		/*
		elapsedTime += Gdx.graphics.getDeltaTime();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw((TextureRegion) player.animation.getKeyFrame(elapsedTime,true), 0, 0);
		batch.end();

		 */
		super.render();
	}
	@Override
	public void resize(int width, int height) {
        super.resize(width, height);
	}
	
	@Override
	public void dispose () {
        super.dispose();
		batch.dispose();

	}

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }
/*
    public static Jogo getInstance() {
		//return instance;
	}

 */
}
