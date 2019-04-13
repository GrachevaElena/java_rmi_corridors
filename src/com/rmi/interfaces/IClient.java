package com.rmi.interfaces;

import com.common.PlayerItem;
import com.common.packages.ServerPackage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote {

    void getMessage(ServerPackage message) throws RemoteException;

    void unbind(PlayerItem playerItem) throws RemoteException;;
}
