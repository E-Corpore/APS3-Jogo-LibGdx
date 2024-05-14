package com.aps3.jogo.Telas;

//import com.aps3.jogo.Entidades.Player;
import com.aps3.jogo.Controles.Colisao;
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
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;

public class Play implements Screen{

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    Player player;
    //float playerX,playerY;
    Vector3 posicaoPlayer;
    float playerXtela,playerYtela;
    boolean andando = false;
    private float elapsedTime = 0f;
    private int velocidade = 1;
    Entrada processor = new Entrada();
    private int largura,altura;
    private float cameraX,cameraY;
    private boolean foraBordaCima;
    private boolean foraBordaBaixo;
    private boolean foraBordaDireita;
    private boolean foraBordaEsquerda;

    //variaveis para detectar colisão
    private TiledMapTileLayer camadaCasas;
    //private float tileWidth,tileHeight;
    private TiledMapTileLayer.Cell cellCasaDireita,cellCasaEsquerda,cellCasaBaixo,cellCasaCima;
    private int cellX,cellY;
    //rivate boolean colisaoCasaDireita,colisaoCasaEsquerda,colisaoCasaBaixo,colisaoCasaCima;
    private Colisao colisaoCasa = new Colisao();


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

        camadaCasas = (TiledMapTileLayer) map.getLayers().get("Casas");

        // Acessar a camada de colisão
        cellX = (int)player.getX()/128;
        cellY = (int)player.getY()/128;
        /*
        cellCasa = camadaCasas.getCell(cellX, cellY);
        //System.out.println("Casa: " + cellX + " - " + cellY);

        if (cellCasa != null) {
            System.out.println("Casa: " + cellX + " - " + cellY);
        } else{
            System.out.println("Nada: x:" + cellX +  " - y:" + cellY);
        }

         */



        /*
        // Iterar sobre cada célula na camada de colisão
        for (int y = 0; y < camadaCasas.getHeight(); y++) {
            for (int x = 0; x < camadaCasas.getWidth(); x++) {
                cell = camadaCasas.getCell(x, y);
                if (cell != null) {
                    // Obter propriedades do tile
                    int tileX = x; // posição x do tile
                    int tileY = y; // posição y do tile
                    int tileWidth = camadaCasas.getTileWidth(); // largura do tile
                    int tileHeight = camadaCasas.getTileHeight(); // altura do tile

                    // Aqui você pode fazer o que quiser com as propriedades do tile
                    System.out.println("Tile em (" + tileX + ", " + tileY + ")");
                    //System.out.println("Largura: " + tileWidth + ", Altura: " + tileHeight);
                }
            }
        }

         */


        //cell = camadaCasas.getCell(cellX, cellY);

        camera.update();
    }
  public void render(float v){
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    elapsedTime += Gdx.graphics.getDeltaTime();
    Gdx.input.setInputProcessor(processor);


    if(processor.cima && player.getY()<4096-player.getAltura()){
        player.costa();
        if (!colisaoCasa.cima(player,camadaCasas)){
            player.andarY(velocidade);
        }

        if (foraBordaCima && (player.getY()+player.getAltura()/2<(4096 - altura/3))) {
            camera.translate(0,velocidade);
            cameraY = camera.position.y;
            camera.update();
            player.setProjectionMatrix(camera.combined);
        }
    }
      if(processor.baixo && player.getY() > 0){
          player.frente();
          if (!colisaoCasa.baixo(player,camadaCasas)) {
              player.andarY(-velocidade);;
          }
          if (foraBordaBaixo && (player.getY()+player.getAltura()/2)>(altura/3)) {
              camera.translate(0,-velocidade);
              cameraY = camera.position.y;
              camera.update();
              player.setProjectionMatrix(camera.combined);
          }
      }
      if(processor.esquerda && player.getX() > 0 ){
          player.esquerda();
          if (!colisaoCasa.esquerda(player,camadaCasas)) {
              player.andarX(-velocidade);
          }
          if (foraBordaEsquerda && (player.getX()+player.getLargura()/2)>(largura/3)){
              camera.translate(-velocidade,0);
              cameraX = camera.position.x;
              camera.update();
              player.setProjectionMatrix(camera.combined);
          }
      }

    if(processor.direita && player.getX() < 4096 -player.getLargura()){
        player.direita();
        if (!colisaoCasa.direita(player,camadaCasas)) {
            player.andarX(velocidade);
        }
        if (foraBordaDireita && (player.getX()+player.getLargura()/2<(4096 - largura/3))) {
            camera.translate(velocidade,0);
            cameraX = camera.position.x;
            camera.update();
            player.setProjectionMatrix(camera.combined);
        }
    }

    if (processor.cima || processor.baixo || processor.direita || processor.esquerda){
        player.getAnimation().setFrameDuration(0.15f);
        posicaoPlayer = camera.project(new Vector3(player.getX(), player.getY(),0));
        playerYtela = posicaoPlayer.y;
        playerXtela = posicaoPlayer.x;
    }else {
        player.getAnimation().setFrameDuration(0f);
    }
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

      renderer.setView(camera);
      renderer.render();
      player.begin();
      player.draw((TextureRegion) player.getAnimation().getKeyFrame(elapsedTime,true), player.getX(), player.getY());
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


