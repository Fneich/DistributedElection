/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import Communications.Connection;
import Communications.Message;
import Communications.Message.MessageSide;
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
public Connection connection;
    public SocketListener(Connection connection) {
        this.thread= new Thread(this);
        this.thread.start();
        this.connection=connection;
    }

        @Override
    public void run() {
    try {

        while(true){
            Message message=connection.WaitMessage();
            System.out.println(message.getValue());
        }
    } catch (IOException ex) {
        Logger.getLogger(SocketSender.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
}
