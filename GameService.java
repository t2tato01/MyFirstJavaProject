//Tomoko Takami

package com.number_guessing_game_for2.number_guessing_game_for2;

import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class GameService implements Game{
    private int targetNumber;
    private boolean gameFinished;
    private int totalGuesses;

    private PlayerService playerService;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public GameService(PlayerService playerService) {
        this.playerService = playerService;
        player1 = null;
        player2 = null;
        currentPlayer = null;
    }

    @Override
    public void initializeGame(String playerName1, String playerName2) {
        Random random = new Random();
        targetNumber = random.nextInt(10) + 1; 
        gameFinished = false;
        totalGuesses = 0;

        player1 = playerService.getPlayerByName(playerName1);
        if (player1 == null) {
            player1 = playerService.createPlayer(playerName1);
        }

        player2 = playerService.getPlayerByName(playerName2);
        if (player2 == null) {
            player2 = playerService.createPlayer(playerName2);
        }
            
        JsonFileWriter.savePlayerInfo(player1, player2);
        currentPlayer = player1;
    }

    @Override
    public String play(String playerName, int guess) {
        if (gameFinished) {
            return "Game is finished, please start a new game";
        }

        if (!currentPlayer.getName().equals(playerName)) {
            return "It's not your turn, " + playerName + "!";
        }

        totalGuesses++;
    
        switchPlayer();

        if (guess < targetNumber) {
            return "The number is larger";
        } else if (guess > targetNumber) {
            return "The number is smaller";
        } else {
            gameFinished = true;

            switchPlayer();

            Player winner = currentPlayer;
            Player loser = (currentPlayer == player1) ? player2 : player1;

            winner.settotalWins(winner.gettotalWins() + 1);
            winner.setTotalMoves(winner.getTotalMoves() + totalGuesses);
            loser.settotalWins(loser.gettotalWins() + 0);
            loser.setTotalMoves(loser.getTotalMoves() + totalGuesses);
     
            JsonFileWriter.savePlayerInfo(player1, player2);
           
            return "Correct! " + winner.getName() + " wins. You used " + totalGuesses + " moves";
        }
     }

    @Override
    public boolean isGameFinished() {
        return gameFinished;
    }

    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public void switchPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

}
