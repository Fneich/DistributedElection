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
public class ServerConnection implements SiteConnection{
    private String IP;
    private int Port;
    private ServerSocket _ServerSocket;
    private Socket ActiveSocket;


    public ServerConnection( String IP) throws UnknownHostException, IOException {
        this.IP = IP;
        this.Port=0;
        this._ServerSocket = new ServerSocket(this.Port, 100,InetAddress.getByName(this.getIP()));
       this.Port = this._ServerSocket.getLocalPort();
    }

 public ServerConnection( String IP,int port) throws UnknownHostException, IOException {
        this.IP = IP;
        this.Port=port;
        this._ServerSocket = new ServerSocket(this.Port, 100,InetAddress.getByName(this.getIP()));
        this.Port = this._ServerSocket.getLocalPort();
    }

    public String getIP() {
        return IP;
    }

    public int getPort() {
        return Port;
    }

    public ServerSocket getServerSocket() {
        return _ServerSocket;
    }

    public Socket getActiveSocket() {
        return ActiveSocket;
    }


    public void setIP(String IP) {
        this.IP = IP;
    }

    public void setPort(int Port) {
        this.Port = Port;
    }

    public void setServerSocket(ServerSocket _ServerSocket) {
        this._ServerSocket = _ServerSocket;
    }

    public void setActiveSocket(Socket ActiveSocket) {
        this.ActiveSocket = ActiveSocket;
    }


    

    
    @Override
    public Message WaitMessage() throws IOException{
       if(this.ActiveSocket==null){
           this.ActiveSocket = this._ServerSocket.accept();
       }
            
       BufferedReader socketReader = new BufferedReader(new InputStreamReader(this.ActiveSocket.getInputStream()));
       String inMsg = socketReader.readLine();
       Message message = Message.fromJson(inMsg);
       this.ActiveSocket.close();
       this.ActiveSocket=null;
       return message;
    }
@Override
    public void SendMessage(Message message) throws IOException{
        BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(this.ActiveSocket.getOutputStream()));
        socketWriter.write(message.toJson());
        socketWriter.newLine();
        socketWriter.flush();
    }


}
