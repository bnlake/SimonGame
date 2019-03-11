package com.csci4020.simon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class ClassicSimon extends Game
{
	// TODO NEED TO FIND A WAY TO STORE NECESSARY INFO FOR APP ROTATIONS

    // The saved high score in SharedPreferences.
    private int savedHighScore;

    // SharedPreferences key and preference name
    private String highScoreKey = "HIGHSCORE";
    private String highScorePref = "SHAREDPREFERENCE";

    private TextView highScoreTextView;

	/**
	 * Game Buttons
	 */
	public GameButton GAME_BUTTON_RED;
	public GameButton GAME_BUTTON_BLUE;
	public GameButton GAME_BUTTON_GREEN;
	public GameButton GAME_BUTTON_YELLOW;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		// TODO NEED TO LOOK FOR BOOLEAN FROM INTENT TO EITHER MUTE THE GAME OR NOT
        // TODO: - Could possibly use this to separate the different methods and replace needing a boolean for muting the game or not
        if (MainActivity.gameMode.equals(MainActivity.CLASSIC_GAME)) {

        } else if (MainActivity.gameMode.equals(MainActivity.REWIND_GAME)) {

        } else if (MainActivity.gameMode.equals(MainActivity.SURPRISE_GAME)) {

        }

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
				startClassicGame();
			}
		});

		// Assign listener to high score text view
        highScoreTextView = findViewById(R.id.highScoreTextView);

        loadHighScore();
        updateHighScoreTextView();
    }

	@Override
	protected void onResume()
	{
		super.onResume();

        // Surprise Mode will have the same button color, highlighting, and sound for each button
		if (MainActivity.gameMode == MainActivity.SURPRISE_GAME) {
            GAME_BUTTON_BLUE = new GameButton(((ImageButton) findViewById(R.id.btnBlue)), SOUND_EFFECT_BLUE, R.color.btnBlue, R.color.btnBlueHighlight);
            GAME_BUTTON_RED = new GameButton(((ImageButton) findViewById(R.id.btnRed)), SOUND_EFFECT_BLUE, R.color.btnBlue, R.color.btnBlueHighlight);
            GAME_BUTTON_GREEN = new GameButton(((ImageButton) findViewById(R.id.btnGreen)), SOUND_EFFECT_BLUE, R.color.btnBlue, R.color.btnBlueHighlight);
            GAME_BUTTON_YELLOW = new GameButton(((ImageButton) findViewById(R.id.btnYellow)), SOUND_EFFECT_BLUE, R.color.btnBlue, R.color.btnBlueHighlight);
        } else {
		    // Setup for button color, highlighting, and sound
            GAME_BUTTON_BLUE = new GameButton(((ImageButton) findViewById(R.id.btnBlue)), SOUND_EFFECT_BLUE, R.color.btnBlue, R.color.btnBlueHighlight);
            GAME_BUTTON_RED = new GameButton(((ImageButton) findViewById(R.id.btnRed)), SOUND_EFFECT_RED, R.color.btnRed, R.color.btnRedHighlight);
            GAME_BUTTON_GREEN = new GameButton(((ImageButton) findViewById(R.id.btnGreen)), SOUND_EFFECT_GREEN, R.color.btnGreen, R.color.btnGreenHighlight);
            GAME_BUTTON_YELLOW = new GameButton(((ImageButton) findViewById(R.id.btnYellow)), SOUND_EFFECT_YELLOW, R.color.btnYellow, R.color.btnYellowHighlight);
        }

            // Create the listeners for Game buttons. Assign sound effect appropriately
            // Need to put these in onResume since the soundpool wouldn't have been created until now
            GAME_BUTTON_BLUE.getImageButton().setOnClickListener(new GameButtonListener(GAME_BUTTON_BLUE));
            GAME_BUTTON_GREEN.getImageButton().setOnClickListener(new GameButtonListener(GAME_BUTTON_GREEN));
            GAME_BUTTON_RED.getImageButton().setOnClickListener(new GameButtonListener(GAME_BUTTON_RED));
            GAME_BUTTON_YELLOW.getImageButton().setOnClickListener(new GameButtonListener(GAME_BUTTON_YELLOW));
	}

	/**
	 * Begin logic when a user starts a classic simon game
	 */
	protected void startClassicGame()
	{
		super.startGame();
		// Initiate the game sequence
		super.addToGameSequence(1);
		// Play the sequence for the user
		PlaySequence playSequence = new PlaySequence(getGameSequence());
		playSequence.execute();
	}

	/**
	 * Begin logic when a game is finished.
     * Displays messages and saves the high score
	 */
	private void gameOver()
	{
		Log.i("4020debug", "User scored: " + getScore());
		Toast.makeText(this, "Game Over!", Toast.LENGTH_SHORT).show();

		// If current score is greater than the set high score, update the new high score
        if (getScore() > savedHighScore) {
            saveHighScore();
            loadHighScore();
            updateHighScoreTextView();
        }


		super.endGame();
	}

	/**
	 * Method for when a player manually requests a restart
	 */
	private void restartGame()
	{
		super.endGame();
	}

	/**
	 * Send user back to the main menu
	 */
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


	}


	/**
	 * Class to minimize code when assigning onclicklisteners to Game buttons
	 * Inner class so it can utilize the methods from superclass
	 */
	class GameButtonListener implements View.OnClickListener
	{
		private GameButton gameButton;

		/**
		 * Constructor to recieve that GameButton class
		 * Needed to reference all of the methods of the class
		 *
		 * @param gameButton button context
		 */
		public GameButtonListener(GameButton gameButton)
		{
			this.gameButton = gameButton;
		}

		/**
		 * Manage the listener for a game button
		 * Should have all the steps that are performed when a game button
		 * is pressed
		 *
		 * @param view
		 */
		@Override
		public void onClick(View view)
		{
			if (isGameStarted())
			{
				// Run thread for button sound and highlight
				GameButtonPress gameButtonPress = new GameButtonPress();
				gameButtonPress.execute();

				// Retrieve button "tag"
				// Add to user sequence
				addToUserSequence(gameButton.getButtonChar());

				// If user sequence size is < game sequence size
				if (getUserSequence().size() < getGameSequence().size())
				{

					// if position n matches in both sequences
					if (getUserSequenceChar(getUserSequence().size() - 1) == getGameSequenceChar(getUserSequence().size() - 1))
					{

						// do nothing
					}
					else
					{

						// else user missed n in sequence
						// initiate game over
						gameOver();
					}
				}
				else
				{
					// Increment the users score
					setScore(getScore() + 1);

					// Add to game sequence
					addToGameSequence(1);

					// Clear user sequence
					getUserSequence().clear();

					// Pause for a moment to make user aware of next round
					PauseGame pauseGame = new PauseGame();
					pauseGame.execute();

					// Play game sequence
					PlaySequence playSequence = new PlaySequence(getGameSequence());
					playSequence.execute();
				}
			}
		}

		/**
		 * GameButtonPress class. Using it this way allows us to utilize AsyncTask
		 * but still reach all of the methods of the Game class
		 */
		class GameButtonPress extends AsyncTask<Void, Void, Void>
		{

			@Override
			protected Void doInBackground(Void... voids)
			{
				try
				{
					// Store current game speed
					float x = GAME_SPEED;
					// Speed up the game
					setGameSpeed(4.0f);

					runOnUiThread(new Runnable()
					{
						@Override
						public void run()
						{
							gameButton.toggleHighlight(true);
						}
					});

					playSound(gameButton.getSoundEffectId());

					Thread.sleep(((Float) (((GAME_INTERVAL_TIME / 3)) / GAME_SPEED)).longValue());
					// Return the color of the button
					runOnUiThread(new Runnable()
					{
						@Override
						public void run()
						{
							gameButton.toggleHighlight(false);
						}
					});
					// Pause for a moment without highlight
					Thread.sleep(((Float) ((2 * (GAME_INTERVAL_TIME / 3)) / GAME_SPEED)).longValue());
					// Restore the game speed
					setGameSpeed(x);

				} catch (InterruptedException e)
				{
					Log.i("4020debug", "Thread was interrupted");
				}
				return null;
			}
		}
	}


	/**
	 * ASYNC class to play a specified sequence
	 * Equates to highlighting, sounds
	 */
	public class PlaySequence extends AsyncTask<Void, Void, Void>
	{
		LinkedList<Character> sequence;

		PlaySequence(LinkedList<Character> sequence)
		{
			this.sequence = sequence;
		}

		@Override
		protected Void doInBackground(Void... voids)
		{
			try
			{
				// Loop through each character in the sequence
				// Need to highlight the button for the current character
				for (int i = 0; i < sequence.size(); i++)
				{
					final GameButton gameButton;
					// Get values depending on button to highlight
					switch (sequence.get(i))
					{
						case 'r':
							gameButton = GAME_BUTTON_RED;
							break;
						case 'g':
							gameButton = GAME_BUTTON_GREEN;
							break;
						case 'b':
							gameButton = GAME_BUTTON_BLUE;
							break;
						case 'y':
							gameButton = GAME_BUTTON_YELLOW;
							break;
						default:
							// Will never reach a default state
							gameButton = null;
					}

					try
					{
						// Highlight the button
						runOnUiThread(new Runnable()
						{
							@Override
							public void run()
							{
								gameButton.toggleHighlight(true);
							}
						});
						playSound(gameButton.getSoundEffectId());
						// Pause on highlight
						Thread.sleep(((Float) (((GAME_INTERVAL_TIME / 3)) / GAME_SPEED)).longValue());
						// Return the color of the button
						runOnUiThread(new Runnable()
						{
							@Override
							public void run()
							{
								gameButton.toggleHighlight(false);
							}
						});
						// Pause for a moment without highlight
						Thread.sleep(((Float) ((2 * (GAME_INTERVAL_TIME / 3)) / GAME_SPEED)).longValue());
					} catch (InterruptedException e)
					{
						Log.w("4020debug", "Exception while trying to play the sequence");
					}
				}
			} catch (Exception e)
			{
				Log.w("4020debug", "Exception thrown during sequence playing");
			}

			return null;
		}
	}

    /**
     * Using SharedPreferences it saves the high score
     */
	private void saveHighScore() {

	    SharedPreferences sharedPreferences = getSharedPreferences(highScorePref, MODE_PRIVATE);
	    SharedPreferences.Editor editor = sharedPreferences.edit();

	    editor.putInt(highScoreKey, getScore());
	    editor.apply();
    }

    /**
     * Loads the highest score that has been set
     */
    private void loadHighScore() {

        SharedPreferences sharedPreferences = getSharedPreferences(highScorePref, MODE_PRIVATE);
        savedHighScore = sharedPreferences.getInt(highScoreKey, 0);

        // when we want to reset high score Sharedpreferences to test project in simulator
//        sharedPreferences.edit().clear().commit();
    }


    /**
     * Updates the high score TextView to the latest high score
     */
    private void updateHighScoreTextView() {

	    highScoreTextView.setText("High Score: " + savedHighScore);
    }
}