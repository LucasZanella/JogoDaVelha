package com.example.jogodavelha;

public class Players {
    private String name;
    private String symbol;
    private Integer victories;
    private Integer defeats;
    private Integer draws;

    public Players(String name, String symbol, Integer victories, Integer defeats, Integer draws) {
        this.name = name;
        this.symbol = symbol;
        this.victories = victories;
        this.defeats = defeats;
        this.draws = draws;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getVictories() {
        return victories;
    }

    public void setVictories(Integer victories) {
        this.victories = victories;
    }

    public Integer getDefeats() {
        return defeats;
    }

    public void setDefeats(Integer defeats) {
        this.defeats = defeats;
    }

    public Integer getDraws() {
        return draws;
    }

    public void setDraws(Integer draws) {
        this.draws = draws;
    }
}
