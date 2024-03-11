package com.example.springboot15puzzle.dto;

import lombok.Data;

@Data
public class MoveRequest {
    private int tileValue;

    public int getTileValue() {
        return tileValue;
    }

    public void setTileValue(int tileValue) {
        this.tileValue = tileValue;
    }
}
