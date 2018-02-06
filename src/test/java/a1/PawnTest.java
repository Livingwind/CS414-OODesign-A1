package a1;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PawnTest {
  private ChessBoard board;
  private ChessPiece testWhitePawn;
  private ChessPiece testBlackPawn;

  @Before
  public void setUp () {
    board = new ChessBoard();

    testWhitePawn = new Pawn(board, ChessPiece.Color.WHITE);
    testBlackPawn = new Pawn(board, ChessPiece.Color.BLACK);
  }

  @Test
  public void testBasic () {
    board.placePiece(testWhitePawn, "b4");
    board.placePiece(testBlackPawn, "c5");

    ArrayList<String> moves = testWhitePawn.legalMoves();

    assertEquals(2, moves.size());
    assertTrue(moves.contains("c5"));   // Able to take black at c5
    assertFalse(moves.contains("b6"));  // Can't move 2 spaces
    assertFalse(moves.contains("b3"));

    moves = testBlackPawn.legalMoves();

    assertEquals(2, moves.size());
    assertTrue(moves.contains("b4"));   // Able to take white at b4
    assertTrue(moves.contains("c4"));   // Able to move 1 forward
    assertFalse(moves.contains("c6"));  // Can't move backwards
  }

  @Test
  public void testStart () {
    board.placePiece(testWhitePawn, "b2");
    board.placePiece(testBlackPawn, "b7");


    ArrayList<String> moves = testWhitePawn.legalMoves();
    assertEquals(2, moves.size());
    assertTrue(moves.contains("b3"));   // Able to move 1 forward
    assertTrue(moves.contains("b4"));   // Able to move 2 forward if at start

    moves = testBlackPawn.legalMoves();
    assertEquals(2, moves.size());
    assertTrue(moves.contains("b6"));   // Able to move 1 forward
    assertTrue(moves.contains("b5"));   // Able to move 2 forward
  }

  @Test
  public void testToString () {
    assertEquals("\u2659", testWhitePawn.toString());
    assertEquals("\u265F", testBlackPawn.toString());
  }
}
