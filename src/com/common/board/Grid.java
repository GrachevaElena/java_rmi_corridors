package com.common.board;

public class Grid {

    public static final int SIZE = 9;

    Cell[][] cells;

    Board[][] horizontalBoards;
    Board[][] verticalBoards;

    public Grid() {
        createCells();
        createHorizontalBoards();
        createVerticalBoards();
        linkCellsAndBoards();
    }

    private void createCells() {
        cells = new Cell[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = new Cell();
                cells[i][j].x = i;
                cells[i][j].y = j;
            }
    }

    private void createHorizontalBoards() {
        horizontalBoards = new Board[SIZE + 1][SIZE];
        for (int i = 0; i < SIZE + 1; i++)
            for (int j = 0; j < SIZE; j++)
                if (i == 0)
                    horizontalBoards[i][j] = new Board(Board.Position.HORIZONTAL, i, j,
                            null, cells[i][j]);
                else if (i == SIZE)
                    horizontalBoards[i][j] = new Board(Board.Position.HORIZONTAL, i, j,
                            cells[i - 1][j], null);
                else
                    horizontalBoards[i][j] = new Board(Board.Position.HORIZONTAL, i, j,
                            cells[i - 1][j], cells[i][j]);

    }

    private void createVerticalBoards() {
        verticalBoards = new Board[SIZE][SIZE + 1];
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE + 1; j++)
                if (j == 0)
                    verticalBoards[i][j] = new Board(Board.Position.VERTICAL, i, j,
                            null, cells[i][j]);
                else if (j == SIZE)
                    verticalBoards[i][j] = new Board(Board.Position.VERTICAL, i, j,
                            cells[i][j - 1], null);
                else
                    verticalBoards[i][j] = new Board(Board.Position.VERTICAL, i, j,
                            cells[i][j - 1], cells[i][j]);

    }

    private void linkCellsAndBoards() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++) {
                cells[i][j].setLeftBoard(verticalBoards[i][j]);
                cells[i][j].setTopBoard(horizontalBoards[i][j]);
                cells[i][j].setRightBoard(verticalBoards[i][j + 1]);
                cells[i][j].setDownBoard(horizontalBoards[i + 1][j]);
            }
    }

    public Cell getCell(int i, int j) {
        return cells[i][j];
    }

    public Board getHorizontalBoard(int i, int j) {
        return horizontalBoards[i][j];
    }

    public Board getVerticalBoard(int i, int j) {
        return verticalBoards[i][j];
    }

    public Board getBoard(Board.Position position, int i, int j) {
        if (position == Board.Position.VERTICAL)
            return getVerticalBoard(i, j);
        else return getHorizontalBoard(i, j);
    }

}
