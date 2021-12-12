package com.conradtibesar;

import java.util.Scanner;


// extend the player class, which
public class HumanPlayer extends Player {
    Scanner input = new Scanner(System.in);


    // Implement the abstract method getPlayerType, which returns an 'H' character
    public char getPlayerType() {
        return 'H';
    }


    // Get the move from the user
    // Continue getting the move until it is an open space
    public void getMove(Board board) {
        int input_row, input_column;
        do {
            System.out.println("Please enter your move in the form 'row, column': ");
            String input_space = input.nextLine();
            String[] parse = input_space.split(", "); // separate the two integers, separated by ", "
            input_row = Integer.parseInt(parse[0]);
            input_column = Integer.parseInt(parse[1]);
        }
        while (!board.open_spot(input_row, input_column));

        // set the row and column of the HumanPlayer
        setRow(input_row);
        setColumn(input_column);

    }
}
