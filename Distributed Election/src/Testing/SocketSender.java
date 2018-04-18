/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

/**
 *
 * @author Fneich
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
public class SocketSender implements Runnable{
public Thread thread;
public Connection connection;
    public SocketSender(Connection connection) {
        this.thread= new Thread(this);
        this.thread.start();
        this.connection=connection;
    }



@Override
    public void run() {
         try {
        Scanner sc=new Scanner(System.in);              
        while(true){
            Message message=new Message(Message.MessageKey.Vote,Message.MessageSide.Audit,sc.nextLine());
            connection.SendMessage(message);
        }
    } catch (IOException ex) {
        Logger.getLogger(SocketSender.class.getName()).log(Level.SEVERE, null, ex);
    }
   
    }
    
}