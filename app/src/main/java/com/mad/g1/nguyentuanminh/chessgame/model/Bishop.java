package com.mad.g1.nguyentuanminh.chessgame.model;

import android.util.Pair;

import com.mad.g1.nguyentuanminh.chessgame.ChessBoard;

import java.util.List;

public class Bishop extends ChessPiece {

    public Bishop(Type type, Color color, int chessIMGid, int row, int col) {
        super(Type.BISHOP, color, chessIMGid, row, col);
    }

    @Override
    public boolean isValidMove(int toRow, int toCol, ChessBoard chessBoard) {
        // Kiểm tra xem ô đích có trống không
        ChessPiece destinationPiece = chessBoard.getPiece(toRow, toCol);
        if (destinationPiece != null) {
            // Kiểm tra xem ô đích có chứa quân cờ đồng minh không
            if (destinationPiece.getColor() == getColor()) {
                return false; // Quân cờ đồng minh chặn, không hợp lệ
            }
        }

        // Kiểm tra xem nước đi có theo đúng quy tắc của quân Bishop không
        int rowDifference = Math.abs(toRow - getRow());
        int colDifference = Math.abs(toCol - getCol());

        return rowDifference == colDifference;
    }

    @Override
    public List<Pair<Integer, Integer>> getValidMove(ChessBoard chessBoard) {
        return null;
    }

    @Override
    public void move(int toRow, int toCol, ChessBoard chessBoard) {
        if (isValidMove(toRow, toCol, chessBoard)) {
            setRow(toRow);
            setCol(toCol);
        }
    }
}
