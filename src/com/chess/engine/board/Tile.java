package com.chess.engine.board;

public abstract class Tile {
    int tileCoordinate;

    Tile(int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }
    public abstract boolean isOccupied();
    public abstract Piece getPiece();
    public static final class EmptyTile extends Tile {
        EmptyTile(int coordinate) {
            super(coordinate);
        }
        @Override
        public boolean isOccupied() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }
    }
    public static final class OccupiedTile extends Tile {
        Piece pieceOnTile;

        OccupiedTile(int tileCoordinate) {
            super(tileCoordinate);
        }

        @Override
        public boolean isOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return this.pieceOnTile;
        }
    }
}
