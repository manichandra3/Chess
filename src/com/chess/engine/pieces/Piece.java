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
}


/*
public abstract class Piece {
    private int x;
    private int y;
    private final boolean isWhite;

    public Piece(int x, int y, boolean isWhite) {
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isWhite() {
        return isWhite;
    }

    // Method to check if a move is valid for this piece
    public abstract boolean isValidMove(int newX, int newY);

    // Method to move the piece
    public void move(int newX, int newY) {
        if (isValidMove(newX, newY)) {
            this.x = newX;
            this.y = newY;
        }
    }
}

// King.java
class King extends Piece {
    public King(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        // Implement logic to check if the move is valid for a King
        // Example: A King can move one square in any direction
        int dx = Math.abs(newX - getX());
        int dy = Math.abs(newY - getY());
        return (dx <= 1 && dy <= 1);
    }
}

// Queen.java
class Queen extends Piece {
    public Queen(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        // Implement logic to check if the move is valid for a Queen
        // Example: A Queen can move diagonally, horizontally, or vertically any number of squares
        int dx = Math.abs(newX - getX());
        int dy = Math.abs(newY - getY());
        return (dx == dy || getX() == newX || getY() == newY);
    }
}

// Rook.java
class Rook extends Piece {
    public Rook(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        // Implement logic to check if the move is valid for a Rook
        // Example: A Rook can move horizontally or vertically any number of squares
        return (getX() == newX || getY() == newY);
    }
}

// Bishop.java
class Bishop extends Piece {
    public Bishop(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        // Implement logic to check if the move is valid for a Bishop
        // Example: A Bishop can move diagonally any number of squares
        int dx = Math.abs(newX - getX());
        int dy = Math.abs(newY - getY());
        return (dx == dy);
    }
}

// Knight.java
class Knight extends Piece {
    public Knight(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        // Implement logic to check if the move is valid for a Knight
        // Example: A Knight can move in an L-shape: 2 squares in one direction and 1 square in a perpendicular direction
        int dx = Math.abs(newX - getX());
        int dy = Math.abs(newY - getY());
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }
}

// Pawn.java
class Pawn extends Piece {
    private boolean hasMoved; // Track whether the pawn has moved before

    public Pawn(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
        this.hasMoved = false;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        // Implement logic to check if the move is valid for a Pawn
        // Example: A Pawn can move forward one square, or two squares on its first move,
        // and can capture diagonally
        int dx = Math.abs(newX - getX());
        int dy = newY - getY();

        if (isWhite()) {
            if (!hasMoved && dx == 0 && (dy == -1 || dy == -2)) {
                hasMoved = true;
                return true;
            } else if (dx == 0 && dy == -1) {
                return true;
            } else return dx == 1 && dy == -1;
        } else {
            if (!hasMoved && dx == 0 && (dy == 1 || dy == 2)) {
                hasMoved = true;
                return true;
            } else if (dx == 0 && dy == 1) {
                return true;
            } else return dx == 1 && dy == 1;
        }
    }
}
*/
