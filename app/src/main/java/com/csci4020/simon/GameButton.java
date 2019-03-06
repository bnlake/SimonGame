package com.csci4020.simon;

import android.content.res.ColorStateList;
import android.graphics.ColorFilter;
import android.widget.ImageButton;

/**
 * Game Button Class. All functions that a game button can do
 * Relates to the R/G/B/Y buttons in simon
 */
public class GameButton{
    private ImageButton imageButton;    // The view that is assigned as a game button
    private int soundEffectId;

    public GameButton(ImageButton imageButton, int soundEffectId) {
        this.imageButton = imageButton;
        this.soundEffectId = soundEffectId;
    }

    public GameButton() {
    }

    public ImageButton getImageButton() {
        return imageButton;
    }

    public void setImageButton(ImageButton imageButton) {
        this.imageButton = imageButton;
    }

    public int getSoundEffectId() {
        return soundEffectId;
    }

    public void setSoundEffectId(int soundEffectId) {
        this.soundEffectId = soundEffectId;
    }

}
