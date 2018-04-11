/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blockchain;

import java.util.Objects;



public class Vote {
    
private String VoterId;
private Elect Elect;

public Vote(String voterId,Elect elect){
this.VoterId=voterId;
this.Elect=elect;
}

    public String getVoteId() {
        return VoterId;
    }

    public Elect getElect() {
        return Elect;
    }

    public void setVoteId(String VoterId) {
        this.VoterId = VoterId;
    }

    public void setElect(Elect Elect) {
        this.Elect = Elect;
    }

    
  




    
}
