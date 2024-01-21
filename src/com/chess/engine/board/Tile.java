package com.chess.engine.board;
import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;
// Square Centric representation of the board is implemented in this program
// .
// Visit:https://www.chessprogramming.org/Board_Representation to know more.
public abstract class Tile {
    protected final int tileCoordinate;//Immutable
    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE =  createAllPossibleEmptyTiles();
    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {

        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        //creates all the possible empty tiles
        //you get an empty tile by simply calling EmptyTiles.get(x);
        for(int i = 0; i < BoardUtils.NUM_TILES; i++) {
            emptyTileMap.put(i , new EmptyTile(i));
        }

        //could have used Collections.unmodifiableMap(emptyTileMap)
        return ImmutableMap.copyOf(emptyTileMap);
    }

    public static Tile createTile(final int tileCoordinate, final Piece piece) {
        return piece != null ? new OccupiedTile(tileCoordinate,piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
    }

    //Private constructors for immutability.
    private Tile(final int tileCoordinate) {

        this.tileCoordinate = tileCoordinate;
    }
    public abstract boolean isOccupied();
    public abstract Piece getPiece();
    public static final class EmptyTile extends Tile {
        private EmptyTile(int coordinate) {
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
        private final Piece pieceOnTile;

        private OccupiedTile(int tileCoordinate, final Piece pieceOnTile) {
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
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
