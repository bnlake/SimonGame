package com.csci4020.simon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class clsGameSequence implements Serializable
{
	private static final long serialVersionUID = -398377828479839840L;


	/**
	 * Use characters to identify the different buttons that exist in a game
	 */
	private char[] buttonChoices = {'r', 'g', 'b', 'y'};


	/**
	 * Stores the sequence of buttons in a game
	 */
	private List<Character> sequence = new ArrayList<>();


	/**
	 * Random object to obtain random numbers
	 */
	private Random rand = new Random();


	/**
	 * Current Score
	 */
	private int score = 0;


	/**
	 * Class constructor. Defaults to a start of 0 sequence if a number isn't provided
	 */
	clsGameSequence()
	{
	}


	/**
	 * Class constructors. Accepts a value of how many buttons to begin the game sequence
	 *
	 * @param n int Number of initial choices in game sequence
	 */
	clsGameSequence(int n)
	{
		for (int i = 0; i < n; i++)
		{
			sequence.add(buttonChoices[rand.nextInt(buttonChoices.length)]);
		}
	}


	/**
	 * Retrieve the current game sequence
	 *
	 * @return List<Character>
	 */
	public List<Character> getSequence()
	{
		return sequence;
	}


	/**
	 * Add a user guess and check if it matches the corresponding game sequence value
	 *
	 * @param x char User Guess
	 * @return boolean User guess matches game sequence
	 */
	public boolean addToSequence(char x)
	{
		sequence.add(x);
		return (sequence.get(sequence.size() - 1) == sequence.get(sequence.size() - 1));
	}


	/**
	 * Add one more button to the game sequence
	 *
	 * @return boolean Indicating success on adding one character to game sequence
	 */
	public boolean addToSequence()
	{
		try
		{
			sequence.add(buttonChoices[rand.nextInt(buttonChoices.length)]);
			return true;
		} catch (RuntimeException e)
		{
			return false;
		}
	}


	/**
	 * Get character at position n from game sequence
	 *
	 * @param n int
	 * @return char
	 */
	public char getSequenceChar(int n)
	{
		return sequence.get(n);
	}


	/**
	 * Compare one game sequence to another.
	 * @param y clsGameSequence compared object
	 * @return boolean
	 */
	public boolean equals(clsGameSequence y)
	{
		if (this.sequence.size() < 1 && y.sequence.size() < 1)
			return true;

		if (this.sequence.size() != y.sequence.size())
			return false;
		else
		{
			for (int i = 0; i < this.sequence.size(); i++)
			{
				if (this.getSequenceChar(i) != y.getSequenceChar(i))
					return false;
				else
					return true;
			}
		}

		return false;	// Safety statement if we get here for some reason
	}
}