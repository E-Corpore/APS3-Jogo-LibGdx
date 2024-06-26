package com.aps3.jogo.Telas;

import com.aps3.jogo.Controles.Colisao;
import com.aps3.jogo.Controles.Entrada;
import com.aps3.jogo.Controles.PosicaoLixo;
import com.aps3.jogo.Entidades.*;
import com.aps3.jogo.Jogo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static com.aps3.jogo.Entidades.tipoLixo.*;

public class Play implements Screen{

    private int qtdLixo = 50;

    // Mapa
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    // Player
    Player player;
    Vector3 posicaoPlayer;
    float playerXtela,playerYtela;
    boolean podeAndar = true;
    private float elapsedTime = 0f;
    Entrada entrada;
    private int largura,altura;

    //variaveis para detectar colisão
    private TiledMapTileLayer camadaColisao;
    private TiledMapTileLayer camadaRua;
    private Colisao colisao;
    private Rectangle playerRec;

    // Pausar jogo
    private boolean pause=false;
    private SpriteBatch quadrado;
    private Texture transparencia;
    private SpriteBatch btnMenuInicial;
    private Texture texturaMenuInicial;
    private int btnMenuX, btnMenuY;
    private SpriteBatch btnReiniciar;
    private Texture texturaReiniciar;
    private int btnReiniciarX, btnReiniciarY;

    // Adicionar lixos no mapa
    private List<Lixo> lixos;
    private PosicaoLixo posicaoLixos;

    // Adicionando mochila
    Texture texturaMochila;
    private Batch mochila;
    private List<Lixo> itensMochila;

    // Caçambas
    private List<Cacamba> cacambas;
    private Boolean cacambaAberta=false;

    // Placar
    private Stage pontuacao;
    private Label labelTextoAcertos;
    private Label labelAcertos;
    private int acertos;
    private Label labelX;
    private Label labelTextoErros;
    private Label labelErros;
    private int erros;
    private Image fundoPlacar;
    private Label labelQtdLixo;

    //Inventario
    private Stage inventario;
    private SpriteBatch quadradoInventario;
    private Texture transparenciaInventario;
    private Cacamba atualCacamba;
    private Label labelAtualCacamba;
    private Drawable drawableVazio;
    private ImageButton lixo1;
    private ImageButton lixo2;
    private ImageButton lixo3;
    private ImageButton lixo4;
    private ImageButton lixo5;
    private Label labelLixo1;
    private Label labelLixo2;
    private Label labelLixo3;
    private Label labelLixo4;
    private Label labelLixo5;

    // Fim do jogo
    private Boolean fimJogo = false;
    private Stage fimDoJogo;
    private Image imageFimJogo;
    private ImageButton botaoMenu;
    private ImageButton botaoReiniciar;
    private Label labelFim;
    private Label labelLixosPlastico;
    private Label labelLixosOrganico;
    private Label labelLixosMetal;
    private Label labelLixosPapel;
    private Label labelLixosVidro;
    private Label labelFimAcertos;
    private Label labelFimErros;
    private Label fimAcertos;
    private Label fimErros;

