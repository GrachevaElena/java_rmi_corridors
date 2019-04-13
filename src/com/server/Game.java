package com.server;

import com.client.Player;
import com.common.*;
import com.common.board.Board;
import com.common.board.Cell;
import com.common.board.Grid;
import com.common.status.*;

public class Game {

    Controller controller;

    Grid grid;

    PlayerItem currentPlayer = PlayerItem.player1;
    GameStatus gameStatus;
    MoveStatus moveStatus;
    Board currentBoard;
    Cell neighbourCellLeftOrTop;
    Cell neighbourCellRightOrDown;


    public Game(Controller controller) {
        this.controller = controller;
        grid = new Grid();
        gameStatus = GameStatus.CONTINUE;
    }

    public PlayerItem getCurrentPlayer() {
        return currentPlayer;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public MoveStatus getMoveStatus() {
        return moveStatus;
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    public Cell getNeighbourCellLeftOrTop() {
        return neighbourCellLeftOrTop;
    }

    public Cell getNeighbourCellRightOrDown() {
        return neighbourCellRightOrDown;
    }

    public void processMove(PlayerItem player, Board clickedBoard) {

        if (!validateMove(player, clickedBoard)) {
            moveStatus = MoveStatus.FAILED;
            return;
        }

        currentBoard = grid.getBoard(clickedBoard.getPosition(),
                clickedBoard.getIndexX(), clickedBoard.getIndexY());

        if (player == PlayerItem.player1)
            currentBoard.setStatus(BoardStatus.OCCUPIED_BY_FIRST);
        else currentBoard.setStatus(BoardStatus.OCCUPIED_BY_SECOND);

        boolean firstCellOccupied = false, secondCellOccupied = false;

        if (clickedBoard.getNeighbourLeftOrTop() != null) {
            neighbourCellLeftOrTop = grid.getCell(clickedBoard.getNeighbourLeftOrTop().getX(),
                    clickedBoard.getNeighbourLeftOrTop().getY());
            firstCellOccupied = processCell(neighbourCellLeftOrTop, currentBoard);
        }
        if (clickedBoard.getNeighbourRightOrDown() != null) {
            neighbourCellRightOrDown = grid.getCell(clickedBoard.getNeighbourRightOrDown().getX(),
                    clickedBoard.getNeighbourRightOrDown().getY());
            secondCellOccupied = processCell(neighbourCellRightOrDown, currentBoard);
        }

        checkGameStatus(player);

        if (!firstCellOccupied && !secondCellOccupied && gameStatus == GameStatus.CONTINUE)
            changePlayer();

        moveStatus = MoveStatus.SUCCESS;
    }

    private void checkGameStatus(PlayerItem player) {
        boolean allCellsOccupied = true;
        int nFirstCells = 0, nSecondCells = 0;
        for (int i = 0; i < grid.SIZE; i++)
            for (int j = 0; j < grid.SIZE; j++) {
                if (grid.getCell(i, j).getStatus() == CellStatus.FREE) {
                    allCellsOccupied = false;
                    break;
                }
                else if (grid.getCell(i,j).getStatus()==CellStatus.OCCUPIED_BY_FIRST)
                    nFirstCells++;
                else if (grid.getCell(i,j).getStatus()==CellStatus.OCCUPIED_BY_SECOND)
                    nSecondCells++;
            }
        if (allCellsOccupied == true)
            if (nFirstCells > nSecondCells)
                gameStatus = GameStatus.FIRST_WON;
            else if (nFirstCells < nSecondCells)
                gameStatus = GameStatus.SECOND_WON;

    }

    private boolean validateMove(PlayerItem player, Board clickedBoard) {
        if (currentPlayer != player) return false;
        if (clickedBoard.getStatus() != BoardStatus.FREE) return false;
        return true;
    }

    private void changePlayer(){
        if (currentPlayer == PlayerItem.player1)
            currentPlayer = PlayerItem.player2;
        else currentPlayer = PlayerItem.player1;
    }

    private boolean processCell(Cell cell, Board lastBoard) {

        if (cell == null || cell.getStatus() != CellStatus.FREE)
            return false;

        BoardStatus leftBoardStatus = cell.getLeftBoard().getStatus();
        BoardStatus rightBoardStatus = cell.getRightBoard().getStatus();
        BoardStatus topBoardStatus = cell.getTopBoard().getStatus();
        BoardStatus downBoardStatus = cell.getDownBoard().getStatus();

        if ((leftBoardStatus == BoardStatus.OCCUPIED_BY_FIRST ||
                leftBoardStatus == BoardStatus.OCCUPIED_BY_SECOND) &&
                (rightBoardStatus == BoardStatus.OCCUPIED_BY_FIRST ||
                        rightBoardStatus == BoardStatus.OCCUPIED_BY_SECOND) &&
                (topBoardStatus == BoardStatus.OCCUPIED_BY_FIRST ||
                        topBoardStatus == BoardStatus.OCCUPIED_BY_SECOND) &&
                (downBoardStatus == BoardStatus.OCCUPIED_BY_FIRST ||
                        downBoardStatus == BoardStatus.OCCUPIED_BY_SECOND)) {

            if (lastBoard.getStatus() == BoardStatus.OCCUPIED_BY_FIRST)
                cell.setStatus(CellStatus.OCCUPIED_BY_FIRST);
            else cell.setStatus(CellStatus.OCCUPIED_BY_SECOND);

            return true;
        }

        return false;
    }

}
