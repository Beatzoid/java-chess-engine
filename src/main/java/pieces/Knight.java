package pieces;

import board.Board;
import board.BoardUtils;
import board.Move;
import board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Knight extends Piece {

    private final static int[] CANDIDATE_MOVE_OFFSET_COORDINATES = { -17, -15, -10, -6, 6, 10, 15, 17 };

    /**
     * The Knight class manages all knight pieces
     *
     * @param piecePosition The position of the knight
     * @param pieceAlliance The alliance (black/white) of the knight
     */
    Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        List<Move> legalMoves = new ArrayList<>();

        // For every move in the move offset coordinates
        for (final int currentCandidateOffset : CANDIDATE_MOVE_OFFSET_COORDINATES) {
            // Get the destination board coordinate
            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;

            // If the move coordinate is valid (e.g. not out of bounds)
            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset)
                        || isSecondColumnExclusion(this.piecePosition, currentCandidateOffset)
                        || isSeventhColumnExclusion(this.piecePosition, currentCandidateOffset)
                        || isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)
                ) continue;

                // Get the tile of the destination coordinate
                final Tile candidateDestinationTile = Board.getTile(candidateDestinationCoordinate);

                // If the tile is not occupied
                if (!candidateDestinationTile.isTileOccupied()) {
                    legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                } else {
                    // If the tile is occupied
                    // Get the piece and alliance of said piece at the destination tile
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = candidateDestinationTile.getPiece().getPieceAlliance();

                    // If the knights alliance is not the same as the piece alliance, it's an enemy
                    if (this.pieceAlliance != pieceAlliance) {
                        legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPos, final int candidateOffset) {
       return BoardUtils.FIRST_COLUMN[currentPos]
               && candidateOffset == -17
               || candidateOffset == -10
               || candidateOffset == 6
               || candidateOffset == 15;
    }

    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.SECOND_COLUMN[currentPosition]
                && candidateOffset == -10
                || candidateOffset == 6;
    }

    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.SEVENTH_COLUMN[currentPosition]
                && candidateOffset == -6
                || candidateOffset == 10;
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition]
                && candidateOffset == -15
                || candidateOffset == -6
                || candidateOffset == 10
                || candidateOffset == 17;
    }
}
