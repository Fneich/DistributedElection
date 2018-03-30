/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Audit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author Fneich
 */
public class Audit implements Runnable{
    private Socket socket;
    private Thread thread;
    @Override
    public void run() {
      try{
      BufferedReader socketReader = null;
      BufferedWriter socketWriter = null;
      socketReader = new BufferedReader(new InputStreamReader(
          socket.getInputStream()));
      socketWriter = new BufferedWriter(new OutputStreamWriter(
          socket.getOutputStream()));

      String inMsg = null;
      while ((inMsg = socketReader.readLine()) != null) {
        System.out.println("Received from  client: " + inMsg);
        if (inMsg.equalsIgnoreCase("bye")) {
        break;
      }
        String outMsg = inMsg;
        socketWriter.write(outMsg);
        socketWriter.write("\n");
        socketWriter.flush();
      }
      socket.close();
      this.thread.join();
    }catch(Exception e){
      e.printStackTrace();
    }
    }

    public Audit(Socket socket) {
        this.socket = socket;
        this.thread =new Thread(this);
        this.thread.start();
    }
    
}
