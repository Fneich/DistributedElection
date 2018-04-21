/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blockchain;

import com.google.gson.Gson;
import java.util.Objects;



public class Vote {

    public static Vote fromJson(String message) {
           Gson g=new Gson();
    return g.fromJson(message, Vote.class);
    }
    
private String VoterId;
private int ElectId;

public Vote(String voterId,int electid){
this.VoterId=voterId;
this.ElectId=electid;
}

    public String getVoteId() {
        return VoterId;
    }

    public int getElect() {
        return ElectId;
    }

    public void setVoteId(String VoterId) {
        this.VoterId = VoterId;
    }

    public void setElect(int electid) {
        this.ElectId = electid;
    }

    
   public String toString(){
     Gson g =new Gson();
     return g.toJson(this);
     
 }




    
}
