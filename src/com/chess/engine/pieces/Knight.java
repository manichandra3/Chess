package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{

    private final static int[] CANDIDATE_MOVE_COORDINATES={-17, -15, -10, -6, 6, 10, 15, 17};
    public Knight(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public List<Move> calculateLegalMove(Board board) {
        int candidateDestinationCoordinate;
        List<Move> legalMoves = new ArrayList<>();
        for(int currentCandidate: CANDIDATE_MOVE_COORDINATES){
            candidateDestinationCoordinate = this.piecePosition+currentCandidate;
            if(true /*isValidTileCoordinate*/ ) {

                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                //if tile not occupied
                if(!candidateDestinationTile.isOccupied()){
                    legalMoves.add(new Move());
                //if tile occupied
                } else {

                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                    //if tile occupied by enemy piece
                    if(this.pieceAlliance != pieceAlliance){
                        legalMoves.add(new Move());
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }
}
