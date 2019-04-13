package com.client.form;

import com.client.Controller;
import com.common.board.Board;
import com.common.board.Grid;
import com.common.status.BoardStatus;
import com.common.status.CellStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualGrid {

    Controller controller;

    JPanel[][] cells;
    JButton[][] horizontalBoards;
    JButton[][] verticalBoards;

    JPanel gridPanel;

    int cellSize;
    int boardWidth;

    Color boardColor = Color.LIGHT_GRAY;
    Color cellColor = Color.WHITE;
    Color cellColorOccupiedFirst = new Color(167, 204, 249);
    Color cellColorOccupiedSecond = new Color(242, 199, 197);
    Color boardColorOccupiedFirst = Color.BLUE;
    Color boardColorOccupiedSecond = Color.RED;


    public VisualGrid(JPanel gridPanel) {

        cells = new JPanel[Grid.SIZE][Grid.SIZE];
        horizontalBoards = new JButton[Grid.SIZE + 1][Grid.SIZE];
        verticalBoards = new JButton[Grid.SIZE][Grid.SIZE + 1];

        this.gridPanel = gridPanel;
        gridPanel.setLayout(null);

        double part = 0.2;
        cellSize = (int) (gridPanel.getWidth() / (Grid.SIZE + part));
        boardWidth = (int) (cellSize * part + 1);

        drawHorizontalBoards();
        drawVerticalBoards();
        drawCells();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    private void drawCells() {
        JPanel cellPanel = new JPanel(null);
        cellPanel.setBounds(boardWidth / 2, boardWidth / 2,
                gridPanel.getWidth() - boardWidth, gridPanel.getHeight() - boardWidth);
        for (int i = 0; i < Grid.SIZE; i++)
            for (int j = 0; j < Grid.SIZE; j++) {
                cells[i][j] = createCell(i * cellSize, j * cellSize, cellSize, cellSize);
                cellPanel.add(cells[i][j]);
            }
        gridPanel.add(cellPanel);
    }

    private void drawHorizontalBoards() {
        for (int i = 0; i < Grid.SIZE + 1; i++)
            for (int j = 0; j < Grid.SIZE; j++) {
                horizontalBoards[i][j] = createBoard(i,j, Board.Position.HORIZONTAL);
                gridPanel.add(horizontalBoards[i][j]);
            }
    }

    private void drawVerticalBoards() {
        for (int i = 0; i < Grid.SIZE; i++)
            for (int j = 0; j < Grid.SIZE + 1; j++) {
                verticalBoards[i][j] = createBoard(i,j, Board.Position.VERTICAL);
                gridPanel.add(verticalBoards[i][j]);
            }
    }

    private JButton createBoard(int i, int j, Board.Position position) {
        JButton board = new JButton();

        if (position == Board.Position.HORIZONTAL)
            board.setBounds(i * cellSize,
                    j * cellSize + boardWidth, boardWidth, cellSize - boardWidth);
        else board.setBounds(i * cellSize + boardWidth,
                j * cellSize, cellSize - boardWidth, boardWidth);

        board.setBackground(boardColor);
        board.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.makeMove(position, i, j);
            }
        });

        board.setEnabled(false);

        return board;
    }

    public void enableBoards() {
        for (int i = 0; i < Grid.SIZE; i++)
            for (int j = 0; j < Grid.SIZE + 1; j++)
                verticalBoards[i][j].setEnabled(true);
        for (int i = 0; i < Grid.SIZE + 1; i++)
            for (int j = 0; j < Grid.SIZE; j++)
                horizontalBoards[i][j].setEnabled(true);
    }

    private JPanel createCell(int x, int y, int width, int height) {
        JPanel cell = new JPanel();
        cell.setBounds(x, y, width, height);
        cell.setBackground(cellColor);
        return cell;
    }

    public void refresh(Grid grid) {
        reloadCells(grid);
        reloadHorizontalBoards(grid);
        reloadVerticalBoards(grid);
        gridPanel.repaint();
    }

    private void reloadCells(Grid grid) {
        for (int i = 0; i < Grid.SIZE; i++)
            for (int j = 0; j < Grid.SIZE; j++)
                if (grid.getCell(i, j).getStatus() == CellStatus.OCCUPIED_BY_FIRST)
                    cells[i][j].setBackground(cellColorOccupiedFirst);
                else if (grid.getCell(i, j).getStatus() == CellStatus.OCCUPIED_BY_SECOND)
                    cells[i][j].setBackground(cellColorOccupiedSecond);
                else cells[i][j].setBackground(cellColor);
    }

    private void reloadHorizontalBoards(Grid grid) {
        for (int i = 0; i < Grid.SIZE + 1; i++)
            for (int j = 0; j < Grid.SIZE; j++)
                if (grid.getHorizontalBoard(i, j).getStatus() == BoardStatus.OCCUPIED_BY_FIRST)
                    horizontalBoards[i][j].setBackground(boardColorOccupiedFirst);
                else if (grid.getHorizontalBoard(i, j).getStatus() == BoardStatus.OCCUPIED_BY_SECOND)
                    horizontalBoards[i][j].setBackground(boardColorOccupiedSecond);
                else horizontalBoards[i][j].setBackground(boardColor);
    }

    private void reloadVerticalBoards(Grid grid) {
        for (int i = 0; i < Grid.SIZE; i++)
            for (int j = 0; j < Grid.SIZE + 1; j++)
                if (grid.getVerticalBoard(i, j).getStatus() == BoardStatus.OCCUPIED_BY_FIRST)
                    verticalBoards[i][j].setBackground(boardColorOccupiedFirst);
                else if (grid.getVerticalBoard(i, j).getStatus() == BoardStatus.OCCUPIED_BY_SECOND)
                    verticalBoards[i][j].setBackground(boardColorOccupiedSecond);
                else verticalBoards[i][j].setBackground(boardColor);
    }

}
