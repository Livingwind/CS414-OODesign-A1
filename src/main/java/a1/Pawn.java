package a1;
import java.util.ArrayList;

public class Pawn extends ChessPiece {
  public Pawn (ChessBoard board, Color color) {
    super(board, color);
  }

  private boolean testFirstMove (ChessBoard.Pair cur) {
    if (color == Color.WHITE && cur.row == 1)
      return true;
    else if (color == Color.BLACK && cur.row == 6)
      return true;
    return false;
  }

  private void addMoves (ArrayList<String> moves, int direction) {
    ChessBoard.Pair curLoc, toLoc;
    try {
      curLoc = new ChessBoard.Pair(row, column);
      toLoc = new ChessBoard.Pair(curLoc);
    } catch (IllegalPositionException e) { return; }

    try {
      toLoc.add(1 * direction, 0);
      if (board.canPlace(this, toLoc)) {
        moves.add(toLoc.pos);
        if (testFirstMove(curLoc) && board.canPlace(this, toLoc)) {
          toLoc.add(1*direction, 0);
          moves.add(toLoc.pos);
        }
      }

      ChessPiece toPiece;
      toLoc = new ChessBoard.Pair(curLoc);
      try {
        toLoc.add(1 * direction, -1);
        toPiece = board.getPiece(toLoc.pos);
        if (toPiece != null && toPiece.color != color)
          moves.add(toLoc.pos);
      } catch (IllegalPositionException e) {}

      toLoc = new ChessBoard.Pair(curLoc);
      try {
        toLoc.add(1 * direction, 1);
        toPiece = board.getPiece(toLoc.pos);
        if (toPiece != null && toPiece.color != color)
          moves.add(toLoc.pos);
      } catch (IllegalPositionException e) {}
    } catch (IllegalPositionException e) {}
  }

  public ArrayList<String> legalMoves () {
    ArrayList<String> moves = new ArrayList<>();

    if (color == Color.WHITE)
      addMoves(moves, 1);
    else
      addMoves(moves, -1);

    return moves;
  }

  public String toString () {
    if(this.color == Color.WHITE)
      return "\u2659";
    return "\u265F";
  }
}
