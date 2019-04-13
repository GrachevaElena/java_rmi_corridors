package com.common.packages;

import com.common.PlayerItem;
import com.common.board.Board;
import com.common.board.Cell;
import com.common.status.GameStatus;
import com.common.status.MoveStatus;

import java.io.Serializable;

public class ServerPackage implements Serializable {

    Cell cell1;
    Cell cell2;

    Board board;

    MoveStatus moveStatus;
    GameStatus gameStatus;

    PlayerItem currentPlayer;

    public ServerPackage(Cell cell1, Cell cell2, Board board, MoveStatus moveStatus, GameStatus gameStatus, PlayerItem currentPlayer) {
        this.cell1 = cell1;
        this.cell2 = cell2;
        this.board = board;
        this.moveStatus = moveStatus;
        this.gameStatus = gameStatus;
        this.currentPlayer = currentPlayer;
    }

    public PlayerItem getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(PlayerItem currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Cell getCell1() {
        return cell1;
    }

    public void setCell1(Cell cell1) {
        this.cell1 = cell1;
    }

    public Cell getCell2() {
        return cell2;
    }

    public void setCell2(Cell cell2) {
        this.cell2 = cell2;
    }

    public MoveStatus getMoveStatus() {
        return moveStatus;
    }

    public void setMoveStatus(MoveStatus moveStatus) {
        this.moveStatus = moveStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    @Override
    public String toString() {
        return "ServerPackage{" +
                "cell1=" + cell1 +
                ", cell2=" + cell2 +
                ", board=" + board +
                ", moveStatus=" + moveStatus +
                ", gameStatus=" + gameStatus +
                '}';
    }
}
