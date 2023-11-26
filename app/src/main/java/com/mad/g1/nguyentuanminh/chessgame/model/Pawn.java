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
        int forwardDirection = (getColor() == Color.WHITE) ? 1 : -1;

        // Kiểm tra nước đi có hợp lệ theo hướng tiến của quân tốt
        if (toCol == getCol() && toRow == getRow() + forwardDirection && chessBoard.getPiece(toRow, toCol) == null) {
            return true;
        }

        // Kiểm tra nước đi có hợp lệ khi quân tốt ở vị trí xuất phát và có thể di chuyển 2 ô
        if (toCol == getCol() && Math.abs(toRow - getRow()) == 2 && getRow() == getStartingRow() &&
                chessBoard.getPiece(toRow, toCol) == null &&
                chessBoard.getPiece(getRow() + forwardDirection, toCol) == null) {
            return true;
        }

        // Kiểm tra nước đi có hợp lệ khi quân tốt ăn quân đối phương theo đường chéo
        if (Math.abs(toCol - getCol()) == 1 && toRow == getRow() + forwardDirection) {
            ChessPiece destinationPiece = chessBoard.getPiece(toRow, toCol);
            return destinationPiece != null && destinationPiece.getColor() != getColor();
        }

        // Kiểm tra nước đi có hợp lệ khi quân tốt làm quân hậu sau khi di chuyển đến hàng cuối cùng
        if (toCol == getCol() && toRow == getRow() + forwardDirection && chessBoard.getPiece(toRow, toCol) == null &&
                (toRow == 0 || toRow == 7)) {
            return true;
        }

        // Nếu không phải trường hợp nào trên, nước đi không hợp lệ
        return false;
    }


    @Override
    public List<Pair<Integer, Integer>> getValidMove(ChessBoard chessBoard) {
        List<Pair<Integer, Integer>> validMoves = new ArrayList<>();

        int forwardDirection = (getColor() == Color.WHITE) ? -1 : 1;

        // Kiểm tra một ô phía trước
        int oneSquareForward = getRow() + forwardDirection;
        if (isValidMove(oneSquareForward, getCol(), chessBoard)) {
            validMoves.add(new Pair<>(oneSquareForward, getCol()));
        }

        // Kiểm tra hai ô phía trước từ hàng bắt đầu
        if (getRow() == getStartingRow() && isValidMove(oneSquareForward + forwardDirection, getCol(), chessBoard)) {
            validMoves.add(new Pair<>(oneSquareForward + forwardDirection, getCol()));
        }

        // Kiểm tra chéo để bắt
        int leftDiagonal = getCol() - 1;
        int rightDiagonal = getCol() + 1;

        if (isValidCapture(oneSquareForward, leftDiagonal, chessBoard)) {
            validMoves.add(new Pair<>(oneSquareForward, leftDiagonal));
        }

        if (isValidCapture(oneSquareForward, rightDiagonal, chessBoard)) {
            validMoves.add(new Pair<>(oneSquareForward, rightDiagonal));
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
}

