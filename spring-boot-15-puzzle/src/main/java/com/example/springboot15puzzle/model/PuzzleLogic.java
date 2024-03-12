package com.example.springboot15puzzle.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PuzzleLogic {

    private List<Integer> flatGameBoard;
    private List<List<Integer>> matrixGameBoard;
    private final int boardSize;

    public PuzzleLogic(int boardSize) {
        this.boardSize = boardSize;
        initializeFlatBoard();
        initializeShuffledMatrixBoard();
    }

    // Creates array with values from 0 to 15.
    private void initializeFlatBoard() {
        flatGameBoard = new ArrayList<>();

        for (int tile = 0; tile < boardSize * boardSize; tile++) {
            flatGameBoard.add(tile);
        }
    }

    // Shuffles existing array and splits to appropriate board size.
    private void initializeShuffledMatrixBoard() {
        Collections.shuffle(flatGameBoard);

        matrixGameBoard = new ArrayList<>();

        for (int rowIndex = 0; rowIndex < boardSize; rowIndex++) {
            List<Integer> row = new ArrayList<>();

            for (int colIndex = 0; colIndex < boardSize; colIndex++) {
                row.add(flatGameBoard.get(rowIndex * boardSize + colIndex));
            }
            matrixGameBoard.add(row);
        }
    }

    // Logic and checkups to move tile
    public void moveTile(int tileValue){
        int[] tilePosition = findTilePosition(tileValue); // tilePosition = [2, 3]
        int[] emptyTilePosition = findEmptyTilePosition(); // emptyTilePosition = [2, 2]

        if (!isAdjacent(tilePosition, emptyTilePosition)) {
            throw new IllegalArgumentException("Invalid move");
        }
        swapTiles(tilePosition, emptyTilePosition);
    }

    // Returns empty tile position in matrix [2, 3]
    private int[] findEmptyTilePosition() {
        for (int rowIndex = 0; rowIndex < matrixGameBoard.size(); rowIndex++) {
            List<Integer> currentRow = matrixGameBoard.get(rowIndex);

            for (int colIndex = 0; colIndex < currentRow.size(); colIndex++) {

                if (currentRow.get(colIndex) == 0) {
                    return new int[]{rowIndex, colIndex};
                }
            }
        }
        throw new IllegalStateException("Empty tile not found on the game board");
    }

    // Returns requested value tile position in matrix [2, 3]
    private int[] findTilePosition(int tileValue) {
        for (int rowIndex = 0; rowIndex < matrixGameBoard.size(); rowIndex++) {
            List<Integer> currentRow = matrixGameBoard.get(rowIndex);

            for (int colIndex = 0; colIndex < currentRow.size(); colIndex++) {

                if (currentRow.get(colIndex) == tileValue) {
                    return new int[]{rowIndex, colIndex};
                }
            }
        }
        throw new IllegalStateException("Tile not found on the game board");
    }

    // Takes tile and empty tile position arrays. Swap tiles positions.
    private void swapTiles(int[] tilePosition, int[] emptyTilePosition) {
        List<Integer> tileRow = matrixGameBoard.get(tilePosition[0]);
        List<Integer> emptyTileRow = matrixGameBoard.get(emptyTilePosition[0]);

        int tempTile = tileRow.get(tilePosition[1]);

        tileRow.set(tilePosition[1], emptyTileRow.get(emptyTilePosition[1]));
        emptyTileRow.set(emptyTilePosition[1], tempTile);
    }

    // Takes tile and empty tile position arrays. Returns true if tile is adjacent to empty tile and false if not.
    private boolean isAdjacent(int[] tilePosition, int[] emptyTilePosition) {
        int rowDiff = Math.abs(tilePosition[0] - emptyTilePosition[0]);
        int colDiff = Math.abs(tilePosition[1] - emptyTilePosition[1]);

        return (rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1);
    }

    // Returns matrixGameBoard.
    public List<List<Integer>> getMatrixGameBoard() {
        return matrixGameBoard;
    }

    // Returns true if tiles are ascending order from 0 index otherwise false.
    public boolean isGameWon() {
        int expectedValue = 1;

        for (List<Integer> currentRow : matrixGameBoard) {
            for (int tileValue : currentRow) {

                if (tileValue != expectedValue % (boardSize * boardSize)) {
                    return false;
                }

                expectedValue++;
            }
        }
        return true;
    }

}


