package com.aps3.jogo.world;

public enum TileType {

    GRAMA(1, false, "Grama"),
    RUA_1(26, false, "Rua Reta"),
    RUA_2(96, false, "Rua tripla"),
    RUA_3(97, false, "Insterseção"),
    RUA_CURVA(95, false, "Curva"),
    ARVORE(38, false, "Árvore 1"),
    ARVORE_2(37, false, "Árvore 2");

    private int id;
    private boolean colidivel;
    private String nome;

    private TileType (int id, boolean colidivel, String nome){
        this.id = id;
        this.colidivel = colidivel;
        this.nome = nome;
    }

}
