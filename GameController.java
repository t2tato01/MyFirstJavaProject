//Tomoko Takami

package com.number_guessing_game_for2.number_guessing_game_for2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService, PlayerService playerService) {
        this.gameService = gameService;
    }

    // Starting a New Game
    // game/start?playerName1=Momoka&playerName2=Kazuki
    @PostMapping("/start")
    public ResponseEntity<String> startNewGame(
        @RequestParam String playerName1, 
        @RequestParam String playerName2  
        ) {
        // Initialize the game using the player's name.
        gameService.initializeGame(playerName1, playerName2);
        return ResponseEntity.ok("New game started for players: " + playerName1 + " and " + playerName2);
        }

    // Making a Guess
    // game/guess?guess=9&playerName=Momoka
    @PostMapping("/guess")
    public ResponseEntity<String> makeGuess(
        @RequestParam String playerName, 
        @RequestParam int guess 
        ) {
        String result = gameService.play(playerName, guess);
        return ResponseEntity.ok(result);
        }
    
    // Getting Current Player Info
    // game/current-player
    @GetMapping("/current-player")
    public ResponseEntity<Player> getCurrentPlayerInfo() {
        if (gameService.isGameFinished()) {
            return ResponseEntity.ok(null); 
        }
        Player currentPlayer = gameService.getCurrentPlayer();
        return ResponseEntity.ok(currentPlayer);
    }
    
    // Default Welcome Message
    // game/
    @GetMapping("/")
    public String getRoot() {
        return "Welcome to My Game App! The Number Guessing Game is an engaging two-player game where participants take turns guessing a randomly selected number between 1 and 10. It's a fun way to challenge your guessing skills and enjoy some friendly competition with a friend.";
    }

    public GameService getGameService() {
        return gameService;
    }
}
