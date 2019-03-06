package com.csci4020.simon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.LinkedList;

public class ClassicSimon extends Game {

    SharedPreferences sharedPreferences;
    /**
     * Game Buttons
     */
    public GameButton GAME_BUTTON_RED;
    public GameButton GAME_BUTTON_BLUE;
    public GameButton GAME_BUTTON_GREEN;
    public GameButton GAME_BUTTON_YELLOW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Assign listeners to menu buttons
        findViewById(R.id.btn_restartgame).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });
        findViewById(R.id.btn_mainmenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMainMenu();
            }
        });
        findViewById(R.id.btnStartGame).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        GAME_BUTTON_BLUE = new GameButton(((ImageButton) findViewById(R.id.btnBlue)), SOUND_EFFECT_BLUE, R.color.btnBlue, R.color.btnBlueHighlight);
        GAME_BUTTON_RED = new GameButton(((ImageButton) findViewById(R.id.btnRed)), SOUND_EFFECT_RED, R.color.btnRed, R.color.btnRedHighlight);
        GAME_BUTTON_GREEN = new GameButton(((ImageButton) findViewById(R.id.btnGreen)), SOUND_EFFECT_GREEN, R.color.btnGreen, R.color.btnGreenHighlight);
        GAME_BUTTON_YELLOW = new GameButton(((ImageButton) findViewById(R.id.btnYellow)), SOUND_EFFECT_YELLOW, R.color.btnYellow, R.color.btnYellowHighlight);

        // Create the listeners for Game buttons. Assign sound effect appropriately
        // Need to put these in onResume since the soundpool wouldn't have been created until now
        GAME_BUTTON_BLUE.getImageButton().setOnClickListener(new GameButtonListener(SOUND_EFFECT_BLUE));
        GAME_BUTTON_GREEN.getImageButton().setOnClickListener(new GameButtonListener(SOUND_EFFECT_GREEN));
        GAME_BUTTON_RED.getImageButton().setOnClickListener(new GameButtonListener(SOUND_EFFECT_RED));
        GAME_BUTTON_YELLOW.getImageButton().setOnClickListener(new GameButtonListener(SOUND_EFFECT_YELLOW));
    }

    /**
     * Change text when user presses a button and check if a player has win.
     */

    private void startGame() {
        findViewById(R.id.btnStartGame).setVisibility(View.INVISIBLE);
        // Reset items
        setScore(0);
        getGameSequence().clear();
        getUserSequence().clear();
        // Initiate the game sequence
        super.addToGameSequence(4); //TODO THIS IS ONLY A DEMO SIZE
        // Play the sequence for the user
        PlaySequence playSequence = new PlaySequence(getGameSequence());
        playSequence.execute();
    }

    private void gameOver() {

        Toast.makeText(this, "Game Over!", Toast.LENGTH_SHORT).show();
    }

    private void restartGame() {
        startGame();
    }

    private void returnToMainMenu() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }


    /**
     * Used to handle app cycles during rotations
     * Store what needs to be retrieved when they get back
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onClick(View v) {
    }


    /**
     * Class to minimize code when assigning onclicklisteners to Game buttons
     * Inner class so it can utilize the methods from superclass
     */
    class GameButtonListener implements View.OnClickListener {
        private int assignedSoundEffect;

        GameButtonListener(int assignedSoundEffect) {
            this.assignedSoundEffect = assignedSoundEffect;
        }

        @Override
        public void onClick(View view) {
            playSound(assignedSoundEffect);
        }
    }


    /**
     * Plays the specified sequence. Simulates "simon" playing
     */
    public void PlaySequence() {
        PlaySequence playSequence = new PlaySequence(super.getGameSequence());
        playSequence.execute();
    }

    public class PlaySequence extends AsyncTask<Void, Void, Void> {
        LinkedList<Character> sequence;

        PlaySequence(LinkedList<Character> sequence) {
            this.sequence = sequence;
        }

        //TODO IMPLEMENT PLAYING THE SEQUENCE
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                // Loop through each character in the sequence
                // Need to highlight the button for the current character
                for (int i = 0; i < sequence.size(); i++) {
                    final GameButton gameButton;
                    // Get values depending on button to highlight
                    switch (sequence.get(i)) {
                        case 'r':
                            gameButton = GAME_BUTTON_RED;
                            break;
                        case 'g':
                            gameButton = GAME_BUTTON_GREEN;
                            break;
                        case 'b':
                            gameButton = GAME_BUTTON_BLUE;
                            break;
                        case 'y':
                            gameButton = GAME_BUTTON_YELLOW;
                            break;
                        default:
                            // Will never reach a default state
                            gameButton = null;
                    }

                    try {
                        // Highlight the button
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //TODO CAN CREATE METHOD IN GAMEBUTTON CLASS TO DO THE HIGHLIGHT FOR US
                                gameButton.toggleHighlight(true);
                            }
                        });
                        playSound(gameButton.getSoundEffectId());
                        // Pause on highlight
                        Thread.sleep(((Float) (GAME_INTERVAL_TIME / GAME_SPEED)).longValue());
                        // Return the color of the button
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                gameButton.toggleHighlight(false);
                            }
                        });
                    } catch (InterruptedException e) {
                        Log.w("4020debug", "Exception while trying to play the sequence");
                    }
                }
            } catch (Exception e) {
                Log.w("4020debug", "Exception thrown during sequence playing");
            }

            return null;
        }
    }
}
