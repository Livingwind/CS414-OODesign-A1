package a1;
import java.util.ArrayList;

public class Rook extends ChessPiece {
  public Rook (ChessBoard board, Color color) {
    super(board, color);
  }

  private void searchDiag (ArrayList<String> moves, int rowDir, int colDir) {
    ChessBoard.Pair pair;
    try {
      pair = new ChessBoard.Pair(getPosition());
    } catch (IllegalPositionException e){ return; }

    ChessPiece temp;
    while(true) {
      try {
        pair = new ChessBoard.Pair(pair.row + rowDir, pair.col + colDir);
        temp = board.getPiece(pair.pos);
        if (temp != null) {
          if (this.color != temp.color)
            moves.add(pair.pos);
          break;
        }
        moves.add(pair.pos);
      } catch (IllegalPositionException e) {
        break;
      }
    }
  }

  public ArrayList<String> legalMoves () {
    ArrayList<String> moves = new ArrayList<>();

    searchDiag(moves, 0, 1);
    searchDiag(moves, 1, 0);
    searchDiag(moves, 0, -1);
    searchDiag(moves, -1, 0);

    return moves;
  }

  public String toString () {
    if(this.color == Color.WHITE)
      return "\u2656";
    return "\u265C";
  }
}
