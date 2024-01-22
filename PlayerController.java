//Tomoko Takami

package com.number_guessing_game_for2.number_guessing_game_for2;

import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private final PlayerService playerService;
    private final ObjectMapper objectMapper; 


    public PlayerController(PlayerService playerService, ObjectMapper objectMapper) {
        this.playerService = playerService;
        this.objectMapper = objectMapper; 
    }

    // Getting Previous Game Records
    // player/get-info?playerName=Momoka
    @GetMapping("/get-info")
    public ResponseEntity<String> getPlayerInfo(@RequestParam String playerName) {
        Player player = playerService.getPlayerByName(playerName);
        if (player != null) {
            try {
                String playerInfoJson = objectMapper.writeValueAsString(player); // Convert a Player object to a JSON string.
                return ResponseEntity.ok(playerInfoJson);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.internalServerError().body("Failed to serialize Player information to JSON");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
