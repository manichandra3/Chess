package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.chess.engine.board.Move.*;

//  8 | ** 57 58 59 60 61 ** 63
//  7 | 48 ** 50 51 52 ** 54 55
//  6 | 40 41 ** 43 ** 45 46 47
//  5 | 32 33 34 Bi 36 37 38 39
//  4 | 24 25 ** 27 ** 29 30 31
//  3 | 16 ** 18 19 20 ** 22 23
//  2 | ** 9  10 11 12 13 ** 15
//  1 | 0  1  2  3  4  5  6  **
//     -----------------------
//     a  b  c  d  e  f  g  h
//     here kn = 35 and possible moves are {7, 8, 14, 17, 21, 26, 28, 42, 44, 49, 51, 56, 62}
//     hence the move offsets are {-28, -27, -21, -18, -14, -9, -7, 7, 9, 14, 16, 21, 27}.
//     Alternatively, you can also employ { -9, -7, 7, 9 } repeatedly.
public class Bishop extends Piece{

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = { -9, -7, 7, 9 };

    public Bishop(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMove(final Board board) {
        final List<Move>  legalMoves = new ArrayList<>();
        // Loop iterates through all the possible piece positions using candidate offsets.
        for(final int candidateDestinationCoordinateOffset: CANDIDATE_MOVE_VECTOR_COORDINATES){
            int candidateDestinationCoordinate = this.piecePosition;
            // For all the valid moves
            while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                candidateDestinationCoordinate+=candidateDestinationCoordinateOffset;

                if (BoardUtils.isValidTileCoordinate((candidateDestinationCoordinate))) {
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);

                    if(isFirstColumnExclusion(candidateDestinationCoordinate,candidateDestinationCoordinateOffset) ||
                       isEighthColumnExclusion(candidateDestinationCoordinate,candidateDestinationCoordinateOffset)){
                        break;
                    }

                    // If the destination tile is not occupied
                    if (!candidateDestinationTile.isOccupied()) {
                        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                    } else {
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                        // If the destination tile is occupied by an enemy piece
                        if (this.pieceAlliance != pieceAlliance) {
                            legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                        }
                    }
                    break;
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public String toString() {
        return pieceType.BISHOP.toString();
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){

        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9 || candidateOffset == 7);
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){

        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset == 9);
    }
}
