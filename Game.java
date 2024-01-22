//Tomoko Takami

package com.number_guessing_game_for2.number_guessing_game_for2;

public interface Game {
    void initializeGame(String playerName1, String playerName2);
    String play(String playerName, int guess);
    boolean isGameFinished();
    Player getCurrentPlayer();
    void switchPlayer();
}

