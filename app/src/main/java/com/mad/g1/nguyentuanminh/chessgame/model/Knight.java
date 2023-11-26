package com.mad.g1.nguyentuanminh.chessgame.model;

import android.util.Pair;

import com.mad.g1.nguyentuanminh.chessgame.ChessBoard;

import java.util.ArrayList;
import java.util.List;

public class Knight extends ChessPiece {


    public Knight(Type type, Color color, int chessIMGid, int row, int col) {
        super(Type.KNIGHT, color, chessIMGid, row, col);
    }

    @Override
    public boolean isValidMove(int toRow, int toCol, ChessBoard chessBoard) {
        // Check if the move is a valid L-shaped move for a knight

        int rowDifference = Math.abs(toRow - getRow());
        int colDifference = Math.abs(toCol - getCol());

        // The Knight moves in an L-shape: two squares in one direction and one square perpendicular
        return (rowDifference == 2 && colDifference == 1) || (rowDifference == 1 && colDifference == 2);
    }

    @Override
    public List<Pair<Integer, Integer>> getValidMove(ChessBoard chessBoard) {
        List<Pair<Integer, Integer>> validMoves = new ArrayList<>();

        // Knight can move in an "L" shape: two squares in one direction and one square in a perpendicular direction

        int[] rowOffsets = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] colOffsets = {1, 2, 2, 1, -1, -2, -2, -1};

        for (int i = 0; i < rowOffsets.length; i++) {
            int newRow = getRow() + rowOffsets[i];
            int newCol = getCol() + colOffsets[i];

            // Check if the move is within the board bounds
            if (newRow >= 0 && newRow < chessBoard.getRows() && newCol >= 0 && newCol < chessBoard.getCols()) {
                ChessPiece destinationPiece = chessBoard.getPiece(newRow, newCol);

                // Check if the destination square is empty or contains an opponent's piece
                if (destinationPiece == null || destinationPiece.getColor() != getColor()) {
                    validMoves.add(new Pair<>(newRow, newCol));
                }
            }
        }

        return validMoves;
    }


}

