/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blockchain;

import java.util.Objects;



public class Vote {
    
private String VoteId;
private Elect Elect;

public Vote(String voteId,Elect elect){
this.VoteId=voteId;
this.Elect=elect;
}

    public String getVoteId() {
        return VoteId;
    }

    public Elect getElect() {
        return Elect;
    }

    public void setVoteId(String VoteId) {
        this.VoteId = VoteId;
    }

    public void setElect(Elect Elect) {
        this.Elect = Elect;
    }

    
  

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.VoteId);
        hash = 17 * hash + Objects.hashCode(this.Elect);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vote other = (Vote) obj;
        return true;
    }
    
}
