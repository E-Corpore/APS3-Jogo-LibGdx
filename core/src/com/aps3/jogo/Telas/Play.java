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

public class Play implements Screen{

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    Player player;
    int playerX=100,playerY=100;
    boolean andando = false;
    private float elapsedTime = 0f;

    Entrada processor = new Entrada();

  public void render(float v){
    //Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    renderer.setView(camera);
    renderer.render();
    elapsedTime += Gdx.graphics.getDeltaTime();
    //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    player.begin();
    player.draw((TextureRegion) player.animation.getKeyFrame(elapsedTime,true), playerX, playerY);
    player.end();

    Gdx.input.setInputProcessor(processor);

    //if(Gdx.input.isKeyPressed(Input.Keys.UP)){
      //entrada.keyUp(Input.Keys)
    if(processor.cima){
        System.out.println("UP");
        playerY+=5;
        //andando = true;
    }
    //if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
    if(processor.direita){
        System.out.println("RIGHT");
        playerX+=5;
        //andando = true;
    }
    //if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
    if(processor.baixo){
        System.out.println("DOWN");
        playerY-=5;
        //andando = true;
    }
    //if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
    if(processor.esquerda){
        System.out.println("LEFT");
        playerX-=5;
        //andando = true;
    }
    if (processor.cima || processor.baixo || processor.direita || processor.esquerda){
        player.animation.setFrameDuration(0.15f);
    }else {
        player.animation.setFrameDuration(0f);
    }

  }

    public void resize(int width, int height){
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
        player.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    public void show(){
        map = new TmxMapLoader().load("mapa.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        //camera.setToOrtho(false, 1200, 800);
        player = new Player();
    }
     public void hide(){
        dispose();
     }

     public void dispose(){
        map.dispose();
        renderer.dispose();
     }

}


