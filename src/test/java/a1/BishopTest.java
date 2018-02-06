package a1;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BishopTest {
  private ChessBoard board;
  private ChessPiece testBishop;

  @Before
  public void setUp() {
    board = new ChessBoard();

    testBishop = new Bishop(board, ChessPiece.Color.WHITE);
  }

  @Test
  public void testLegalMoves() {
    board.placePiece(testBishop, "b4");
    board.placePiece(new Bishop(board, ChessPiece.Color.WHITE), "c3");
    board.placePiece(new Bishop(board, ChessPiece.Color.BLACK), "c5");

    ArrayList<String> moves = testBishop.legalMoves();

    assertEquals(3, moves.size());
    assertTrue(moves.contains("c5"));   // Able to take black at c5
    assertFalse(moves.contains("d6"));  // Blocked by black at c5
    assertFalse(moves.contains("c3"));  // Blocked by white at c3
    assertFalse(moves.contains("b5"));  // Can't move vertically
  }

  @Test
  public void testToString() {
    assertEquals("\u2657", testBishop.toString());
    assertEquals("\u265D", new Bishop(board, ChessPiece.Color.BLACK).toString());
  }

}
