package com.csci4020.simon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ClassicSimon extends clsGame
{

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

	private void restartGame() {

	}

	private void returnToMainMenu() {

		startActivity(new Intent(getApplicationContext(), MainActivity.class));
	}


}
