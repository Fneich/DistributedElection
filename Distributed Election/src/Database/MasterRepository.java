/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Blockchain.Citizen;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Fneich
 */
public class MasterRepository<T> {
   private Class<T> type;
   public String Table;

    public MasterRepository(Class<T> type,String Table) {
        this.Table = Table;
        this.type=type;
    }
   
    public  List<T> getAll(){       
        MongoCollection<Document> collection = MongodbConnection.db.Database.getCollection(this.Table);      
        FindIterable<Document> iterDoc = collection.find(); 
        Iterator it = iterDoc.iterator(); 
        ArrayList<T> list = new ArrayList<T>();
         while (it.hasNext()) { 
            Document d=(Document)it.next();
            Gson g=new Gson();
            T t =g.fromJson(d.toJson(), type);     
            list.add(t);
        }
         return list;
    }
     public  List<T> Search(BasicDBObject query){       
        MongoCollection<Document> collection = MongodbConnection.db.Database.getCollection(this.Table);      
        FindIterable<Document> iterDoc = collection.find(query); 
        Iterator it = iterDoc.iterator(); 
        ArrayList<T> list = new ArrayList<T>();
         while (it.hasNext()) { 
            Document d=(Document)it.next();
            Gson g=new Gson();
            T t =g.fromJson(d.toJson(), type);     
            list.add(t);
        }
         return list;
    }
    
    public void Add(T t){
        MongoCollection<Document> collection = MongodbConnection.db.Database.getCollection(this.Table);       
        Gson g=new Gson();
        Document document = Document.parse(g.toJson(t));
        collection.insertOne(document);
    }
    
    public void Update(T t,String Id){
        MongoCollection<Document> collection = MongodbConnection.db.Database.getCollection(this.Table);       
        Bson filter = new Document("Id", Id);
        Gson g=new Gson();
        Bson newValue = Document.parse(g.toJson(t));
	Bson updateOperationDocument = new Document("$set", newValue);
	collection.updateOne(filter, updateOperationDocument);
    }
    
    public void Delete(String Id){
        MongoCollection<Document> collection = MongodbConnection.db.Database.getCollection(this.Table);       
        Bson filter = new Document("Id", Id);
        Gson g=new Gson();
	collection.deleteOne(filter);
    }
    
    
    
}
