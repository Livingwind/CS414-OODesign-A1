package a1;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QueenTest {
  private ChessBoard board;
  private ChessPiece testQueen;

  @Before
  public void setUp() {
    board = new ChessBoard();

    testQueen = new Queen(board, ChessPiece.Color.WHITE);
  }

  @Test
  public void testLegalMoves() {
    board.placePiece(testQueen, "b4");

    ArrayList<String> moves = testQueen.legalMoves();
    assertTrue(moves.isEmpty());
  }

  @Test
  public void testToString() {
    assertEquals("\u2655", testQueen.toString());
    assertEquals("\u265B", new Queen(board, ChessPiece.Color.BLACK).toString());
  }
}