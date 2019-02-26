package com.csci4020.simon;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import java.util.HashSet;
import java.util.Set;

public class clsSoundEffect extends Activity
{
	private SoundPool soundPool;
	private Set<Integer> soundsLoaded;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		soundsLoaded = new HashSet<Integer>();
	}
	@Override
	protected void onResume() {
		super.onResume();

		AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
		attrBuilder.setUsage(AudioAttributes.USAGE_GAME);

		SoundPool.Builder spBuilder = new SoundPool.Builder();
		spBuilder.setAudioAttributes(attrBuilder.build());
		spBuilder.setMaxStreams(4);
		soundPool = spBuilder.build();

		soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
				if (status == 0) { // success
					soundsLoaded.add(sampleId);
					Log.i("SOUND", "Sound loaded " + sampleId);
				} else {
					Log.i("SOUND", "Error cannot load sound status = " + status);
				}
			}
		});

		final int laughId = soundPool.load(this, R.raw.laugh, 1);
		findViewById(R.id.laugh_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				playSound(laughId);
			}
		});

		final int cowId = soundPool.load(this, R.raw.cow, 1);
		findViewById(R.id.cow_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				playSound(cowId);
			}
		});

		final int clickId = soundPool.load(this, R.raw.click, 1);
		findViewById(R.id.editText).setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				Log.i("EVENT", event.getAction() + "");
				if (event.getAction() == KeyEvent.ACTION_UP) {
					playSound(clickId);
				}
				return false;
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (soundPool != null) {
			soundPool.release();
			soundPool = null;

			soundsLoaded.clear();
		}
	}

	private void playSound(int soundId) {
		if (soundsLoaded.contains(soundId)) {
			soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f);
		}
	}
}
