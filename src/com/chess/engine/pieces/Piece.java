package com.chess.engine.pieces;
import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.Collection;
// Piece.java

public abstract class Piece{
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;

    public Piece(final int piecePosition, final Alliance pieceAlliance) {
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        this.isFirstMove = false;
    }
    public Piece(final int piecePosition, final Alliance pieceAlliance, boolean isFirstMove) {
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        // TODO more work here!
        this.isFirstMove = false;
    }
    public Alliance getPieceAlliance(){
        return this.pieceAlliance;
    }
    public abstract Collection<Move> calculateLegalMove(final Board board);
    public boolean isFirstMove(){return this.isFirstMove;}

    public int getPiecePosition() {
        return this.piecePosition;
    }

    public enum pieceType{
        PAWN("P"),
        KNIGHT("N"),
        ROOK("R"),
        BISHOP("B"),
        QUEEN("Q"),
        KING("K");

        private final String pieceName;
        pieceType(final String pieceName){
            this.pieceName = pieceName;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }
    }
}
