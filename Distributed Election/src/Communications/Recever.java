/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communications;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Fneich
 */
public class Recever {
    private String Ip;
    private int Port;
    private ServerSocket serverSocket;
    private Socket Socket;

    public Recever(String Ip) throws IOException {
        this.Ip = Ip;
        this.serverSocket = new ServerSocket(0, 100,InetAddress.getByName(this.Ip));
        this.Port = this.serverSocket.getLocalPort();
    }

    public String getIp() {
        return Ip;
    }

    public int getPort() {
        return Port;
    }

    public void setIp(String Ip) {
        this.Ip = Ip;
    }

    public void setPort(int Port) {
        this.Port = Port;
    }
    
       public Message ReceveMessage() throws IOException{
       if(this.Socket==null){
           this.Socket = this.serverSocket.accept();
       }         
       BufferedReader socketReader = new BufferedReader(new InputStreamReader(this.Socket.getInputStream()));
       String inMsg = socketReader.readLine();
       Message message = Message.fromJson(inMsg);
       return message;
    }
}
