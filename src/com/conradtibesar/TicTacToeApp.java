package com.conradtibesar;

/**
 *@author: Dr Kafi Rahman
code template designed for assignment purposes
educational and academic purpose only
 **/

import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeApp implements ActionListener{

    // create board object to keep track of moves in 2D array
    Board board = new Board();
    ComputerPlayer computerPlayer = new ComputerPlayer();
    HumanPlayer humanPlayer = new HumanPlayer();

    final int SIZE = 9;
    // Buttons to hold the selection values
    JButton boardButtons[]= new JButton[SIZE];

    // buttons to restart or exit the game
    JButton bRestart;
    JButton bExit;

    // to provide status message
    JLabel gameStatusLabel;
    JFrame gWindow; // main window object

    // constructor
    public TicTacToeApp(String title) {

        // creating a JFrame window with the title
        gWindow = new JFrame(title);

        // The JPanel holds the buttons
        JPanel upperLayerPanel = new JPanel();
        upperLayerPanel.setLayout(new GridLayout(3, 3));
        // creating memory for the buttons
        for(int i=0;i<SIZE;i++){
            boardButtons[i] = new JButton();
            boardButtons[i].setText(Integer.toString(i+1));
            boardButtons[i].setFont(new Font("SansSerif", Font.PLAIN, 20));
            boardButtons[i].addActionListener(this);

            // adding the button to the Panel
            upperLayerPanel.add(boardButtons[i]);
        }



        // Panel holding buttons at the south side
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2, 1));

        bRestart = new JButton("Restart Game");
        bRestart.setFont(new Font("SansSerif", Font.PLAIN, 16));
        bRestart.addActionListener(this);

        bExit = new JButton("Exit Game");
        bExit.setFont(new Font("SansSerif", Font.PLAIN, 16));
        bExit.addActionListener(this);


        gameStatusLabel= new JLabel("   Welcome. Your Turn. Select any button above to begin ..");
        gameStatusLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        gameStatusLabel.setPreferredSize(new Dimension(100, 40));

        southPanel.add(gameStatusLabel, BorderLayout.CENTER);

        JPanel lowerButtonPanel =new JPanel();
        lowerButtonPanel.setLayout(new GridLayout(1, 2));
        lowerButtonPanel.add(bRestart, BorderLayout.WEST);
        lowerButtonPanel.add(bExit, BorderLayout.EAST);
        southPanel.add(lowerButtonPanel);


        // adding all the panels to the main window
        gWindow.setLayout(new BorderLayout());
        gWindow.add(upperLayerPanel, BorderLayout.CENTER);
        gWindow.add(southPanel, BorderLayout.SOUTH);


        gWindow.setSize(500, 500);
        gWindow.setVisible(true);
        gWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    // this resets the buttons text values
    public void resetGame()
    {
        // creating memory for the buttons
        for(int i=0;i<SIZE;i++){
            boardButtons[i].setText(Integer.toString(i+1));
            boardButtons[i].setForeground(Color.BLACK);

        }
        board.clear();  // clear board
    }


    // Handles clicks on Compute button by computing the BMI.
    public void actionPerformed(ActionEvent event) {

        // if the event source is the restart button then
        if(event.getSource().equals(bRestart)){
            resetGame();
            this.gameStatusLabel.setText("   Game has restarted. Select any button above to begin ..");
            gWindow.setTitle("TicTacToe [Your Turn]"); // this can be randomized
        }
        // exit window if source is exit button
        if(event.getSource().equals(bExit)){
            gWindow.dispose();
        }
        else { // determine which cell button triggered the action event
            for(int i=0;i<SIZE;i++){
                if(event.getSource().equals(boardButtons[i])){ // button found

                    // if the cell has already been selected then do not do anything
                    if(!boardButtons[i].getText().equals("H") && !boardButtons[i].getText().equals("C") && !board.did_win()) {

                        // get column and row from i, set them for humanPlayer obj.
                        int column = i % 3;
                        int row = (i-column)/3;

                        humanPlayer.setColumn(column);
                        humanPlayer.setRow(row);

                        board.setSpace(humanPlayer);        // set the space in the board object, then on the GUI window

                        gWindow.setTitle("TicTacToe [Your Turn]");
                        boardButtons[i].setText("H");
                        boardButtons[i].setForeground(Color.BLUE);


                        if(board.did_win()){
                            gameStatusLabel.setText("    You won! Restart or exit the game to continue ...");
                            gWindow.setTitle("TicTacToe [Game Over!]");
                        }

                        else if(board.scratch()){ // if a scratch game
                            gameStatusLabel.setText("    Tie game! Restart the game to continue ...");
                            gWindow.setTitle("TicTacToe [Game Over!]");
                        }

                        // computerPlayer makes a move
                        else{
                            computerPlayer.getMove(board);
                            board.setSpace(computerPlayer);
                            // convert row and column of computerPlayer to int for GUI window
                            int j = computerPlayer.getColumn() + (3 * computerPlayer.getRow());
                            boardButtons[j].setText("C");
                            boardButtons[j].setForeground(Color.RED);
                            gameStatusLabel.setText("  Computer has selected cell " + (j+1) );
                            // check for win or scratch
                            if(board.did_win()){
                                gameStatusLabel.setText("    Looks like the computer won! Restart or exit the game to continue ...");
                                gWindow.setTitle("TicTacToe [Game Over!]");
                            }
                            else if(board.scratch()){
                                gameStatusLabel.setText("    Tie game! Restart the game to continue ...");
                                gWindow.setTitle("TicTacToe [Game Over!]");
                            }

                        }

                    } // new move: if condition ends

                } // main if inside the loop ends
            } // for loop ends
        } // else block ends
    } // actionPerformed function ends


    // main driver program
    public static void main(String[] args) {
        // create an object of the TikTakToe class
        TicTacToeApp gameWindow = new TicTacToeApp("TikTakToe Game");
    }

}
