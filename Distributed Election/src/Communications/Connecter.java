/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communications;

import Testing.SocketListener;
import Testing.SocketSender;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author Fneich
 */
public class Connecter {
    private String Id;
    private String Ip;
    private Message.MessageSide messageSide;
    private Connection connection;

    public Connecter(String Id, String Ip, Message.MessageSide messageSide) {
        this.Id = Id;
        this.Ip = Ip;
        this.messageSide = messageSide;
    }

    public String getId() {
        return Id;
    }

    public String getIp() {
        return Ip;
    }


    public Message.MessageSide getMessageSide() {
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



    public void setMessageSide(Message.MessageSide messageSide) {
        this.messageSide = messageSide;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public void ConnectTo(int port) throws IOException{
         Socket s = new Socket(this.Ip,port);
         Connection c = new Connection("",this.Ip,this.messageSide);
         int myport = c.getReceverPort();
         BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
         Message myportmessage = new Message(Message.MessageKey.Connect,messageSide,String.valueOf(myport)) ;
         socketWriter.write(myportmessage.toJson());
         socketWriter.newLine();
         socketWriter.flush();        
         BufferedReader socketReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
         String inMsg = socketReader.readLine();
         Message portmessage = Message.fromJson(inMsg);
         if(portmessage.getKey()==Message.MessageKey.Accept){
         int _port = Integer.parseInt(portmessage.getValue());
         c.CreateSender(_port);
         this.connection =c;
         }
    }
}
