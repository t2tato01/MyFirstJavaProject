//Tomoko Takami

package com.number_guessing_game_for2.number_guessing_game_for2;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    // A list to store player information.
    private List<Player> players; 

    public PlayerService() {
        this.players = new ArrayList<>();
    }

    // A method to create a player and add them to the list.
    public Player createPlayer(String name) {
        Player player = new Player(name);
        players.add(player);
        return player; 
    }

    // A method to retrieve player information by the specified name.
    public Player getPlayerByName(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null; // Return null if the player with the specified name is not found.
    }

}
