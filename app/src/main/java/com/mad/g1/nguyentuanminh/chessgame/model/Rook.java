package com.mad.g1.nguyentuanminh.chessgame.model;

import android.util.Pair;

import com.mad.g1.nguyentuanminh.chessgame.ChessBoard;

import java.util.ArrayList;
import java.util.List;

public class Rook extends ChessPiece {

    public Rook(Type type, Color color, int chessIMGid, int row, int col) {
        super(Type.ROOK, color, chessIMGid, row, col);
    }

    @Override
    public boolean isValidMove(int toRow, int toCol, ChessBoard chessBoard) {
        // Check if the move is a valid horizontal or vertical move

        // Horizontal move
        if (toRow == getRow() && toCol != getCol()) {
            return isPathClearHorizontal(toCol, chessBoard);
        }

        // Vertical move
        if (toCol == getCol() && toRow != getRow()) {
            return isPathClearVertical(toRow, chessBoard);
        }

        return false;
    }

    @Override
    public List<Pair<Integer, Integer>> getValidMove(ChessBoard chessBoard) {
        List<Pair<Integer, Integer>> validMoves = new ArrayList<>();

        // Horizontal moves
        for (int col = 0; col < chessBoard.getCols(); col++) {
            if (isValidMove(getRow(), col, chessBoard)) {
                validMoves.add(new Pair<>(getRow(), col));
            }
        }

        // Vertical moves
        for (int row = 0; row < chessBoard.getRows(); row++) {
            if (isValidMove(row, getCol(), chessBoard)) {
                validMoves.add(new Pair<>(row, getCol()));
            }
        }

        return validMoves;
    }

    private boolean isPathClearHorizontal(int toCol, ChessBoard chessBoard) {
        int startCol = Math.min(getCol(), toCol);
        int endCol = Math.max(getCol(), toCol);

        for (int col = startCol + 1; col < endCol; col++) {
            if (chessBoard.getPiece(getRow(), col) != null) {
                // There is a piece in the path
                return false;
            }
        }

        return true;
    }

    private boolean isPathClearVertical(int toRow, ChessBoard chessBoard) {
        int startRow = Math.min(getRow(), toRow);
        int endRow = Math.max(getRow(), toRow);

        for (int row = startRow + 1; row < endRow; row++) {
            if (chessBoard.getPiece(row, getCol()) != null) {
                // There is a piece in the path
                return false;
            }
        }

        return true;
    }
    @Override
    public void move(int toRow, int toCol, ChessBoard chessBoard) {
        if (isValidMove(toRow, toCol, chessBoard)) {
            setRow(toRow);
            setCol(toCol);
        }
    }
}
