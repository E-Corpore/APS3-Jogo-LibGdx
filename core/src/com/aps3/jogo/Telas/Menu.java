package com.aps3.jogo.Telas;

import com.aps3.jogo.Jogo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class Menu implements Screen{
    private final Jogo jogo;
    private Play play;
    private Stage stage;
    private Texture backgroundTexture;
    private SpriteBatch batch;

    public Menu() {

        this.jogo = new Jogo();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Carregar a textura do fundo
        backgroundTexture = new Texture(Gdx.files.internal("menu/fundo-menu.jpeg"));
        batch = new SpriteBatch();

        // Carregar os assets das imagens dos botões
        //Skin skin = new Skin();
        Texture playTexture = new Texture(Gdx.files.internal("menu/btnIniciar.png"));
        Drawable playDrawable = new TextureRegionDrawable(playTexture);
        ImageButton playButton = new ImageButton(playDrawable);

        Texture skinTexture = new Texture(Gdx.files.internal("menu/btnSkin.png"));
        Drawable skinDrawable = new TextureRegionDrawable(skinTexture);
        ImageButton skinButton = new ImageButton(skinDrawable);

        Texture lojaTexture = new Texture(Gdx.files.internal("menu/btnLoja.png"));
        Drawable lojaDrawable = new TextureRegionDrawable(lojaTexture);
        ImageButton lojaButton = new ImageButton(lojaDrawable);

        Texture tutorialTexture = new Texture(Gdx.files.internal("menu/btnTutorial.png"));
        Drawable tutorialDrawable = new TextureRegionDrawable(tutorialTexture);
        ImageButton tutorialButton = new ImageButton(tutorialDrawable);

        Texture sairTexture = new Texture(Gdx.files.internal("menu/btnSair.png"));
        Drawable sairDrawable = new TextureRegionDrawable(sairTexture);
        ImageButton sairButton = new ImageButton(sairDrawable);

        // Adicionar listeners aos botões
        playButton.addListener(e -> {
            if (playButton.isPressed()) {
                new Jogo();
                Jogo.getInstance().setScreen(new Play()); // Mude para a tela do jogo
            }
            return true;
        });

        sairButton.addListener(e -> {
            if (sairButton.isPressed()) {
                Gdx.app.exit(); // Saia do aplicativo
            }
            return true;
        });

        // Usar uma tabela para organizar os botões
        Table table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(playButton).padBottom(20);
        table.row();
        //table.add(skinButton);
        //table.row();
        //table.add(lojaButton);
        //table.row();
        table.add(tutorialButton);
        table.row();
        table.add(sairButton);

        stage.addActor(table);
    }

    @Override
    public void show() {
        // É chamado quando esta tela se torna a tela atual do jogo.
    }

    @Override
    public void render(float delta) {


        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Desenhar o fundo
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Desenhar o Stage
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
