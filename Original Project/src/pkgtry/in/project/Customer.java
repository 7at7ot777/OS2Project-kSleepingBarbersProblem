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
public class Customer extends Thread {

    int customer_id;
    barberShop shop;
    Customer(int customer_id, barberShop shop){
        this.customer_id = customer_id;
        this.shop = shop;
        
    }


    @Override
    public void run(){

       goForHairCut();
    }
    // its' syncronized method to allow only 1 thread to be added at a time to prevent race condition
    public synchronized void goForHairCut(){
        shop.add(this);
    }
}
        
    
    

