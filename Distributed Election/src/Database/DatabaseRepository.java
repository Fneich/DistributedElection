/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Blockchain.Citizen;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;
import org.bson.Document;


public class DatabaseRepository {
       public static void main( String args[] ) {  
           DateFormat df = new SimpleDateFormat("HH:mm:ss");
            Date dateobj = new Date();
            System.out.println(df.format(dateobj));
       
      CitizenRepository CR = new CitizenRepository();
      
     
      Citizen c = new Citizen(UUID.fromString("d7b903fc-5029-45e1-8dc0-3fa3181cd0ba"),"AFPO","Mohamad","Fneich",new Date(1990,11,8),1003);
      CR.Delete(c);
      System.out.println(CR.getAll().size());
      Date dateobj2 = new Date();
      System.out.println(df.format(dateobj2));
   } 
}
