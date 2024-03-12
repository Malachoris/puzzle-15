package com.example.springboot15puzzle.service;

import com.example.springboot15puzzle.model.PuzzleLogic;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PuzzleManagerService {
    private final Map<String, PuzzleLogic> games;

    public PuzzleManagerService() {
        this.games = new HashMap<>();

    }

    // Returns map IDs as keys and games as value.
    public Map<String, PuzzleLogic> getActiveGameIds(){
        return games;
    }

    // Creates new game.
    public String createGame(){
        String gameId = generateGameId();
        PuzzleLogic puzzleLogic = new PuzzleLogic(4);
        games.put(gameId, puzzleLogic);
        return gameId;
    }

    // Returns unique ID.
    public String generateGameId() {
        return UUID.randomUUID().toString();
    }

    // Returns game by ID.
    public List<List<Integer>> getMatrixGameBoard(String gameId) {
        PuzzleLogic puzzleLogic = games.get(gameId);
        if (puzzleLogic == null) {
            throw new IllegalArgumentException("Game not found");
        }
        return puzzleLogic.getMatrixGameBoard();

    }

    // Moves tile to adjacent empty tile.
    public void moveTile(String gameId, int tileValue) {
        PuzzleLogic puzzleLogic = games.get(gameId);
        if (puzzleLogic == null) {
            throw new IllegalArgumentException("Game not found");
        }
        puzzleLogic.moveTile(tileValue);
    }

    // Check if game is won.
    public boolean isGameWon(String gameId) {
        PuzzleLogic puzzleLogic = games.get(gameId);
        if (puzzleLogic == null) {
            throw new IllegalArgumentException("Game not found");
        }
        return puzzleLogic.isGameWon();
    }

}
