/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communications;

import Communications.Message.MessageKey;
import Communications.Message.MessageSide;
import Testing.SocketListener;
import Testing.SocketSender;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fneich
 */
public class Hoster implements Runnable{
    private String Id;
    private String Ip;
    private int port;
    private MessageSide messageSide;
    private Connection connection;

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(this.port, 100,InetAddress.getByName(this.Ip));
            while(true){
                Socket s = ss.accept();
                System.out.println("Incoming Connection...");
                BufferedReader socketReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String inMsg = socketReader.readLine();
                Message portmessage = Message.fromJson(inMsg);
                if(portmessage.getKey()==MessageKey.Connect){
                    int port = Integer.parseInt(portmessage.getValue());
                    Connection c = new Connection(this.Id,this.Ip,this.messageSide);
                    c.CreateSender(port);
                    int myport = c.getReceverPort();
                    Message myportmessage = new Message(MessageKey.Accept,messageSide,String.valueOf(myport)) ;
                    BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                    socketWriter.write(myportmessage.toJson());
                    socketWriter.newLine();
                    socketWriter.flush();
                    this.connection = c;
                }

            }} catch (UnknownHostException ex) {
            Logger.getLogger(Hoster.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Hoster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
