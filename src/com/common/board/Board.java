package com.common.board;

import com.common.status.BoardStatus;

import java.io.Serializable;

public class Board implements Serializable {

    public enum Position {
        VERTICAL,
        HORIZONTAL
    }

    BoardStatus status = BoardStatus.FREE;
    Position position;

    int indexX;
    int indexY;
;
    Cell neighbourLeftOrTop; // left or top
    Cell neighbourRightOrDown; // right or down

    public Board(Position position, int indexX, int indexY, Cell neighbourLeftOrTop, Cell neighbourRightOrDown) {
        this.position = position;
        this.indexX=indexX;
        this.indexY=indexY;
        this.neighbourLeftOrTop = neighbourLeftOrTop;
        this.neighbourRightOrDown = neighbourRightOrDown;
    }

    public BoardStatus getStatus() {
        return status;
    }

    public void setStatus(BoardStatus status) {
        this.status = status;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Cell getNeighbourLeftOrTop() {
        return neighbourLeftOrTop;
    }

    public void setNeighbourLeftOrTop(Cell neighbourLeftOrTop) {
        this.neighbourLeftOrTop = neighbourLeftOrTop;
    }

    public Cell getNeighbourRightOrDown() {
        return neighbourRightOrDown;
    }

    public void setNeighbourRightOrDown(Cell neighbourRightOrDown) {
        this.neighbourRightOrDown = neighbourRightOrDown;
    }

    public int getIndexX() {
        return indexX;
    }

    public void setIndexX(int indexX) {
        this.indexX = indexX;
    }

    public int getIndexY() {
        return indexY;
    }

    public void setIndexY(int indexY) {
        this.indexY = indexY;
    }

    @Override
    public String toString() {
        return "Board{" +
                "status=" + status +
                ", position=" + position +
                ", neighbourLeftOrTop=" + neighbourLeftOrTop +
                ", neighbourRightOrDown=" + neighbourRightOrDown +
                '}';
    }
}
