package com.csci4020.simon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ClassicSimon extends clsGame
{

    private int score;

    private final int BLUE_SOUND = 0;
    private final int GREEN_SOUND = 1;
    private final int RED_SOUND = 2;
    private final int YELLOW_SOUND = 3;



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

	private void setUpSounds() {

        int sounds[] = {
                clsGame.SOUND_EFFECT_BLUE, clsGame.SOUND_EFFECT_GREEN, clsGame.SOUND_EFFECT_RED, clsGame.SOUND_EFFECT_YELLOW
        };
    }

    private void startGame() {

	    score = 0;
    }

    private void gameOver() {


    }

	private void restartGame() {

	    score = 0;
	}

	private void returnToMainMenu() {

		startActivity(new Intent(getApplicationContext(), MainActivity.class));
	}



}
