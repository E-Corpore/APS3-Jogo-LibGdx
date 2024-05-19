package com.aps3.jogo;

import com.aps3.jogo.Entidades.Player;
import com.aps3.jogo.Telas.Menu;
import com.aps3.jogo.Telas.Play;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.tools.classfile.ConstantPool;

public class Jogo extends Game {
	private static Jogo instancia = new Jogo();
	//private DesktopLauncher janela;
	private static Play play = new Play();
	private static Menu menu;

	public Jogo() {

	}
	@Override
	public void create () {
		menu = new Menu();
		setScreen(menu);
	}
	public void telas(Screen tela){
		setScreen(tela);
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
		return instancia;
	}
	public static Play getPlay() {
		return play;
	}
	public static void setPlay(Play _play) {
		play = _play;
	}


}
