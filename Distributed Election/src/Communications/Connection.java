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
    private int Port;
    private MessageSide Side;
    private SiteConnection SiteConnection;

    public void setIP(String IP) {
        this.IP = IP;
    }

    public void setSiteConnection(SiteConnection SiteConnection) {
        this.SiteConnection = SiteConnection;
    }

    public String getIP() {
        return IP;
    }

    public SiteConnection getSiteConnection() {
        return SiteConnection;
    }


    public Connection(String Id,String ip, MessageSide Side, SiteConnection siteConnection) {
        this.Id = Id;
        this.Side = Side;
        this.SiteConnection =siteConnection;
        this.IP=ip;
    }
 public Connection(String Id,String ip, MessageSide Side) throws IOException {
     this.IP=ip;
        this.Id = Id;
        this.Side = Side;
         this.SiteConnection = new ServerConnection(this.IP);
    }
  public Connection(String Id,String ip,int port, MessageSide Side) throws IOException {
         this.IP=ip;
        this.Port=port;
        this.Id = Id;
        this.Side = Side;
         this.SiteConnection = new ClientConnection(this.IP,this.Port);
    }
    public String getId() {
        return Id;
    }

    public MessageSide getSide() {
        return Side;
    }
    public void setId(String Id) {
        this.Id = Id;
    }

    public void setSide(MessageSide Side) {
        this.Side = Side;
    }
    public static Connection CreateConnectionAsServer(String Id,String ip, MessageSide Side) throws IOException{
          return new Connection(Id,ip,Side);
    }
public static Connection CreateConnectionAsClient(String Id,String ip,int port, MessageSide Side) throws IOException{
          return new Connection(Id,ip,port,Side);
    }
}
