/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communications;

import Communications.Message.MessageKey;
import Communications.Message.MessageSide;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 *
 * @author Fneich
 */
public class Connection {
    
    private String Id;
    private String IP;
    private MessageSide Side;
    private Recever Recever;
    private Sender Sender;
    private Message LastMessage;
    private ReceverRepository receverRepository ;

    public Connection(String Id, String IP, MessageSide Side) throws IOException {
        this.Id = Id;
        this.IP = IP;
        this.Side = Side;
        this.Recever = new Recever(this.IP);
        this.receverRepository = new ReceverRepository(this);
    }

    public void setLastMessage(Message LastMessage) {
        this.LastMessage = LastMessage;
    }

    public Message getLastMessage() {
        return LastMessage;
    }

    public String getId() {
        return Id;
    }

    public String getIP() {
        return IP;
    }

    public MessageSide getSide() {
        return Side;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public void setSide(MessageSide Side) {
        this.Side = Side;
    }

    public void CreateSender(int port) throws IOException{
    this.Sender = new Sender(this.IP,port);
    }
  public void SendMessage(Message message) throws IOException{
      if(this.Sender!=null){
       this.Sender.SendMessage(message);
      }
    }
  
  public Message ReceveMessage() throws IOException{
     
     Message m =this.Recever.ReceveMessage();
     if(m.getKey()==MessageKey.Disconnect){
         if(this.Sender!=null){
            this.Sender.SendMessage(new Message(MessageKey.Disconnect,this.getSide(),"")); 
         }
     
     
     }
     
     return m;
  }
  
  public Message WaitMessage(){
      while(this.LastMessage==null){System.out.println("wait !!");}
      Message message=this.LastMessage;
      this.LastMessage=null;
      return message;
  }
  
  public int getReceverPort(){
    return this.Recever.getPort();
  }
  
  public void closeConnection(){
      
      
  }
  
}
