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
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Fneich
 */
public interface SiteConnection {
    
    public String getIP();
    public int getPort();
    public void setIP(String IP);
    public void setPort(int Port);
    public Message WaitMessage()throws IOException;
    public void SendMessage(Message message)throws IOException;

}
