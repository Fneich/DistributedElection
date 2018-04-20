/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communications;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fneich
 */
public class ReceverRepository implements Runnable {

    private Connection connection;
    private Thread thread;

    public ReceverRepository(Connection connection) {
        this.connection = connection;
        this.thread = new Thread(this);
        this.thread.start();
    }
    
    
    @Override
    public void run() {
        while(true){
            try {
                Message lastmessage=connection.ReceveMessage();
                connection.setLastMessage(lastmessage);
                if(lastmessage.getKey()==Message.MessageKey.Disconnect){break;}
            } catch (IOException ex) {
                Logger.getLogger(ReceverRepository.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
}
