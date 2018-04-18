/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communications;

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

    public Connection(String Id, String IP, MessageSide Side) throws IOException {
        this.Id = Id;
        this.IP = IP;
        this.Side = Side;
        this.Recever = new Recever(this.IP);
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
  
  public Message WaitMessage() throws IOException{
     return this.Recever.WaitMessage();
  }
  
  public int getReceverPort(){
    return this.Recever.getPort();
  }
  
  
}
