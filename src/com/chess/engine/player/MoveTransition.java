package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

public class MoveTransition {
    private final Board transitionBoard;
    private final Move transitionMove;
    private final MoveStatus moveStatus;

    public MoveTransition(final Board transitionBoard, final Move transitionMove, final MoveStatus moveStatus) {
        this.transitionBoard = transitionBoard;
        this.transitionMove = transitionMove;
        this.moveStatus = moveStatus; 
    }
}
