/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Blockchain.Citizen;
import Blockchain.Elect;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;
import org.bson.Document;


public class DatabaseRepository {
       public static void main( String args[] ) {  
            DateFormat df = new SimpleDateFormat("HH:mm:ss");
            
            Date dateobj = new Date();
            System.out.println(df.format(dateobj));
       
            ElectRepository ER = new ElectRepository();
         
            Elect e1=new Elect(2,"Hillary","Clinton","Democratic");
            Elect e2=new Elect(3,"Gary","Johnson","Libertarian");
            Elect e3=new Elect(4,"Jill","Stein","Green");
            Elect e4=new Elect(5,"Darrell","Castle","Constitution");
            ER.Add(e1);ER.Add(e2);ER.Add(e3);ER.Add(e4);
            Date dateobj2 = new Date();
            System.out.println(df.format(dateobj2));
            
   } 
}
 