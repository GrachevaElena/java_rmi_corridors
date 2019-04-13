package com.client;

import com.client.form.MainClientForm;
import com.client.form.VisualGrid;
import com.common.PlayerItem;
import com.common.board.Board;
import com.common.packages.ClientPackage;
import com.common.packages.ServerPackage;
import com.common.status.GameStatus;
import com.common.status.MoveStatus;

public class Controller {

    Client client;
    Player player;
    VisualGrid visualGrid;
    MainClientForm mainClientForm;

    GameStatus gameStatus;

    PlayerItem playerItem;

    public Controller(Client client, Player player, MainClientForm mainClientForm) {
        this.client = client;
        this.player = player;
        this.visualGrid = mainClientForm.getVisualGrid();
        this.mainClientForm = mainClientForm;
    }

    public void processServerMessage(ServerPackage serverPackage) {
        mainClientForm.setInfo(serverPackage.getGameStatus(), playerItem,
                playerItem==serverPackage.getCurrentPlayer());
        if (serverPackage.getGameStatus()==GameStatus.NEW_GAME)
            return;
        if (serverPackage.getMoveStatus() == MoveStatus.SUCCESS) {
            gameStatus = serverPackage.getGameStatus();
            player.refreshGrid(serverPackage.getCell1(), serverPackage.getCell2(), serverPackage.getBoard(),
                    serverPackage.getCurrentPlayer());
            visualGrid.refresh(player.getGrid());
        }
    }

    public void connect() {
        playerItem = client.bind();
    }

    public void disconnect() {
        client.unbind(playerItem);
    }

    public void makeMove(Board.Position position, int i, int j) {
        if (playerItem != null)
            client.sendMessage(new ClientPackage(playerItem, player.getGrid().getBoard(position, i, j)));
    }

    public static void main(String[] args) {
        Client client = new Client();
        Player player = new Player();
        MainClientForm mainClientForm = new MainClientForm();

        Controller controller = new Controller(client, player, mainClientForm);

        client.setController(controller);
        player.setController(controller);
        mainClientForm.setController(controller);
        mainClientForm.getVisualGrid().setController(controller);
    }
}
