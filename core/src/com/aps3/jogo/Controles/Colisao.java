package com.aps3.jogo.Controles;

import com.aps3.jogo.Entidades.Player;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Colisao {
    int xDireira;
    int xEsquerda;
    int ySuperior;
    int yInferior;
    Player player;

    public Colisao(Player player){
        this.player = player;
    }
    private void atualizarPosicao(Player player){
        xDireira = ((int)player.getX()+player.getLargura())/128;
        xEsquerda = ((int)player.getX())/128;
        yInferior = (int)player.getY()/128;
        ySuperior = ((int)player.getY()+34)/128;
    }
    public boolean cima(TiledMapTileLayer camada){
        atualizarPosicao(player);
        int cellX1 = xEsquerda;
        int cellY1 = ySuperior;
        int cellX2 = xDireira;
        int cellY2 = ySuperior;
        return colide(camada,cellX1,cellY1,cellX2,cellY2);
    }
    public boolean baixo(TiledMapTileLayer camada){
        atualizarPosicao(player);
        int cellX1 = xEsquerda;
        int cellY1 = yInferior;
        int cellX2 = xDireira;
        int cellY2 = yInferior;
        return colide(camada,cellX1,cellY1,cellX2,cellY2);
    }
    public boolean esquerda(TiledMapTileLayer camada){
        atualizarPosicao(player);
        int cellX1 = xEsquerda;
        int cellY1 = yInferior;
        int cellX2 = xEsquerda;
        int cellY2 = ySuperior;
        return colide(camada,cellX1,cellY1,cellX2,cellY2);
    }
    public boolean direita(TiledMapTileLayer camada){
        atualizarPosicao(player);
        int cellX1 = xDireira;
        int cellY1 = yInferior;
        int cellX2 = xDireira;
        int cellY2 = ySuperior;
        return colide(camada,cellX1,cellY1,cellX2,cellY2);
    }
    private boolean colide(TiledMapTileLayer camada,int x1, int y1,int x2, int y2){
        TiledMapTileLayer.Cell celula1,celula2;
        celula1 = camada.getCell(x1,y1);
        celula2 = camada.getCell(x2,y2);
        if ((celula1 != null) || (celula2 != null)){
        //if (celula1 != null){
            return true;
        }else {
            return false;
        }
    }

}
