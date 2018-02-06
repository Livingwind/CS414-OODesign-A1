package a1;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class KingTest {
  private ChessBoard board;
  private ChessPiece testKing;

  @Before
  public void setUp() {
    board = new ChessBoard();

    testKing = new King(board, ChessPiece.Color.WHITE);
  }

  @Test
  public void testLegalMoves() {
    board.placePiece(testKing, "b4");
    board.placePiece(new King(board, ChessPiece.Color.WHITE), "b5");
    board.placePiece(new King(board, ChessPiece.Color.BLACK), "c4");

    ArrayList<String> moves = testKing.legalMoves();

    assertEquals(7, moves.size());
    assertTrue(moves.contains("c4"));   // Able to take black at c4
    assertFalse(moves.contains("b5"));  // Blocked by white at b5
    assertFalse(moves.contains("d4"));  // Cant move more than 1 space
  }

  @Test
  public void testToString() {
    assertEquals("\u2654", testKing.toString());
    assertEquals("\u265A", new King(board, ChessPiece.Color.BLACK).toString());
  }
}
