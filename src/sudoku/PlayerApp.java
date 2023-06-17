/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sudoku;

import java.rmi.*;
import java.rmi.server.*;
import javax.swing.JTextArea;

/**
 *
 * @author inoca
 */
public class PlayerApp extends UnicastRemoteObject implements PlayerInterface {

    private JTextArea messageTextArea;
    
    public PlayerApp(JTextArea messageTextArea) throws RemoteException {
        super();
        this.messageTextArea = messageTextArea;
    }
     
    @Override
    public void playerJoined(String username) throws RemoteException {
        String message = "Player " + username + " joined the game.";
        messageTextArea.append(message + "\n");
    }

    @Override
    public void playerLeft(String username) throws RemoteException {
        String message = "Player " + username + " left the game.";
        messageTextArea.append(message + "\n");
    }

    @Override
    public void playerMove(String username) throws RemoteException {
        String message = "Player " + username + " did a correct move.";
        messageTextArea.append(message + "\n");
    }

    @Override
    public void startGame() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
