package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityMain extends AppCompatActivity {

	ImageView i0, i1, i2, i3, i4, i5, i6, i7, i8;
	boolean gameState = true;
	int playerTurn = 0, moveCount = 0;
	int[] moves = {2, 2, 2, 2, 2, 2, 2, 2, 2};
	int[][] winningPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
	TextView textViewStatus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		i0 = findViewById(R.id.imageView1);
		i1 = findViewById(R.id.imageView2);
		i2 = findViewById(R.id.imageView3);
		i3 = findViewById(R.id.imageView4);
		i4 = findViewById(R.id.imageView5);
		i5 = findViewById(R.id.imageView6);
		i6 = findViewById(R.id.imageView7);
		i7 = findViewById(R.id.imageView8);
		i8 = findViewById(R.id.imageView9);
		textViewStatus = findViewById(R.id.textView2);
	}

	public void playerTap(View view) {
		if (gameState && moveCount <= 9) {
			ImageView imageView = (ImageView) view;
			moveCount++;
			int position = Integer.parseInt(imageView.getTag().toString());
			if (playerTurn == 0 && moves[position] == 2) {
				imageView.setImageResource(R.drawable.o);
				playerTurn = 1;
				moves[position] = 0;
				textViewStatus.setText(R.string.turn_player_x);
				checkIfSomebodyWon(view);
			} else if (playerTurn == 1 && moves[position] == 2) {
				imageView.setImageResource(R.drawable.x);
				playerTurn = 0;
				moves[position] = 1;
				textViewStatus.setText(R.string.turn_player_o);
				checkIfSomebodyWon(view);
			}
		} else {
			reset();
		}
	}

	public void checkIfSomebodyWon(View view) {
		for (int[] ints : winningPosition) {
			if (moves[ints[0]] == 1 && moves[ints[1]] == 1 && moves[ints[2]] == 1) {
				textViewStatus.setText(R.string.winning_text_x);
				gameState = false;
			} else if (moves[ints[0]] == 0 && moves[ints[1]] == 0 && moves[ints[2]] == 0) {
				textViewStatus.setText(R.string.winning_text_o);
				gameState = false;
			}
		}
	}

	public void reset() {
		gameState = true;
		playerTurn = 0;
		moveCount = 0;
		moves = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};
		textViewStatus.setText(R.string.turn_player_o);
		i0.setImageResource(0);
		i1.setImageResource(0);
		i2.setImageResource(0);
		i3.setImageResource(0);
		i4.setImageResource(0);
		i5.setImageResource(0);
		i6.setImageResource(0);
		i7.setImageResource(0);
		i8.setImageResource(0);

	}

	@Override
	protected void onSaveInstanceState(@NonNull Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putCharSequence("game status", textViewStatus.getText());
	}

	@Override
	protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		textViewStatus.setText(savedInstanceState.getCharSequence("game status"));
	}
}