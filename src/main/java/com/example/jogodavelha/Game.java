package com.example.jogodavelha;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    private String nameP1;                              // Armazena o nome do jogador 1.
    private String nameP2;                              // Armazena o nome do jogador 2.
    private Character symbolP1;                         // Representa o símbolo escolhido pelo jogador 1 ('X' ou 'O').
    private Character symbolP2;                         // Representa o símbolo escolhido pelo jogador 2 ('X' ou 'O').
    private Integer victoriesP1;                        // Conta o número de vitórias do jogador 1.
    private Integer victoriesP2;                        // Conta o número de vitórias do jogador 2.
    private Integer draw;                               // Conta o número de empates entre os dois jogadores.
    private Boolean player1Turn;                        // Indica se é a vez do jogador 1 (true) ou do jogador 2 (false).
    private Boolean lastPlayerStarted;                  // Armazena quem começou a última partida, (true) para o jogador 1 e (false) para o jogador 2.
    private Boolean detectDraw;                         // Armazena se os usuários querem a previsão de empate.
    private Integer moveCount;                          // Conta quantas jogadas foram feitas na partida atual.
    private ArrayList<int[]> winningCombinations;       // Lista dinâmica para armazenar as combinações de vitória restantes.
    private char[] gameXO;                              // Representa o estado atual do tabuleiro de jogo da velha.


    // Matriz bidimencional que armazena as condições de vitória.
    private final int[][] initialWinningCombinations = {
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
        this.detectDraw = false;
        this.moveCount = 0;
        this.winningCombinations = new ArrayList<>(Arrays.asList(initialWinningCombinations));
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
        this.victoriesP1 = victoriesP1 + 1;
    }

    public Integer getVictoriesP2() {
        return victoriesP2;
    }

    public void setVictoriesP2(Integer victoriesP2) {
        this.victoriesP2 = victoriesP2 + 1;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw + 1;
    }

    public Boolean getPlayer1Turn() {
        return player1Turn;
    }

    public void setPlayer1Turn(Boolean player1Turn) {
        this.player1Turn = player1Turn;
    }

    public Boolean getLastPlayerStarted() {
        return lastPlayerStarted;
    }

    public void setLastPlayerStarted(Boolean lastPlayerStarted) {
        this.lastPlayerStarted = lastPlayerStarted;
    }

    public Boolean getDetectDraw() {
        return detectDraw;
    }

    public void setDetectDraw(Boolean detectDraw) {
        this.detectDraw = detectDraw;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(Integer moveCount) {
        this.moveCount = moveCount + 1;
    }

    public ArrayList<int[]> getWinningCombinations() {
        return winningCombinations;
    }

    public void setWinningCombinations(ArrayList<int[]> winningCombinations) {
        this.winningCombinations = winningCombinations;
    }

    public char[] getGameXO() {
        return gameXO;
    }

    public void setGameXO(char[] gameXO) {
        this.gameXO = gameXO;
    }

    public int[][] getInitialWinningCombinations() {
        return initialWinningCombinations;
    }


    // Função que adiciona os símbolos no array e alterna o turno dos jogadores.
    public void play(int position){

        // Quando for verdade o player 1 joga;
        // Quando for falso o player 2 joga.
        if (player1Turn) {
            gameXO[position] = symbolP1;         // Adiciona o símbolo do player 1 no array.
        } else {
            gameXO[position] = symbolP2;         // Adiciona o símbolo do player 2 no array.
        }

        player1Turn = !player1Turn;   // Alterna o turno entre os jogadores.
    }

    // Função para verificar se houve vitória.
    public boolean checkVictory(char symbol) {

        // Percorre todas as combinações de vitória possíveis.
        for (int[] combination : initialWinningCombinations) {

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

    // Função que atualiza as combinações de vitória restantes.
    public void updateWinningCombinations() {
        ArrayList<int[]> validCombinations = new ArrayList<>();

        Character currentSymbol = player1Turn ? symbolP1 : symbolP2;
        
        // Verifica cada combinação para garantir que ela ainda é possível.
        for (int[] combination : winningCombinations) {
            if ((gameXO[combination[0]] == '\u0000' || gameXO[combination[0]] == currentSymbol) &&
                    (gameXO[combination[1]] == '\u0000' || gameXO[combination[1]] == currentSymbol) &&
                    (gameXO[combination[2]] == '\u0000' || gameXO[combination[2]] == currentSymbol)) {

                System.out.println(Arrays.toString(combination));
                // Adiciona a combinação à lista de válidas se ainda for possível.
                validCombinations.add(combination);
            }
        }

        // Atualiza a lista original com as combinações válidas.
        winningCombinations = validCombinations;
    }

    // Função para verificar se houve empate.
    public boolean predictDraw() {

        // Se não há mais combinações de vitória possíveis, é empate.
        return winningCombinations.isEmpty();
    }

    // Função para resetar o jogo.
    public void reset() {

        // Preenche todas as posições do array gameXO com o valor '\u0000' (esvaziando-o).
        Arrays.fill(gameXO, '\u0000');

        // Alterna o jogador que começará a nova partida.
        player1Turn = !lastPlayerStarted;

        // Atualiza o último jogador que começou.
        lastPlayerStarted = player1Turn;

        // Reseta a contagem de jogadas.
        moveCount = 0;

        // Reseta as combinações de vitória.
        winningCombinations = new ArrayList<>(Arrays.asList(initialWinningCombinations));
    }
}