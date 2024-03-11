package com.example.springboot15puzzle.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PuzzleManagerService {
    private final Map<String, PuzzleLogicService> games;

    public PuzzleManagerService() {
        this.games = new HashMap<>();

    }

    // Returns map IDs as keys and games as value.
    public Map<String, PuzzleLogicService> getActiveGameIds(){
        return games;
    }

    // Creates new game.
    public String createGame(){
        String gameId = generateGameId();
        PuzzleLogicService puzzleLogicService = new PuzzleLogicService(4);
        games.put(gameId, puzzleLogicService);
        return gameId;
    }

    // Returns unique ID.
    public String generateGameId() {
        return UUID.randomUUID().toString();
    }

    // Returns game by ID.
    public List<List<Integer>> getMatrixGameBoard(String gameId) {
        PuzzleLogicService puzzleLogicService = games.get(gameId);
        if (puzzleLogicService == null) {
            throw new IllegalArgumentException("Game not found");
        }
        return puzzleLogicService.getMatrixGameBoard();

    }

    // Moves tile to adjacent empty tile.
    public void moveTile(String gameId, int tileValue) {
        PuzzleLogicService puzzleLogicService = games.get(gameId);
        if (puzzleLogicService == null) {
            throw new IllegalArgumentException("Game not found");
        }
        puzzleLogicService.moveTile(tileValue);
    }

    // Check if game is won.
    public boolean isGameWon(String gameId) {
        PuzzleLogicService puzzleLogicService = games.get(gameId);
        if (puzzleLogicService == null) {
            throw new IllegalArgumentException("Game not found");
        }
        return puzzleLogicService.isGameWon();
    }

}
