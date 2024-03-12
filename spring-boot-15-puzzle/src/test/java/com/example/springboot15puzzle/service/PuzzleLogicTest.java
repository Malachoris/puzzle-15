package com.example.springboot15puzzle.service;

import com.example.springboot15puzzle.model.PuzzleLogic;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleLogicTest {

    private final PuzzleLogic puzzleLogic = new PuzzleLogic(4);

    @Test
    public void testIsGameWon() {
        List<List<Integer>> matrixBoard = new ArrayList<>();

        matrixBoard.add(Arrays.asList(1, 2, 3, 4));
        matrixBoard.add(Arrays.asList(5, 6, 7, 8));
        matrixBoard.add(Arrays.asList(9, 10, 11, 12));
        matrixBoard.add(Arrays.asList(13, 14, 15, 0));

        assertTrue(isGameWon(matrixBoard));

    }

    public boolean isGameWon(List<List<Integer>> matrixBoard) {
        int expectedValue = 1;

        for (List<Integer> currentRow : matrixBoard) {
            for (int tileValue : currentRow) {

                if (tileValue != expectedValue % (4 * 4)) {
                    return false;
                }

                expectedValue++;
            }
        }
        return true;
    }

    @Test
    public void testMatrixGameBoard(){
        List<List<Integer>> getMatrixGameBoard = puzzleLogic.getMatrixGameBoard();
        assertNotNull(getMatrixGameBoard);
        assertEquals(4, getMatrixGameBoard.size());
        assertEquals(4, getMatrixGameBoard.get(0).size());
        assertEquals(4, getMatrixGameBoard.get(1).size());
        assertEquals(4, getMatrixGameBoard.get(2).size());
        assertEquals(4, getMatrixGameBoard.get(3).size());
    }

    @Test
    public void testNumberRange(){
        List<List<Integer>> gameState = puzzleLogic.getMatrixGameBoard();
        for(List<Integer> row : gameState){
            for(int tile : row){
                assertTrue(tile >= 0 && tile <= 15);
            }
        }
    }

}