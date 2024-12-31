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

public class Queen extends Piece {
//   QUEEN's moves are a union of BISHOP and ROOK.
    private static final int[] CANDIDATE_MOVE_VECTOR_COORDINATES = { -9, -8, -7, -1, 1, 7, 8, 9 };
    public Queen(int piecePosition, final Alliance pieceAlliance) {
        super(PieceType.QUEEN, piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMove(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
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
    @Override
    public String toString() {
        return pieceType.QUEEN.toString();
    }
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){

        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1 || candidateOffset == -9 || candidateOffset == 7);
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){

        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == 1 || candidateOffset == -7 || candidateOffset == 9);
    }
}

