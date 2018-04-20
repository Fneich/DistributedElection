/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Voter;

import Blockchain.Voter;
import Communications.Connecter;
import Communications.Connection;
import Communications.Message;
import Communications.Message.MessageKey;
import Communications.Message.MessageSide;
import Encryption.AsymetricEncryption;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Fneich
 */
public class VoterRegistrationRepository {
    private Voter voter;
    private Connection connection;

    public VoterRegistrationRepository(Voter voter) {
        this.voter = voter;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }
    
    public String getVotingId() throws IOException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException, InterruptedException{

    Connecter connecter = new Connecter("","localhost",MessageSide.Voter);
    connecter.ConnectTo(10000);
    Connection connection=null;
    System.out.println("I send a connection");
    while(true){
    if(connecter.getConnection()!=null){connection=connecter.getConnection(); break;}
    }
    System.out.println("I receve an accept");
    Message registermessage= new Message(MessageKey.Regitration,MessageSide.Voter,"");
    connection.SendMessage(registermessage);
    System.out.println("I send a registration");
    System.out.println("I wait my key");
    Message MyKey= connection.WaitMessage();          
    AsymetricEncryption AE = new AsymetricEncryption();
    System.out.println(MyKey.getValue());
    Gson g =new Gson();
    java.lang.reflect.Type listType = new TypeToken<byte[]>(){}.getType();
    System.out.println("???:"+MyKey.getValue());
    byte[] b =g.fromJson(MyKey.getValue(),listType);
    String MyinfoEncrypted=AE.encryptText(voter.toJson(), b);
    Message Myinfo = new Message(MessageKey.Information,MessageSide.Voter,MyinfoEncrypted);
    connection.SendMessage(Myinfo);
    AsymetricEncryption AE2 = new AsymetricEncryption();
    Message YourKey = new Message(MessageKey.PublicKey,MessageSide.Voter,g.toJson( AE2.getGenerateKeys().getPublicKey().getEncoded()));
   connection.SendMessage(YourKey);
    Message votingMessageid = connection.WaitMessage();
    String VotingId =AE2.decryptText(votingMessageid.getValue()); 
    connection.SendMessage(new Message(MessageKey.Disconnect,MessageSide.Voter,""));
    
    return VotingId;
    }
    
}
