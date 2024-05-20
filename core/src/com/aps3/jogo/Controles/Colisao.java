package com.aps3.jogo.Controles;

import com.aps3.jogo.Entidades.Player;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Colisao {
    public Colisao(){

    }
    public boolean cima(Player player,TiledMapTileLayer camada){
        int cellX1 = (int)player.getX()/128;
        int cellY1 = ((int)player.getY()+player.getAltura())/128;
        int cellX2 = (int)player.getX()+player.getLargura()/128;
        int cellY2 = ((int)player.getY()+player.getAltura())/128;
        return colide(camada,cellX1,cellY1,cellX2,cellY2);
    }
    public boolean baixo(Player player,TiledMapTileLayer camada){
        int cellX1 = ((int)player.getX())/128;
        int cellY1 = (int)player.getY()/128;
        int cellX2 = ((int)player.getX()+ player.getLargura())/128;
        int cellY2 = (int)player.getY()/128;
        return colide(camada,cellX1,cellY1,cellX2,cellY2);
    }
    public boolean esquerda(Player player,TiledMapTileLayer camada){
        int cellX1 = ((int)player.getX())/128;
        int cellY1 = (int)player.getY()/128;
        int cellX2 = ((int)player.getX())/128;
        int cellY2 = (int)player.getY()+player.getAltura()/128;
        return colide(camada,cellX1,cellY1,cellX2,cellY2);
    }
    public boolean direita(Player player,TiledMapTileLayer camada){
        int cellX1 = ((int)player.getX()+player.getLargura())/128;
        int cellY1 = (int)player.getY()/128;
        int cellX2 = (int)player.getX()+player.getLargura()/128;
        int cellY2 = (int)player.getY()+player.getAltura()/128;
        return colide(camada,cellX1,cellY1,cellX2,cellY2);
    }
    private boolean colide(TiledMapTileLayer camada,int x1, int y1,int x2, int y2){
        TiledMapTileLayer.Cell celula1,celula2;
        celula1 = camada.getCell(x1,y1);
        celula2 = camada.getCell(x2,y2);
        //if ((celula1 != null) || (celula2 != null)){
        if (celula1 != null){
            return true;
        }else {
            return false;
        }
    }

}
