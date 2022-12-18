/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template 
 */ 
package pkgtry.in.project; 
 
import java.util.logging.Level; 
import java.util.logging.Logger; 
 
/** 
 * 
 * @author Hathout 
 */ 
public class Barber extends Thread { 
    int barberID; 
    
    barberShop shop;
    
    int flag ;
     
    Barber(int id , barberShop shop){ 
        this.barberID = id; //barber number
        this.shop = shop;  // shared object 
        this.flag = 0;  //used to inturrupt the thread
        
       
         
    } 
     
     
    @Override 
    public void run(){ 
      while(true){
            try { 
                flag = shop.cutHair(this.barberID); 
            } catch (InterruptedException ex) { 
                Logger.getLogger(Barber.class.getName()).log(Level.SEVERE, null, ex); 
            } 
            
          if(flag == 1)
              return;
    } 
    } 
}