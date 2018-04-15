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
    private String Password;
    private String FirstName;
    private String LastName;
    private Date BirthDate;
    private int PostalCode;
    private int ElectionId;
    private String VoterId;


    public Citizen(UUID Id,String password, String FirstName, String LastName, Date BirthDate, int PostalCode) {
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
 public String getPassword() {
        return Password;
    }
    public void setId(UUID Id) {
        this.Id = Id;
    }

    public void setPassword(String Password) {
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

    public int getElectionId() {
        return ElectionId;
    }

    public void setElectionId(int ElectionId) {
        this.ElectionId = ElectionId;
    }

    public void setVoterId() {
        this.VoterId = HashUtil.applySha256(this.Id.toString() + this.FirstName+this.LastName+this.Password+this.BirthDate+ String.valueOf(this.PostalCode) + String.valueOf(this.ElectionId));
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
