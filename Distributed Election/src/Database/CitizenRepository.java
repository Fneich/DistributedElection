/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Blockchain.Citizen;
import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Fneich
 */
public class CitizenRepository {


    public  List<Citizen> getAll(){       
        MasterRepository<Citizen> mr = new MasterRepository<Citizen>(Citizen.class,"Citizen");
         return mr.getAll();
    }
    
    public  List<Citizen> Search(List<Parameter> parameters){       
        
        MasterRepository<Citizen> mr = new MasterRepository<Citizen>(Citizen.class,"Citizen");
        return mr.getAll();
    }

    public void Add(Citizen c) {
       MasterRepository<Citizen> mr = new MasterRepository<Citizen>(Citizen.class,"Citizen");
       mr.Add(c);
    }

   public void Update(Citizen c) {
       MasterRepository<Citizen> mr = new MasterRepository<Citizen>(Citizen.class,"Citizen");
       mr.Update(c,c.getId().toString());
    }
 public void Delete(Citizen c){
        MasterRepository<Citizen> mr = new MasterRepository<Citizen>(Citizen.class,"Citizen");      
	mr.Delete(c.getId().toString());
    }
    
}
