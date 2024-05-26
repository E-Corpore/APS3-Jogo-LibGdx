package com.aps3.jogo.Controles;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PosicaoLixo {
    private final List<Vector2> vetores;
    TiledMapTileLayer camadaLixos;
    TiledMapTileLayer.Cell celula;
    Random aleatorio=new Random();
    public PosicaoLixo(TiledMapTileLayer camada){
        this.camadaLixos = camada;
        vetores = new ArrayList<>();
        adicionarVetores();
    }
    public Vector2 retornaXY(){
        return vetores.get(aleatorio.nextInt(vetores.size()));
    }
    public void adicionarVetores() {
        // Iterar sobre cada célula na camada de lixos
        for (int y = 0; y < camadaLixos.getHeight(); y++) {
            for (int x = 0; x < camadaLixos.getWidth(); x++) {
                celula = camadaLixos.getCell(x, y);
                if (celula != null) {
                    vetores.add(new Vector2(x, y));
                }
            }
        }
    }
}
