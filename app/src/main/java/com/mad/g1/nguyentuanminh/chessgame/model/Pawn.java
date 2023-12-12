package com.mad.g1.nguyentuanminh.chessgame.model;
import android.util.Pair;

import com.mad.g1.nguyentuanminh.chessgame.ChessBoard;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece {



    public Pawn(Type type, Color color, int chessIMGid, int row, int col) {
        super(Type.PAWN, color, chessIMGid, row, col);
    }




    @Override
    public boolean isValidMove(int toRow, int toCol, ChessBoard chessBoard) {
        // Lấy thông tin về quân cờ ở ô đích
        ChessPiece destinationPiece = chessBoard.getPiece(toRow, toCol);

        // Kiểm tra nếu ô đích là ô trống
        if (destinationPiece instanceof EmptyPiece) {
            // Xử lý nước đi cho quân tốt khi di chuyển vào ô trống
            int direction = (getColor() == Color.BLACK) ? 1 : -1;

            // Kiểm tra nếu di chuyển lên hoặc xuống một ô
            if (toRow == getRow() + direction && toCol == getCol()) {
                return true;
            }

            // Kiểm tra nếu quân tốt ở hàng ban đầu và có thể di chuyển hai ô
            if (isStartingRow() && toRow == getRow() + 2 * direction && toCol == getCol()) {
                // Kiểm tra xem có quân cờ nào ở ô giữa không
                int middleRow = getRow() + direction;
                ChessPiece middlePiece = chessBoard.getPiece(middleRow, getCol());
                return middlePiece instanceof EmptyPiece;
            }

            // Nếu không phải các trường hợp trên, trả về false
            return false;
        } else {
            // Nếu ô đích không phải là ô trống, xử lý kiểm tra nước đi ăn quân cờ
            return super.isValidMove(toRow,toCol,chessBoard);
        }
    }


    @Override
    public List<Pair<Integer, Integer>> getValidMove(ChessBoard chessBoard) {
        List<Pair<Integer, Integer>> validMoves = new ArrayList<>();

        int direction = (getColor() == Color.BLACK) ? 1 : -1;

        // Kiểm tra di chuyển lên hoặc xuống một ô
        int move1Row = getRow() + direction;
        if (isValidPosition(move1Row, getCol()) && chessBoard.getPiece(move1Row, getCol()) instanceof EmptyPiece) {
            validMoves.add(new Pair<>(move1Row, getCol()));
        }

        // Kiểm tra di chuyển hai ô từ hàng ban đầu
        if (isStartingRow()) {
            int move2Row = getRow() + 2 * direction;
            int middleRow = getRow() + direction;

            if (isValidPosition(move2Row, getCol()) && chessBoard.getPiece(move2Row, getCol()) instanceof EmptyPiece
                    && chessBoard.getPiece(middleRow, getCol()) instanceof EmptyPiece) {
                validMoves.add(new Pair<>(move2Row, getCol()));
            }
        }

        return validMoves;
    }

    private boolean isValidCapture(int toRow, int toCol, ChessBoard chessBoard) {
        ChessPiece destinationPiece = chessBoard.getPiece(toRow, toCol);
        return destinationPiece != null && destinationPiece.getColor() != getColor();
    }

    @Override
    public void move(int toRow, int toCol, ChessBoard chessBoard) {
        if (isValidMove(toRow, toCol, chessBoard)) {
            setRow(toRow);
            setCol(toCol);
        }
    }
    private boolean isStartingRow() {
        return (getColor() == Color.BLACK && getRow() == 1) || (getColor() == Color.WHITE && getRow() == 6);
    }
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
}

