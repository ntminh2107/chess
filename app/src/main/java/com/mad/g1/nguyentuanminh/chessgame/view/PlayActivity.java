package com.mad.g1.nguyentuanminh.chessgame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.widget.GridLayout;
import android.widget.Toast;

import com.mad.g1.nguyentuanminh.chessgame.ChessBoard;
import com.mad.g1.nguyentuanminh.chessgame.ChessGameController;
import com.mad.g1.nguyentuanminh.chessgame.R;
import com.mad.g1.nguyentuanminh.chessgame.model.ChessPiece;
import com.mad.g1.nguyentuanminh.chessgame.model.Pawn;

import java.util.List;

public class PlayActivity extends AppCompatActivity {
    private int BOARD_SIZE = 8;
    private ChessPiece selectedPiece;
    private SquareView squareView;

    private ChessBoard chessBoard;
    private ChessGameController chessGameController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        GridLayout chessboardLayout = findViewById(R.id.chessboardLayout);
        initializeChessboard(chessboardLayout);
        chessGameController = new ChessGameController();
    }

    private void initializeChessboard(GridLayout chessboardLayout) {
        chessBoard = new ChessBoard();
        chessBoard.initializeChessBoard();
        SquareView[][] squareViews = new SquareView[BOARD_SIZE][BOARD_SIZE];

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                SquareView squareView = new SquareView(this);
                squareView.setChessBoard(chessBoard);
                squareView.setRow(row);
                squareView.setCol(col);

                // Customize the appearance of the square as needed
                if ((row + col) % 2 == 0) {
                    squareView.setBackgroundColor(getResources().getColor(R.color.lightSquareColor));
                } else {
                    squareView.setBackgroundColor(getResources().getColor(R.color.darkSquareColor));
                }

                ChessPiece chessPiece = chessBoard.getPiece(row, col);
                if (chessPiece != null) {
                    squareView.setChessPiece(chessPiece);
                    squareView.setSquareTouchListener(new SquareView.SquareTouchListener() {
                        @Override
                        public void onSquareTapped(ChessPiece piece) {
                            handleSquareTap(squareView, piece);
                        }
                    });
                } else {
                    // If there's no chess piece, still set the touch listener to handle empty squares
                    squareView.setSquareTouchListener(new SquareView.SquareTouchListener() {
                        @Override
                        public void onSquareTapped(ChessPiece piece) {
                            handleSquareTap(squareView, null);
                        }
                    });
                }

                // Add the square to the chessboard layout
                GridLayout.Spec rowSpec = GridLayout.spec(row, 1, 1f);
                GridLayout.Spec colSpec = GridLayout.spec(col, 1, 1f);
                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, colSpec);

                // Ensure that each SquareView covers only one cell in the grid
                layoutParams.width = 0;
                layoutParams.height = 0;

                squareView.setLayoutParams(layoutParams);
                chessboardLayout.addView(squareView);
                chessBoard.setSquareView(squareView, row, col);
                squareViews[row][col] = squareView;
            }
        }
        chessBoard.setSquareViews(squareViews);
    }


    public void handleSquareTap(SquareView squareView, ChessPiece piece) {
        if (selectedPiece == null) {
            // No piece selected, highlight valid moves for the tapped piece
            selectedPiece = piece;
            if (selectedPiece != null) {
                List<Pair<Integer, Integer>> validMoves = selectedPiece.getValidMove(chessBoard);
                // Do something with validMoves if needed
            }
        } else {
            // A piece is selected, handle the tap based on the selected piece
            int toRow = squareView.getRow();
            int toCol = squareView.getCol();

            if (selectedPiece.isValidMove(toRow, toCol, chessBoard)) {
                // Valid move, perform the move
                chessGameController.movePiece(selectedPiece.getRow(), selectedPiece.getCol(), toRow, toCol, chessBoard);
                chessBoard.updateUI();
            } else {
                // Invalid move, display a message or handle it as needed
                Toast.makeText(this, "Invalid move", Toast.LENGTH_SHORT).show();
            }

            // Clear the selected piece and move highlights
            selectedPiece = null;
        }
    }

}
