package a1;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KnightTest {
  private ChessBoard board;
  private ChessPiece testKnight;

  @Before
  public void setUp() {
    board = new ChessBoard();

    testKnight = new Knight(board, ChessPiece.Color.WHITE);
  }

  @Test
  public void testLegalMoves() {
    board.placePiece(testKnight, "b4");

    ArrayList<String> moves = testKnight.legalMoves();
    assertTrue(moves.isEmpty());
  }

  @Test
  public void testToString() {
    assertEquals("\u2658", testKnight.toString());
    assertEquals("\u265E", new Knight(board, ChessPiece.Color.BLACK).toString());
  }
}
