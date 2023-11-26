package com.mad.g1.nguyentuanminh.chessgame.model;
import android.util.Pair;

import com.mad.g1.nguyentuanminh.chessgame.ChessBoard;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece {

    private static final int STARTING_ROW_BLACK = 1;
    private static final int STARTING_ROW_WHITE = 6;


    public Pawn(Type type, Color color, int chessIMGid, int row, int col) {
        super(Type.PAWN, color, chessIMGid, row, col);
    }

    @Override
    public int getStartingRow() {
        return (getColor() == Color.BLACK) ? STARTING_ROW_BLACK : STARTING_ROW_WHITE;
    }


    @Override
    public boolean isValidMove(int toRow, int toCol, ChessBoard chessBoard) {
        int forwardDirection = (getColor() == Color.WHITE) ? -1 : 1;

        // Check if the move is a valid forward move
        if (toCol == getCol() && toRow == getRow() + forwardDirection) {
            return true;
        }

        // Check if the move is a valid forward 2 squares from the starting row
        if (toCol == getCol() && Math.abs(toRow - getRow()) == 2 && getRow() == getStartingRow()) {
            int middleRow = (getRow() + toRow) / 2;
            if (chessBoard.getPiece(middleRow, getCol()) == null) {
                return true;
            }
        }

        // Check if the move is a valid diagonal move for capturing
        if (Math.abs(toCol - getCol()) == 1 && toRow == getRow() + forwardDirection) {
            ChessPiece destinationPiece = chessBoard.getPiece(toRow, toCol);
            return destinationPiece != null && destinationPiece.getColor() != getColor();
        }

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

