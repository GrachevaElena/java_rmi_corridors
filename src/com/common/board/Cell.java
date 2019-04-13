package com.common.board;

import com.common.board.Board;
import com.common.status.CellStatus;

import java.io.Serializable;

public class Cell implements Serializable {

    int x = 0;
    int y = 0;

    transient Board leftBoard;
    transient Board rightBoard;
    transient Board topBoard;
    transient Board downBoard;

    CellStatus status = CellStatus.FREE;

    public Cell() {
    }

    public Cell(int x, int y, Board leftBoard, Board rightBoard, Board topBoard, Board downBoard) {
        this.x = x;
        this.y = y;
        this.leftBoard = leftBoard;
        this.rightBoard = rightBoard;
        this.topBoard = topBoard;
        this.downBoard = downBoard;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Board getLeftBoard() {
        return leftBoard;
    }

    public void setLeftBoard(Board leftBoard) {
        this.leftBoard = leftBoard;
    }

    public Board getRightBoard() {
        return rightBoard;
    }

    public void setRightBoard(Board rightBoard) {
        this.rightBoard = rightBoard;
    }

    public Board getTopBoard() {
        return topBoard;
    }

    public void setTopBoard(Board topBoard) {
        this.topBoard = topBoard;
    }

    public Board getDownBoard() {
        return downBoard;
    }

    public void setDownBoard(Board downBoard) {
        this.downBoard = downBoard;
    }

    public CellStatus getStatus() {
        return status;
    }

    public void setStatus(CellStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                ", status=" + status +
                '}';
    }
}
