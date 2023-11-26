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

    public void handleSquareTap() {
        if (piece != null && squareTouchListener != null) {
            // Log the coordinates of the tapped square
            Log.d("ChessGame", "Tapped on square: " + row + ", " + col+ " " + piece.getType().toString());
            // Notify the listener that the square was tapped
            squareTouchListener.onSquareTapped(piece);
            }
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

        for (int row = 0; row < chessBoard.getRows(); row++) {
            for (int col = 0; col < chessBoard.getCols(); col++) {
                SquareView squareView = chessBoard.getSquareView(row, col);
                ChessPiece chessPiece = chessBoard.getPiece(row, col);

                squareView.setChessPiece(chessPiece);

                // Check if the piece has valid moves and highlight them
                if (chessPiece != null && chessPiece == piece) {
                    List<Pair<Integer, Integer>> validMoves = chessPiece.getValidMove(chessBoard);
                }
            }
        }
    }



}
