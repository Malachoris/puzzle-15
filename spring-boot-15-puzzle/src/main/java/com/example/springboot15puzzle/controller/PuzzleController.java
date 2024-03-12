package com.example.springboot15puzzle.controller;

import com.example.springboot15puzzle.dto.MoveRequest;
import com.example.springboot15puzzle.model.PuzzleLogic;
import com.example.springboot15puzzle.service.PuzzleManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/game")
public class PuzzleController {

    @Autowired
    private PuzzleManagerService puzzleManagerService;

    @PostMapping
    public ResponseEntity<String> createGame(){
        String gameId = puzzleManagerService.createGame();
        return new ResponseEntity<>(gameId, HttpStatus.CREATED);
    }

    @GetMapping("/IDs")
    public ResponseEntity<Map<String, PuzzleLogic>> getGameIds(){
        Map<String, PuzzleLogic> gameIds = puzzleManagerService.getActiveGameIds();
        return ResponseEntity.ok().body(gameIds);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<List<List<Integer>>> getGameState(@PathVariable String gameId){
        List<List<Integer>> matrixGameBoard = puzzleManagerService.getMatrixGameBoard(gameId);
        return ResponseEntity.ok().body(matrixGameBoard);
    }

    @PutMapping("/{gameId}/move")
    public ResponseEntity<List<List<Integer>>> makeMove(@PathVariable String gameId,
                                                        @RequestBody MoveRequest moveRequest) {
        puzzleManagerService.moveTile(gameId, moveRequest.getTileValue());
        List<List<Integer>> updatedMatrixGameBoard = puzzleManagerService.getMatrixGameBoard(gameId);
        return ResponseEntity.ok().body(updatedMatrixGameBoard);
    }

    @GetMapping("/{gameId}/complete")
    public ResponseEntity<Boolean> checkGameCompletion(@PathVariable String gameId){
        boolean isGameWon = puzzleManagerService.isGameWon(gameId);
        return new ResponseEntity<>(isGameWon, HttpStatus.OK);
    }



}
