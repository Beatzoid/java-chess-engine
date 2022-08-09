package board;

import pieces.Piece;

public abstract class Move {
    final Board board;
    final Piece movedPiece;
    final int destinationCoordinate;

    private Move(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public static final class MajorMove extends Move {

        /**
         * The MajorMove class manages all major moves in the game
         *
         * @param board                 The board
         * @param movedPiece            The piece that was moved
         * @param destinationCoordinate The coordinate of where the piece is going
         */
        public MajorMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }

    public static final class AttackMove extends Move {

        final Piece attackedPiece;

        /**
         * The AttackMove class manages all attacking moves in the game
         *
         * @param board                 The board
         * @param movedPiece            The piece that was moved
         * @param destinationCoordinate The coordinate of where the piece is going
         * @param attackedPiece The piece that was attacked
         */
        public AttackMove(final Board board,
                          final Piece movedPiece,
                          final int destinationCoordinate,
                          final Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate);
            this.attackedPiece = attackedPiece;
        }
    }
}
