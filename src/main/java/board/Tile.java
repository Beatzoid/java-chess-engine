package board;

import com.google.common.collect.ImmutableMap;
import pieces.Piece;

import java.util.HashMap;
import java.util.Map;

/**
 * The Tile class manages all tiles on the board
 */
public abstract class Tile {
    protected final int tileCoordinate;

    private static final Map<Integer, EmptyTile> EMPTY_TILES = createAllPossibleEmptyTiles();

    private Tile(int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }

    /**
     * Create a tile
     * @param tileCoordinate The tile coordinate for the new tile
     * @param piece The piece, or null if none
     * @return A new OccupiedTile if a piece was provided, otherwise it returns an empty tile
     */
    public static Tile createTile(final int tileCoordinate, final Piece piece) {
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES.get(tileCoordinate);
    }

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for (int i = 0; i < 64; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }

        return ImmutableMap.copyOf(emptyTileMap);
    }

    /**
     * @return Whether the tile is occupied
     */
    public abstract boolean isTileOccupied();

    /**
     * @return The piece on the Tile, or null if no piece is on it
     */
    public abstract Piece getPiece();

    /**
     * The EmptyTile class manages all empty tiles on the board
     */
    public static final class EmptyTile extends Tile {
        EmptyTile(final int tileCoordinate) {
            super(tileCoordinate);
        }

        @Override
        public boolean isTileOccupied() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }
    }

    /**
     * The OccupiedTile class manages all occupied tiles on the board
     */
    public static final class OccupiedTile extends Tile {
        private final Piece pieceOnTile;

        /**
         * Creates a new OccupiedTile
         * @param tileCoordinate The tile coordinate for the new occupied tile
         * @param pieceOnTile The piece to put on the tile
         */
        public OccupiedTile(final int tileCoordinate, Piece pieceOnTile) {
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return pieceOnTile;
        }
    }
}