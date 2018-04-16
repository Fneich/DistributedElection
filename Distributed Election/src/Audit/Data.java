/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Audit;

import static Audit.Data.CitizensList;

import Blockchain.Citizen;
import Blockchain.HashUtil;
import Blockchain.Voter;
import Database.CitizenRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Reader;
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class Data {
    
   public static List<Citizen> CitizensList = new ArrayList<Citizen>();
   public static String ElectionId="Election_2018";
   
   public static void LoadCitizensData() throws FileNotFoundException{
       CitizenRepository CR = new CitizenRepository();
       CitizensList = CR.getAll();
   }
   public static void SaveCitizensData() throws FileNotFoundException, IOException{
       CitizenRepository CR = new CitizenRepository();
       for(Citizen c:CitizensList){
           CR.Update(c, c.getId().toString());
       }
   }
   

   public static void GenerateVoterId()
   {     
       for(Citizen c:CitizensList){
           String id= HashUtil.applySha256(Data.ElectionId + c.getId().toString() + c.getFirstName()+c.getLastName()+c.getPassword()+c.getBirthDate()+ String.valueOf(c.getPostalCode()));
           c.setVoterId(id);
       }
   }
   
   
   public static String getVotingId(Voter voter){
        Citizen citizen=null;
       for(Citizen c:CitizensList){
       if(c.getId().equals(voter.getUuid()))
       {
           citizen = c;
           break;
       }
       }
       System.out.println(citizen.toString());
       if(citizen!=null && citizen.getPassword()==voter.getPassword() && citizen.getFirstName().equals(voter.getFirstName()) && citizen.getLastName().equals(voter.getLastName()))
       {
           System.out.println(citizen.getVoterId());
           return citizen.getVoterId();
       }
           return null;
        }
   
}
