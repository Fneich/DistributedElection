/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Fneich
 */
public class MongodbConnection {
    public static MongodbConnection db = new MongodbConnection("localhost",27017,"Election");
    public String Server;
    public int Port;
    public String DatabaseName;
    public MongoDatabase Database;

    public MongodbConnection(String Server, int Port, String DatabaseName) {
        this.Server = Server;
        this.Port = Port;
        this.DatabaseName = DatabaseName;
        Connect();
    }
    
    public void Connect(){
        MongoClient mongo = new MongoClient( this.Server , this.Port ); 
        this.Database = mongo.getDatabase(this.DatabaseName);
    }
    
}
