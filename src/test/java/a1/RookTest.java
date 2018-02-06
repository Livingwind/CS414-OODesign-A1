package a1;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RookTest {
  private ChessBoard board;
  private ChessPiece testRook;

  @Before
  public void setUp() {
    board = new ChessBoard();

    testRook = new Rook(board, ChessPiece.Color.WHITE);
  }

  @Test
  public void testLegalMoves() {
    board.placePiece(testRook, "b4");
    board.placePiece(new Rook(board, ChessPiece.Color.WHITE), "b5");
    board.placePiece(new Rook(board, ChessPiece.Color.BLACK), "c4");

    ArrayList<String> moves = testRook.legalMoves();

    assertEquals(5, moves.size());
    assertTrue(moves.contains("c4"));   // Able to take black at c4
    assertFalse(moves.contains("d4"));  // Blocked by black at c4
    assertFalse(moves.contains("b5"));  // Blocked by white at b5
    assertFalse(moves.contains("c5"));  // Can't move diagonally
  }

  @Test
  public void testToString() {
    assertEquals("\u2656", testRook.toString());
    assertEquals("\u265C", new Rook(board, ChessPiece.Color.BLACK).toString());
  }

}