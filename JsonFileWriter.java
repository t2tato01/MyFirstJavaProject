//Tomoko Takami

package com.number_guessing_game_for2.number_guessing_game_for2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonFileWriter {
    private static final String JSON_FILE_PATH = "players.json"; 
    public static List<Player> loadPlayerInfo() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(JSON_FILE_PATH);
            if (file.exists()) {
                return objectMapper.readValue(file, new TypeReference<List<Player>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void savePlayerInfo(Player player1, Player player2) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Player> players = new ArrayList<>();
            players.add(player1);
            players.add(player2);
            objectMapper.writeValue(new File(JSON_FILE_PATH), players);
            System.out.println("Player data saved to " + JSON_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

