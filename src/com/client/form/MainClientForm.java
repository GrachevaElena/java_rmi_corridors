package com.client.form;

import com.client.Controller;
import com.client.Player;
import com.common.PlayerItem;
import com.common.status.GameStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainClientForm extends JFrame {

    Controller controller;

    private JPanel rootPanel;
    private JPanel panelGrid;
    private JLabel labelInfo;
    private JButton buttonConnect;

    private VisualGrid visualGrid;

    static final int frameWidth = 600;
    static final int frameHeight = 620;
    static final int gridWidth = 450 + 11;
    static final int topSpace = 25;
    static final int betweenSpace = 5;
    static final int downSpace = 40;
    static final int labelWidth = 400;
    static final int buttonWidth = 150;
    static final int buttonHeight = 35;

    Color backgroundColor = new Color(255, 255, 255);

    public MainClientForm() {
        super("Corridors");

        setLayout(null);
        setBackground(backgroundColor);
        addEventOnExit();
        createElements();

        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(frameWidth, frameHeight);
        setVisible(true);

        visualGrid = new VisualGrid(panelGrid);

        SwingUtilities.updateComponentTreeUI(this);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public VisualGrid getVisualGrid() {
        return visualGrid;
    }

    private void addEventOnExit() {
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                Object[] options = {"Yes", "No"};
                int answer = JOptionPane
                        .showOptionDialog(e.getWindow(), "Do you want to close the window?",
                                "Confirmation", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null, options,
                                options[0]);
                if (answer == 0) {
                    e.getWindow().setVisible(false);
                    controller.disconnect();
                    System.exit(0);
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    private void createElements() {
        rootPanel = new JPanel(null);
        rootPanel.setBackground(backgroundColor);
        rootPanel.setBounds(0, 0, frameWidth, frameHeight);
        add(rootPanel);

        panelGrid = new JPanel();
        panelGrid.setBounds((frameWidth - gridWidth) / 2, topSpace, gridWidth, gridWidth);
        panelGrid.setBackground(backgroundColor);
        rootPanel.add(panelGrid);

        labelInfo = new JLabel();
        int labelHeight = frameHeight - topSpace - betweenSpace - downSpace - gridWidth;
        labelInfo.setBounds((frameWidth - labelWidth - buttonWidth) / 2,
                topSpace + gridWidth + betweenSpace, labelWidth, labelHeight);
        labelInfo.setBackground(Color.blue);
        setInfo(null, null, false);
        rootPanel.add(labelInfo);

        buttonConnect = new JButton("Connect");
        buttonConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.connect();
                buttonConnect.setEnabled(false);
                visualGrid.enableBoards();
            }
        });
        buttonConnect.setBounds((frameWidth - labelWidth - buttonWidth) / 2 + labelWidth,
                topSpace + gridWidth + betweenSpace + (labelHeight - buttonHeight) / 2, buttonWidth, buttonHeight);
        rootPanel.add(buttonConnect);

    }

    public void setInfo(GameStatus gameStatus, PlayerItem you, boolean yourMove) {
        if (gameStatus == null) {
            labelInfo.setText(Info.TRY_CONNECT.toString());
            return;
        }
        switch (gameStatus) {
            case NEW_GAME:
                labelInfo.setText(Info.WAIT_FOR_ANOTHER_PLAYER.toString());
                break;
            case CONTINUE:
                if (yourMove)
                    labelInfo.setText(Info.WAIT_FOR_YOUR_MOVE.toString());
                else labelInfo.setText(Info.WAIT_FOR_ANOTHER_MOVE.toString());
                break;
            case FIRST_WON:
                if (you == PlayerItem.player1)
                    labelInfo.setText(Info.YOU_WON.toString());
                else if (you == PlayerItem.player2)
                    labelInfo.setText(Info.YOU_LOSE.toString());
                break;
            case SECOND_WON:
                if (you == PlayerItem.player2)
                    labelInfo.setText(Info.YOU_WON.toString());
                else if (you == PlayerItem.player2)
                    labelInfo.setText(Info.YOU_LOSE.toString());
                break;
            default:
                labelInfo.setText(Info.TRY_CONNECT.toString());
                break;
        }

    }

}
