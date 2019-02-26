package com.csci4020.simon;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

public class clsSoundEffect extends Activity
{
	private final int MAX_AUDIO_STREAMS = 4;
	private SoundPool soundPool;
	private Set<Integer> soundsLoaded;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		soundsLoaded = new HashSet<Integer>();
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

		final int redId = soundPool.load(this, R.raw.red, 1);
		final int greenId = soundPool.load(this, R.raw.green, 1);
		final int blueId = soundPool.load(this, R.raw.blue, 1);
		final int yellowId = soundPool.load(this, R.raw.yellow, 1);

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
