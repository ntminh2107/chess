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
        int rowDifference = Math.abs(toRow - getRow());
        int colDifference = Math.abs(toCol - getCol());

        // Quân mã di chuyển theo hình L: hai ô theo một hướng và một ô theo hướng vuông góc
        return (rowDifference == 2 && colDifference == 1) || (rowDifference == 1 && colDifference == 2);
    }

    @Override
    public List<Pair<Integer, Integer>> getValidMove(ChessBoard chessBoard) {
        List<Pair<Integer, Integer>> validMoves = new ArrayList<>();

        // Quân mã có thể di chuyển theo hình "L": hai ô theo một hướng và một ô theo hướng vuông góc

        int[] rowOffsets = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] colOffsets = {1, 2, 2, 1, -1, -2, -2, -1};

        for (int i = 0; i < rowOffsets.length; i++) {
            int newRow = getRow() + rowOffsets[i];
            int newCol = getCol() + colOffsets[i];

            // Kiểm tra xem bước đi có nằm trong giới hạn của bàn cờ không
            if (newRow >= 0 && newRow < chessBoard.getRows() && newCol >= 0 && newCol < chessBoard.getCols()) {
                ChessPiece destinationPiece = chessBoard.getPiece(newRow, newCol);

                // Kiểm tra xem ô đích có trống không hoặc chứa quân cờ đối phương không
                if (destinationPiece == null || destinationPiece.getColor() != getColor()) {
                    validMoves.add(new Pair<>(newRow, newCol));
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

