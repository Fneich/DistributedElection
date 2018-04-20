

package Voter;

import Blockchain.Elect;
import Blockchain.Voter;
import Communications.Connecter;
import Communications.Connection;
import Communications.Message;
import Communications.Message.MessageKey;
import Communications.Message.MessageSide;
import Communications.Site;
import Encryption.AsymetricEncryption;
import PollingStation.PollingAudit;
import PollingStation.PollingMaster;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Fneich
 */
public class VoterVotingRepository {
    private String VotingId ;
    private Connection connection;



    public Connection getConnection() {
        return connection;
    }

    public void setVotingId(String VotingId) {
        this.VotingId = VotingId;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public List<Elect> getElects() throws IOException, InterruptedException{
        Random r=new Random();
        int randomNum = r.nextInt(VoterProgram.Pollings.size());
        Site s= VoterProgram.Pollings.get(randomNum);
        s.connect();
        Message message = new Message(MessageKey.Elects,MessageSide.Voter,"");
        this.connection=s.getConnection();
        this.connection .SendMessage(message);
        System.out.println("moudi1");
        Message elects =this.connection .WaitMessage();
        if(elects.getKey()==MessageKey.Elects && elects.getSide()==MessageSide.Polling){
            
            String ElectsString = elects.getValue();
            Gson g =new Gson();
            List<Elect> Elects =  g.fromJson(ElectsString,ArrayList.class);
            return Elects;
        }
        //PollingAudit pollingAudit= new PollingAudit(VoterProgram.Pollings.get(randomNum).getConnection()); 
        return null;
    }
    
        public String getVotingId(String voterId) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException, InterruptedException{

    Connecter connecter = new Connecter("","localhost",Message.MessageSide.Voter);
    connecter.ConnectTo(10000);
    Connection connection=null;
    System.out.println("I send a connection");
    while(true){
    if(connecter.getConnection()!=null){connection=connecter.getConnection(); break;}
    }
    System.out.println("I receve an accept");
    Message registermessage= new Message(Message.MessageKey.Regitration,Message.MessageSide.Voter,"");
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
    /*String MyinfoEncrypted=AE.encryptText(voter.toJson(), b);
    Message Myinfo = new Message(Message.MessageKey.Information,Message.MessageSide.Voter,MyinfoEncrypted);
    connection.SendMessage(Myinfo);
    AsymetricEncryption AE2 = new AsymetricEncryption();
    Message YourKey = new Message(Message.MessageKey.PublicKey,Message.MessageSide.Voter,g.toJson( AE2.getGenerateKeys().getPublicKey().getEncoded()));
   connection.SendMessage(YourKey);
    Message votingMessageid = connection.WaitMessage();
    String VotingId =AE2.decryptText(votingMessageid.getValue()); 
    connection.SendMessage(new Message(Message.MessageKey.Disconnect,Message.MessageSide.Voter,""));*/
    
    return VotingId;
    }
    
}
