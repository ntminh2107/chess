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
    private Context context;

    public ChessGameController() {
        chessBoard = new ChessBoard();
        chessBoard.initializeChessBoard();
        currentPlayerTurn = PLAYER_WHITE;
    }

    public void movePiece(int fromRow, int fromCol , int toRow, int toCol, ChessBoard chessBoard)
    {
        ChessPiece piece = chessBoard.getPiece(fromRow, fromCol);
            if(piece != null && piece.isValidMove(toRow,toCol,chessBoard)) {
                chessBoard.movePiece(piece.getRow(), piece.getCol(), toRow, toCol);
                switchTurns();
            }
        }


    public void updateChessboardUI() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                SquareView squareView = chessBoard.getSquareView(row, col);
                Log.d("debug","row" + row +" " +"col" + col);
                ChessPiece chessPiece = chessBoard.getPiece(row, col);

                if (chessPiece != null) {
                    // Update the SquareView with the new ChessPiece
                    squareView.setChessPiece(chessPiece);
                } else {
                    // Clear the SquareView if there is no ChessPiece at this position
                    squareView.setChessPiece(null);
                }

                // Clear any highlights from the SquareView
            }
        }
    }

    public void switchTurns() {
        // Switch the turn to the other player
        currentPlayerTurn = (currentPlayerTurn == PLAYER_WHITE) ? PLAYER_BLACK : PLAYER_WHITE;
    }

}
