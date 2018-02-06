package a1;
import java.util.ArrayList;

public class King extends ChessPiece {
  public King (ChessBoard board, Color color) {
    super(board, color);
  }

  private boolean testSpace(ChessBoard.Pair pair) {
    ChessPiece temp;
    try {
      temp = board.getPiece(pair.pos);
      if (temp != null && this.color == temp.color)
        return false;
    } catch (IllegalPositionException e) {
      return false;
    }

    return true;
  }

  public ArrayList<String> legalMoves () {
    ArrayList<String> moves = new ArrayList<>();

    ChessBoard.Pair loc;
    try {
      loc = new ChessBoard.Pair(getPosition());
    } catch (IllegalPositionException e) {
      return null;
    }


    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        try {
          ChessBoard.Pair testLoc = new ChessBoard.Pair(loc.row + i, loc.col + j);
          if (testSpace(testLoc))
            moves.add(testLoc.pos);
        } catch (IllegalPositionException e) {}
      }
    }

    return moves;
  }

  public String toString () {
    if(this.color == Color.WHITE)
      return "\u2654";
    return "\u265A";
  }
}
