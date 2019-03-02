package com.csci4020.simon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class clsGameSequence
{
	/**
	 * Use characters to identify the different buttons that exist in a game
	 */
	private char[] buttonChoices = {'r', 'g', 'b', 'y'};


	/**
	 * Stores the sequence of buttons in a game
	 */
	private List<Character> generatedGameSequence = new ArrayList<>();


	/**
	 * Stores the users sequence of guesses
	 */
	private List<Character> userGuessSequence = new ArrayList<>();


	/**
	 * Random object to obtain random numbers
	 */
	private Random rand = new Random();


	/**
	 * Current Score
	 */
	private int score = 0;


	/**
	 * Class constructor. Defaults to a start of 4 sequence if a number isn't provided
	 */
	public clsGameSequence()
	{
		for (int i = 0; i < 4; i++)
		{
			generatedGameSequence.add(buttonChoices[rand.nextInt(buttonChoices.length)]);
		}
	}


	/**
	 * Class constructors. Accepts a value of how many buttons to begin the game sequence
	 *
	 * @param n int Number of initial choices in game sequence
	 */
	public clsGameSequence(int n)
	{
		for (int i = 0; i < n; i++)
		{
			generatedGameSequence.add(buttonChoices[rand.nextInt(buttonChoices.length)]);
		}
	}


	/**
	 * Retrieve the current game sequence
	 *
	 * @return List<Character>
	 */
	public List<Character> getGeneratedGameSequence()
	{
		return generatedGameSequence;
	}


	/**
	 * Retrieve the current game sequence
	 *
	 * @return List<Character>
	 */
	public List<Character> getUserGuessSequence()
	{
		return userGuessSequence;
	}


	/**
	 * Add a user guess and check if it matches the corresponding game sequence value
	 *
	 * @param x char User Guess
	 * @return boolean User guess matches game sequence
	 */
	public boolean addUserGuess(char x)
	{
		userGuessSequence.add(x);
		return (generatedGameSequence.get(userGuessSequence.size() - 1) == userGuessSequence.get(userGuessSequence.size() - 1));
	}


	/**
	 * Add one more button to the game sequence
	 *
	 * @return boolean Indicating success on adding one character to game sequence
	 */
	public boolean addToGameSequence()
	{
		try
		{
			generatedGameSequence.add(buttonChoices[rand.nextInt(buttonChoices.length)]);
			return true;
		} catch (RuntimeException e)
		{
			return false;
		}
	}


	/**
	 * Get the current score
	 *
	 * @return int
	 */
	public int getScore()
	{
		return score;
	}


	/**
	 * Set the current score. Most likely used to reset to zero
	 *
	 * @param score int
	 */
	public void setScore(int score)
	{
		this.score = score;
	}


	/**
	 * Get character at position n from game sequence
	 *
	 * @param n int
	 * @return char
	 */
	public char getGameChar(int n)
	{
		return generatedGameSequence.get(n);
	}


	/**
	 * Get character at position n from user guess sequence
	 *
	 * @param n int
	 * @return char
	 */
	public char getUserChar(int n)
	{
		return userGuessSequence.get(n);
	}


	/**
	 * @return boolean
	 */
	public boolean turnOver()
	{
		return generatedGameSequence.size() == userGuessSequence.size();
	}
}
