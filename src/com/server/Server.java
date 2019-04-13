package com.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.common.*;
import com.common.packages.*;
import com.common.status.GameStatus;
import com.rmi.interfaces.*;

public class Server implements IServer {

    private IClient player1 = null;
    private IClient player2 = null;

    private Controller controller;

    public Server() {
    }

    public Server(Controller controller) {
        this.controller = controller;
    }

    public void start() {
        try {
            IServer stub = (IServer) UnicastRemoteObject.exportObject(this, 0);
            Registry registry = LocateRegistry.createRegistry(Constants.PORT);
            registry.bind(Constants.SERVER_NAME, stub);

            System.out.println("Server is ready");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public PlayerItem register(IClient player) {
        if (player1 == null) {
            player1 = player;
            System.out.println("Player1 registered");

            try {
                player1.getMessage(new ServerPackage(null, null, null, null,
                        GameStatus.NEW_GAME, PlayerItem.player1));
            } catch (Exception e) {
                System.out.println(e);
            }

            return PlayerItem.player1;

        } else if (player2 == null) {
            player2 = player;
            System.out.println("Player2 registered");

            try {
                player1.getMessage(new ServerPackage(null, null, null, null,
                        GameStatus.CONTINUE, PlayerItem.player1));
                player2.getMessage(new ServerPackage(null, null, null, null,
                        GameStatus.CONTINUE, PlayerItem.player1));
            } catch (Exception e) {
                System.out.println(e);
            }

            if (isReadyToPlay())
                controller.startGame();

            return PlayerItem.player2;
        }
        return null;
    }

    public boolean isReadyToPlay(){
        return player1 != null && player2 != null;
    }

    @Override
    public void getMessage(ClientPackage clientPackage) {
        System.out.println(clientPackage);
        controller.processMove(clientPackage);
    }

    @Override
    public void sendMessage(ServerPackage message, PlayerItem playerItem) {
        try {
            if (playerItem == PlayerItem.player1)
                player1.getMessage(message);
            else if (playerItem == PlayerItem.player2)
                player2.getMessage(message);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void unbind(PlayerItem playerItem) {
        if (playerItem == PlayerItem.player1) {
            player1 = null;
            System.out.println("Player1 unbind");
        }
        else if (playerItem == PlayerItem.player2) {
            player2 = null;
            System.out.println("Player2 unbind");
        }
    }

}
