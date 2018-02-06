package a1;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ChessBoardTest {
  private ChessBoard board;

  @Before
  public void setUp () {
    board = new ChessBoard();
    board.initialize();
  }

  @Test
  public void testInit () throws Exception {
    assertEquals("\u265C", board.getPiece("a8").toString());
    assertEquals("\u265C", board.getPiece("h8").toString());
    assertEquals("\u265E", board.getPiece("b8").toString());
    assertEquals("\u265E", board.getPiece("g8").toString());
    assertEquals("\u265D", board.getPiece("c8").toString());
    assertEquals("\u265D", board.getPiece("f8").toString());
    assertEquals("\u265A", board.getPiece("e8").toString());
    assertEquals("\u265B", board.getPiece("d8").toString());
    for (int i = 0; i < 8; i++) {
      ChessBoard.Pair pair = new ChessBoard.Pair(6, i);
      assertEquals("\u265F", board.getPiece(pair.pos).toString());
    }

    assertEquals("\u2656", board.getPiece("a1").toString());
    assertEquals("\u2656", board.getPiece("h1").toString());
    assertEquals("\u2658", board.getPiece("b1").toString());
    assertEquals("\u2658", board.getPiece("g1").toString());
    assertEquals("\u2657", board.getPiece("c1").toString());
    assertEquals("\u2657", board.getPiece("f1").toString());
    assertEquals("\u2654", board.getPiece("e1").toString());
    assertEquals("\u2655", board.getPiece("d1").toString());
    for (int i = 0; i < 8; i++) {
      ChessBoard.Pair pair = new ChessBoard.Pair(1, i);
      assertEquals("\u2659", board.getPiece(pair.pos).toString());
    }
  }

  @Test
  public void testPosition () throws Exception {
    ChessBoard.Pair pair = new ChessBoard.Pair("d8");
    assertEquals(7, pair.row);
    assertEquals(3, pair.col);
    pair = new ChessBoard.Pair(7, 3);
    assertEquals("d8", pair.pos);
  }

  @Test
  public void testMove () throws Exception {
    board.placePiece(new Rook(board, ChessPiece.Color.WHITE), "a4");
    board.placePiece(new Rook(board, ChessPiece.Color.BLACK), "a5");
    board.move("a4", "a5");

    assertTrue(board.getPiece("a5") instanceof Rook);
    assertEquals(null, board.getPiece("a4"));
  }

  @Test
  public void testPieceGetSet () throws Exception {
    ChessPiece testPawn = new Pawn(board, ChessPiece.Color.WHITE);
    boolean result = board.placePiece(testPawn, "e5");
    assertTrue(result);
    result = board.placePiece(testPawn, "e1");
    assertFalse(result);

    assertEquals(board.getPiece("e5"), testPawn);
  }
}
