package com.chess.engine;

public enum Alliance {
    White(1) {
        @Override
        public int getDirection() {
            return 1; // Moving up the board
        }
    },
    Black(-1) {
        @Override
        public int getDirection() {
            return -1; // Moving down the board
        }
    };

    private final int direction;

    Alliance(int direction) {
        this.direction = direction;
    }

    public abstract int getDirection();

    public boolean isBlack() {
        return this == Black;
    }

    public boolean isWhite() {
        return this == White;
    }

    public int getDirectionValue() {
        return this.direction;
    }
}