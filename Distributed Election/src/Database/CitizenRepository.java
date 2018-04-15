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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Fneich
 */
public class CitizenRepository extends DocumentRepository<Citizen>{

    public CitizenRepository() {
        super(Citizen.class, "Citizen");
    }

}
