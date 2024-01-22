//Tomoko Takami

package com.number_guessing_game_for2.number_guessing_game_for2;

public class Player {
    private String name;
    private int totalWins;
    private int totalMoves;

    public Player(String name) {
        this.name = name;
        this.totalWins = 0;
        this.totalMoves = 0;
    }

    public String getName() {
        return name;
    }

    public int gettotalWins() {
        return totalWins;
    }

    public void settotalWins(int totalWins) {
        this.totalWins = totalWins;
    }

    public int getTotalMoves() {
        return totalMoves;
    }

    public void setTotalMoves(int totalMoves) {
        this.totalMoves = totalMoves;
    }

    // 他に必要なメソッドや属性があれば追加できます。
    
}
