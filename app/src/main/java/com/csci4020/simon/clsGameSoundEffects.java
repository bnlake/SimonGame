package com.csci4020.simon;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class clsGameSoundEffects extends Activity
{
	private final int MAX_AUDIO_STREAMS = 4;
	public int SOUND_EFFECT_BLUE;
	public int SOUND_EFFECT_GREEN;
	public int SOUND_EFFECT_RED;
	public int SOUND_EFFECT_YELLOW;
	private SoundPool soundPool;
	private Set<Integer> soundsLoaded;
	private List<Integer> rawSoundEffects;

	/**
	 * Class Constructor. No overloads to ensure this always runs
	 */
	public clsGameSoundEffects()
	{
		soundsLoaded = new HashSet<Integer>();
		// Obtain the ID's of the raw audio files
		SOUND_EFFECT_BLUE = R.raw.blue;
		SOUND_EFFECT_GREEN = R.raw.green;
		SOUND_EFFECT_RED = R.raw.red;
		SOUND_EFFECT_YELLOW = R.raw.yellow;
		// Add the ids to a list to easily identify a sound file
		rawSoundEffects.add(SOUND_EFFECT_BLUE);
		rawSoundEffects.add(SOUND_EFFECT_GREEN);
		rawSoundEffects.add(SOUND_EFFECT_RED);
		rawSoundEffects.add(SOUND_EFFECT_YELLOW);
	}

	@Override
	protected void onResume()
	{
		super.onResume();

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
					// success
					soundsLoaded.add(sampleId);
				}
				else
				{
					Log.i("4020debug", "Error cannot load sound status = " + status);
				}
			}
		});

		// TODO SET ONCLICKLISTENERS TO SOMETHING AND PLAY SOUND
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
	 * Plays the sound identified by the sound ID
	 *
	 * @param soundId int
	 */
	protected void playSound(int soundId)
	{
		if (soundsLoaded.contains(soundId))
		{
			soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f);
		}
	}
}
