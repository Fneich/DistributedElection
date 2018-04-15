/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Blockchain.Citizen;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Fneich
 */
public class CitizenRepository {


    public  List<Citizen> getAll(){       
        MasterRepository<Citizen> mr = new MasterRepository<Citizen>(Citizen.class,"Citizen");
         return mr.getAll();
    }


    public void Add(Citizen c) {
       // MongoCollection<Document> collection = MongodbConnection.db.Database.getCollection(this.Table);
        
        //Document document1 = Document.parse(c.toJson());
       // Document document = new Document("Id", c.getId())
     // .append("Password", c.getPassword())          
     // .append("FirstName", c.getFirstName())
     // .append("LastName", c.getLastName()) 
     // .append("BirthDate", c.getBirthDate()) 
      //.append("PostalCode",c.getPostalCode()) 
      //.append("by", "tutorials point");  
      //collection.insertOne(document1);
       MasterRepository<Citizen> mr = new MasterRepository<Citizen>(Citizen.class,"Citizen");
       mr.Add(c);
    }

   

    
}
