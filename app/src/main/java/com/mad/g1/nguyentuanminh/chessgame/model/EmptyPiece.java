package com.mad.g1.nguyentuanminh.chessgame.model;

import android.util.Pair;

import com.mad.g1.nguyentuanminh.chessgame.ChessBoard;
import com.mad.g1.nguyentuanminh.chessgame.R;

import java.util.List;

public class EmptyPiece extends ChessPiece{
    public EmptyPiece() {
        super(Type.NONE, Color.EMPTY, R.drawable.emptydrw, -1, -1);
    }

    @Override
    public boolean isValidMove(int toRow, int toCol, ChessBoard chessBoard) {
        return false;
    }

    @Override
    public List<Pair<Integer, Integer>> getValidMove(ChessBoard chessBoard) {
        return null;
    }

    @Override
    public void move(int toRow, int toCol, ChessBoard chessBoard) {

    }

}
