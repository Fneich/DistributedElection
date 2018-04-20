/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communications;

import com.google.gson.Gson;

/**
 *
 * @author Fneich
 */
public class Message {
    public enum MessageKey
    {
        Connect, Disconnect, Accept,Reject,PublicKey,Information,Regitration,Result,Vote,Begin,End,Port,Verification,Elects;
    }
     
     public enum MessageSide
     {
         Voter,Polling,Audit;
     }
    
    private MessageKey Key;
    private MessageSide Side;
    private String Value;

    public Message(MessageKey Key, MessageSide Side, String Value) {
        this.Key = Key;
        this.Side = Side;
        this.Value = Value;
    }

    public MessageKey getKey() {
        return Key;
    }

    public MessageSide getSide() {
        return Side;
    }

    public String getValue() {
        return Value;
    }

    public void setKey(MessageKey Key) {
        this.Key = Key;
    }

    public void setSide(MessageSide Side) {
        this.Side = Side;
    }

    public void setValue(String Value) {
        this.Value = Value;
    }

    public String toJson(){
        Gson g=new Gson();
        return g.toJson(this);
    }
    public static Message fromJson(String message){
    Gson g=new Gson();
    return g.fromJson(message, Message.class);
    }
    
}
