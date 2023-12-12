package com.mad.g1.nguyentuanminh.chessgame;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.mad.g1.nguyentuanminh.chessgame.model.ChessPiece;
import com.mad.g1.nguyentuanminh.chessgame.model.King;
import com.mad.g1.nguyentuanminh.chessgame.view.SquareView;

import java.util.List;

public class ChessGameController {
    private ChessBoard chessBoard;
    private ChessPiece.Color currentPlayerTurn;
    private static final ChessPiece.Color PLAYER_BLACK = ChessPiece.Color.BLACK;
    private static final ChessPiece.Color PLAYER_WHITE = ChessPiece.Color.WHITE;
    private static int BOARD_SIZE = 8;

    public ChessGameController(Context context) {
        chessBoard = new ChessBoard(context);
        chessBoard.initializeChessBoard();
        currentPlayerTurn = PLAYER_WHITE;
    }

    public void movePiece(int fromRow, int fromCol , int toRow, int toCol, ChessBoard chessBoard)
    {
        ChessPiece piece = chessBoard.getPiece(fromRow, fromCol);
            if(piece != null && piece.isValidMove(toRow,toCol,chessBoard)) {
                chessBoard.movePiece(fromRow, fromCol, toRow, toCol);
                switchTurns();
            }
        }


    public void switchTurns() {
        // Switch the turn to the other player
        currentPlayerTurn = (currentPlayerTurn == PLAYER_WHITE) ? PLAYER_BLACK : PLAYER_WHITE;
    }

}
