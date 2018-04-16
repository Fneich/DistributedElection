/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blockchain;


import com.google.gson.Gson;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Fneich
 */
public class Citizen {
    private UUID Id ;
    private int Password;
    private String FirstName;
    private String LastName;
    private Date BirthDate;
    private int PostalCode;
    private String VoterId;


    public Citizen(UUID Id,int password, String FirstName, String LastName, Date BirthDate, int PostalCode) {
        this.Id = Id;
        this.Password=password;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.BirthDate = BirthDate;
        this.PostalCode = PostalCode;
        
    }

    public UUID getId() {
        return Id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public int getPostalCode() {
        return PostalCode;
    }

    public String getVoterId() {
        return VoterId;
    }
 public int getPassword() {
        return Password;
    }
    public void setId(UUID Id) {
        this.Id = Id;
    }

    public void setPassword(int Password) {
        this.Password = Password;
    }
    

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public void setBirthDate(Date BirthDate) {
        this.BirthDate = BirthDate;
    }

    public void setPostalCode(int PostalCode) {
        this.PostalCode = PostalCode;
    }

    public void setVoterId(String id) {
        this.VoterId = id;
    }


  public static Citizen fromJson(String message){
    Gson g=new Gson();
    return g.fromJson(message, Citizen.class);
    }
     public  String toJson(){
    Gson g=new Gson();
    return g.toJson(this);
    }

}
