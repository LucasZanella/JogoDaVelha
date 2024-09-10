package com.example.jogodavelha;

import java.util.Arrays;

public class Game {
    private String nameP1;                  // Armazena o nome do jogador 1.
    private String nameP2;                  // Armazena o nome do jogador 2.
    private Character symbolP1;             // Representa o símbolo escolhido pelo jogador 1 ('X' ou 'O').
    private Character symbolP2;             // Representa o símbolo escolhido pelo jogador 2 ('X' ou 'O').
    private Integer victoriesP1;            // Conta o número de vitórias do jogador 1.
    private Integer victoriesP2;            // Conta o número de vitórias do jogador 2.
    private Integer draw;                   // Conta o número de empates entre os dois jogadores.
    private Boolean playerTurn;             // Indica se é a vez do jogador 1 (true) ou do jogador 2 (false).
    private Boolean lastPlayerStarted;      // Armazena quem começou a última partida, (true) para o jogador 1 e (false) para o jogador 2.
    private int moveCount;                  // Conta quantas jogadas foram feitas na partida atual.
    private char[] gameXO;                  // Representa o estado atual do tabuleiro de jogo da velha.

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
        this.playerTurn = true;
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
        return playerTurn;
    }

    public void setPlayer1Turn(Boolean playerTurn) {
        this.playerTurn = playerTurn;
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


    // Função que adiciona os símbolos no array e verifica as condições
    // de vitória e empate e alterna o turno dos jogadores.
    public void play(int posicao){

        // Quando for verdade o player 1 joga;
        // Quando for falso o player 2 joga.
        if (playerTurn) {

            // Adiciona o símbolo do player 1 no array.
            gameXO[posicao] = symbolP1;

        } else {
            // Adiciona o símbolo do player 2 no array.
            gameXO[posicao] = symbolP2;
        }

        // Incrementa o contador de jogadas.
        moveCount++;

        // A verificação de vitória ou empate só acontece após 5 jogadas (moveCount > 4),
        // pois antes disso é impossível que alguém tenha vencido ou empatado.
        if (moveCount > 4) {

            // Verifica se o jogador que fez a jogada atual venceu.
            if (checkVictory(gameXO[posicao])) {

                // Se o player 1 estiver jogando e vencer, incrementa suas vitórias.
                if (playerTurn) {
                    victoriesP1++;

                // Se for o player 2 que estiver jogando e vencer, incrementa as vitórias dele.
                } else {
                    victoriesP2++;
                }

            // Se ninguém venceu, verifica se deu empate.
            } else if (checkDraw()) {
                // Incrementa o número de empates.
                draw++;
            }
        }
        // Alterna o turno entre os jogadores.
        playerTurn = !playerTurn;
    }

    // Função para verificar se houve vitória.
    public boolean checkVictory(char symbol) {

        // Percorre todas as combinações de vitória possíveis.
        for (int[] combination : winningCombinations) {

            // Verifica se todas as três posições de uma combinação contêm o símbolo do jogador atual.
            if (gameXO[combination[0]] == symbol && gameXO[combination[1]] == symbol && gameXO[combination[2]] == symbol) {

                // Se a condição for verdadeira, retorna true, indicando vitória.
                return true;
            }
        }
        // Se nenhuma combinação de vitória foi atingida, retorna false.
        return false;
    }

    // Função para verificar se houve empate.
    public boolean checkDraw() {

        // Percorre todas as células do array gameXO.
        for (char cell : gameXO) {

            // Verifica se há alguma célula vazia (representada pelo valor '\u0000').
            if (cell == '\u0000') {

                // Ainda há células vazias, não é empate.
                return false;
            }
        }
        // Todas as células estão preenchidas, é empate.
        return true;
    }

    // Função para resetar o jogo.
    public void reset() {

        // Preenche todas as posições do array gameXO com o valor '\u0000' (esvaziando-o).
        Arrays.fill(gameXO, '\u0000');

        // Alterna o jogador que começará a nova partida.
        playerTurn = !lastPlayerStarted;

        // Atualiza o último jogador que começou.
        lastPlayerStarted = playerTurn;

        // Reseta a contagem de jogadas.
        moveCount = 0;
    }
}
