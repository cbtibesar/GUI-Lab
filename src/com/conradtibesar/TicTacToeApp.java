package com.conradtibesar;

import java.util.Scanner;

// This is the main class that simulates a tic-tac-toe game between a human player and a computer
public class TicTacToeApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello, let's play tic tac toe");

        // Create Board and Player objects
        Board board = new Board();
        HumanPlayer humanPlayer = new HumanPlayer();
        ComputerPlayer computerPlayer = new ComputerPlayer();

        int continuePlaying = 1;

        // give user an option to continue playing games, or to quit
        while (continuePlaying == 1) {
            boolean gameOver = false;
            board.clear();          // clear the board after each game
            board.displayBoard();
            // go through cycle of
            // 1. Check if there is a scratch, if not, go to step 2
            // 2. Get the move from user, set the space on the game board
            // 3. Display the board
            // 4. Check if there is a game winner or scratch. If yes, end the game
            // 5. If no game winner, get the move from the computer, set space on game board
            // 6. Check if there is a winner. If yes, end the game. If not, repeat the whole cycle
            while (!gameOver) {
                if (board.scratch()) {
                    board.displayBoard();

                    gameOver = true;
                } else {
                    humanPlayer.getMove(board);
                    board.setSpace(humanPlayer);
                    board.displayBoard();

                    if (board.did_win() | board.scratch()) {
                        gameOver = true;
                    } else {
                        computerPlayer.getMove(board);
                        board.setSpace(computerPlayer);
                        board.displayBoard();
                        if (board.did_win() | board.scratch()) {
                            gameOver = true;
                        }
                    }
                }
            }

            // After the end of each game, see if the user would like to play again
            System.out.println("Would you like to play again? Press 1 to play again and 0 to quit: ");
            continuePlaying = input.nextInt();
        }
    }
}
