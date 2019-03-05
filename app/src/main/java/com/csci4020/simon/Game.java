package com.csci4020.simon;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public abstract class Game extends Activity
		implements GameSequence, View.OnClickListener
{
	/**
	 * GLOBAL Variable to identify the maximum sound effects playable at one time
	 */
	private final int MAX_AUDIO_STREAMS = 4;


	/**
	 * Use characters to identify the different buttons that exist in a Game
	 */
	public static final char[] buttonChoices = {'r', 'g', 'b', 'y'};


	/**
	 * Integer ID of loaded sound effects in a sound pool
	 */
	private static Set<Integer> soundsLoaded;
	public static int SOUND_EFFECT_BLUE;
	public static int SOUND_EFFECT_GREEN;
	public static int SOUND_EFFECT_RED;
	public static int SOUND_EFFECT_YELLOW;


	/**
	 * Sound pool to manage Game sound effects
	 */
	private static SoundPool soundPool;


	/**
	 * Random object to obtain random numbers
	 */
	private Random rand = new Random();


	/**
	 * Current Score
	 */
	private int score = 0;

	/**
	 * Game Time. Is the basis for button highlight, pause, and wait for user input.
	 * Time is in milliseconds
	 */
	protected int GAME_INTERVAL_TIME = 300;


	/**
	 * Game speed multiplier. Can be used to slow down or speed up a Game
	 */
	protected float GAME_SPEED = 1.0f;


	/**
	 * Constant variables to easily identify words used in savedstate
	 */
	final String KEY_GAME_SEQUENCE = "KEY_GAME_SEQUENCE";
	final String KEY_USER_SEQUENCE = "KEY_USER_SEQUENCE";


	/**
	 * Stores the sequence of buttons in a Game
	 */
	private LinkedList<Character> userSequence = new LinkedList<>();
	private LinkedList<Character> gameSequence = new LinkedList<>();

	/**
	 * Game Button class to identify the buttons that are playable
	 */
	protected List<GameButton<ImageButton, Integer>> gameButtons;

	/**
	 * On Create. Ensure global variables are instantiated
	 *
	 * @param savedInstanceState default
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// A unique set of integers that correlates to loaded sound ids
		soundsLoaded = new HashSet<>();
	}


	/**
	 * onResume always called so use this to deal with managing preparing/resuming a Game
	 */
	@Override
	protected void onResume()
	{
		super.onResume();

		// Simply identify that these sounds are used in a Game
		AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
		attrBuilder.setUsage(AudioAttributes.USAGE_GAME);

		SoundPool.Builder spBuilder = new SoundPool.Builder();
		spBuilder.setAudioAttributes(attrBuilder.build());
		spBuilder.setMaxStreams(MAX_AUDIO_STREAMS);
		soundPool = spBuilder.build();

		soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener()
		{
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status)
			{
				if (status == 0)
				{
					// success. Sound was loaded into memory. Store the loaded sound id
					soundsLoaded.add(sampleId);
				}
				else
				{
					Log.i("4020debug", "Error cannot load sound status = " + status);
				}
			}
		});

		// Obtain the ID's of the raw audio files for easy access
		SOUND_EFFECT_BLUE = soundPool.load(this, R.raw.blue, 1);
		SOUND_EFFECT_GREEN = soundPool.load(this, R.raw.green, 1);
		SOUND_EFFECT_RED = soundPool.load(this, R.raw.red, 1);
		SOUND_EFFECT_YELLOW = soundPool.load(this, R.raw.yellow, 1);

		// Set the game buttons
		gameButtons = new ArrayList<>();
		gameButtons.add(new GameButton<>(((ImageButton)findViewById(R.id.btnBlue)),SOUND_EFFECT_BLUE));
		gameButtons.add(new GameButton<>(((ImageButton)findViewById(R.id.btnRed)),SOUND_EFFECT_RED));
		gameButtons.add(new GameButton<>(((ImageButton)findViewById(R.id.btnGreen)),SOUND_EFFECT_GREEN));
		gameButtons.add(new GameButton<>(((ImageButton)findViewById(R.id.btnYellow)),SOUND_EFFECT_YELLOW));
	}


	/**
	 * Need to stop any sounds effects if the user leaves the app for any reason
	 */
	@Override
	protected void onPause()
	{
		super.onPause();
		if (soundPool != null)
		{
			soundPool.release();
			soundPool = null;

			soundsLoaded.clear();
		}
	}


	/**
	 * Plays the sound identified by the loaded sound ID
	 *
	 * @param soundId int
	 */
	public final void playSound(int soundId)
	{
		if (soundsLoaded.contains(soundId))
		{
			soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f);
		}
	}


	/**
	 * Change the Game speed multiplier. Must be a float
	 *
	 * @param GAME_SPEED float (e.g. 1.2f)
	 */
	public final void setGameSpeed(float GAME_SPEED)
	{
		this.GAME_SPEED = GAME_SPEED;
	}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	/**
	 * Retrieve the current Game sequence
	 *
	 * @return List<Character>
	 */
	public LinkedList<Character> getUserSequence()
	{
		return userSequence;
	}


	/**
	 * Retrieve the current Game sequence
	 *
	 * @return List<Character>
	 */
	public LinkedList<Character> getGameSequence()
	{
		return gameSequence;
	}

	/**
	 * Add a user guess and check if it matches the corresponding Game sequence value
	 *
	 * @param x char User Guess
	 * @return boolean User guess matches Game sequence
	 */
	public boolean addToUserSequence(char x)
	{
		userSequence.add(x);
		return (userSequence.get(userSequence.size() - 1) == userSequence.get(userSequence.size() - 1));
	}


	/**
	 * Add one more button to the Game sequence
	 *
	 * @return boolean Indicating success on adding one character to Game sequence
	 */
	public boolean addToGameSequence()
	{
		try
		{
			gameSequence.add(buttonChoices[rand.nextInt(buttonChoices.length)]);
			return true;
		} catch (RuntimeException e)
		{
			return false;
		}
	}


	/**
	 * Get character at position n from Game sequence
	 *
	 * @param n int
	 * @return char
	 */
	public char getGameSequenceChar(int n)
	{
		return gameSequence.get(n);
	}

	/**
	 * Get character at position n from User sequence
	 *
	 * @param n int
	 * @return char
	 */
	public char getUserSequenceChar(int n)
	{
		return gameSequence.get(n);
	}


	/**
	 * Onclick to handle the pressing of a game button
	 * @param v View object that was clicked
	 */
	@Override
	public void onClick(View v) {
		switch (v.getTag().toString())
		{
			case "r":

		}
	}
}
