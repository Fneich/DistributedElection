/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communications;

import Communications.Message.MessageSide;
import java.io.IOException;

public class Site {
    private String Ip;
    private int port;
    private MessageSide messageside;
    private MessageSide Anothermessageside;
    private Connection connection;

    public Site(String Ip, int port, MessageSide messageside,MessageSide anothermessageside, Connection connection) {
        this.Ip = Ip;
        this.port = port;
        this.messageside = messageside;
        this.Anothermessageside=anothermessageside;
        this.connection = connection;
    }

    public MessageSide getAnothermessageside() {
        return Anothermessageside;
    }
    
    public void setAnothermessageside(MessageSide Anothermessageside) {
        this.Anothermessageside = Anothermessageside;
    }
    public void connect() throws IOException{
         Connecter connecter = new Connecter("",this.Ip,this.messageside);
         connecter.ConnectTo(this.port);
         this.connection=connecter.getConnection();
    }

    public String getIp() {
        return Ip;
    }

    public int getPort() {
        return port;
    }

    public MessageSide getMessageside() {
        return messageside;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setIp(String Ip) {
        this.Ip = Ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setMessageside(MessageSide messageside) {
        this.messageside = messageside;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    
}
