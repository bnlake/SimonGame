package com.csci4020.simon;
/**
 * CSCI 4020
 * Assignment 2 - Simon
 * Hannie Kim
 * Brian Lake
 */
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.io.Serializable;

/**
 * Game Button Class. All functions that a game button can do
 * Relates to the R/G/B/Y buttons in simon
 */
public class GameButton extends Activity implements Serializable, View.OnClickListener
{
	private ImageButton imageButton;    // The view that is assigned as a game button
	private int soundEffectId;
	private int baseColor;
	private int highlightColor;
	private boolean isHighlighted;


	public int getBaseColor()
	{
		return baseColor;
	}

	public void setBaseColor(int baseColor)
	{
		this.baseColor = baseColor;
	}


	public int getHighlightColor()
	{
		return highlightColor;
	}

	public void setHighlightColor(int highlightColor)
	{
		this.highlightColor = highlightColor;
	}


	public GameButton(ImageButton imageButton, int soundEffectId, int baseColor, int highlightColor)
	{
		this.imageButton = imageButton;
		this.soundEffectId = soundEffectId;
		this.baseColor = baseColor;
		this.highlightColor = highlightColor;
		this.isHighlighted = false;
		// Set the color of the ImageView
		this.imageButton.setImageResource(this.baseColor);
	}

	public GameButton()
	{
	}

	public ImageButton getImageButton()
	{
		return imageButton;
	}

	public void setImageButton(ImageButton imageButton)
	{
		this.imageButton = imageButton;
	}

	public int getSoundEffectId()
	{
		return soundEffectId;
	}

	public void setSoundEffectId(int soundEffectId)
	{
		this.soundEffectId = soundEffectId;
	}

	/**
	 * Return the imagebutton tag as a character
	 *
	 * @return char Gamebutton Char Value
	 */
	public char getButtonChar()
	{
		switch (getImageButton().getTag().toString())
		{
			case "r":
				return 'r';
			case "b":
				return 'b';
			case "g":
				return 'g';
			case "y":
				return 'y';
			default:
				return 'z';
		}
	}

	/**
	 * Handle when a game button is pressed
	 * Should highlight, play sound
	 *
	 * @param v View
	 */
	@Override
	public void onClick(View v)
	{
	}

	/**
	 * Toggle the color of the imageview between the base color and highlight color
	 *
	 * @return boolean Indicating operation success
	 */
	public boolean toggleHighlight()
	{
		try
		{
			if (!this.isHighlighted)
			{
				this.getImageButton().setBackgroundColor(this.getHighlightColor());
				this.isHighlighted = true;
				return true;
			}
			else
			{
				this.getImageButton().setBackgroundColor(this.getBaseColor());
				this.isHighlighted = false;
				return true;
			}
		} catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * Toggle the color of the imageview between the base color and highlight color
	 *
	 * @param x boolean Force which color to choose
	 * @return boolean Indicating operation success
	 */
	public boolean toggleHighlight(boolean x)
	{
		try
		{
			if (x)
			{
				this.getImageButton().setImageResource(this.getHighlightColor());
				this.isHighlighted = true;
				return true;
			}
			else
			{
				this.getImageButton().setImageResource(this.getBaseColor());
				this.isHighlighted = false;
				return true;
			}
		} catch (Exception e)
		{
			return false;
		}
	}
}
