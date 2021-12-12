package com.conradtibesar;

import java.util.Random;

public class ComputerPlayer extends Player {

    // implement the abstract Player method to return 'C' for ComputerPlayer
    public char getPlayerType() {
        return 'C';
    }

    // set the play after getting the move
    private boolean setPlay(Board board, int row, int column) {
        if (board.open_spot(row, column)) {
            setRow(row);
            setColumn(column);
            return true;
        } else {
            return false;
        }
    }


    // get the move from the ComputerPlayer, which first checks if it has
    // a winning move, then checks if it has to make a defensive move, and finally picks a random
    // move if the latter two options are false
    public void getMove(Board board) {
        // if there is an offensive play, make it
        boolean smartMove = decideMove(board, 'C');

        // if not, check for defensive move
        if (!smartMove) {
            smartMove = decideMove(board, 'H');
        }

        // if no offensive or defensive more, make a random move, checking that the space is available
        if (!smartMove) {
            int input_row, input_column;
            Random rand = new Random();
            do {
                input_row = rand.nextInt(3);
                input_column = rand.nextInt(3);
            }
            while (!board.open_spot(input_row, input_column));
            setRow(input_row);
            setColumn(input_column);
        }

    }

    // this method allows the ComputerPlayer to make a smart move (offensive or defensive)
    // since making an offensive or defensive move is the same, varying only by the character, the function takes a character
    // as a parameter (C for offense, H for defense)
    public boolean decideMove(Board board, char playerType) {
        char[][] played_spaces = board.getPlayed_spaces();
        // Check if there is a winning/losing move in the three rows, if there is, set the play accordingly, and return true
        for (int x = 0; x < 3; x++) {
            if (played_spaces[x][0] == played_spaces[x][1] && played_spaces[x][1] == playerType) {
                if (setPlay(board, x, 2)) {
                    return true;
                }
            }
            if (played_spaces[x][1] == played_spaces[x][2] && played_spaces[x][1] == playerType) {
                if (setPlay(board, x, 0)) {
                    return true;
                }
            }
            if (played_spaces[x][0] == played_spaces[x][2] && played_spaces[x][0] == playerType) {
                if (setPlay(board, x, 1)) {
                    return true;
                }
            }
        }
        // Check if there is a winning/losing move in the three columns, if there is, set the play accordingly, and return true
        for (int x = 0; x < 3; x++) {
            if (played_spaces[0][x] == played_spaces[1][x] && played_spaces[1][x] == playerType) {
                if (setPlay(board, 2, x)) {
                    return true;
                }
            }
            if (played_spaces[1][x] == played_spaces[2][x] && played_spaces[1][x] == playerType) {
                if (setPlay(board, 0, x)) {
                    return true;
                }
            }
            if (played_spaces[0][x] == played_spaces[2][x] && played_spaces[0][x] == playerType) {
                if (setPlay(board, 1, x)) {
                    return true;
                }
            }
        }
        // Check if there is a winning/losing move in the crossing rows,
        // if there is, set the play accordingly, and return true
        if (played_spaces[0][0] == played_spaces[1][1] && played_spaces[1][1] == playerType) {
            if (setPlay(board, 2, 2)) {
                return true;
            }
        }
        if (played_spaces[1][1] == played_spaces[2][2] && played_spaces[1][1] == playerType) {
            if (setPlay(board, 0, 0)) {
                return true;
            }
        }
        if (played_spaces[0][0] == played_spaces[2][2] && played_spaces[0][0] == playerType) {
            if (setPlay(board, 1, 1)) {
                return true;
            }
        }
        if (played_spaces[0][2] == played_spaces[1][1] && played_spaces[1][1] == playerType) {
            if (setPlay(board, 2, 0)) {
                return true;
            }
        }
        if (played_spaces[1][1] == played_spaces[2][0] && played_spaces[1][1] == playerType) {
            if (setPlay(board, 0, 2)) {
                return true;
            }
        }
        if (played_spaces[0][2] == played_spaces[2][0] && played_spaces[0][2] == playerType) {
            if (setPlay(board, 1, 1)) {
                return true;
            }
        }

        // if there are no winning/losing plays, return false
        return false;
    }


}
