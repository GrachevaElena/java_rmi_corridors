package com.common.packages;

import com.common.PlayerItem;
import com.common.board.Board;

import java.io.Serializable;

public class ClientPackage implements Serializable {

    PlayerItem player;
    Board clickedBoard;

    public ClientPackage(PlayerItem player, Board clickedBoard) {
        this.player = player;
        this.clickedBoard = clickedBoard;
    }

    public PlayerItem getPlayer() {
        return player;
    }

    public void setPlayer(PlayerItem player) {
        this.player = player;
    }

    public Board getClickedBoard() {
        return clickedBoard;
    }

    public void setClickedBoard(Board clickedBoard) {
        this.clickedBoard = clickedBoard;
    }

    @Override
    public String toString() {
        return "ClientPackage{" +
                "player=" + player +
                ", clickedBoard=" + clickedBoard +
                '}';
    }
}
