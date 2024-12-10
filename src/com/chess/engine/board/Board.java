package com.chess.engine.board;

import com.chess.engine.Alliance;
import com.chess.engine.pieces.*;
import com.google.common.collect.ImmutableList;

import java.util.*;

// Square Centric --> Mailbox representation of the board is implemented in this program
// Visit:https://www.chessprogramming.org/Board_Representation to know more.
public class Board {

    public List<Tile> gameBoard;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;
    public Board(Builder builder){
        this.gameBoard = createGameBoard(builder);
        this.whitePieces = calculateActivePieces(this.gameBoard, Alliance.White);
        this.blackPieces = calculateActivePieces(this.gameBoard, Alliance.Black);

        final Collection<Move> whiteStandardLegalMoves = calculateLegalMoves(this.whitePieces);
        final Collection<Move> blackStandardLegalMoves = calculateLegalMoves(this.blackPieces);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
            final String tileText = this.gameBoard.get(i).toString();
            builder.append(String.format("%3s ", tileText));

            if ((i + 1) % BoardUtils.NUM_TILES_PER_ROW == 0) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    private Collection<Move> calculateLegalMoves(Collection<Piece> pieces) {
        final List<Move> legalMoves = new ArrayList<>();
        for(Piece piece : pieces){
            legalMoves.addAll(piece.calculateLegalMove(this));
        }
        return ImmutableList.copyOf(legalMoves);
    }

    private static Collection<Piece> calculateActivePieces(final List<Tile> gameBoard,
                                                           final Alliance alliance) {
        List<Piece> activePieces = new ArrayList<>();
        for (final Tile tile : gameBoard) {
            if(tile.isOccupied()){
                final Piece piece = tile.getPiece();
                if(piece.getPieceAlliance() == alliance){
                    activePieces.add(piece);
                }
            }
        }
        return ImmutableList.copyOf(activePieces);
    }

    public Tile getTile(final int tileCoordinate) {
        return this.gameBoard.get(tileCoordinate);
    }

    private List<Tile> createGameBoard(final Builder builder) {
        final Tile[] tiles = new Tile[BoardUtils.NUM_TILES];
        for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
            tiles[i] = Tile.createTile(i, builder.boardConfig.get(i));
        }
        return ImmutableList.copyOf(tiles);
    }

    public static Board createStandardBoard() {
        Builder builder = new Builder();

        // Place Black pieces
        builder.setPiece(new Rook(0, Alliance.Black));
        builder.setPiece(new Knight(1, Alliance.Black));
        builder.setPiece(new Bishop(2, Alliance.Black));
        builder.setPiece(new Queen(3, Alliance.Black));
        builder.setPiece(new King(4, Alliance.Black));
        builder.setPiece(new Bishop(5, Alliance.Black));
        builder.setPiece(new Knight(6, Alliance.Black));
        builder.setPiece(new Rook(7, Alliance.Black));
        builder.setPiece(new Pawn(8, Alliance.Black));
        builder.setPiece(new Pawn(9, Alliance.Black));
        builder.setPiece(new Pawn(10, Alliance.Black));
        builder.setPiece(new Pawn(11, Alliance.Black));
        builder.setPiece(new Pawn(12, Alliance.Black));
        builder.setPiece(new Pawn(13, Alliance.Black));
        builder.setPiece(new Pawn(14, Alliance.Black));
        builder.setPiece(new Pawn(15, Alliance.Black));

        // Place White pieces
        builder.setPiece(new Pawn(48, Alliance.White));
        builder.setPiece(new Pawn(49, Alliance.White));
        builder.setPiece(new Pawn(50, Alliance.White));
        builder.setPiece(new Pawn(51, Alliance.White));
        builder.setPiece(new Pawn(52, Alliance.White));
        builder.setPiece(new Pawn(53, Alliance.White));
        builder.setPiece(new Pawn(54, Alliance.White));
        builder.setPiece(new Pawn(55, Alliance.White));
        builder.setPiece(new Rook(56, Alliance.White));
        builder.setPiece(new Knight(57, Alliance.White));
        builder.setPiece(new Bishop(58, Alliance.White));
        builder.setPiece(new Queen(59, Alliance.White));
        builder.setPiece(new King(60, Alliance.White));
        builder.setPiece(new Bishop(61, Alliance.White));
        builder.setPiece(new Knight(62, Alliance.White));
        builder.setPiece(new Rook(63, Alliance.White));

        // Set the move maker
        builder.setNextMoveMaker(Alliance.White);

        return builder.build();
    }

    public static class Builder{

        Map<Integer, Piece> boardConfig;
        Alliance nextMoveMaker;

        public Builder(){
            boardConfig = new HashMap<>();
        }

        public Builder setPiece(final Piece piece){
            this.boardConfig.put(piece.getPiecePosition(), piece);
            return this;
        }

        public Builder setNextMoveMaker(final Alliance nextMoveMaker){
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }

        public Board build(){
            return new Board(this);
        }
    }
}
