package com.example.jogodavelha;

public class Game {
    private String nameP1;
    private String nameP2;
    private Character symbolP1;
    private Character symbolP2;
    private Integer victoriesP1;
    private Integer victoriesP2;
    private Integer draw;
    private Boolean player1Turn;
    private char[] gameXO;

    public Game(String nameP1, String nameP2, Character symbolP1, Character symbolP2, Integer victoriesP1, Integer victoriesP2, Integer draw) {
        this.nameP1 = nameP1;
        this.nameP2 = nameP2;
        this.symbolP1 = symbolP1;
        this.symbolP2 = symbolP2;
        this.victoriesP1 = victoriesP1;
        this.victoriesP2 = victoriesP2;
        this.draw = draw;
        this.player1Turn = true;
        gameXO = new char[9];
    }


    public String getNameP1() {
        return nameP1;
    }

    public void setNameP1(String nameP1) {
        this.nameP1 = nameP1;
    }

    public String getNameP2() {
        return nameP2;
    }

    public void setNameP2(String nameP2) {
        this.nameP2 = nameP2;
    }

    public Character getSymbolP1() {
        return symbolP1;
    }

    public void setSymbolP1(Character symbolP1) {
        this.symbolP1 = symbolP1;
    }

    public Character getSymbolP2() {
        return symbolP2;
    }

    public void setSymbolP2(Character symbolP2) {
        this.symbolP2 = symbolP2;
    }

    public Integer getVictoriesP1() {
        return victoriesP1;
    }

    public void setVictoriesP1(Integer victoriesP1) {
        this.victoriesP1 = victoriesP1;
    }

    public Integer getVictoriesP2() {
        return victoriesP2;
    }

    public void setVictoriesP2(Integer victoriesP2) {
        this.victoriesP2 = victoriesP2;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Boolean getPlayerTurn() {
        return player1Turn;
    }

    public void setPlayerTurn(Boolean playerTurn) {
        this.player1Turn = playerTurn;
    }

    public char[] getGameXO() {
        return gameXO;
    }

    public void setGameXO(char[] gameXO) {
        this.gameXO = gameXO;
    }


    public void play(int posicao){
        if (player1Turn) {
            // Player 1 joga
            gameXO[posicao] = symbolP1;
        } else {
            // Player 2 joga
            gameXO[posicao] = symbolP2;
        }
        // Alterna a vez
        player1Turn = !player1Turn;
    }
}
