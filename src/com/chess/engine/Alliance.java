package com.chess.engine;

public enum Alliance {
    White {
        @Override
        public int getDirection() {
            return 0;
        }
    },
    Black {
        @Override
        public int getDirection() {
            return 1;
        }
    };

    public abstract int getDirection();

    public boolean isBlack() {
        return this.equals(Black);
    }

    public boolean isWhite() {
        return this.equals(White);
    }
}