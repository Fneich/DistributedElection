/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blockchain;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Fneich
 */
public class Citizen implements ICitizen {
    private UUID Id ;
    private String FirstName;
    private String LastName;
    private Date BirthDate;
    private int PostalCode;
    private int VoterId;
    private Boolean CanVoted;
    private Boolean Voted;

    public Citizen(UUID Id, String FirstName, String LastName, Date BirthDate, int PostalCode) {
        this.Id = Id;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.BirthDate = BirthDate;
        this.PostalCode = PostalCode;
        this.Voted=false;
        this.CanVoted=true;
    }

    public void setCanVoted(Boolean CanVoted) {
        this.CanVoted = CanVoted;
    }

    public Boolean getCanVoted() {
        return CanVoted;
    }

    public void setVoted(Boolean Voted) {
        this.Voted = Voted;
    }

    public Boolean getVoted() {
        return Voted;
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

    public int getVoterId() {
        return VoterId;
    }

    public void setId(UUID Id) {
        this.Id = Id;
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

    public void setVoterId(int VoterId) {
        this.VoterId = VoterId;
    }

    @Override
    public Boolean CanVoting(Voter voter) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Boolean result = false;
        if(this.VoterId!=0 && voter.getUuid()==this.Id && voter.getFirstName()==this.FirstName && voter.getLastName()==this.LastName)
        {result =true;}
        
        return result;
    }

    @Override
    public int VotingRegister(Voter voter) {
        int result=0;
        if(this.CanVoting(voter)){          
            result=this.VoterId;
            this.setVoterId(0);
        }
        return result;
    }

    @Override
    public String toString() {
        return "Citizen{" + "Id=" + Id + ", FirstName=" + FirstName + ", LastName=" + LastName + ", BirthDate=" + BirthDate + ", PostalCode=" + PostalCode + ", VoterId=" + VoterId + ", CanVoted=" + CanVoted + ", Voted=" + Voted + '}';
    }
    
    
}
