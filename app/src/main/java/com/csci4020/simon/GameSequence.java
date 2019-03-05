package com.csci4020.simon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface GameSequence
{
	/**
	 * Use characters to identify the different buttons that exist in a Game
	 */
	static final char[] buttonChoices = {'r', 'g', 'b', 'y'};


	/**
	 * Stores the sequence of buttons in a Game
	 */
	List<Character> sequence = new ArrayList<>();


	/**
	 * Random object to obtain random numbers
	 */
	static final Random rand = new Random();


	/**
	 * Retrieve the current Game sequence
	 *
	 * @return List<Character>
	 */
	List<Character> getSequence();


	/**
	 * Add a user guess and check if it matches the corresponding Game sequence value
	 *
	 * @param x char User Guess
	 * @return boolean User guess matches Game sequence
	 */
	boolean addToSequence(char x);


	/**
	 * Add one more button to the Game sequence
	 *
	 * @return boolean Indicating success on adding one character to Game sequence
	 */
	boolean addToSequence();


	/**
	 * Get character at position n from Game sequence
	 *
	 * @param n int
	 * @return char
	 */
	char getSequenceChar(int n);
}