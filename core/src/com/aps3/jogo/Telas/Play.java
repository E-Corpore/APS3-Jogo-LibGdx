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
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static com.aps3.jogo.Entidades.tipoLixo.*;

public class Play implements Screen{

    private int qtdLixo = 80;

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
    //private float cameraX,cameraY;
    private boolean foraBordaCima;
    private boolean foraBordaBaixo;
    private boolean foraBordaDireita;
    private boolean foraBordaEsquerda;

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
    private TiledMapTileLayer camadaLixos;

    // Adicionando mochila
    private Mochila mochila;
    private List<Lixo> itensMochila;

    // Caçambas
    private List<Cacamba> cacambas;
    private Boolean cacambaAberta=false;

    // Pontuação
    private BitmapFont fonte;
    private BitmapFont fonteNumeros;
    private Stage pontuacao;
    private Texture fundoTextoPontuacao;
    private Texture fundoPontuacao;
    private Label labelTextoAcertos;
    private Label labelAcertos;
    private int acertos;
    private Label labelTextoErros;
    private Label labelErros;
    private int erros;
    private Image imagemFundoTexto1;
    private Image imagemFundoPontuacao1;
    private Image imagemFundoTexto2;
    private Image imagemFundoPontuacao2;

    //Inventario
    private Stage inventario;
    private SpriteBatch quadradoInventario;
    private Texture transparenciaInventario;
    private Cacamba atualCacamba;
    private Label labelAtualCacamba;
    private Drawable drawableVazio;
    private Drawable fundoBotaoLixo;
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
        entrada = new Entrada(player);

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
        camadaLixos = (TiledMapTileLayer) map.getLayers().get("Lixos");
        posicaoLixos = new PosicaoLixo(camadaLixos);
        carregarLixos();

        // Mochila
        mochila = new Mochila();
        itensMochila = new ArrayList<Lixo>();

        // Caçambas
        cacambas = new ArrayList<Cacamba>();
        cacambas.add(new Cacamba(METAL,10,16));
        cacambas.add(new Cacamba(PAPEL,13,16));
        cacambas.add(new Cacamba(PLASTICO,15,15));
        cacambas.add(new Cacamba(VIDRO,10,14));
        cacambas.add(new Cacamba(ORGANICO,13,14));

        // Estilo de fonte
        fonte = new BitmapFont(Gdx.files.internal("fonte/PartyConfettiRegular.fnt"));
        fonteNumeros = new BitmapFont(Gdx.files.internal("fonte/numeros-mix-bit.fnt"));
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = fonte;
        labelStyle.fontColor = Color.WHITE;

        Label.LabelStyle numerosStyle = new Label.LabelStyle();
        numerosStyle.font = fonteNumeros;
        numerosStyle.fontColor = Color.BLACK;

        // Pontuação
        fundoPontuacao = new Texture(Gdx.files.internal("img/fundoPontuacao.png"));
        fundoTextoPontuacao = new Texture(Gdx.files.internal("img/fundoPontuacaoTexto.png"));
        imagemFundoTexto1 = new Image(new TextureRegionDrawable(new TextureRegion(fundoPontuacao)));
        imagemFundoPontuacao1 = new Image(new TextureRegionDrawable(new TextureRegion(fundoTextoPontuacao)));
        imagemFundoTexto1.setBounds(largura/2-140, altura-40,80,40);
        imagemFundoPontuacao1.setBounds(largura/2-60, altura-40,60,40);
        imagemFundoTexto2 = new Image(new TextureRegionDrawable(new TextureRegion(fundoPontuacao)));
        imagemFundoPontuacao2 = new Image(new TextureRegionDrawable(new TextureRegion(fundoTextoPontuacao)));
        imagemFundoTexto2.setBounds(largura/2+60, altura-40,80,40);
        imagemFundoPontuacao2.setBounds(largura/2, altura-40,60,40);

        pontuacao = new Stage(new ScreenViewport());
        labelTextoAcertos = new Label("Acertos",labelStyle);
        labelTextoErros = new Label("Erros", labelStyle);
        labelAcertos = new Label("0", numerosStyle);
        labelErros = new Label("0", numerosStyle);
        labelTextoAcertos.setBounds(largura/2-140,altura-40,80,40);
        labelTextoErros.setBounds(largura/2+60,altura-40,80,40);
        labelAcertos.setBounds(largura/2-60,altura-40,60,40);
        labelErros.setBounds(largura/2,altura-40,60,40);

