package a1;
import java.util.ArrayList;

public abstract class ChessPiece {
  public enum Color {WHITE, BLACK}

  protected ChessBoard board;
  protected int column;
  protected int row;
  protected Color color;

  public ChessPiece (ChessBoard board, Color color) {
    this.board = board;
    this.color = color;
  }

  public Color getColor() {
    return color;
  }

  public String getPosition () {
    try {
      return (new ChessBoard.Pair(row, column)).pos;
    } catch (IllegalPositionException e) {
      return null;
    }
  }

  public void setPosition (String position) throws IllegalPositionException {
    ChessBoard.Pair newPos = new ChessBoard.Pair(position);
    this.row = newPos.row;
    this.column = newPos.col;
  }

  abstract public String toString();
  abstract public ArrayList<String> legalMoves();
}
