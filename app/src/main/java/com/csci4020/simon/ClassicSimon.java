package com.csci4020.simon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ClassicSimon extends clsGame
{

    SharedPreferences sharedPreferences;
    private int score;
    private boolean mute = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		findViewById(R.id.btnBlue).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				playSound(SOUND_EFFECT_BLUE);
			}
		});

		findViewById(R.id.btnGreen).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				playSound(SOUND_EFFECT_GREEN);
			}
		});

		findViewById(R.id.btnRed).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				playSound(SOUND_EFFECT_RED);
			}
		});

		findViewById(R.id.btnYellow).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				playSound(SOUND_EFFECT_YELLOW);
			}
		});

		findViewById(R.id.btn_restartgame).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				restartGame();
			}
		});

		findViewById(R.id.btn_mainmenu).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				returnToMainMenu();
			}
		});
	}


    /**
     * Change text when user presses a button and check if a player has win.
     */
	private void setUpSounds() {

	    // If we're playing Surprise, don't need sound.
	    if (mute) {

	        return;
        }

        // TODO: Calling clsGame onResume() to set up the SoundPool
        
    }

    private void startGame() {

	    score = 0;
    }

    private void gameOver() {

        Toast.makeText(this, "Game Over!", Toast.LENGTH_SHORT).show();
    }

	private void restartGame() {

	    score = 0;
	}

	private void returnToMainMenu() {

		startActivity(new Intent(getApplicationContext(), MainActivity.class));
	}
}
