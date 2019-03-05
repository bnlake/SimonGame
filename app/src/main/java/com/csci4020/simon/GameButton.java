package com.csci4020.simon;

import android.widget.ImageButton;

/**
 * Game Button Class. All functions that a game button can do
 * Relates to the R/G/B/Y buttons in simon
 */
public class GameButton {
    ImageButton imageButton;    // The view that is assigned as a game button
    int soundEffectId;

    public GameButton(ImageButton imageButton, int soundEffectId) {
        this.imageButton = imageButton;
        this.soundEffectId = soundEffectId;
    }

    public GameButton() {
    }
}
