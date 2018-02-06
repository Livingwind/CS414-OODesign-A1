package a1;
import java.util.ArrayList;

public class Knight extends ChessPiece {
  public Knight (ChessBoard board, Color color) {
    super(board, color);
  }
  public ArrayList<String> legalMoves () {
    return new ArrayList<>();
  }

  public String toString () {
    if(this.color == Color.WHITE)
      return "\u2658";
    return "\u265E";
  }
}
