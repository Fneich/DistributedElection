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
      System.out.println(CR.Search(new Parameter("PostalCode",1029)).size());
      Date dateobj2 = new Date();
      System.out.println(df.format(dateobj2));
   } 
}
