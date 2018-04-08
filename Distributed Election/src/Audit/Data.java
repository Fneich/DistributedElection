/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Audit;

import static Audit.Data.CitizensList;

import Blockchain.Citizen;
import Blockchain.Voter;
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
import java.util.UUID;


public class Data {
    
   public static ArrayList<Citizen> CitizensList = new ArrayList<Citizen>();
   
   public static void LoadCitizensData() throws FileNotFoundException{
            Reader reader = new FileReader("Citizens.json");
            Gson gson=new GsonBuilder().setDateFormat("MMM dd, yyyy").create();
           java.lang.reflect.Type listType = new TypeToken<ArrayList<Citizen>>(){}.getType();
            CitizensList= gson.fromJson(reader,listType);
   }
   public static void SaveCitizensData() throws FileNotFoundException, IOException{

   Gson gson=new Gson();
   String data=gson.toJson(CitizensList);
   BufferedWriter out = new BufferedWriter(new FileWriter("Citizens.json"));
    out.write(data);
    out.flush();
    out.close();
   }
   

   public static void GenerateVoterId()
   {
       
       int x = 1000000001;
       for(Citizen c:CitizensList){
       c.setVoterId(x);
       c.setVoted(Boolean.FALSE);
       x++;
       }
   }
   
   
   public static int getVotingId(Voter voter){
        Citizen citizen=null;
       for(Citizen c:CitizensList){
       if(c.getId().equals(voter.getUuid()))
       {
           citizen = c;
           break;
       }
       }
       System.out.println(citizen.toString());
       if(citizen!=null && citizen.getPassword().equals(voter.getPassword()) && citizen.getFirstName().equals(voter.getFirstName()) && citizen.getLastName().equals(voter.getLastName()))
       {
           System.out.println(citizen.getVoterId());
           return citizen.getVoterId();
       }
           return 0;
        }
   
}
