/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import Communications.ClientConnection;
import Communications.Message;
import Communications.ServerConnection;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fneich
 */
public class SocketListener implements Runnable{
public Thread thread;
public int Port;
    public SocketListener(int port) {
        this.thread= new Thread(this);
        this.thread.start();
        this.Port=port;
    }

        @Override
    public void run() {
    try {
        ServerConnection c = new ServerConnection("localhost");
        //Connection c = new Connection("localhost",10000,MessageSide.Voter);
        c.OpenConnection();
       // System.out.println(c.getPort());
        while(true){
            Message message=c.WaitMessage();
            System.out.println(message.getValue());
        }
    } catch (IOException ex) {
        Logger.getLogger(SocketSender.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
}
