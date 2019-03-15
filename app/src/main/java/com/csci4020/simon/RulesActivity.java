package com.csci4020.simon;
/**
 * CSCI 4020
 * Assignment 2 - Simon
 * Hannie Kim
 * Brian Lake
 */
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class RulesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rules);

        findViewById(R.id.view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    v.setBackgroundColor(Color.RED);
            }
        });

    }
}