        pontuacao.addActor(imagemFundoTexto1);
        pontuacao.addActor(imagemFundoPontuacao1);
        pontuacao.addActor(imagemFundoTexto2);
        pontuacao.addActor(imagemFundoPontuacao2);
        pontuacao.addActor(labelTextoAcertos);
        pontuacao.addActor(labelAcertos);
        pontuacao.addActor(labelTextoErros);
        pontuacao.addActor(labelErros);

        // Inventario
        quadradoInventario = new SpriteBatch();
        transparenciaInventario = new Texture("img/quadradoInventario.png");
        inventario = new Stage(new ScreenViewport());

        Texture botaoLixoVazio = new Texture(Gdx.files.internal("img/tipo-lixo/nenhum.png"));
        Texture fundoTexturaLixo = new Texture(Gdx.files.internal("img/tipo-lixo/fundoBotaoLixo.png"));
        drawableVazio = new TextureRegionDrawable(botaoLixoVazio);
        fundoBotaoLixo = new TextureRegionDrawable(fundoTexturaLixo);

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
    {
      if (playerYtela + player.getAltura() / 2 > altura * 2 / 3) {
          foraBordaCima = true;
      } else {
          foraBordaCima = false;
      }
      if (playerYtela + player.getAltura() / 2 < altura / 3) {
          foraBordaBaixo = true;
      } else {
          foraBordaBaixo = false;
      }
      if (playerXtela + player.getLargura() / 2 > largura * 2 / 3) {
          foraBordaDireita = true;
      } else {
          foraBordaDireita = false;
      }
      if (playerXtela + player.getLargura() / 2 < largura / 3) {
          foraBordaEsquerda = true;
      } else {
          foraBordaEsquerda = false;
      }
    }

    //Movimento caso não esteja pausado
    if (podeAndar) {
        // Movimento para cima
        if (entrada.cima && player.getY() < 4096 - player.getAltura()) {
            if (!colisao.cima(camadaColisao)) {
                player.setCorrendo(colisao.cima(camadaRua));
                player.setDirecao(Direcao.CIMA);
                player.andarY(player.getVelocidade());
            }else  {player.recuar();}

            if (foraBordaCima && (player.getY() + player.getAltura() / 2 < (4096 - altura / 3))) {
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
                 player.andarY(-player.getVelocidade());
            }else {player.recuar();}

            if (foraBordaBaixo && (player.getY() + player.getAltura() / 2) > (altura / 3)) {
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
                player.andarX(-player.getVelocidade());
            }else {player.recuar();}

            if (foraBordaEsquerda && (player.getX() + player.getLargura() / 2) > (largura / 3)) {
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
                player.andarX(player.getVelocidade());
            }else {player.recuar();}

            if (foraBordaDireita && (player.getX() + player.getLargura() / 2 < (4096 - largura / 3))) {
                camera.translate(player.getVelocidade(), 0);
                camera.update();
                player.setProjectionMatrix(camera.combined);
            }
        }
    }

    if ((entrada.cima || entrada.baixo || entrada.direita || entrada.esquerda) & podeAndar ){
        posicaoPlayer = camera.project(new Vector3(player.getX(), player.getY(),0));
        playerYtela = posicaoPlayer.y;
        playerXtela = posicaoPlayer.x;
    }else {
        player.getAnimation().setFrameDuration(0f);
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
      //System.out.println(lixo.getX() +" - "+lixo.getY());
      lixo.begin();
      lixo.draw(lixo.getImagem(), lixo.getX(), lixo.getY(),36,36);
      lixo.end();
      //camera.update();
      lixo.setProjectionMatrix(camera.combined);
    }

    player.begin();
    player.draw((TextureRegion) player.getAnimation().getKeyFrame(elapsedTime,true), player.getX(), player.getY());
    //player.draw(player.framePlayer(direcao,elapsedTime), player.getX(), player.getY());
    player.end();

    renderer.render(new int[]{13});

