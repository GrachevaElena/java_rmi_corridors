package com.rmi.interfaces;

import com.common.packages.ServerPackage;
import com.common.packages.ClientPackage;
import com.common.PlayerItem;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {

    PlayerItem register(IClient player) throws RemoteException;

    void getMessage(ClientPackage clientPackage) throws RemoteException;
    void sendMessage(ServerPackage message, PlayerItem playerItem) throws RemoteException;

    void unbind(PlayerItem playerItem) throws RemoteException;

}
