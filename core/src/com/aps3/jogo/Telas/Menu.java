package com.aps3.jogo.Telas;

import com.aps3.jogo.Jogo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class Menu implements Screen{
    private final Stage stage;
    private final Texture backgroundTexture;
    private final SpriteBatch batch;

    public Menu() {

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        BitmapFont fonteBotoes = new BitmapFont(Gdx.files.internal("fonte/mix-bit48.fnt"));

        // Carregar a textura do fundo
        backgroundTexture = new Texture(Gdx.files.internal("menu/fundo-menu.jpeg"));
        batch = new SpriteBatch();

        Texture playTexture = new Texture(Gdx.files.internal("menu/fundoBotao.png"));
        Drawable playDrawable = new TextureRegionDrawable(playTexture);
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle( playDrawable, playDrawable, playDrawable, fonteBotoes);
        textButtonStyle.fontColor = Color.GRAY;

        // Carregar os botões
        TextButton playButton = new TextButton("Play", textButtonStyle);
        TextButton tutorialButton = new TextButton("Tutorial", textButtonStyle);
        TextButton sairButton = new TextButton("Sair", textButtonStyle);

        // Adicionar listeners aos botões
        playButton.addListener(e -> {
            if (playButton.isPressed()) {
                Jogo.getInstance().setScreen(new Play());
            }
            return true;
        });
        tutorialButton.addListener(e -> {
            if (tutorialButton.isPressed()) {
                System.out.println("Tutorial");
                Jogo.getInstance().setScreen(new Tutorial());
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
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
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
