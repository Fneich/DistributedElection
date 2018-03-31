/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Audit;

import Blockchain.ICitizen;
import Blockchain.Citizen;
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
   public static void A() throws FileNotFoundException, IOException{

       CitizensList.add(new Citizen(UUID.randomUUID(),"ggg","dgd",new Date(1,12,1990),23));
CitizensList.add(new Citizen(UUID.randomUUID(),"fsdf","asfdad",new Date(1,12,1990),23));
CitizensList.add(new Citizen(UUID.randomUUID(),"dfgdg","ggg",new Date(1,12,1990),23));
   
   Gson gson=new Gson();
   String data=gson.toJson(CitizensList);
   System.out.println(data);
   BufferedWriter out = new BufferedWriter(new FileWriter("Citizens.txt"));
    out.write(data);
    out.flush();
    out.close();
   }
   
   public static void B() throws FileNotFoundException, IOException{
       Reader reader = new FileReader("Citizens2.json");

           Gson gson=new GsonBuilder().setDateFormat("MMM dd, yyyy").create();
           java.lang.reflect.Type listType = new TypeToken<ArrayList<Citizen>>(){}.getType();
            ArrayList<Citizen> list= gson.fromJson(reader,listType);
            System.out.println(list.size());
   }
   public static void GenerateVoterId()
   {
       
       int x = 1000000001;
       for(Citizen c:CitizensList){
       c.setVoterId(x);
       c.setVoted(Boolean.TRUE);
       x++;
       }
   }
}
