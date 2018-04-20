/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blockchain;

import com.google.gson.Gson;
import java.util.Objects;

/**
 *
 * @author Fneich
 */
public class Elect {
    private int Id;
    private String FirstName;
    private String LastName;
    private String Party;

    public Elect(int Id, String FirstName, String LastName, String Party) {
        this.Id = Id;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Party = Party;
    }

    public int getId() {
        return Id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getParty() {
        return Party;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public void setParty(String Party) {
        this.Party = Party;
    }

 public String toString(){
     Gson g =new Gson();
     return g.toJson(this);
     
 }
  
    public String Represent(){

     return this.Id + "." + this.FirstName + " " + this.LastName + "-" + this.Party;
     
 }
    
    
    
}
