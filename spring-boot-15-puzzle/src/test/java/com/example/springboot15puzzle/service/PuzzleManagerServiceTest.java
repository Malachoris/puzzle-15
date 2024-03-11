package com.example.springboot15puzzle.service;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

class PuzzleManagerServiceTest {

    @Test
    void testGenerateGameId() {

        PuzzleManagerService puzzleManagerService = new PuzzleManagerService();

        Set<String> gameIds = new HashSet<>();
        int numberOfIdsToGenerate = 1000;
        for (int i = 0; i < numberOfIdsToGenerate; i++) {
            String gameId = puzzleManagerService.generateGameId();
            assertNotNull(gameId);
            assertFalse("Duplicate game ID generated", gameIds.contains(gameId));
            gameIds.add(gameId);
        }
        assertEquals(numberOfIdsToGenerate, gameIds.size());
    }

}