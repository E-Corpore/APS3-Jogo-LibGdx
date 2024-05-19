package com.aps3.jogo.Telas;

import com.aps3.jogo.Controles.Colisao;
import com.aps3.jogo.Controles.Entrada;
import com.aps3.jogo.Controles.PosicaoLixo;
import com.aps3.jogo.Entidades.Lixo;
import com.aps3.jogo.Entidades.Player;
import com.aps3.jogo.Entidades.tipoLixo;
import com.aps3.jogo.Jogo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Play implements Screen{
    //private final Jogo jogo;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    Player player;
    //float playerX,playerY;
    Vector3 posicaoPlayer;
    float playerXtela,playerYtela;
    boolean andando = false;
    private float elapsedTime = 0f;
    private int velocidade = 10;
    Entrada entrada = new Entrada();
    private int largura,altura;
    private float cameraX,cameraY;
    private boolean foraBordaCima;
    private boolean foraBordaBaixo;
    private boolean foraBordaDireita;
    private boolean foraBordaEsquerda;

    //variaveis para detectar colisão
    private TiledMapTileLayer camadaColisao;
    //private float tileWidth,tileHeight;
    private int cellX,cellY;
    //rivate boolean colisaoCasaDireita,colisaoCasaEsquerda,colisaoCasaBaixo,colisaoCasaCima;
    private Colisao colisao = new Colisao();
    private Colisao colisaoLixo = new Colisao();

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
    Random aleatorio=new Random();

    public Play(){

    }
    public void show() {
        map = new TmxMapLoader().load("mapa.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();

        largura = Gdx.graphics.getWidth();
        altura = Gdx.graphics.getHeight();

        //map.getLayers().get(3).getObjects().get(1);

        player = new Player();

        camera.position.x = player.getX();
        camera.position.y = player.getY();

        camadaColisao = (TiledMapTileLayer) map.getLayers().get("Colisao");
        camadaLixos = (TiledMapTileLayer) map.getLayers().get("Lixos");

        posicaoLixos = new PosicaoLixo(camadaLixos);
        carregarLixos();

        // Acessar a camada de colisão
        cellX = (int)player.getX()/128;
        cellY = (int)player.getY()/128;

        // Itens para pausa
        transparencia = new Texture("menu/quadradoTransparente.png");
        texturaMenuInicial = new Texture("menu/btnMenuInicial.png");
        texturaReiniciar = new Texture("menu/btnReiniciar.png");
        quadrado = new SpriteBatch();
        btnMenuInicial = new SpriteBatch();
        btnReiniciar = new SpriteBatch();


        camera.update();
    }
  public void render(float v){
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    elapsedTime += Gdx.graphics.getDeltaTime();
    Gdx.input.setInputProcessor(entrada);
    // Quando pressionado ESC
      if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
          pause = !pause; // Alterna o estado de escPressed
      }

      // Verificando se passou das bordas
      if (playerYtela+player.getAltura()/2 > altura*2/3){
          foraBordaCima = true;
      }else {
          foraBordaCima = false;
      }
      if (playerYtela+player.getAltura()/2 < altura/3){
          foraBordaBaixo = true;
      }else {
          foraBordaBaixo = false;
      }
      if (playerXtela+player.getLargura()/2>largura*2/3){
          foraBordaDireita = true;
      }else {
          foraBordaDireita = false;
      }
      if (playerXtela+player.getLargura()/2 < largura/3){
          foraBordaEsquerda = true;
      }else {
          foraBordaEsquerda = false;
      }

      //Movimento caso não esteja pausado
    if (!pause) {
        // Movimento para cima
        if (entrada.cima && player.getY() < 4096 - player.getAltura()) {
            player.costa();
            if (!colisao.cima(player, camadaColisao)) {
                player.andarY(velocidade);
            }

            if (foraBordaCima && (player.getY() + player.getAltura() / 2 < (4096 - altura / 3))) {
                camera.translate(0, velocidade);
                cameraY = camera.position.y;
                camera.update();
                player.setProjectionMatrix(camera.combined);
            }
        }
        // Movimento para baixo
        if (entrada.baixo && player.getY() > 0) {
            player.frente();
            if (!colisao.baixo(player, camadaColisao)) {
                player.andarY(-velocidade);
                ;
            }
            if (foraBordaBaixo && (player.getY() + player.getAltura() / 2) > (altura / 3)) {
                camera.translate(0, -velocidade);
                cameraY = camera.position.y;
                camera.update();
                player.setProjectionMatrix(camera.combined);
            }
        }
        // Movimento para a esquerda
        if (entrada.esquerda && player.getX() > 0) {
            player.esquerda();
            if (!colisao.esquerda(player, camadaColisao)) {
                player.andarX(-velocidade);
            }
            if (foraBordaEsquerda && (player.getX() + player.getLargura() / 2) > (largura / 3)) {
                camera.translate(-velocidade, 0);
                cameraX = camera.position.x;
                camera.update();
                player.setProjectionMatrix(camera.combined);
            }
        }
        // Movimento para a direita
        if (entrada.direita && player.getX() < 4096 - player.getLargura()) {
            player.direita();
            if (!colisao.direita(player, camadaColisao)) {
                player.andarX(velocidade);
            }
            if (foraBordaDireita && (player.getX() + player.getLargura() / 2 < (4096 - largura / 3))) {
                camera.translate(velocidade, 0);
                cameraX = camera.position.x;
                camera.update();
                player.setProjectionMatrix(camera.combined);
            }
        }
    }

    if ((entrada.cima || entrada.baixo || entrada.direita || entrada.esquerda) & !pause ){
        player.getAnimation().setFrameDuration(0.15f);
        posicaoPlayer = camera.project(new Vector3(player.getX(), player.getY(),0));
        playerYtela = posicaoPlayer.y;
        playerXtela = posicaoPlayer.x;
    }else {
        player.getAnimation().setFrameDuration(0f);
    }


      renderer.setView(camera);
      renderer.render();
      player.begin();
      player.draw((TextureRegion) player.getAnimation().getKeyFrame(elapsedTime,true), player.getX(), player.getY());
      player.end();

      for(Lixo lixo:lixos){
          System.out.println(lixo.getX() +" - "+lixo.getY());
          lixo.begin();
          lixo.draw(lixo.getImagem(), lixo.getX(), lixo.getY(),36,36);
          lixo.end();
          camera.update();
          lixo.setProjectionMatrix(camera.combined);

      }
      // Desenhar quadrado quando pausado
      if(pause){
          quadrado.begin();
          quadrado.setColor(0, 0, 0, 0.3f);
          quadrado.draw(transparencia, 0, 0, largura, altura);
          quadrado.end();

          btnMenuInicial.begin();
          btnMenuInicial.draw(texturaMenuInicial,btnMenuX, btnMenuY, texturaMenuInicial.getWidth(), texturaMenuInicial.getHeight());
          btnMenuInicial.end();

          btnReiniciar.begin();
          btnReiniciar.draw(texturaReiniciar, btnReiniciarX, btnReiniciarY, texturaReiniciar.getWidth(), texturaReiniciar.getHeight());
          btnReiniciar.end();

      }
      if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
          float mouseX = Gdx.input.getX();
          float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
          if (mouseX >= btnReiniciarX && mouseX <= btnReiniciarX + texturaReiniciar.getWidth() && mouseY >= btnReiniciarY && mouseY <= btnReiniciarY + texturaReiniciar.getHeight()) {
              reiniciarJogo();
          }
          if (mouseX >= btnMenuX && mouseX <= btnMenuX + texturaReiniciar.getWidth() && mouseY >= btnReiniciarY && mouseY <= btnReiniciarY + texturaReiniciar.getHeight()) {
              // Chama a função desejada
              System.out.println("Menu");
              voltarMenu();
          }
      }


  }

  private void carregarLixos(){
      lixos = new ArrayList<Lixo>();
      for(int i =0;i<10;i++){
          tipoLixo[] tlixo = tipoLixo.values();
          tipoLixo lixoAleatorio = tlixo[aleatorio.nextInt(tlixo.length)];
         lixos.add(new Lixo(lixoAleatorio,posicaoLixos.retornaXY()));
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

        quadrado.dispose();
        quadrado = new SpriteBatch();

        btnMenuInicial.dispose();
        btnMenuInicial = new SpriteBatch();
        btnMenuX=(largura/3)-(texturaMenuInicial.getWidth()/2);
        btnMenuY=(altura/2)-(texturaMenuInicial.getWidth()/2);

        btnReiniciar.dispose();
        btnReiniciar = new SpriteBatch();
        btnReiniciarX =(largura*2/3)-(texturaReiniciar.getWidth()/2);
        btnReiniciarY =(altura/2)-(texturaReiniciar.getWidth()/2);


        //camera.position.x = width/
        //System.out.println("L: "+largura+" A: "+altura);


        camera.update();

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


