/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customerserviceproject;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hathout
 */
public class CustomerService extends Thread {
    int csID; 
    
    Company company;
    
    int flag ;
     
    CustomerService(int id,Company company){ 
        this.csID = id; 
        this.company = company; 
        this.flag = 0;
        
       
         
    } 
     
     
    @Override 
    public void run(){ 
      while(true){
            try { 
                flag = company.MakeCall(this.csID); 
            } catch (InterruptedException ex) { 
                Logger.getLogger(CustomerService.class.getName()).log(Level.SEVERE, null, ex); 
            } 
            
          if(flag == 1)
              break;
    } 
    } 
}
