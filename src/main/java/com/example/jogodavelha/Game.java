package com.example.jogodavelha;

public class Game {
    private String nameP1;
    private String nameP2;
    private Character symbolP1;
    private Character symbolP2;
    private Integer victoriesP1;
    private Integer victoriesP2;
    private Integer draw;

    public Game(String nameP1, String nameP2, Character symbolP1, Character symbolP2, Integer victoriesP1, Integer victoriesP2, Integer draw) {
        this.nameP1 = nameP1;
        this.nameP2 = nameP2;
        this.symbolP1 = symbolP1;
        this.symbolP2 = symbolP2;
        this.victoriesP1 = victoriesP1;
        this.victoriesP2 = victoriesP2;
        this.draw = draw;
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
}
