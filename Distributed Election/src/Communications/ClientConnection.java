/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communications;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Fneich
 */
public class ClientConnection {
    private String IP;
    private int Port;
    private Socket ActiveSocket;
    private Boolean Opened;
    private Message.MessageSide messageSide;
    
    public ClientConnection( String IP,int port,Message.MessageSide messageside) throws UnknownHostException, IOException {
        this.IP = IP;
        this.Port=port;
        this.Opened=false;
        this.messageSide=messageside;
    }


    public String getIP() {
        return IP;
    }

    public int getPort() {
        return Port;
    }


    public Socket getActiveSocket() {
        return ActiveSocket;
    }

    public Boolean getOpened() {
        return Opened;
    }


    public void setIP(String IP) {
        this.IP = IP;
    }

    public void setPort(int Port) {
        this.Port = Port;
    }



    public void setActiveSocket(Socket ActiveSocket) {
        this.ActiveSocket = ActiveSocket;
    }

    public void OpenConnection() throws UnknownHostException, IOException{
        if(!this.Opened){
            this.ActiveSocket = new Socket(InetAddress.getByName(this.getIP()),this.Port);
            this.Opened=true;
        }
    }
    
    public void CloseConnection() throws IOException{
        if(this.Opened){
            
        this.ActiveSocket.close();
        this.ActiveSocket=null;
        this.Opened=false;
        }
    }
    
    public Message WaitMessage() throws IOException{
        if(this.Opened){            
       BufferedReader socketReader = new BufferedReader(new InputStreamReader(this.ActiveSocket.getInputStream()));
       String inMsg = socketReader.readLine();
       Message message = Message.fromJson(inMsg);
       this.ActiveSocket.close();
       this.ActiveSocket=null;
       return message;
    }
        return null;
    }
    public void SendMessage(Message message) throws IOException{
        BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(this.ActiveSocket.getOutputStream()));
        socketWriter.write(message.toJson());
        socketWriter.newLine();
        socketWriter.flush();
    }

}
