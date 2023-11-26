package com.mad.g1.nguyentuanminh.chessgame.model;

import android.util.Pair;

import com.mad.g1.nguyentuanminh.chessgame.ChessBoard;

import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece {


    public Queen(Type type, Color color, int chessIMGid, int row, int col) {
        super(Type.QUEEN, color, chessIMGid, row, col);
    }

    @Override
    public boolean isValidMove(int toRow, int toCol, ChessBoard chessBoard) {
        // Check if the move is a valid horizontal, vertical, or diagonal move for a queen

        int rowDifference = Math.abs(toRow - getRow());
        int colDifference = Math.abs(toCol - getCol());

        // The Queen can move horizontally, vertically, or diagonally, so check all possibilities
        return (getRow() == toRow || getCol() == toCol || rowDifference == colDifference);
    }

    @Override
    public List<Pair<Integer, Integer>> getValidMove(ChessBoard chessBoard) {
        List<Pair<Integer, Integer>> validMoves = new ArrayList<>();

        // Check horizontally to the right
        for (int col = getCol() + 1; col < chessBoard.getCols(); col++) {
            if (!isValidMove(getRow(), col, chessBoard)) {
                break;
            }
            validMoves.add(new Pair<>(getRow(), col));
        }

        // Check horizontally to the left
        for (int col = getCol() - 1; col >= 0; col--) {
            if (!isValidMove(getRow(), col, chessBoard)) {
                break;
            }
            validMoves.add(new Pair<>(getRow(), col));
        }

        // Check vertically upwards
        for (int row = getRow() - 1; row >= 0; row--) {
            if (!isValidMove(row, getCol(), chessBoard)) {
                break;
            }
            validMoves.add(new Pair<>(row, getCol()));
        }

        // Check vertically downwards
        for (int row = getRow() + 1; row < chessBoard.getRows(); row++) {
            if (!isValidMove(row, getCol(), chessBoard)) {
                break;
            }
            validMoves.add(new Pair<>(row, getCol()));
        }

        // Check diagonally to the upper right
        for (int row = getRow() - 1, col = getCol() + 1; row >= 0 && col < chessBoard.getCols(); row--, col++) {
            if (!isValidMove(row, col, chessBoard)) {
                break;
            }
            validMoves.add(new Pair<>(row, col));
        }

        // Check diagonally to the upper left
        for (int row = getRow() - 1, col = getCol() - 1; row >= 0 && col >= 0; row--, col--) {
            if (!isValidMove(row, col, chessBoard)) {
                break;
            }
            validMoves.add(new Pair<>(row, col));
        }

        // Check diagonally to the lower right
        for (int row = getRow() + 1, col = getCol() + 1; row < chessBoard.getRows() && col < chessBoard.getCols(); row++, col++) {
            if (!isValidMove(row, col, chessBoard)) {
                break;
            }
            validMoves.add(new Pair<>(row, col));
        }

        // Check diagonally to the lower left
        for (int row = getRow() + 1, col = getCol() - 1; row < chessBoard.getRows() && col >= 0; row++, col--) {
            if (!isValidMove(row, col, chessBoard)) {
                break;
            }
            validMoves.add(new Pair<>(row, col));
        }

        return validMoves;
    }
}
