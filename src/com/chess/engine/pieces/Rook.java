package com.chess.engine.pieces;
//  8 | 56 57 58 ** 60 61 62 63
//  7 | 48 49 50 ** 52 53 54 55
//  6 | 40 41 42 ** 44 45 46 47
//  5 | ** ** ** Rk ** ** ** **
//  4 | 24 25 26 ** 28 29 30 31
//  3 | 16 17 18 ** 20 21 22 23
//  2 | 8  9  10 ** 12 13 14 15
//  1 | 0  1  2  **  4  5  6  7
//     -----------------------
//     a  b  c  d  e  f  g  h
//     here Rk = 35 and the possible legal moves are { 3, 11, 19, 27, 32, 33, 34, 36, 37, 38, 39, 43, 51, 59 }
//     so the candidate offset is { -32, -24, -16, -8, -3, -2, -1, 1, 2, 3, 4, 8, 16, 24 }.
//     alternatively, you can also repeatedly employ the offset { -8, -1, 1, 8 }.
import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rook extends Piece {

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-8, -1, 1, 8};

    public Rook(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMove(Board board) {

        final List<Move> legalMoves = new ArrayList<>();
        // Loop iterates through all the possible piece positions using candidate offsets.
        for (final int candidateDestinationCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
            int candidateDestinationCoordinate = this.piecePosition;
            // For all the valid moves
            while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                candidateDestinationCoordinate += candidateDestinationCoordinateOffset;

                if (BoardUtils.isValidTileCoordinate((candidateDestinationCoordinate))) {
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);

                    if (isFirstColumnExclusion(candidateDestinationCoordinate, candidateDestinationCoordinateOffset) ||
                            isEighthColumnExclusion(candidateDestinationCoordinate, candidateDestinationCoordinateOffset)) {
                        break;
                    }

                    // If the destination tile is not occupied
                    if (!candidateDestinationTile.isOccupied()) {
                        legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                    } else {
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                        // If the destination tile is occupied by an enemy piece
                        if (this.pieceAlliance != pieceAlliance) {
                            legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                        }
                    }
                    break;
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }
    private static boolean isFirstColumnExclusion ( final int currentPosition, final int candidateOffset){

        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1);
    }

    private static boolean isEighthColumnExclusion ( final int currentPosition, final int candidateOffset){

        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == 1);
    }
}
