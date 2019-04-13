package com.client;

import com.common.PlayerItem;
import com.common.board.Board;
import com.common.board.Cell;
import com.common.board.Grid;

public class Player {

    Controller controller;

    Grid grid;

    public Player() {
        grid = new Grid();
    }

    public Player(Controller controller) {
        this.controller = controller;
        grid = new Grid();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Grid getGrid() {
        return grid;
    }

    public void refreshGrid(Cell cell1, Cell cell2, Board board, PlayerItem currentPlayer){
        if (board != null)
            grid.getBoard(board.getPosition(), board.getIndexX(), board.getIndexY()).
                    setStatus(board.getStatus());

        if (cell1 != null)
            grid.getCell(cell1.getX(), cell1.getY()).setStatus(cell1.getStatus());

        if (cell2 != null)
            grid.getCell(cell2.getX(), cell2.getY()).setStatus(cell2.getStatus());
    }

}
