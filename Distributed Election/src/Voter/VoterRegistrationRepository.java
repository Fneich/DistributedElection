/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Voter;

import Blockchain.Voter;
import Communications.Message;
import Communications.Message.MessageKey;
import Communications.Message.MessageSide;
import Encryption.AsymetricEncryption;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
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

    public VoterRegistrationRepository(Voter voter) {
        this.voter = voter;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }
    
    public int Register() throws IOException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException{
    Socket socket = new Socket("localhost", 12900);
    System.out.println("Started client  socket at " + socket.getLocalSocketAddress());
    BufferedReader socketReader = new BufferedReader(new InputStreamReader(
        socket.getInputStream()));
    BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(
        socket.getOutputStream()));
    BufferedReader consoleReader = new BufferedReader(
        new InputStreamReader(System.in));
     Message RegisterMessage = new Message(MessageKey.Regitration,MessageSide.Voter,"");
    socketWriter.write(RegisterMessage.toJson());
    socketWriter.flush();
    System.out.println("I send a registration");
    String outMsg = null;
    String Mykey = socketReader.readLine();
    System.out.println("I Receve My Key");
    Message MyKey = Message.fromJson(Mykey);
    AsymetricEncryption AE = new AsymetricEncryption();
    String MyinfoEncrypted=AE.encryptText(voter.toJson(), MyKey.getValue().getBytes());
    Message Myinfo = new Message(MessageKey.Information,MessageSide.Voter,MyinfoEncrypted);
    socketWriter.write(Myinfo.toJson());
    socketWriter.flush();
    AsymetricEncryption AE2 = new AsymetricEncryption();
    Message YourKey = new Message(MessageKey.PublicKey,MessageSide.Voter,AE2.getGenerateKeys().getPublicKey().toString());
    socketWriter.write(YourKey.toJson());
    socketWriter.flush(); 
    String vid = socketReader.readLine(); 
    Message votingMessageid = Message.fromJson(vid);
    int VotingId =Integer.parseInt( AE2.decryptText(votingMessageid.getValue()));
    socket.close();
    
    return VotingId;
    }
    
}
