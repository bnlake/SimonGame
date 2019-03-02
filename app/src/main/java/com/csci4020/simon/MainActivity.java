package com.csci4020.simon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends clsGame
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testlayout);

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
		setContentView(R.layout.activity_main);

		findViewById(R.id.button_play).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{

				startActivity(new Intent(getApplicationContext(), GameActivity.class));
			}

		});
	}
}
