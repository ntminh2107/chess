package com.mad.g1.nguyentuanminh.chessgame;

import android.content.Context;
import android.util.Log;

import com.mad.g1.nguyentuanminh.chessgame.model.Bishop;
import com.mad.g1.nguyentuanminh.chessgame.model.ChessPiece;
import com.mad.g1.nguyentuanminh.chessgame.model.EmptyPiece;
import com.mad.g1.nguyentuanminh.chessgame.model.King;
import com.mad.g1.nguyentuanminh.chessgame.model.Knight;
import com.mad.g1.nguyentuanminh.chessgame.model.Pawn;
import com.mad.g1.nguyentuanminh.chessgame.model.Queen;
import com.mad.g1.nguyentuanminh.chessgame.model.Rook;
import com.mad.g1.nguyentuanminh.chessgame.view.SquareView;

import java.util.Arrays;

public class ChessBoard {
    private static final int BOARD_SIZE = 8;
    private ChessPiece[][] board;
    private SquareView[][] squareViews = new SquareView[BOARD_SIZE][BOARD_SIZE];
    private int rows;
    private int cols;

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public ChessBoard(Context context) {
        // Khởi tạo bảng cờ và squareViews
        board = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
        squareViews = new SquareView[BOARD_SIZE][BOARD_SIZE];

        // Khởi tạo giá trị rỗng cho tất cả ô trên bàn cờ và tạo SquareView tương ứng
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = null;

                // Tạo mới SquareView và thiết lập nó cho ô trên bàn cờ
                SquareView squareView = new SquareView(context);
                squareView.setRow(row);
                squareView.setCol(col);

                // Set up touch listener for each SquareView

                squareViews[row][col] = squareView;

                // ... (Thêm squareView vào layout của bạn)
            }
        }
    }



    public void setPiece(int row, int col, ChessPiece piece) {
        if (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE) {
            board[row][col] = piece;
            if (piece != null) {
                piece.setCol(col);
                piece.setRow(row);
            }
        }
    }
    public ChessPiece getPiece(int row, int col) {
        if (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE) {
            return board[row][col];
        } else {
            return null;
        }
    }


    public void initializeChessBoard()
    {
        for (int col = 0; col < 8; col++) {

            if(col == 0 || col == 7)
            {
                board[0][col] = new Rook(ChessPiece.Type.ROOK,ChessPiece.Color.BLACK,R.drawable.black_rook,0,col);
            }
            if(col == 1 || col == 6)
            {
                board[0][col] = new Knight(ChessPiece.Type.KNIGHT,ChessPiece.Color.BLACK, R.drawable.black_knight,0,col);

            }
            if(col == 2 || col == 5)
            {
                board[0][col] = new Bishop(ChessPiece.Type.BISHOP,ChessPiece.Color.BLACK,R.drawable.black_bishop,0,col);
            }
            if(col == 3)
            {
                board[0][col] = new Queen(ChessPiece.Type.QUEEN,ChessPiece.Color.BLACK,R.drawable.black_queen,0,col);
            }
            if(col == 4)
            {
                board[0][col] = new King(ChessPiece.Type.KING,ChessPiece.Color.BLACK,R.drawable.black_king,0,col);
            }
            board[1][col] = new Pawn(ChessPiece.Type.PAWN, ChessPiece.Color.BLACK,R.drawable.black_pawn,1,col);
        }

        // Example: Add black pawns to the seventh row
        for (int col = 0; col < 8; col++) {
            if(col == 0 || col == 7)
            {
                board[7][col] = new Rook(ChessPiece.Type.ROOK,ChessPiece.Color.WHITE,R.drawable.rookw,7,col);
            }
            if(col == 1 || col == 6)
            {
                board[7][col] = new Knight(ChessPiece.Type.KNIGHT,ChessPiece.Color.WHITE, R.drawable.white_knight,7,col);

            }
            if(col == 2 || col == 5)
            {
                board[7][col] = new Bishop(ChessPiece.Type.BISHOP,ChessPiece.Color.WHITE,R.drawable.white_bishop,7,col);
            }
            if(col == 3)
            {
                board[7][col] = new Queen(ChessPiece.Type.QUEEN,ChessPiece.Color.WHITE,R.drawable.white_queen,7,col);
            }
            if(col == 4)
            {
                board[7][col] = new King(ChessPiece.Type.KING,ChessPiece.Color.WHITE,R.drawable.white_king,7,col);
            }
            board[6][col] = new Pawn(ChessPiece.Type.PAWN, ChessPiece.Color.WHITE,R.drawable.white_pawn,6,0);

        }
        System.out.println(toString());
        for(int row = 2; row<6;row++)
        {
            for (int col = 0; col < 8; col++)
            {
                board[row][col] = new EmptyPiece();
            }
        }

        }

    public void setSquareViews(SquareView[][] squareViews) {
        this.squareViews = squareViews;
    }


    public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        ChessPiece pieceToMove = getPiece(fromRow, fromCol);
        if(pieceToMove !=null && pieceToMove.isValidMove(toRow,toCol,this))
        {
            System.out.println("run" + toCol +" "+ toRow);
            pieceToMove.move(toRow,toCol,this);
            setPiece(toRow, toCol, pieceToMove);
            setPiece(fromRow, fromCol, null);

            // Update SquareViews after the move
            squareViews[toRow][toCol].setChessPiece(pieceToMove);
            squareViews[fromRow][fromCol].setChessPiece(null);
        }

    }

    public SquareView getSquareView(int row, int col) {
        if (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE) {
            return squareViews[row][col];
        } else {
            return null;
        }
    }

    public void setSquareView(SquareView squareView, int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            squareViews[row][col] = squareView;
        }
    }

    public void updateUI() {
        // Gọi phương thức updateChessboardUI() của từng SquareView trong bảng cờ
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                squareViews[row][col].updateChessboardUI();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (ChessPiece[] row : board) {
            stringBuilder.append(Arrays.toString(row)).append("\n");
        }
        return stringBuilder.toString();
    }
}
