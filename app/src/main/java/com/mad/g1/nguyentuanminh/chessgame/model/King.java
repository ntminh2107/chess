package com.mad.g1.nguyentuanminh.chessgame.model;

import android.util.Pair;

import com.mad.g1.nguyentuanminh.chessgame.ChessBoard;

import java.util.ArrayList;
import java.util.List;

public class King extends ChessPiece {

    public King(Type type, Color color, int chessIMGid, int row, int col) {
        super(Type.KING, color, chessIMGid, row, col);
    }

    @Override
    public boolean isValidMove(int toRow, int toCol, ChessBoard chessBoard) {
        // Check if the move is a valid horizontal, vertical, or diagonal move for a king

        int rowDifference = Math.abs(toRow - getRow());
        int colDifference = Math.abs(toCol - getCol());

        // The King can move one square in any direction, so check if the differences are at most 1
        return (rowDifference <= 1 && colDifference <= 1);
    }

    @Override
    public List<Pair<Integer, Integer>> getValidMove(ChessBoard chessBoard) {
        List<Pair<Integer, Integer>> validMoves = new ArrayList<>();

        // Check horizontally and vertically
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int colOffset = -1; colOffset <= 1; colOffset++) {
                if (rowOffset == 0 && colOffset == 0) {
                    continue;  // Skip the current position
                }

                int toRow = getRow() + rowOffset;
                int toCol = getCol() + colOffset;

                if (isValidMove(toRow, toCol, chessBoard)) {
                    validMoves.add(new Pair<>(toRow, toCol));
                }
            }
        }

        return validMoves;
    }

    @Override
    public void move(int toRow, int toCol, ChessBoard chessBoard) {
        if (isValidMove(toRow, toCol, chessBoard)) {
            setRow(toRow);
            setCol(toCol);
        }
    }
}
