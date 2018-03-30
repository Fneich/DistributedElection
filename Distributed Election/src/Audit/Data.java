/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Audit;

import Blockchain.ICitizen;
import Blockchain.Citizen;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;


public class Data {
    
   public static ArrayList<ICitizen> CitizensList = new ArrayList<ICitizen>();
   public static void A(){
   CitizensList.add(new Citizen(UUID.randomUUID(),"Mohamad Ali","Fneich",new Date(90,11,8),1002));
   CitizensList.add(new Citizen(UUID.randomUUID(),"Sara","Kassir",new Date(93,10,29),1002));
   Gson gson=new Gson();
   System.out.println(gson.toJson(CitizensList));
   }
}
