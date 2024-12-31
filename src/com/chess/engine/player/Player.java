package com.chess.engine.player;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Piece;

import java.util.Collection;

public abstract class Player {

    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMoves;
    public Player(
        Board board,
        Collection<Move> legalMoves,
        Collection<Move> opponentMoves
    ) {
        this.board = board;
        this.playerKing = establishKing();
        this.legalMoves = legalMoves;
    }
    private King establishKing() {
        for (final Piece piece : getActivePieces()) {
            if (piece.getPieceType().isKing()) {
                return (King) piece;
            }
        }
        throw new IllegalStateException("No king");
    }
//    TODO Implement the  methods below
    public boolean isMoveLegal(Move move) {
        return this.legalMoves.contains(move);
    }
    public boolean isCheck(Move move) {
        return false;
    }
    public boolean isCheckMate(Move move) {
        return false;
    }
    public boolean isInStaleMate(Move move) {
        return false;
    }
    public boolean isCastled(Move move) {
        return false;
    }
    public MoveTransition makeMove(Move move){}
    public abstract Collection<Piece> getActivePieces();
    public abstract Alliance getAlliance();
    public abstract Player getOpponent();
}