    public Play(){
    }
    public void show() {
        map = new TmxMapLoader().load("mapa.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();

        largura = Gdx.graphics.getWidth();
        altura = Gdx.graphics.getHeight();

        //map.getLayers().get(3).getObjects().get(1);

        // Player
        player = new Player();
        camera.position.x = player.getX();
        camera.position.y = player.getY();
        colisao = new Colisao(player);
        entrada = new Entrada();
        camadaColisao = (TiledMapTileLayer) map.getLayers().get("Colisao");
        camadaRua = (TiledMapTileLayer) map.getLayers().get("Ruas");
        playerRec = new Rectangle(player.getX(), player.getY(),player.getLargura(), player.getAltura());

        // Itens para pausa
        transparencia = new Texture("menu/quadradoTransparente.png");
        texturaMenuInicial = new Texture("menu/btnMenuInicial.png");
        texturaReiniciar = new Texture("menu/btnReiniciar.png");
        quadrado = new SpriteBatch();
        btnMenuInicial = new SpriteBatch();
        btnReiniciar = new SpriteBatch();

        // Lixos
        TiledMapTileLayer camadaLixos = (TiledMapTileLayer) map.getLayers().get("Lixos");
        posicaoLixos = new PosicaoLixo(camadaLixos);
        carregarLixos();

        // Mochila
        texturaMochila = new Texture("img/mochila.png");
        mochila = new SpriteBatch();
        itensMochila = new ArrayList<>();

        // Caçambas
        cacambas = new ArrayList<>();
        cacambas.add(new Cacamba(METAL,10,16));
        cacambas.add(new Cacamba(PAPEL,13,16));
        cacambas.add(new Cacamba(PLASTICO,15,15));
        cacambas.add(new Cacamba(VIDRO,10,14));
        cacambas.add(new Cacamba(ORGANICO,13,14));

        // Pontuação
        BitmapFont fonte = new BitmapFont(Gdx.files.internal("fonte/PartyConfettiRegular.fnt"));
        BitmapFont fonteNumeros = new BitmapFont(Gdx.files.internal("fonte/numeros-mix-bit.fnt"));
        BitmapFont fonteGrande = new BitmapFont(Gdx.files.internal("fonte/mix-bit48.fnt"));

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = fonte;
        labelStyle.fontColor = Color.WHITE;

        Label.LabelStyle numerosStyle = new Label.LabelStyle();
        numerosStyle.font = fonteNumeros;
        numerosStyle.fontColor = Color.WHITE;

        Label.LabelStyle labelGrande = new Label.LabelStyle();
        labelGrande.font = fonteGrande;
        labelGrande.fontColor = Color.BLACK;

        // Pontuação
        Texture texturaPlacar = new Texture(Gdx.files.internal("img/placar.png"));

        fundoPlacar = new Image(new TextureRegionDrawable(new TextureRegion(texturaPlacar)));
        fundoPlacar.setBounds((float) largura /2-160, altura-40,320,40);

        pontuacao = new Stage(new ScreenViewport());
        labelTextoAcertos = new Label("Acertos",labelStyle);
        labelTextoErros = new Label("Erros", labelStyle);
        labelAcertos = new Label("0", numerosStyle);
        labelErros = new Label("0", numerosStyle);
        labelX = new Label("X", labelStyle);
        labelQtdLixo = new Label("Lixos restantes: "+qtdLixo, labelStyle);
        labelTextoAcertos.setBounds((float) largura /2-160,altura-40,80,40);
        labelTextoErros.setBounds((float) largura /2+80,altura-40,80,40);
        labelAcertos.setBounds((float) largura /2-80,altura-40,80,40);
        labelX.setBounds((float) largura /2-9,altura-40,18,40);
        labelErros.setBounds((float) largura /2,altura-40,80,40);
        labelQtdLixo.setBounds(largura-160,altura-40,160,40);

        labelTextoAcertos.setAlignment(Align.left);
        labelTextoErros.setAlignment(Align.right);
        labelAcertos.setAlignment(Align.center);
        labelErros.setAlignment(Align.center);
        labelX.setAlignment(Align.center);
        labelQtdLixo.setAlignment(Align.center);

        pontuacao.addActor(fundoPlacar);
        pontuacao.addActor(labelTextoAcertos);
        pontuacao.addActor(labelAcertos);
        pontuacao.addActor(labelX);
        pontuacao.addActor(labelTextoErros);
        pontuacao.addActor(labelErros);
        pontuacao.addActor(labelQtdLixo);

        // Inventario
        quadradoInventario = new SpriteBatch();
        transparenciaInventario = new Texture("img/quadradoInventario.png");
        inventario = new Stage(new ScreenViewport());

        Texture botaoLixoVazio = new Texture(Gdx.files.internal("img/tipo-lixo/nenhum.png"));
        Texture fundoTexturaLixo = new Texture(Gdx.files.internal("img/tipo-lixo/fundoBotaoLixo.png"));
        drawableVazio = new TextureRegionDrawable(botaoLixoVazio);
        Drawable fundoBotaoLixo = new TextureRegionDrawable(fundoTexturaLixo);

        labelAtualCacamba = new Label("", labelStyle);
        labelLixo1 = new Label("", labelStyle);
        labelLixo2 = new Label("", labelStyle);
        labelLixo3 = new Label("", labelStyle);
        labelLixo4 = new Label("", labelStyle);
        labelLixo5 = new Label("", labelStyle);

        lixo1 = new ImageButton(drawableVazio);
        lixo2 = new ImageButton(drawableVazio);
        lixo3 = new ImageButton(drawableVazio);
        lixo4 = new ImageButton(drawableVazio);
        lixo5 = new ImageButton(drawableVazio);

        lixo1.getStyle().up= fundoBotaoLixo;
        lixo2.getStyle().up= fundoBotaoLixo;
        lixo3.getStyle().up= fundoBotaoLixo;
        lixo4.getStyle().up= fundoBotaoLixo;
        lixo5.getStyle().up= fundoBotaoLixo;

        lixo1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {jogarNaCacamba(1);}
        });
        lixo2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {jogarNaCacamba(2);}
        });
        lixo3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {jogarNaCacamba(3);}
        });
        lixo4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {jogarNaCacamba(4);}
        });
        lixo5.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {jogarNaCacamba(5);}
        });

        labelAtualCacamba.setPosition(largura-320,528);
        labelLixo1.setPosition(228,236);
        lixo1.setBounds(128,200,72,72);
        labelLixo2.setPosition(228,336);
        lixo2.setBounds(128,300,72,72);
        labelLixo3.setPosition(228,436);
        lixo3.setBounds(128,400,72,72);
        labelLixo4.setPosition(228,536);
        lixo4.setBounds(128,500,72,72);
        labelLixo5.setPosition(228,636);
        lixo5.setBounds(128,600,72,72);

        atualCacamba = new Cacamba(METAL,largura-286, 360);

        inventario.addActor(labelAtualCacamba);
        inventario.addActor(labelLixo1);
        inventario.addActor(labelLixo2);
        inventario.addActor(labelLixo3);
        inventario.addActor(labelLixo4);
        inventario.addActor(labelLixo5);
        inventario.addActor(lixo1);
        inventario.addActor(lixo2);
        inventario.addActor(lixo3);
        inventario.addActor(lixo4);
        inventario.addActor(lixo5);

        // Fim do jogo
        fimDoJogo = new Stage(new ScreenViewport());
        imageFimJogo = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("img/fimJogo.png")))));
        imageFimJogo.setBounds((float) largura /2-238, 120,475,664);

        labelLixosPlastico= new Label("Lixos de plastico: ", labelStyle);
        labelLixosOrganico= new Label("Lixos orgânicos: ", labelStyle);
        labelLixosMetal= new Label("Lixos de metal: ", labelStyle);
        labelLixosPapel= new Label("Lixos de papel: ", labelStyle);
        labelLixosVidro= new Label("Lixos de vidro: ", labelStyle);

        labelLixosPlastico.setBounds((float) largura /2-100, 460,100,40);
        labelLixosOrganico.setBounds((float) largura /2-100, 420,100,40);
        labelLixosMetal.setBounds((float) largura /2-100, 380,100,40);
        labelLixosPapel.setBounds((float) largura /2-100, 340,100,40);
        labelLixosVidro.setBounds((float) largura /2-100, 300,100,40);

        Texture TexturaBotaoMenu = new Texture(Gdx.files.internal("menu/btnMenuInicial.png"));
        Texture TexturaBotaoReiniciar = new Texture(Gdx.files.internal("menu/btnReiniciar.png"));
        Drawable drawableMenu = new TextureRegionDrawable(TexturaBotaoMenu);
        Drawable drawableReiniciar = new TextureRegionDrawable(TexturaBotaoReiniciar);

        botaoMenu = new ImageButton(drawableMenu);
        botaoReiniciar = new ImageButton(drawableReiniciar);
        botaoMenu.setPosition(((float) largura /2)-194,150);
        botaoReiniciar.setPosition(((float) largura /2)+54,150);

        botaoMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {voltarMenu();}
        });
        botaoReiniciar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {reiniciarJogo();}
        });

        labelFim= new Label("Fim", labelGrande);
        labelFimAcertos= new Label("Total de Acertos", labelStyle);
        labelFimErros= new Label("Total de erros", labelStyle);
        fimAcertos = new Label("0", numerosStyle);
        fimErros = new Label("0", numerosStyle);

        labelFim.setBounds((float) largura /2-50, 710,100,60);
        labelFim.setAlignment(Align.center);
        labelFimAcertos.setBounds((float) largura /2-100, 580,80,40);
        fimAcertos.setBounds((float) largura /2+50, 580,80,40);
        labelFimErros.setBounds((float) largura /2-100, 540,80,40);
        fimErros.setBounds((float) largura /2+50, 540,80,40);

        fimDoJogo.addActor(imageFimJogo);
        fimDoJogo.addActor(labelFim);
        fimDoJogo.addActor(labelFimAcertos);
        fimDoJogo.addActor(fimAcertos);
        fimDoJogo.addActor(labelFimErros);
        fimDoJogo.addActor(fimErros);
        fimDoJogo.addActor(labelLixosPlastico);
        fimDoJogo.addActor(labelLixosOrganico);
        fimDoJogo.addActor(labelLixosMetal);
        fimDoJogo.addActor(labelLixosPapel);
        fimDoJogo.addActor(labelLixosVidro);
        fimDoJogo.addActor(botaoMenu);
        fimDoJogo.addActor(botaoReiniciar);

        camera.update();
    }
  public void render(float v){
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    elapsedTime += Gdx.graphics.getDeltaTime();
    Gdx.input.setInputProcessor(entrada);

    // Quando pressionado ESC
    if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
      pause = !pause; // Alterna o estado de pause
    }
    // Verifica se passou das bordas
    boolean foraBordaCima;
    boolean foraBordaBaixo;
    boolean foraBordaDireita;
    boolean foraBordaEsquerda;
    {
      foraBordaCima = playerYtela + (float) player.getAltura() / 2 > (float) (altura * 2) / 3;
      foraBordaBaixo = playerYtela + (float) player.getAltura() / 2 < (float) altura / 3;
      foraBordaDireita = playerXtela + (float) player.getLargura() / 2 > (float) (largura * 2) / 3;
      foraBordaEsquerda = playerXtela + (float) player.getLargura() / 2 < (float) largura / 3;
    }

    // Animação quando esta em movimento
    if ((entrada.cima || entrada.baixo || entrada.direita || entrada.esquerda) & podeAndar ){
      posicaoPlayer = camera.project(new Vector3(player.getX(), player.getY(),0));
      playerYtela = posicaoPlayer.y;
      playerXtela = posicaoPlayer.x;
    }else {
      player.getAnimation().setFrameDuration(0f);
    }
    //Movimento caso não esteja pausado
    if (podeAndar) {
        // Movimento para cima
        if (entrada.cima && player.getY() < 4096 - player.getAltura()) {
            if (!colisao.cima(camadaColisao)) {
                player.setCorrendo(colisao.cima(camadaRua));
                player.setDirecao(Direcao.CIMA);
                player.mover();
            }else  {player.recuar();}

            if (foraBordaCima && ((player.getY() + (float) player.getAltura() / 2) < (4096 - ((float) altura / 3)))) {
                camera.translate(0, player.getVelocidade());
                camera.update();
                player.setProjectionMatrix(camera.combined);
            }
        }
        // Movimento para baixo
        if (entrada.baixo && player.getY() > 0) {
             if (!colisao.baixo(camadaColisao)) {
                 player.setCorrendo(colisao.baixo(camadaRua));
                 player.setDirecao(Direcao.BAIXO);
                 player.mover();
            }else {player.recuar();}

            if (foraBordaBaixo && (player.getY() + (float) player.getAltura() / 2) > ((float) altura / 3)) {
                camera.translate(0, -player.getVelocidade());
                camera.update();
                player.setProjectionMatrix(camera.combined);
            }
        }
        // Movimento para a esquerda
        if (entrada.esquerda && player.getX() > 0) {
            if (!colisao.esquerda(camadaColisao)) {
                player.setCorrendo(colisao.esquerda(camadaRua));
                player.setDirecao(Direcao.ESQUERDA);
                player.mover();
            }else {player.recuar();}

            if (foraBordaEsquerda && (player.getX() + (float) player.getLargura() / 2) > ((float) largura / 3)) {
                camera.translate(-player.getVelocidade(), 0);
                camera.update();
                player.setProjectionMatrix(camera.combined);
            }
        }
        // Movimento para a direita
        if (entrada.direita && player.getX() < 4096 - player.getLargura()) {
            if (!colisao.direita(camadaColisao)) {
                player.setCorrendo(colisao.direita(camadaRua));
                player.setDirecao(Direcao.DIREITA);
                player.mover();
            }else {player.recuar();}

            if (foraBordaDireita && (player.getX() + (float) player.getLargura() / 2 < (4096 - (float) largura / 3))) {
                camera.translate(player.getVelocidade(), 0);
                camera.update();
                player.setProjectionMatrix(camera.combined);
            }
        }
    }

    renderer.setView(camera);
    renderer.render(new int[]{2,3,4,5,6,7,8,9,10,11,12});

    //Desenha as caçambas
    for(Cacamba cacamba:cacambas){
      cacamba.begin();
      cacamba.draw(cacamba.getImagem(), cacamba.getX(), cacamba.getY(),128,128);
      cacamba.end();
      cacamba.setProjectionMatrix(camera.combined);
    }
    // Desenha os lixos
    for(Lixo lixo:lixos){
      lixo.begin();
      lixo.draw(lixo.getImagem(), lixo.getX(), lixo.getY(),36,36);
      lixo.end();
      lixo.setProjectionMatrix(camera.combined);
    }

    player.begin();
    player.draw((TextureRegion) player.getAnimation().getKeyFrame(elapsedTime,true), player.getX(), player.getY());
    player.end();

    renderer.render(new int[]{13});

    playerRec = new Rectangle(player.getX(),player.getY(),player.getLargura(),player.getAltura());

    // Pega o item quando enconsta em algum lixo
    if(itensMochila.size()<5) {
      Iterator<Lixo> iterator = lixos.iterator();
      while (iterator.hasNext()) {
          Lixo lixo = iterator.next();
          if (lixo.getRectangle().overlaps(playerRec)) {
              pegarLixo(lixo);
              iterator.remove();
          }
      }
    }

    // Abre o inventario pressionando o E quando está perto da caçamba
    if(Gdx.input.isKeyJustPressed(Input.Keys.E)){
      for (Cacamba cacamba : cacambas){
          if (cacamba.getRectangle().overlaps(playerRec)) {
              entrada.limparTeclas();
              atualCacamba.setImagem(cacamba.getImagem());
              atualCacamba.setNome(cacamba.getNome());
              atualCacamba.setTipo(cacamba.getTipo());
              atualizarInventario();
              cacambaAberta = !cacambaAberta;
          }
      }
    }

    // Desenhar quadrado
    if (cacambaAberta || pause || fimJogo){
      quadrado.begin();
      quadrado.setColor(256, 256, 256, 0.4f);
      quadrado.draw(transparencia, 0, 0, largura, altura);
      quadrado.end();
      podeAndar = false;
    }else{
      podeAndar = true;
    }

    mochila.begin();
    mochila.draw(texturaMochila, (float) largura /2-190, 20);
    mochila.end();

    // Desenha itens da mochila
    for(Lixo lixo:itensMochila){
      lixo.begin();
      lixo.draw(lixo.getImagem(), lixo.getX(), lixo.getY(),36,36);
      lixo.end();
    }
    pontuacao.draw();

    // Desenhar quando abrir inventario
    if (cacambaAberta){
      quadradoInventario.begin();
      quadradoInventario.draw(transparenciaInventario, 80, 128, largura-160, 620);
      quadradoInventario.end();
      Gdx.input.setInputProcessor(inventario);
      inventario.draw();
      atualCacamba.begin();
      atualCacamba.draw(atualCacamba.getImagem(), largura-286, 360);
      atualCacamba.end();
    }
    // Desenhar quando pausado
    if(pause){
      cacambaAberta = false;
      btnMenuInicial.begin();
      btnMenuInicial.draw(texturaMenuInicial,btnMenuX, btnMenuY, texturaMenuInicial.getWidth(), texturaMenuInicial.getHeight());
      btnMenuInicial.end();

      btnReiniciar.begin();
      btnReiniciar.draw(texturaReiniciar, btnReiniciarX, btnReiniciarY, texturaReiniciar.getWidth(), texturaReiniciar.getHeight());
      btnReiniciar.end();
    }

    // Fim do jogo
    if(itensMochila.isEmpty() & qtdLixo<=0){
      fimJogo = true;
    }
    if (fimJogo){
        cacambaAberta = false;
        fimAcertos.setText(acertos);
        fimErros.setText(erros);
        labelLixosMetal.setText("Lixos de metal:    "+cacambas.get(0).getLixosRecebidos());
        labelLixosPapel.setText("Lixos de papel:    "+cacambas.get(1).getLixosRecebidos());
        labelLixosPlastico.setText("Lixos de plastico: "+cacambas.get(2).getLixosRecebidos());
        labelLixosVidro.setText("Lixos de vidro:    "+cacambas.get(3).getLixosRecebidos());
        labelLixosOrganico.setText("Lixos orgânicos:   "+cacambas.get(4).getLixosRecebidos());
        Gdx.input.setInputProcessor(fimDoJogo);
        fimDoJogo.draw();
    }

    if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) & pause){
      float mouseX = Gdx.input.getX();
      float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
      if (mouseX >= btnReiniciarX && mouseX <= btnReiniciarX + texturaReiniciar.getWidth() && mouseY >= btnReiniciarY && mouseY <= btnReiniciarY + texturaReiniciar.getHeight()) {
          reiniciarJogo();
      }
      if (mouseX >= btnMenuX && mouseX <= btnMenuX + texturaReiniciar.getWidth() && mouseY >= btnMenuY && mouseY <= btnMenuY + texturaReiniciar.getHeight()) {
          voltarMenu();
      }
    }
  }
    private void carregarLixos(){
        lixos = new ArrayList<>();
        for(int i =0;i<qtdLixo;i++){
            tipoLixo[] tlixo = tipoLixo.values();
            tipoLixo lixoAleatorio = tlixo[new Random().nextInt(tlixo.length)];
            lixos.add(new Lixo(lixoAleatorio,posicaoLixos.retornaXY()));
        }
    }
    private void pegarLixo(Lixo lixo){
        itensMochila.add(lixo);
        qtdLixo--;
        labelQtdLixo.setText("Lixos restantes: "+qtdLixo);
        lixo.setX((largura/2-190)+(itensMochila.indexOf(lixo)*61)+77);
        lixo.setY(32);
        lixo.getProjectionMatrix().setToOrtho2D(0, 0, largura, altura);
    }
    private void jogarNaCacamba(int item){
        if(itensMochila.size()>=item){
            if (atualCacamba.getTipo() == itensMochila.get(item-1).getTipo()){
                switch (atualCacamba.getTipo()){
                    case METAL:
                        cacambas.get(0).setLixosRecebidos();
                        break;
                    case PAPEL:
                        cacambas.get(1).setLixosRecebidos();
                        break;
                    case PLASTICO:
                        cacambas.get(2).setLixosRecebidos();
                        break;
                    case VIDRO:
                        cacambas.get(3).setLixosRecebidos();
                        break;
                    case ORGANICO:
                        cacambas.get(4).setLixosRecebidos();
                        break;
                }
                acertos +=1;
                itensMochila.remove(item-1);
            }else{
                erros +=1;
            }
            labelAcertos.setText(acertos);
            labelErros.setText(erros);
        }
        atualizarInventario();
    }
    private void atualizarInventario(){

        lixo1.getStyle().imageUp = drawableVazio;
        lixo2.getStyle().imageUp = drawableVazio;
        lixo3.getStyle().imageUp = drawableVazio;
        lixo4.getStyle().imageUp = drawableVazio;
        lixo5.getStyle().imageUp = drawableVazio;
        labelAtualCacamba.setText(atualCacamba.getNome());
        labelLixo1.setText("");
        labelLixo2.setText("");
        labelLixo3.setText("");
        labelLixo4.setText("");
        labelLixo5.setText("");

        if(!itensMochila.isEmpty()){
            labelLixo1.setText(itensMochila.get(0).getNome());
            lixo1.getStyle().imageUp = new TextureRegionDrawable(itensMochila.get(0).getImagem());
        }
        if(itensMochila.size()>=2){
            labelLixo2.setText(itensMochila.get(1).getNome());
            lixo2.getStyle().imageUp = new TextureRegionDrawable(itensMochila.get(1).getImagem());
        }
        if(itensMochila.size()>=3){
            labelLixo3.setText(itensMochila.get(2).getNome());
            lixo3.getStyle().imageUp = new TextureRegionDrawable(itensMochila.get(2).getImagem());
        }
        if(itensMochila.size()>=4){
            labelLixo4.setText(itensMochila.get(3).getNome());
            lixo4.getStyle().imageUp = new TextureRegionDrawable(itensMochila.get(3).getImagem());
        }
        if(itensMochila.size()>=5){
            labelLixo5.setText(itensMochila.get(4).getNome());
            lixo5.getStyle().imageUp = new TextureRegionDrawable(itensMochila.get(4).getImagem());
        }
        for(Lixo lixo:itensMochila){
            lixo.setX((largura/2-190)+(itensMochila.indexOf(lixo)*61)+77);
        }
    }
    private void reiniciarJogo() {
        System.out.println("Reiniciando o Jogo");
        Jogo.getInstance().setScreen(new Play());
        //this.dispose();
    }
    private void voltarMenu() {
        System.out.println("Voltando ao Menu");
        Jogo.getInstance().setScreen(new Menu());
    }
    public void resize(int width, int height){
        camera.viewportWidth = width;
        camera.viewportHeight = height;

        largura = width;
        altura = height;

        quadrado.getProjectionMatrix().setToOrtho2D(0, 0, width, height);

        mochila.getProjectionMatrix().setToOrtho2D(0, 0, width, height);

        for(Lixo lixo:itensMochila){
            lixo.setX((largura/2-190)+(itensMochila.indexOf(lixo)*61)+77);
            lixo.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
        }

        pontuacao.getViewport().update(width, height, true);
        labelTextoAcertos.setPosition((float) largura /2-145,altura-40);
        labelTextoErros.setPosition((float) largura /2+65,altura-40);
        labelAcertos.setPosition((float) largura /2-80,altura-40);
        labelX.setPosition((float) largura /2-9,altura-40);
        labelErros.setPosition((float) largura /2,altura-40);
        labelQtdLixo.setPosition(largura-160,altura-40);
        fundoPlacar.setPosition((float) largura /2-160, altura-40);


        inventario.getViewport().update(width, height, true);
        quadradoInventario.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
        atualCacamba.getProjectionMatrix().setToOrtho2D(0, 0, width, height);

        btnMenuInicial.dispose();
        btnMenuInicial = new SpriteBatch();
        btnMenuX=(largura/3)-(texturaMenuInicial.getWidth()/2);
        btnMenuY=(altura/4)-(texturaMenuInicial.getWidth()/2);

        btnReiniciar.dispose();
        btnReiniciar = new SpriteBatch();
        btnReiniciarX =(largura*2/3)-(texturaReiniciar.getWidth()/2);
        btnReiniciarY =(altura/4)-(texturaReiniciar.getWidth()/2);

        labelAtualCacamba.setPosition(largura-320,528);

        fimDoJogo.getViewport().update(width, height, true);

        imageFimJogo.setPosition((float) largura /2-238, 120);

        labelFim.setPosition((float) largura /2-50, 710);
        labelFimAcertos.setPosition((float) largura /2-100, 580);
        fimAcertos.setPosition((float) largura /2+50, 580);
        labelFimErros.setPosition((float) largura /2-100, 540);
        fimErros.setPosition((float) largura /2+50, 540);

        labelLixosPlastico.setPosition((float) largura /2-100, 460);
        labelLixosOrganico.setPosition((float) largura /2-100, 420);
        labelLixosMetal.setPosition((float) largura /2-100, 380);
        labelLixosPapel.setPosition((float) largura /2-100, 340);
        labelLixosVidro.setPosition((float) largura /2-100, 300);

        botaoMenu.setPosition(((float) largura /2)-194,150);
        botaoReiniciar.setPosition(((float) largura /2)+54,150);

        camera.update();
        player.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
     public void hide(){
        dispose();
     }

     public void dispose(){
        map.dispose();
        renderer.dispose();
        quadrado.dispose();
        pontuacao.dispose();
        inventario.dispose();
     }

}


