package com.mad.g1.nguyentuanminh.chessgame.model;

import com.mad.g1.nguyentuanminh.chessgame.ChessBoard;

public class Rook extends ChessPiece {

    public Rook(Type type, Color color, int chessIMGid, int row, int col) {
        super(Type.ROOK, color, chessIMGid, row, col);
    }

    @Override
    public boolean isValidMove(int toRow, int toCol, ChessBoard chessBoard) {
        // Check if the move is a valid horizontal or vertical move

        // The Rook can move horizontally (same row)
        if (toRow == getRow() && toCol != getCol()) {
            // Check if there are no pieces in between (assuming no capturing for this example)
            int step = (toCol > getCol()) ? 1 : -1;
            for (int col = getCol() + step; col != toCol; col += step) {
                if (chessBoard.getPiece(getRow(), col) != null) {
                    return false; // There is a piece in the way
                }
            }
            return true; // Valid horizontal move
        }

        // The Rook can move vertically (same column)
        if (toCol == getCol() && toRow != getRow()) {
            // Check if there are no pieces in between (assuming no capturing for this example)
            int step = (toRow > getRow()) ? 1 : -1;
            for (int row = getRow() + step; row != toRow; row += step) {
                if (chessBoard.getPiece(row, getCol()) != null) {
                    return false; // There is a piece in the way
                }
            }
            return true; // Valid vertical move
        }

        return false; // Invalid move
    }
}