    playerRec = new Rectangle(player.getX(),player.getY(),player.getLargura(),player.getAltura());
    //playerRec.x = player.getX();
    //playerRec.y = player.getY();

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
    for (Cacamba cacamba : cacambas){
      if (cacamba.getRectangle().overlaps(playerRec) & Gdx.input.isKeyJustPressed(Input.Keys.E)) {
          Gdx.input.setInputProcessor(inventario);

          entrada.limparTeclas();
          atualCacamba.setImagem(cacamba.getImagem());
          atualCacamba.setNome(cacamba.getNome());
          atualCacamba.setTipo(cacamba.getTipo());
          atualizarInventario();
          cacambaAberta = !cacambaAberta;
      }
    }

    mochila.begin();
    mochila.draw(mochila.getImagem(), largura/2-190, 20);
    mochila.end();

    // Desenha itens da mochila
    for(Lixo lixo:itensMochila){
      lixo.begin();
      lixo.draw(lixo.getImagem(), lixo.getX(), lixo.getY(),36,36);
      lixo.end();
    }
    pontuacao.draw();

    // Desenhar quadrado
    if (cacambaAberta || pause){
        quadrado.begin();
        quadrado.setColor(0, 0, 0, 0.3f);
        quadrado.draw(transparencia, 0, 0, largura, altura);
        quadrado.end();
        podeAndar = false;
    }else{
        podeAndar = true;
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


    if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) & pause){
      float mouseX = Gdx.input.getX();
      float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
      if (mouseX >= btnReiniciarX && mouseX <= btnReiniciarX + texturaReiniciar.getWidth() && mouseY >= btnReiniciarY && mouseY <= btnReiniciarY + texturaReiniciar.getHeight()) {
          reiniciarJogo();
      }
      if (mouseX >= btnMenuX && mouseX <= btnMenuX + texturaReiniciar.getWidth() && mouseY >= btnReiniciarY && mouseY <= btnReiniciarY + texturaReiniciar.getHeight()) {
          voltarMenu();
      }
    }
  }
    private void carregarLixos(){
        lixos = new ArrayList<Lixo>();
        for(int i =0;i<qtdLixo;i++){
            tipoLixo[] tlixo = tipoLixo.values();
            tipoLixo lixoAleatorio = tlixo[new Random().nextInt(tlixo.length)];
            lixos.add(new Lixo(lixoAleatorio,posicaoLixos.retornaXY()));
        }
    }
    private void pegarLixo(Lixo lixo){
        itensMochila.add(lixo);
        lixo.setX((largura/2-190)+(itensMochila.indexOf(lixo)*61)+77);
        lixo.setY(32);
        lixo.getProjectionMatrix().setToOrtho2D(0, 0, largura, altura);
    }
    private void jogarNaCacamba(int item){
        //itensMochila.get(item).getTipo();
        if(itensMochila.size()>=item){
            if (atualCacamba.getTipo() == itensMochila.get(item-1).getTipo()){
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

        if(itensMochila.size()>=1){
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
        labelTextoAcertos.setPosition(largura/2-140,altura-40);
        labelTextoErros.setPosition(largura/2+60,altura-40);
        labelAcertos.setPosition(largura/2-60,altura-40);
        labelErros.setPosition(largura/2,altura-40);
        imagemFundoTexto1.setPosition(largura/2-140, altura-40);
        imagemFundoPontuacao1.setPosition(largura/2-60, altura-40);
        imagemFundoTexto2.setPosition(largura/2+60, altura-40);
        imagemFundoPontuacao2.setPosition(largura/2, altura-40);


        inventario.getViewport().update(width, height, true);
        quadradoInventario.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
        atualCacamba.getProjectionMatrix().setToOrtho2D(0, 0, width, height);

        btnMenuInicial.dispose();
        btnMenuInicial = new SpriteBatch();
        btnMenuX=(largura/3)-(texturaMenuInicial.getWidth()/2);
        btnMenuY=(altura/2)-(texturaMenuInicial.getWidth()/2);

        btnReiniciar.dispose();
        btnReiniciar = new SpriteBatch();
        btnReiniciarX =(largura*2/3)-(texturaReiniciar.getWidth()/2);
        btnReiniciarY =(altura/2)-(texturaReiniciar.getWidth()/2);

        labelAtualCacamba.setPosition(largura-320,528);
        //camera.position.x = width/
        //System.out.println("L: "+largura+" A: "+altura);

        camera.update();
        /*
        for(Lixo lixo:lixos){
            lixo.setProjectionMatrix(camera.combined);
        }

         */
        player.setProjectionMatrix(camera.combined);
        //playerX =
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
     }

}


