package com.mad.g1.nguyentuanminh.chessgame.model;

import com.mad.g1.nguyentuanminh.chessgame.ChessBoard;

public class Bishop extends ChessPiece {

    public Bishop(Type type, Color color, int chessIMGid, int row, int col) {
        super(Type.BISHOP, color, chessIMGid, row, col);
    }

    @Override
    public boolean isValidMove(int toRow, int toCol, ChessBoard chessBoard) {
        // Check if the move is a valid diagonal move for a bishop

        int rowDifference = Math.abs(toRow - getRow());
        int colDifference = Math.abs(toCol - getCol());

        // The Bishop moves diagonally, so the row and column differences should be equal
        return rowDifference == colDifference;
    }

    @Override
    public void move(int toRow, int toCol, ChessBoard chessBoard) {
        if (isValidMove(toRow, toCol, chessBoard)) {
            setRow(toRow);
            setCol(toCol);
        }
    }
}
