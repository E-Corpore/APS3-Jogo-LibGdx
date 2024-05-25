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
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Tutorial implements Screen {
    private Stage stage;
    private Texture backgroundTexture;
    private SpriteBatch batch;
    private BitmapFont fonteTexto;
    private Label labelDescricaoJogo;
    private Label labelDescriçaoCacamba;
    private ImageButton botaoVoltar;

    private Image teclasWASD;
    private Image teclasSetas;
    private Image teclaEsc;
    private Image teclaE;
    private Image personagemLixeira;
    private Image inventarioLixeira;

    public Tutorial(){
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        fonteTexto = new BitmapFont(Gdx.files.internal("fonte/PartyConfettiRegular.fnt"));
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = fonteTexto;
        labelStyle.fontColor = Color.WHITE;

        String descriçaoJogo = "O principal objetivo do jogo é coletar todos os 50 lixos espalhados pela cidade e leva-los até sua devida caçamba."
                + "Cada lixo descartado de maneira correta você ganha um ponto em 'Acerto', caso contrario, você ganha um ponto em 'Erro'."
                + "Caso você tenha mais acertos do que erros, você ganha o jogo. Veja os controles abaixo: ";

        String descriçaoCacamba = "Ao abrir a caçamba, você deve clicar no lixo de seu inventário no container correspondente";

        labelDescricaoJogo = new Label(descriçaoJogo, labelStyle);
        labelDescricaoJogo.setWrap(true);

        labelDescriçaoCacamba = new Label(descriçaoCacamba, labelStyle);
        labelDescriçaoCacamba.setWrap(true);

        // Carregar a textura do fundo
        backgroundTexture = new Texture(Gdx.files.internal("menu/fundoTutorial.png"));
        batch = new SpriteBatch();

        // Carregando imagens
        teclasWASD = new Image(new Texture(Gdx.files.internal("menu/teclasWASD.png")));
        teclasSetas = new Image(new Texture(Gdx.files.internal("menu/teclasSetas.png")));
        teclaEsc = new Image(new Texture(Gdx.files.internal("menu/teclaEsc.png")));
        teclaE  = new Image(new Texture(Gdx.files.internal("menu/teclaE.png")));
        personagemLixeira = new Image(new Texture(Gdx.files.internal("menu/personagemLixeira.png")));
        inventarioLixeira = new Image(new Texture(Gdx.files.internal("menu/inventarioLixeira.png")));

        // Botão Voltar
        botaoVoltar = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("menu/btnVoltar.png"))));

        botaoVoltar.addListener(e -> {
            if (botaoVoltar.isPressed()) {
                voltar();
            }
            return true;
        });

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        // Adicionando os elementos na tabela
        table.add(labelDescricaoJogo).colspan(4).pad(10).width(650);
        table.row();

        table.add(teclasWASD).pad(10);
        table.add(new Label("ou",labelStyle)).pad(10);
        table.add(teclasSetas).pad(10);
        table.add(new Label("= Movimentação do jogador",labelStyle)).pad(10);
        table.row();

        table.add(teclaEsc).colspan(3).pad(10);
        table.add(new Label("= Pausa o jogo",labelStyle)).pad(10);
        table.row();

        table.add(personagemLixeira).pad(10);
        table.add(new Label("+",labelStyle)).pad(10);
        table.add(teclaE).pad(10);
        table.add(new Label(" = Abre a lixeira proxima",labelStyle)).pad(10);
        table.row();

        Table tiposLixeira = new Table();
        //tiposLixeira.setFillParent(true);
        tiposLixeira.center();

        tiposLixeira.add(new Image(new Texture(Gdx.files.internal("img/cacamba/plastico.png")))).width(48).height(48).pad(6);
        tiposLixeira.add(new Label("- Plástico",labelStyle)).pad(10);
        tiposLixeira.row();
        tiposLixeira.add(new Image(new Texture(Gdx.files.internal("img/cacamba/organico.png")))).width(48).height(48).pad(6);
        tiposLixeira.add(new Label("- Organico",labelStyle)).pad(10);;
        tiposLixeira.row();
        tiposLixeira.add(new Image(new Texture(Gdx.files.internal("img/cacamba/metal.png")))).width(48).height(48).pad(6);
        tiposLixeira.add(new Label("- Metal",labelStyle)).pad(10);;
        tiposLixeira.row();
        tiposLixeira.add(new Image(new Texture(Gdx.files.internal("img/cacamba/papel.png")))).width(48).height(48).pad(6);
        tiposLixeira.add(new Label("- Papel",labelStyle)).pad(10);;
        tiposLixeira.row();
        tiposLixeira.add(new Image(new Texture(Gdx.files.internal("img/cacamba/vidro.png")))).width(48).height(48).pad(6);
        tiposLixeira.add(new Label("- Vidro",labelStyle)).pad(10);;
        tiposLixeira.row();

        table.add(inventarioLixeira).pad(10);
        table.add(labelDescriçaoCacamba).colspan(2).pad(10).width(300);
        table.add(tiposLixeira);
        table.row();

        table.add(botaoVoltar).colspan(4).pad(10);
        table.row();

        stage.addActor(table);
    }
    public void voltar(){
        Jogo.getInstance().setScreen(new Menu());
    }
    @Override
    public void show() {

    }
    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Desenhar o fundo
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        //stage.act(Gdx.graphics.getDeltaTime());
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
        fonteTexto.dispose();    }
}
