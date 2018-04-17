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


/**
 *
 * @author Fneich
 */
public class Connection {
    private String IP;
    private int Port;
    private int MasterPort;
    private ServerSocket _ServerSocket;
    private Socket ActiveSocket;
    private Boolean Opened;
    private MessageSide messageSide;

    public Connection( String IP,int port,MessageSide messageside) throws UnknownHostException, IOException {
       
        this.IP = IP;
        this.Port=port;
        this.Opened=false;
        this.messageSide=messageside;
    }

    public void setMasterPort(int MasterPort) {
        this.MasterPort = MasterPort;
    }

    public int getMasterPort() {
        return MasterPort;
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

    public Boolean getOpened() {
        return Opened;
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

    public void OpenConnection() throws UnknownHostException, IOException{
        if(!this.Opened){
            this._ServerSocket = new ServerSocket(this.getPort(), 100,InetAddress.getByName(this.getIP()));
            this.Opened=true;
        }
    }
    
    public void CloseConnection() throws IOException{
        if(this.Opened){
            
        this._ServerSocket.close();
        this._ServerSocket=null;
        this.Opened=false;
        }
    }
    
    public Message WaitMessage() throws IOException{
        if(this.Opened){
            
       this.ActiveSocket = this._ServerSocket.accept();
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
        Socket socket = new Socket(this.IP, this.Port);
        BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        socketWriter.write(message.toJson());
        socketWriter.newLine();
        socketWriter.flush();
    }
        public void Connect() throws IOException{
        Socket socket = new Socket(this.IP, this.MasterPort);
        Message message=new Message(MessageKey.Connect,this.messageSide,"");
        BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        socketWriter.write(message.toJson());
        socketWriter.newLine();
        socketWriter.flush();
    }
        
       public void WaitAccept() throws IOException{
        
       ServerSocket _ServerSocket1=new ServerSocket(this.MasterPort, 100,InetAddress.getByName(this.getIP()));
       Socket ActiveSocket2=_ServerSocket1.accept();
       BufferedReader socketReader = new BufferedReader(new InputStreamReader(this.ActiveSocket.getInputStream()));
       String inMsg = socketReader.readLine();
       Message message = Message.fromJson(inMsg);
       if(message.getKey()==MessageKey.Accept){
           this.Port=Integer.parseInt(message.getValue());
       }
    }
    
}
