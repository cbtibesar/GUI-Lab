package com.conradtibesar;

// Abstract Player class, has 2 abstract methods
abstract class Player {
    private int row;
    private int column;

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    // Abstract methods to be implemented in child class

    abstract public char getPlayerType();

    abstract public void getMove(Board board);

}
