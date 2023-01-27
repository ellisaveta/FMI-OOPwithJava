package com;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public interface CipherInterface extends Remote {
    Map<String, String> cardsDB = new TreeMap<>();
    String encrypted(String cardNumber) throws RemoteException;
    String decrypted(String cipher) throws RemoteException;
    boolean validateCardNumber(String cardNumber) throws RemoteException;
    boolean validateUserInfo(String username, String password) throws RemoteException;
    boolean[] correctUserInfo(String username, String password) throws RemoteException;
    void writeCardInfoByCiphers() throws RemoteException;
    void writeCardIngoByCardNumbers() throws RemoteException;
}

