package com.csci4020.simon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity
		implements View.OnClickListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// TODO CREATE ANON INNER CLASS INSTEAD OF USING THIS CURRENT CLASS ONCLICKLISTENER
		findViewById(R.id.button_play).setOnClickListener(this);
		findViewById(R.id.button_rules).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gotoRules();
            }
        });
	}

	@Override
	public void onClick(View view)
	{
		Intent intent = new Intent(getApplicationContext(),ClassicSimon.class);
		startActivity(intent);
	}

	private void gotoRules() {

	    Intent intent = new Intent(getApplicationContext(), RulesActivity.class);
	    startActivity(intent);
    }
}
