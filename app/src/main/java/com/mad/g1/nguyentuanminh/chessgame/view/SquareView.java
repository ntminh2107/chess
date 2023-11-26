package com.mad.g1.nguyentuanminh.chessgame.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mad.g1.nguyentuanminh.chessgame.ChessBoard;
import com.mad.g1.nguyentuanminh.chessgame.ChessGameController;
import com.mad.g1.nguyentuanminh.chessgame.R;
import com.mad.g1.nguyentuanminh.chessgame.model.ChessPiece;

import java.util.List;

public class SquareView extends LinearLayout {

    private ImageView pieceImageView;
    private ChessPiece piece;
    private int row;
    private int col;
    private static final int HIGHLIGHT_COLOR = R.color.yellow;
    private SquareTouchListener squareTouchListener;
    private ChessGameController chessGameController;
    private ChessBoard chessBoard;


    public SquareView(Context context) {
        super(context);
        initialize();
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
    public void setSquareTouchListener(SquareTouchListener listener) {
        this.squareTouchListener = listener;
    }
    public SquareView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    private void initialize() {
        pieceImageView = new ImageView(getContext());
        pieceImageView.setLayoutParams(new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
        ));
        pieceImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        // Add the ImageView to the SquareView
        addView(pieceImageView);

        // Set up touch listener
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // The square was tapped
                    handleSquareTap();
                }
                return true;
            }
        });
    }

    public void setChessPiece(ChessPiece chessPiece) {
        if (chessPiece != null) {
            // Set the chess piece image
            int imageResourceId = chessPiece.getChessIMGid();
            if (imageResourceId != 0) {
                pieceImageView.setImageResource(imageResourceId);
            }
        } else {
            // No chess piece, clear the image
            pieceImageView.setImageDrawable(null);
        }
        this.piece = chessPiece;
    }


    public void highlight() {
        // Add logic to highlight the SquareView when it is selected or for valid moves
        // For example, you can change the background color or add a border to visually indicate the selection.
        setBackgroundColor(getResources().getColor(HIGHLIGHT_COLOR));
    }

    public void clearHighlight() {
        // Add logic to clear the highlight from the SquareView
        // For example, revert the background color or remove the border.
        int defaultColor = (row + col) % 2 == 0 ?
                getResources().getColor(R.color.lightSquareColor) :
                getResources().getColor(R.color.darkSquareColor);
        setBackgroundColor(defaultColor);
    }



    // ... (existing code)

    public void handleSquareTap() {
        if (piece != null && squareTouchListener != null) {
            // Log the coordinates of the tapped square
            Log.d("ChessGame", "Tapped on square: " + row + ", " + col);

            // Notify the listener that the square was tapped
            squareTouchListener.onSquareTapped(piece);

            // Check if the square is highlighted for a valid move
            if (chessGameController != null && piece != null) {
                // Perform the move immediately when the highlighted square is tapped
                chessGameController.movePiece(piece.getRow(), piece.getCol(), row, col, chessBoard);
//                clearHighlightForMove();
            } else {
                // Check if the piece has valid moves and highlight them
                List<Pair<Integer, Integer>> validMoves = piece.getValidMove(chessBoard);
                if (!validMoves.isEmpty()) {
//                    highlightValidMoves();
                }
            }
        }
    }



    public void highlightValidMoves(List<Pair<Integer, Integer>> validMoves) {
        Log.d("Debug", "Valid Moves: " + validMoves);
        // Clear previous highlights
        clearAllMoveHighlights();

        for (Pair<Integer, Integer> move : validMoves) {
            int validMoveRow = move.first;
            int validMoveCol = move.second;

            // Get the SquareView at the valid move position and highlight it
            SquareView validMoveSquare = chessBoard.getSquareView(validMoveRow, validMoveCol);
            if (validMoveSquare != null) {
                validMoveSquare.highlightForMove();
                Log.d("Debug", "Highlighting Valid Moves");
            }
        }
    }


    private void clearAllMoveHighlights() {
        // Iterate through all squares and clear move highlights
        for (int row = 0; row < chessBoard.getRows(); row++) {
            for (int col = 0; col < chessBoard.getCols(); col++) {
                SquareView squareView = chessBoard.getSquareView(row, col);
                if (squareView != null) {
                    squareView.clearHighlightForMove();
                }
            }
        }
    }

    public void highlightForMove() {
        // Add logic to highlight the SquareView for a valid move
        setBackgroundResource(R.drawable.bluedot);

    }

    public void clearHighlightForMove() {
        // Add logic to clear the highlight for a valid move
        int defaultColor = (row + col) % 2 == 0 ?
                getResources().getColor(R.color.lightSquareColor) :
                getResources().getColor(R.color.darkSquareColor);
        setBackgroundColor(defaultColor);

    }


    public interface SquareTouchListener {
        void onSquareTapped(ChessPiece piece);
    }

    public ChessPiece getPiece()
    {
        return piece;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void updateChessboardUI() {
        // Clear all move highlights before updating the UI
        clearAllMoveHighlights();

        for (int row = 0; row < chessBoard.getRows(); row++) {
            for (int col = 0; col < chessBoard.getCols(); col++) {
                SquareView squareView = chessBoard.getSquareView(row, col);
                ChessPiece chessPiece = chessBoard.getPiece(row, col);

                squareView.setChessPiece(chessPiece);

                // Check if the piece has valid moves and highlight them
                if (chessPiece != null && chessPiece == piece) {
                    List<Pair<Integer, Integer>> validMoves = chessPiece.getValidMove(chessBoard);
                    highlightValidMoves(validMoves);
                }
            }
        }
    }
    public void updateSquareView(ChessBoard chessBoard) {
        ChessPiece newPiece = chessBoard.getPiece(row, col);
        setChessPiece(newPiece);

        // Clear all move highlights before updating the UI
        clearAllMoveHighlights();

        // Check if the piece has valid moves and highlight them
        if (newPiece != null && newPiece == piece) {
            List<Pair<Integer, Integer>> validMoves = newPiece.getValidMove(chessBoard);
            highlightValidMoves(validMoves);
        }
    }


}
