/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blockchain;

import java.util.Objects;



public class Vote {
    
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

    
  




    
}
