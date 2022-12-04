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
     
    Barber(int id , barberShop shop){ 
        this.barberID = id; 
        this.shop = shop; 
         
    } 
     
     
    @Override 
    public void run(){ 
        while(true){ 
            try { 
                shop.cutHair(this.barberID); 
            } catch (InterruptedException ex) { 
                Logger.getLogger(Barber.class.getName()).log(Level.SEVERE, null, ex); 
            } 
            
    } 
    } 
}