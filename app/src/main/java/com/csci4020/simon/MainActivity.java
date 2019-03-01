package com.csci4020.simon;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testlayout);

        final clsGameSoundEffects gameSoundEffects = new clsGameSoundEffects();

        findViewById(R.id.btnBlue).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                gameSoundEffects.playSound(gameSoundEffects.SOUND_EFFECT_BLUE);
            }
        });
        findViewById(R.id.btnGreen).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                gameSoundEffects.playSound(gameSoundEffects.SOUND_EFFECT_GREEN);
            }
        });
        findViewById(R.id.btnRed).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                gameSoundEffects.playSound(gameSoundEffects.SOUND_EFFECT_RED);
            }
        });
        findViewById(R.id.btnYellow).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                gameSoundEffects.playSound(gameSoundEffects.SOUND_EFFECT_YELLOW);
            }
        });
    }
}
