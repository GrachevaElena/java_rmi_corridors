package com.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.common.PlayerItem;
import com.common.packages.*;
import com.rmi.interfaces.*;
import com.common.Constants;

public class Client implements IClient {

    IServer server;
    Controller controller;

    public Client() {
    }

    public Client(Controller controller) {
        this.controller = controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void getMessage(ServerPackage message) {
        System.out.println(message);
        controller.processServerMessage(message);
    }

    public void sendMessage(ClientPackage message) {
        try {
            server.getMessage(message);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public PlayerItem bind() {
        try {
            Registry registry = LocateRegistry.getRegistry(Constants.HOST, Constants.PORT);
            server = (IServer) registry.lookup(Constants.SERVER_NAME);

            System.out.println("Connected to server");

            IClient stub = (IClient) UnicastRemoteObject.exportObject(this, 0);
            return server.register(stub);

        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    public void unbind(PlayerItem playerItem) {
        try {
            server.unbind(playerItem);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
