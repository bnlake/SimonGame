package com.csci4020.simon;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class RulesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rules);

//        final View view = findViewById(R.id.view);

        findViewById(R.id.view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    v.setBackgroundColor(Color.RED);
//                v.setBackgroundColor(Color.RED);
            }
        });

    }
}
