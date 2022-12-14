package pieces;

import board.Board;
import board.Move;

import java.util.Collection;

public abstract class Piece {
    protected final int piecePosition;
    protected final Alliance pieceAlliance;

    /**
     * The Piece class manages all chess pieces
     * @param piecePosition The position of the piece
     * @param pieceAlliance The alliance (black/white) of the piece
     */
    Piece(final int piecePosition, final Alliance pieceAlliance) {
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
    }

    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }

    /**
     * Calculate the legal moves for the piece
     * @param board The chess board
     * @return The legal moves of the piece
     */
    public abstract Collection<Move> calculateLegalMoves(final Board board);
}
