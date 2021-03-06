
package test;

import game.Piece;
import game.Piece.Color;
import game.Player;
import game.Square;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author pganuza
 */
public class SquareTest {
    Square square;
    Player playerBlue = new Player("Blue", Color.BLUE, 4);
    Piece pieceBlue0 = playerBlue.getPieces()[0];
    Piece pieceBlue1 = playerBlue.getPieces()[1];
    Piece pieceBlue2 = playerBlue.getPieces()[2];
    Piece pieceBlue3 = playerBlue.getPieces()[3];
    Player playerRed = new Player("Red", Color.RED, 4);
    Piece pieceRed0 = playerRed.getPieces()[0];
    Piece pieceRed1 = playerRed.getPieces()[1];
    Piece pieceRed2 = playerRed.getPieces()[2];
    Piece pieceRed3 = playerRed.getPieces()[3];

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        square = new Square(0);
    }

    /**
     * Test method for {@link game.Square#GetNumber()}.
     */
    @Test
    public void testGetNumberAndPenalty() {
        Assert.assertEquals(0, square.getNumber());

        square = new Square(Integer.MIN_VALUE);
        Assert.assertEquals(Integer.MIN_VALUE, square.getNumber());
        Assert.assertEquals(0, square.getPenalty());

        square = new Square(Integer.MAX_VALUE);
        Assert.assertEquals(Integer.MAX_VALUE, square.getNumber());
        Assert.assertEquals(0, square.getPenalty());
    }

    /**
     * Test method for {@link game.Square#getOccupants()}.
     */
    @Test
    public void testGetOccupants0() {
        List<Piece> occupants = square.getOccupants();

        Assert.assertTrue(occupants.isEmpty());
    }

    /**
     * Test method for {@link game.Square#getOccupants()}.
     */
    @Test
    public void testGetOccupants1() {
        square.add(pieceBlue0);
        List<Piece> occupants = square.getOccupants();

        Assert.assertEquals(pieceBlue0, occupants.get(0));
        Assert.assertEquals(1, occupants.size());
    }

    /**
     * Test method for {@link game.Square#getOccupants()}.
     */
    @Test
    public void testGetOccupants2() {
        square.add(pieceBlue0);
        square.add(pieceBlue1);
        List<Piece> occupants = square.getOccupants();

        Assert.assertEquals(pieceBlue0, occupants.get(0));
        Assert.assertEquals(pieceBlue1, occupants.get(1));
        Assert.assertEquals(2, occupants.size());
    }

    /**
     * Test method for {@link game.Square#add(game.Piece)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNull() {
        square.add(null);
    }

    /**
     * Test method for {@link game.Square#add(game.Piece)}.
     */
    @Test
    public void testAddToEmptySquare() {
        Assert.assertNull(square.add(pieceBlue0));
        Assert.assertEquals(square, pieceBlue0.getSquare());
    }

    /**
     * Test method for {@link game.Square#add(game.Piece)}.
     */
    @Test
    public void testAddToMakeWall() {
        Assert.assertNull(square.add(pieceBlue0));
        Assert.assertEquals(square, pieceBlue0.getSquare());
        Assert.assertNull(square.add(pieceBlue1));
        Assert.assertEquals(square, pieceBlue1.getSquare());
        Assert.assertTrue(square.isWall());
    }

    /**
     * Test method for {@link game.Square#add(game.Piece)}.
     */
    @Test
    public void testAddSamePieceTwice() {
        Assert.assertNull(square.add(pieceBlue0));
        Assert.assertNull(square.add(pieceBlue0));
    }

    /**
     * Test method for {@link game.Square#add(game.Piece)}.
     */
    @Test(expected = IllegalStateException.class)
    public void testAddPieceToWallSameColor() {
        Assert.assertNull(square.add(pieceBlue0));
        Assert.assertNull(square.add(pieceBlue1));
        square.add(pieceBlue2);
    }

    /**
     * Test method for {@link game.Square#add(game.Piece)}.
     */
    @Test(expected = IllegalStateException.class)
    public void testAddPieceToWallDifferentColor() {
        Assert.assertNull(square.add(pieceBlue0));
        Assert.assertNull(square.add(pieceBlue1));
        square.add(pieceRed0);
    }

    /**
     * Test method for {@link game.Square#add(game.Piece)}.
     */
    @Test(expected = IllegalStateException.class)
    public void testAddSamePieceTwiceToWall() {
        Assert.assertNull(square.add(pieceBlue0));
        Assert.assertNull(square.add(pieceBlue1));
        square.add(pieceBlue0);
    }

    /**
     * Test method for {@link game.Square#remove()}.
     */
    @Test
    public void testRemoveEmpty() {
        Assert.assertNull(square.remove());
    }

    /**
     * Test method for {@link game.Square#remove()}.
     */
    @Test
    public void testRemove1Occupant() {
        square.add(pieceBlue0);
        Assert.assertEquals(pieceBlue0, square.remove());
        Assert.assertNull(pieceBlue0.getSquare());
        Assert.assertNull(square.remove());
    }

    /**
     * Test method for {@link game.Square#remove()}.
     */
    @Test
    public void testRemove2Occupants() {
        square.add(pieceBlue0);
        square.add(pieceBlue1);
        Assert.assertEquals(pieceBlue1, square.remove());
        Assert.assertNull(pieceBlue1.getSquare());
        Assert.assertEquals(pieceBlue0, square.remove());
        Assert.assertNull(pieceBlue0.getSquare());
        Assert.assertNull(square.remove());
    }

    /**
     * Test method for {@link game.Square#remove(game.Piece)}.
     */
    @Test
    public void testRemovePieceEmpty() {
        Assert.assertFalse(square.remove(pieceBlue0));
    }

    /**
     * Test method for {@link game.Square#remove(game.Piece)}.
     */
    @Test
    public void testRemovePieceNotPresent() {
        square.add(pieceBlue0);
        Assert.assertFalse(square.remove(pieceBlue1));
    }

    /**
     * Test method for {@link game.Square#remove(game.Piece)}.
     */
    @Test
    public void testRemovePiece() {
        square.add(pieceBlue0);
        Assert.assertTrue(square.remove(pieceBlue0));
        Assert.assertNull(pieceBlue0.getSquare());
    }

    /**
     * Test method for {@link game.Square#remove(game.Piece)}.
     */
    @Test
    public void testRemovePieceTwice() {
        square.add(pieceBlue0);
        Assert.assertTrue(square.remove(pieceBlue0));
        Assert.assertNull(pieceBlue0.getSquare());
        Assert.assertFalse(square.remove(pieceBlue0));
        Assert.assertNull(pieceBlue0.getSquare());
    }

    /**
     * Test method for {@link game.Square#remove(game.Piece)}.
     */
    @Test
    public void testRemovePieceFromWall() {
        square.add(pieceBlue0);
        Assert.assertEquals(square, pieceBlue0.getSquare());
        square.add(pieceBlue1);
        Assert.assertEquals(square, pieceBlue1.getSquare());
        Assert.assertTrue(square.remove(pieceBlue0));
        Assert.assertNull(pieceBlue0.getSquare());
        Assert.assertTrue(square.remove(pieceBlue1));
        Assert.assertNull(pieceBlue1.getSquare());

        square.add(pieceBlue0);
        square.add(pieceBlue1);
        Assert.assertTrue(square.remove(pieceBlue1));
        Assert.assertNull(pieceBlue1.getSquare());
        Assert.assertTrue(square.remove(pieceBlue0));
        Assert.assertNull(pieceBlue0.getSquare());
    }

    /**
     * Test method for {@link game.Square#isEmpty()}.
     */
    @Test
    public void testIsEmpty0Pieces() {
        Assert.assertTrue(square.isEmpty());
    }

    /**
     * Test method for {@link game.Square#isEmpty()}.
     */
    @Test
    public void testIsEmpty1Piece() {
        square.add(pieceBlue0);
        Assert.assertFalse(square.isEmpty());
    }

    /**
     * Test method for {@link game.Square#isEmpty()}.
     */
    @Test
    public void testIsEmptyWall() {
        square.add(pieceBlue0);
        square.add(pieceBlue1);
        Assert.assertFalse(square.isEmpty());
    }

    /**
     * Test method for {@link game.Square#isEmpty()}.
     */
    @Test
    public void testIsEmptyAfterRemove0Pieces() {
        Assert.assertNull(square.remove());
        Assert.assertTrue(square.isEmpty());
    }

    /**
     * Test method for {@link game.Square#isEmpty()}.
     */
    @Test
    public void testIsEmptyAfterRemove1Piece() {
        square.add(pieceBlue0);
        Assert.assertEquals(pieceBlue0, square.remove());
        Assert.assertNull(pieceBlue0.getSquare());
        Assert.assertTrue(square.isEmpty());
    }

    /**
     * Test method for {@link game.Square#isEmpty()}.
     */
    @Test
    public void testIsEmptyAfterRemove2Pieces() {
        square.add(pieceBlue0);
        square.add(pieceBlue1);
        square.remove();
        Assert.assertFalse(square.isEmpty());
        square.remove();
        Assert.assertTrue(square.isEmpty());
    }

    /**
     * Test method for {@link game.Square#isWall()}.
     */
    @Test
    public void testIsWall0Pieces() {
        Assert.assertFalse(square.isWall());
    }

    /**
     * Test method for {@link game.Square#isWall()}.
     */
    @Test
    public void testIsWall1Piece() {
        square.add(pieceBlue0);
        Assert.assertFalse(square.isWall());
    }

    /**
     * Test method for {@link game.Square#isWall()}.
     */
    @Test
    public void testIsWall2PiecesSameColor() {
        square.add(pieceBlue0);
        square.add(pieceBlue1);
        Assert.assertTrue(square.isWall());
    }

    /**
     * Test method for {@link game.Square#isWall()}.
     */
    @Test
    public void testIsWall2PiecesDifferentColor() {
        square.add(pieceBlue0);
        square.add(pieceRed0);
        Assert.assertFalse(square.isWall());
    }
}
