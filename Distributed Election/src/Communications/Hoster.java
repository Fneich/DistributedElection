/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communications;

import Communications.Message.MessageKey;
import Communications.Message.MessageSide;
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
    private Thread thread;

    public Hoster(String Id, String Ip, int port, MessageSide messageSide) {
        this.Id = Id;
        this.Ip = Ip;
        this.port = port;
        this.messageSide = messageSide;
        this.thread=new Thread(this);
        this.thread.start();
    }

    public String getId() {
        return Id;
    }

    public String getIp() {
        return Ip;
    }

    public int getPort() {
        return port;
    }

    public MessageSide getMessageSide() {
        return messageSide;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public void setIp(String Ip) {
        this.Ip = Ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setMessageSide(MessageSide messageSide) {
        this.messageSide = messageSide;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

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
                    c.setConnectionSide(portmessage.getSide());
                    this.connection = c;
                }

            }} catch (UnknownHostException ex) {
            Logger.getLogger(Hoster.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Hoster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
