package com.csci4020.simon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity
{

    // MARK: - Properties
    public static boolean soundOn = true;

    // Game Modes
    public static String gameMode = "classic";
    public static final String CLASSIC_GAME = "classic";
    public static final String SURPRISE_GAME = "surprise";
    public static final String REWIND_GAME = "rewind";


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// TODO CREATE ANON INNER CLASS INSTEAD OF USING THIS CURRENT CLASS ONCLICKLISTENER

		findViewById(R.id.button_rules).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToRules();
            }
        });


		findViewById(R.id.button_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToAbout();
            }
        });

        findViewById(R.id.button_classic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gameMode = CLASSIC_GAME;
                startActivity(new Intent(getApplicationContext(), ClassicSimon.class));
            }
        });

        findViewById(R.id.button_surprise).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gameMode = SURPRISE_GAME;
                soundOn = false;
                startActivity(new Intent(getApplicationContext(), SurpriseSimon.class));
            }
        });

        findViewById(R.id.button_rewind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gameMode = REWIND_GAME;
                startActivity(new Intent(getApplicationContext(), RewindSimon.class));
            }
        });
	}

	private void goToRules() {

        startActivity(new Intent(getApplicationContext(), RulesActivity.class));

    }

    private void goToAbout() {

        startActivity(new Intent(getApplicationContext(), AboutActivity.class));
    }
}
