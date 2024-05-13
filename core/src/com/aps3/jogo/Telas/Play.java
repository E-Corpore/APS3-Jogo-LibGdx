package com.aps3.jogo.Telas;

//import com.aps3.jogo.Entidades.Player;
import com.aps3.jogo.Controles.Entrada;
import com.aps3.jogo.Entidades.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;

public class Play implements Screen{

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    Player player;
    float playerX,playerY;
    Vector3 posicaoPlayer;
    float playerXtela,playerYtela;
    boolean andando = false;
    private float elapsedTime = 0f;
    private int velocidade = 3;
    Entrada processor = new Entrada();
    private int largura,altura;
    private float cameraX,cameraY;
    private boolean foraBordaCima;
    private boolean foraBordaBaixo;
    private boolean foraBordaDireita;
    private boolean foraBordaEsquerda;

    public void show(){
        map = new TmxMapLoader().load("mapa.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();

        largura = Gdx.graphics.getWidth();
        altura = Gdx.graphics.getHeight();

        //camera.setToOrtho(false, 1200, 800);
        player = new Player();
        playerX = 2328;
        playerY = 1965;
        camera.position.x = playerX;
        camera.position.y = playerY;
        camera.update();
    }
  public void render(float v){
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    elapsedTime += Gdx.graphics.getDeltaTime();
    Gdx.input.setInputProcessor(processor);

    if(processor.cima && playerY<4096-player.altura){
        player.costa();
        playerY+=velocidade;

        if (foraBordaCima && (playerY+player.altura/2<(4096 - altura/3))) {
            camera.translate(0,velocidade);
            cameraY = camera.position.y;
            camera.update();
            player.setProjectionMatrix(camera.combined);
        }
        System.out.println(playerX+" - "+playerY);
    }
      //if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
      if(processor.baixo && playerY > 0){
          player.frente();
          playerY-=velocidade;
          if (foraBordaBaixo && (playerY+player.altura/2)>(altura/3)) {
              camera.translate(0,-velocidade);
              cameraY = camera.position.y;
              camera.update();
              player.setProjectionMatrix(camera.combined);
          }
      }
    if(processor.direita && playerX < 4096 -player.largura){
        player.direita();
        playerX+=velocidade;
        if (foraBordaDireita && (playerX+player.largura/2<(4096 - largura/3))) {
            camera.translate(velocidade,0);
            cameraX = camera.position.x;
            camera.update();
            player.setProjectionMatrix(camera.combined);
        }
    }
    if(processor.esquerda && playerX > 0){
        player.esquerda();
        playerX-=velocidade;
        if (foraBordaEsquerda && (playerX+player.largura/2)>(largura/3)){
            camera.translate(-velocidade,0);
            cameraX = camera.position.x;
            camera.update();
            player.setProjectionMatrix(camera.combined);
        }
        //System.out.println("teste: " + (playerX+24>((largura/3)) );
    }
    if (processor.cima || processor.baixo || processor.direita || processor.esquerda){
        player.animation.setFrameDuration(0.15f);
        posicaoPlayer = camera.project(new Vector3(playerX, playerY,0));
        playerYtela = posicaoPlayer.y;
        playerXtela = posicaoPlayer.x;
    }else {
        player.animation.setFrameDuration(0f);
    }
      if (playerYtela+player.altura/2 > altura*2/3){
          foraBordaCima = true;
      }else {
          foraBordaCima = false;
      }
      if (playerYtela+player.altura/2 < altura/3){
          foraBordaBaixo = true;
      }else {
          foraBordaBaixo = false;
      }
      if (playerXtela+player.largura/2>largura*2/3){
          foraBordaDireita = true;
      }else {
          foraBordaDireita = false;
      }
      if (playerXtela+player.largura/2 < largura/3){
          foraBordaEsquerda = true;
      }else {
          foraBordaEsquerda = false;
      }

      renderer.setView(camera);
      renderer.render();
      player.begin();
      player.draw((TextureRegion) player.animation.getKeyFrame(elapsedTime,true), playerX, playerY);
      player.end();
  }

    public void resize(int width, int height){
        camera.viewportWidth = width;
        camera.viewportHeight = height;

        largura = width;
        altura = height;

        //camera.position.x = width/

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
     }

}


