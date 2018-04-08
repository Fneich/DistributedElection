/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blockchain;
import com.google.gson.Gson;
import java.util.UUID;
/**
 *
 * @author Fneich
 */
public class Voter {
    private UUID Id ;
    private String Password; 
    private String FirstName;
    private String LastName;
    

    public Voter(UUID Uuid,String password ,String FirstName, String LastName) {
        this.Id = Uuid;
        this.Password = password;
        this.FirstName = FirstName;
        this.LastName = LastName;
    }

    public UUID getUuid() {
        return Id ;
    }

    public void setUuid(UUID Uuid) {
        this.Id  = Uuid;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
    public static Voter fromJson(String message){
    Gson g=new Gson();
    return g.fromJson(message, Voter.class);
    }
     public  String toJson(){
    Gson g=new Gson();
    return g.toJson(this);
    }

  
    
}
