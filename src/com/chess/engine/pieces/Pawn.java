

//  8 | 56 57 58 59 60 61 62 63
//  7 | 48 49 50 51 52 53 54 55
//  6 | 40 41 xx ** xx 45 46 47
//  5 | 32 33 34 Pn 36 37 38 39
//  4 | 24 25 26 27 28 29 30 31
//  3 | 16 17 18 19 20 21 22 23
//  2 | 8  9  10 11 12 13 14 15
//  1 | 0  1   2  3  4  5  6  7
//     -----------------------
//     a  b  c  d  e  f  g  h
//



package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.Collection;

public class Pawn extends Piece{
    private final int[] CANDIDATE_MOVE_VECTOR_COORDINATES= {8};
    public Pawn(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMove(Board board) {
        return null;
    }
}
