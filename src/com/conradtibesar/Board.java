package com.conradtibesar;

public class Board {
    // Board object consists of a double array, made up of characters
    private char[][] played_spaces;

    // upon initialization, set open spaces using the character '+'
    Board() {
        this.played_spaces = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.played_spaces[i][j] = '+';
            }
        }
    }

    // function to clear the board
    public void clear() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.played_spaces[i][j] = '+';
            }
        }
    }

    // getter function
    public char[][] getPlayed_spaces() {
        return this.played_spaces;
    }

    // function to display the board in a tic-tac-toe fashion


    // function to determine if a space on the board is open
    public boolean open_spot(int row, int column) {
        return played_spaces[row][column] == '+';
    }

    public boolean scratch() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.played_spaces[i][j] == '+') {
                    return false;
                }
            }
        }
        return true;
    }

    // function to set a space on the board with the character of the player type
    // (either 'H' or 'C', depending on the object that calls it)
    public void setSpace(Player player) {
        played_spaces[player.getRow()][player.getColumn()] = player.getPlayerType();
    }

    // function to determine there has been a game winner
    // check if there are three in a row for the 8 possible ways, then check on of the characters in the
    // line to determine who won
    // if no winner, return false
    public boolean did_win() {
        if (played_spaces[0][0] == played_spaces[0][1] && played_spaces[0][2] == played_spaces[0][1] && played_spaces[0][1] != '+') {
            return true;
        }
        if (played_spaces[1][0] == played_spaces[1][1] && played_spaces[1][2] == played_spaces[1][1] && played_spaces[1][1] != '+') {
            return true;
        }
        if (played_spaces[2][0] == played_spaces[2][1] && played_spaces[2][2] == played_spaces[2][1] && played_spaces[2][1] != '+') {

            return true;
        }
        if (played_spaces[0][0] == played_spaces[1][0] && played_spaces[2][0] == played_spaces[1][0] && played_spaces[0][0] != '+') {
            return true;
        }
        if (played_spaces[0][1] == played_spaces[1][1] && played_spaces[2][1] == played_spaces[0][1] && played_spaces[0][1] != '+') {
            return true;
        }
        if (played_spaces[0][2] == played_spaces[1][2] && played_spaces[2][2] == played_spaces[1][2] && played_spaces[0][2] != '+') {
            return true;
        }
        if (played_spaces[0][0] == played_spaces[1][1] && played_spaces[2][2] == played_spaces[1][1] && played_spaces[1][1] != '+') {
            return true;
        }
        if (played_spaces[0][2] == played_spaces[1][1] && played_spaces[2][0] == played_spaces[1][1] && played_spaces[1][1] != '+') {
            return true;
        } else {
            return false;
        }
    }
}
