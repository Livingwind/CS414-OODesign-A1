package a1;
public class ChessBoard {
  public static class Pair {
    public String pos;
    public int row;
    public int col;

    public Pair (String position) throws IllegalPositionException {
      if (position.length() != 2)
        throw new IllegalPositionException();

      row = position.charAt(1) - '1';
      col = position.charAt(0) - 'a';
      pos = position;
    }

    public Pair (int row, int col) throws IllegalPositionException {
      testValid(row, col);
      this.row = row;
      this.col = col;
      reposition();
    }

    public Pair (Pair o) {
      this.pos = o.pos;
      this.row = o.row;
      this.col = o.col;
    }

    public void add (int rowVal, int colVal) throws IllegalPositionException {
      testValid(row + rowVal, col + colVal);
      this.row += rowVal;
      this.col += colVal;
      reposition();
    }

    private void testValid (int row, int col) throws IllegalPositionException {
      if (col < 0 || col > 7)
        throw new IllegalPositionException();
      if (row < 0 || row > 7)
        throw new IllegalPositionException();
    }

    private void reposition() {
      int x = row + 1;
      this.pos = "" + (char)(col + 'a') + x;
    }
  }

  private ChessPiece[][] board;

  public ChessBoard () {
    board = new ChessPiece[8][8];
  }

  public void initialize () {
    placePiece(new Rook(this, ChessPiece.Color.BLACK), "a8");
    placePiece(new Rook(this, ChessPiece.Color.BLACK), "h8");
    placePiece(new Knight(this, ChessPiece.Color.BLACK), "b8");
    placePiece(new Knight(this, ChessPiece.Color.BLACK), "g8");
    placePiece(new Bishop(this, ChessPiece.Color.BLACK), "c8");
    placePiece(new Bishop(this, ChessPiece.Color.BLACK), "f8");
    placePiece(new King(this, ChessPiece.Color.BLACK), "e8");
    placePiece(new Queen(this, ChessPiece.Color.BLACK), "d8");
    for (int i = 0; i < 8; i++) {
      try {
        ChessBoard.Pair pair = new ChessBoard.Pair(6, i);
        placePiece(new Pawn(this, ChessPiece.Color.BLACK), pair.pos);
      } catch (IllegalPositionException e) {}
    }

    placePiece(new Rook(this, ChessPiece.Color.WHITE), "a1");
    placePiece(new Rook(this, ChessPiece.Color.WHITE), "h1");
    placePiece(new Knight(this, ChessPiece.Color.WHITE), "b1");
    placePiece(new Knight(this, ChessPiece.Color.WHITE), "g1");
    placePiece(new Bishop(this, ChessPiece.Color.WHITE), "c1");
    placePiece(new Bishop(this, ChessPiece.Color.WHITE), "f1");
    placePiece(new King(this, ChessPiece.Color.WHITE), "e1");
    placePiece(new Queen(this, ChessPiece.Color.WHITE), "d1");
    for (int i = 0; i < 8; i++) {
      try {
        ChessBoard.Pair pair = new ChessBoard.Pair(1, i);
        placePiece(new Pawn(this, ChessPiece.Color.WHITE), pair.pos);
      } catch (IllegalPositionException e) {}
    }
  }

  public ChessPiece getPiece (String position) throws IllegalPositionException {
    Pair loc = new Pair(position);
    return board[loc.row][loc.col];
  }

  public boolean canPlace(ChessPiece piece, Pair testLoc) {
    try {
      ChessPiece toLoc = getPiece(testLoc.pos);
      if (toLoc != null && toLoc.color == piece.color)
        return false;

      return true;
    } catch (IllegalPositionException e) {
      return false;
    }
  }

  public boolean placePiece(ChessPiece piece, String position) {
    try {
      Pair loc = new Pair(position);
      if(canPlace(piece, loc)) {
        board[loc.row][loc.col] = piece;
        piece.setPosition(position);
        return true;
      }
    } catch (IllegalPositionException e) {}

    return false;
  }

  public void move(String fromPosition, String toPosition) throws IllegalMoveException {
    try {
      ChessPiece fromPiece = getPiece(fromPosition);
      if (fromPiece.legalMoves().contains(toPosition)) {
        Pair fromLoc = new Pair(fromPosition);
        board[fromLoc.row][fromLoc.col] = null;
        placePiece(fromPiece, toPosition);
      }
      else
        throw new IllegalMoveException();
    } catch (IllegalPositionException e) {}
  }

  // toString given by
  // http://www.cs.colostate.edu/~ghosh/courses/cs414-s18/yr2018sp/more_assignments/toString.java
  public String toString(){
    String chess="";
    String upperLeft = "\u250C";
    String upperRight = "\u2510";
    String horizontalLine = "\u2500";
    String horizontal3 = horizontalLine + "\u3000" + horizontalLine;
    String verticalLine = "\u2502";
    String upperT = "\u252C";
    String bottomLeft = "\u2514";
    String bottomRight = "\u2518";
    String bottomT = "\u2534";
    String plus = "\u253C";
    String leftT = "\u251C";
    String rightT = "\u2524";

    String topLine = upperLeft;
    for (int i = 0; i<7; i++){
      topLine += horizontal3 + upperT;
    }
    topLine += horizontal3 + upperRight;

    String bottomLine = bottomLeft;
    for (int i = 0; i<7; i++){
      bottomLine += horizontal3 + bottomT;
    }
    bottomLine += horizontal3 + bottomRight;
    chess+=topLine + "\n";

    for (int row = 7; row >=0; row--){
      String midLine = "";
      for (int col = 0; col < 8; col++){
        if(board[row][col]==null) {
          midLine += verticalLine + " \u3000 ";
        } else {midLine += verticalLine + " "+board[row][col]+" ";}
      }
      midLine += verticalLine;
      String midLine2 = leftT;
      for (int i = 0; i<7; i++){
        midLine2 += horizontal3 + plus;
      }
      midLine2 += horizontal3 + rightT;
      chess+=midLine+ "\n";
      if(row>=1)
        chess+=midLine2+ "\n";
    }

    chess+=bottomLine;
    return chess;
  }

  public static void main(String[] args) {
    ChessBoard board = new ChessBoard();
    board.initialize();
    System.out.println(board);
    try {
      board.move("b2", "b4");
      board.move("c1", "a3");
    } catch (IllegalMoveException e) {}
    System.out.println(board);
  }
}
