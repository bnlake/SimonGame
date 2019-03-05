package com.csci4020.simon;

/**
 * Game Button Class. All functions that a game button can do
 * Relates to the R/G/B/Y buttons in simon
 */
public class GameButton<V, T> {
    V imageButton;    // The view that is assigned as a game button
    T soundEffectId;

    public GameButton(V imageButton, T soundEffectId) {
        this.imageButton = imageButton;
        this.soundEffectId = soundEffectId;
    }
}
