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
    private GamePanel gameFrame;
    
    public PlayerApp(JTextArea messageTextArea, GamePanel gameFrame) throws RemoteException {
        super();
        this.messageTextArea = messageTextArea;
        this.gameFrame = gameFrame;
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
    public void playerMove(String username, String score) throws RemoteException {
        String message = "Player " + username + " did a correct move. Current score: " + score;
        messageTextArea.append(message + "\n");
    }

    @Override
    public void startGame(int[][] values) throws RemoteException {
        System.out.println("ok");
        gameFrame.fillBoard(values);
    }
    
}
