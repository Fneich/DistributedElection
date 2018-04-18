/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communications;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Fneich
 */
public class Sender {
    private String Ip;
    private int Port;
    private Socket Socket;

    public Sender(String Ip, int Port) throws IOException {
        this.Ip = Ip;
        this.Port = Port;
        this.Socket = new Socket(this.Ip,this.Port);
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
    
       public void SendMessage(Message message) throws IOException{

        BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(this.Socket.getOutputStream()));
        socketWriter.write(message.toJson());
        socketWriter.newLine();
        socketWriter.flush();

    }
}
