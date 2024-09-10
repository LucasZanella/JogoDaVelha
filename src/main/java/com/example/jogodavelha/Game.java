package com.example.jogodavelha;

import java.util.Arrays;

public class Game {
    private String nameP1;
    private String nameP2;
    private Character symbolP1;
    private Character symbolP2;
    private Integer victoriesP1;
    private Integer victoriesP2;
    private Integer draw;
    private Boolean player1Turn;
    private Boolean lastPlayerStarted;
    private int moveCount;
    private char[] gameXO;

    // Matriz bidimencional que armazena as condições de vitória.
    private final int[][] winningCombinations = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Linhas
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Colunas
            {0, 4, 8}, {2, 4, 6}  // Diagonais
    };



    // Construtor do jogo.
    public Game(String nameP1, String nameP2, Character symbolP1, Character symbolP2, Integer victoriesP1, Integer victoriesP2, Integer draw) {
        this.nameP1 = nameP1;
        this.nameP2 = nameP2;
        this.symbolP1 = symbolP1;
        this.symbolP2 = symbolP2;
        this.victoriesP1 = victoriesP1;
        this.victoriesP2 = victoriesP2;
        this.draw = draw;
        this.player1Turn = true;
        this.lastPlayerStarted = true;
        this.moveCount = 0;
        gameXO = new char[9];
    }

    // Getters e Setters do construtor.
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

    public Boolean getPlayer1Turn() {
        return player1Turn;
    }

    public void setPlayer1Turn(Boolean playerTurn) {
        this.player1Turn = playerTurn;
    }

    public Boolean getLastPlayerStarted() {
        return lastPlayerStarted;
    }

    public void setLastPlayerStarted(Boolean lastPlayerStarted) {
        this.lastPlayerStarted = lastPlayerStarted;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    public char[] getGameXO() {
        return gameXO;
    }

    public void setGameXO(char[] gameXO) {
        this.gameXO = gameXO;
    }

    public int[][] getWinningCombinations() {
        return winningCombinations;
    }



    // Função que adiciona o símbolo no array e verifica
    // a condição do jogo e alterna os jogadores.
    public void play(int posicao){

        // Quando for verdade o player 1 joga;
        // Quando for falso o player 2 joga.
        if (player1Turn) {

            // Adiciona o símbolo do player 1 no array.
            gameXO[posicao] = symbolP1;

        } else {
            // Adiciona o símbolo do player 2 no array.
            gameXO[posicao] = symbolP2;
        }

        // Contador de jogadas.
        moveCount++;

        // Quando o contador de jogadas for maior que 4, o programa sempre
        // vai verificar se alguém ganhou ou se empatou.
        if (moveCount > 4) {


            if (checkVictory(gameXO[posicao])) {
                if (player1Turn) {
                    victoriesP1++;
                } else {
                    victoriesP2++;
                }
            } else if (checkDraw()) {
                draw++;
            }
        }

        // Alterna a vez dos jogadores.
        player1Turn = !player1Turn;
    }

    // Função para verificar a vitória
    public boolean checkVictory(char symbol) {
        for (int[] combination : winningCombinations) {
            if (gameXO[combination[0]] == symbol && gameXO[combination[1]] == symbol && gameXO[combination[2]] == symbol) {
                return true; // Vitória se uma combinação é atingida
            }
        }
        return false;
    }

    // Função para verificar o empate
    public boolean checkDraw() {
        for (char cell : gameXO) {
            if (cell == '\u0000') {
                return false; // Ainda há células vazias, não é empate
            }
        }
        return true; // Todas as células estão preenchidas, é empate
    }

    // Função para resetar o jogo
    public void reset() {

        // Limpa a array.
        Arrays.fill(gameXO, '\u0000');

        // Alterna o jogador que começará a nova partida
        player1Turn = !lastPlayerStarted;

        // Atualiza o último jogador que começou
        lastPlayerStarted = player1Turn;

        // Reseta a contagem de jogadas
        moveCount = 0;
    }
}
