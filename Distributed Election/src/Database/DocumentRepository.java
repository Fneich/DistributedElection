/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Blockchain.Citizen;
import com.mongodb.BasicDBObject;
import java.util.List;

/**
 *
 * @author Fneich
 */
public class DocumentRepository<T> {
    
    private String Document;
    private Class<T> Type;
    public DocumentRepository(Class<T> type,String Document) {
        this.Document = Document;
        this.Type = type;
    }
    
    
     public  List<T> getAll(){       
        MasterRepository<T> mr = new MasterRepository<T>(Type,Document);
         return mr.getAll();
    }
    
    public  List<T> Search(Parameter... parameters){       
        BasicDBObject query = new BasicDBObject();
        for(Parameter p : parameters){
        query.append(p.getKey(), p.getValue());
        
        }
        MasterRepository<T> mr = new MasterRepository<T>(Type,Document);
        return mr.Search(query);
    }

    public void Add(T c) {
       MasterRepository<T> mr = new MasterRepository<T>(Type,Document);
       mr.Add(c);
    }

   public void Update(T c,String Id) {
       MasterRepository<T> mr = new MasterRepository<T>(Type,Document);
       mr.Update(c,Id);
    }
 public void Delete(T c,String Id){
        MasterRepository<T> mr = new MasterRepository<T>(Type,Document);      
	mr.Delete(Id);
    }
}
