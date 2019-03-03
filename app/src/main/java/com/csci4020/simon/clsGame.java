package com.csci4020.simon;

import android.app.Activity;
import android.graphics.ColorFilter;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class clsGame extends Activity
{
	/**
	 * GLOBAL Variable to identify the maximum sound effects playable at one time
	 */
	private final int MAX_AUDIO_STREAMS = 4;


	/**
	 * Integer ID of loaded sound effects in a sound pool
	 */
	private static Set<Integer> soundsLoaded;
	public static int SOUND_EFFECT_BLUE;
	public static int SOUND_EFFECT_GREEN;
	public static int SOUND_EFFECT_RED;
	public static int SOUND_EFFECT_YELLOW;


	/**
	 * Sound pool to manage game sound effects
	 */
	private static SoundPool soundPool;


	/**
	 * Game Time. Is the basis for button highlight, pause, and wait for user input.
	 * Time is in milliseconds
	 */
	protected int GAME_INTERVAL_TIME = 300;


	/**
	 * Game speed multiplier. Can be used to slow down or speed up a game
	 */
	protected float GAME_SPEED = 1.0f;


	/**
	 * On Create. Ensure global variables are instantiated
	 *
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// A unique set of integers that correlates to loaded sound ids
		soundsLoaded = new HashSet<Integer>();
	}


	/**
	 * onResume always called so use this to deal with managing preparing/resuming a game
	 */
	@Override
	protected void onResume()
	{
		super.onResume();

		// Simply identify that these sounds are used in a game
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

			this.soundsLoaded.clear();
		}
	}


	/**
	 * Plays the sound identified by the loaded sound ID
	 *
	 * @param soundId int
	 */
	protected void playSound(int soundId)
	{
		if (soundsLoaded.contains(soundId))
		{
			this.soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f);
		}
	}


	/**
	 * Change the game speed multiplier. Must be a float
	 *
	 * @param GAME_SPEED float (e.g. 1.2f)
	 */
	public void setGameSpeed(float GAME_SPEED)
	{
		this.GAME_SPEED = GAME_SPEED;
	}


	public void highlightButton (ImageButton button, int highlightColor)
	{
		ColorFilter currentBackgroundTint;
		currentBackgroundTint = button.getColorFilter();
	}
}
