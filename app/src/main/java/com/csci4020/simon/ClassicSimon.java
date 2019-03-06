package com.csci4020.simon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ClassicSimon extends Game
{

	SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		// Assign listeners to menu buttons
		findViewById(R.id.btn_restartgame).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				restartGame();
			}
		});
		findViewById(R.id.btn_mainmenu).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				returnToMainMenu();
			}
		});
		findViewById(R.id.btnStartGame).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				startGame();
			}
		});
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		// Create the listeners for Game buttons. Assign sound effect appropriately
		// Need to put these in onResume since the soundpool wouldn't have been created until now
		GAME_BUTTON_BLUE.getImageButton().setOnClickListener(new GameButtonListener(SOUND_EFFECT_BLUE));
		GAME_BUTTON_GREEN.getImageButton().setOnClickListener(new GameButtonListener(SOUND_EFFECT_GREEN));
		GAME_BUTTON_RED.getImageButton().setOnClickListener(new GameButtonListener(SOUND_EFFECT_RED));
		GAME_BUTTON_YELLOW.getImageButton().setOnClickListener(new GameButtonListener(SOUND_EFFECT_YELLOW));
	}

	/**
	 * Change text when user presses a button and check if a player has win.
	 */

	private void startGame()
	{
		findViewById(R.id.btnStartGame).setVisibility(View.INVISIBLE);
		// Reset items
		setScore(0);
		super.getGameSequence().clear();
		super.getUserSequence().clear();
		// Initiate the game sequence
		super.addToGameSequence(4); //TODO THIS IS ONLY A DEMO SIZE
		// Play the sequence for the user
		PlaySequence playSequence = new PlaySequence(super.getGameSequence());
		playSequence.execute();
	}

	private void gameOver()
	{

		Toast.makeText(this, "Game Over!", Toast.LENGTH_SHORT).show();
	}

	private void restartGame()
	{
		startGame();
	}

	private void returnToMainMenu()
	{
		startActivity(new Intent(getApplicationContext(), MainActivity.class));
	}


	/**
	 * Used to handle app cycles during rotations
	 * Store what needs to be retrieved when they get back
	 *
	 * @param outState
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
	}


	@Override
	public void onClick(View v)
	{
		onClickActions(GAME_BUTTON_BLUE, buttonChoices[0]);
	}

	void onClickActions(GameButton gameButton, char buttonChar)
	{

	}

	/**
	 * Class to minimize code when assigning onclicklisteners to Game buttons
	 * Inner class so it can utilize the methods from superclass
	 */
	class GameButtonListener implements View.OnClickListener
	{
		private int assignedSoundEffect;

		GameButtonListener(int assignedSoundEffect)
		{
			this.assignedSoundEffect = assignedSoundEffect;
		}

		@Override
		public void onClick(View view)
		{
			playSound(assignedSoundEffect);
		}
	}
}
