package com.mad.g1.nguyentuanminh.chessgame.model;

import android.util.Pair;

import com.mad.g1.nguyentuanminh.chessgame.ChessBoard;

import java.util.ArrayList;
import java.util.List;

public abstract class ChessPiece {


    public boolean isValidMove(int toRow, int toCol, ChessBoard chessBoard) {
        return false;
    }

    public abstract List<Pair<Integer, Integer>> getValidMove(ChessBoard chessBoard);

    public abstract void move(int toRow, int toCol, ChessBoard chessBoard);

    public enum Type {
        PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING,NONE;
    }

    public enum Color {
        BLACK, WHITE,EMPTY;
    }

    private Type type;
    private Color color;
    private int chessIMGid;
    private int row;
    private int col;

    public ChessPiece(Type type, Color color, int chessIMGid, int row, int col) {
        this.type = type;
        this.color = color;
        this.chessIMGid = chessIMGid;
        this.row = row;
        this.col = col;
    }

    public ChessPiece(Type type, Color color, int chessIMGid) {
        this.type = type;
        this.color = color;
        this.chessIMGid = chessIMGid;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getChessIMGid() {
        return chessIMGid;
    }

    public void setChessIMGid(int chessIMGid) {
        this.chessIMGid = chessIMGid;
    }

    public int getStartingRow() {
        // Default implementation, assuming starting row is 0 for both colors
        return 0;
    }

    @Override
    public String toString() {
        return "ChessPiece{" +
                "type=" + type +
                ", color=" + color +
                ", chessIMGid=" + chessIMGid +
                ", row=" + row +
                ", col=" + col +
                '}';
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public ChessPiece() {
    }
}
