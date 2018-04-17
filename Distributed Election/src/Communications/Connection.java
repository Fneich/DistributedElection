/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communications;

import Communications.Message.MessageSide;
import java.io.IOException;

/**
 *
 * @author Fneich
 */
public class Connection {
    
    private String Id;
    private String IP;
    private MessageSide Side;
    private ServerConnection ReceverConnection;
    private ClientConnection SenderConnection;

    public Connection(String Id,String ip, MessageSide Side, ServerConnection ReceverConnection, ClientConnection SenderConnection) {
        this.Id = Id;
        this.Side = Side;
        this.ReceverConnection = ReceverConnection;
        this.SenderConnection = SenderConnection;
        this.IP=ip;
    }
 public Connection(String Id,String ip, MessageSide Side) throws IOException {
     this.IP=ip;
        this.Id = Id;
        this.Side = Side;
        this.ReceverConnection = new ServerConnection(this.IP);
    }
    public String getId() {
        return Id;
    }

    public MessageSide getSide() {
        return Side;
    }

    public ServerConnection getReceverConnection() {
        return ReceverConnection;
    }

    public ClientConnection getSenderConnection() {
        return SenderConnection;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public void setSide(MessageSide Side) {
        this.Side = Side;
    }

    public void setReceverConnection(ServerConnection ReceverConnection) {
        this.ReceverConnection = ReceverConnection;
    }

    public void setSenderConnection(ClientConnection SenderConnection) {
        this.SenderConnection = SenderConnection;
    }
    
    public void CreateSenderConnection(int port) throws IOException{
        this.SenderConnection = new ClientConnection(this.IP,port);
    }
    
}
