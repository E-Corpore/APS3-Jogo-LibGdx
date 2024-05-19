package com.aps3.jogo.Controles;

import com.aps3.jogo.Entidades.Lixo;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.badlogic.gdx.scenes.scene2d.ui.Table.Debug.cell;

public class PosicaoLixo {
    private List<Vector2> vetores;
    TiledMapTileLayer camadaLixos;
    TiledMapTileLayer.Cell celula;
    Random aleatorio=new Random();
    public PosicaoLixo(TiledMapTileLayer camada){
        this.camadaLixos = camada;
        vetores = new ArrayList<Vector2>();
        adicionarVetores();
        //System.out.println(vetores);
    }
    public Vector2 retornaXY(){
        Vector2 vetor = vetores.get(aleatorio.nextInt(vetores.size()));
        //System.out.println(vetor);
        return vetor;
    }
    public void adicionarVetores() {
        // Iterar sobre cada célula na camada de colisão
        for (int y = 0; y < camadaLixos.getHeight(); y++) {
            for (int x = 0; x < camadaLixos.getWidth(); x++) {
                celula = camadaLixos.getCell(x, y);
                if (celula != null) {
                    // Obter propriedades do tile
                    int tileX = x; // posição x do tile
                    int tileY = y; // posição y do tile
                    int tileWidth = camadaLixos.getTileWidth(); // largura do tile
                    int tileHeight = camadaLixos.getTileHeight(); // altura do tile
                    vetores.add(new Vector2(tileX, tileY));
                    // Aqui você pode fazer o que quiser com as propriedades do tile
                    //System.out.println("Tile em (" + tileX + ", " + tileY + ")");
                    //System.out.println("Largura: " + tileWidth + ", Altura: " + tileHeight);
                }
            }
        }
    }
}
