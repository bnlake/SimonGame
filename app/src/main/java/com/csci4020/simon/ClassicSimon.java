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
        GAME_BUTTON_BLUE.getImageButton().setOnClickListener(new GameButtonListener(GAME_BUTTON_BLUE));
        GAME_BUTTON_GREEN.getImageButton().setOnClickListener(new GameButtonListener(GAME_BUTTON_GREEN));
        GAME_BUTTON_RED.getImageButton().setOnClickListener(new GameButtonListener(GAME_BUTTON_RED));
        GAME_BUTTON_YELLOW.getImageButton().setOnClickListener(new GameButtonListener(GAME_BUTTON_YELLOW));
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
        super.setGameStarted(true);
        // Play the sequence for the user
        PlaySequence playSequence = new PlaySequence(getGameSequence());
        playSequence.execute();
    }

    private void gameOver() {
        super.setGameStarted(false);
        Toast.makeText(this, "Incorrect, Game Over!", Toast.LENGTH_SHORT).show();
    }

    // TODO When would there be a difference between restart and start?
    private void restartGame() {
        startGame();
    }

    /**
     * Send user back to the main menu
     */
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
        private GameButton gameButton;

        /**
         * Constructor to recieve that GameButton class
         * Needed to reference all of the methods of the class
         *
         * @param gameButton button context
         */
        public GameButtonListener(GameButton gameButton) {
            this.gameButton = gameButton;
        }

        /**
         * Manage the listener for a game button
         * Should have all the steps that are performed when a game button
         * is pressed
         *
         * @param view
         */
        @Override
        public void onClick(View view) {
            GameButtonPress gameButtonPress = new GameButtonPress();
            gameButtonPress.execute();
        }

        /**
         * GameButtonPress class. Using it this way allows us to utilize AsyncTask
         * but still reach all of the methods of the Game class
         */
        class GameButtonPress extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            gameButton.toggleHighlight(true);
                        }
                    });

                    playSound(gameButton.getSoundEffectId());

                    Thread.sleep(((Float) (GAME_INTERVAL_TIME / GAME_SPEED)).longValue());
                    // Return the color of the button
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            gameButton.toggleHighlight(false);
                        }
                    });

                } catch (InterruptedException e) {
                    Log.i("4020debug", "Thread was interrupted");
                }
                return null;
            }
        }
    }


    /**
     * ASYNC class to play a specified sequence
     * Equates to highlighting, sounds
     */
    public class PlaySequence extends AsyncTask<Void, Void, Void> {
        LinkedList<Character> sequence;

        PlaySequence(LinkedList<Character> sequence) {
            this.sequence = sequence;
        }

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
