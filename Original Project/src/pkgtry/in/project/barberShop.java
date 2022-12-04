/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgtry.in.project;

import static java.lang.Thread.sleep;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hathout
 */
public class barberShop {
    int totalHairCuts = 0 ; // number of customers
    int lostCustomer = 0;
    int numberOfChairs;
    Barber barber;
    int numOfBarbers;
    int availableBarbers;
    List<Customer> listCustomer;
    
    barberShop(int numOfBarbers , int numberOfChairs){
        
        this.numberOfChairs = numberOfChairs;
        this.numOfBarbers = numOfBarbers;
        this.availableBarbers = numOfBarbers;
        this.listCustomer = new LinkedList<>();
    }
    
    public synchronized void finishOrNot(){
     if(availableBarbers == numOfBarbers && listCustomer.size() == 0){
                   listCustomer.notify();
                  }
    }
    
    
    public void cutHair(int barberID) throws InterruptedException{
        
        Customer customer ;
        
        synchronized(listCustomer){
            while(listCustomer.size() == 0){
                System.out.println("Barber " +barberID +" is waiting for a customer " );
                System.out.println("Barber "+ barberID + " sleeps in his chair");
                try {
                    
                    finishOrNot();
                    listCustomer.wait(); // wait for a customer to arrive and notify to complete the rest of the code 
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(barberShop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }   
                 
                 customer = (Customer)((LinkedList<?>)listCustomer).poll();	//takes the first customer from the head of the list for haircut
                 System.out.println("customer "+customer.customer_id +" Arrive and wake up the barber " + barberID );
            
        }
        availableBarbers--;
        System.out.println("Barber "+barberID+ " is cutting " + customer.customer_id + " now..");
        Thread.sleep(5000);
        totalHairCuts++;
        
    
    }
    
    
    

    public void add(Customer customer){ // add customer to the customer list in case of customers <= number of chairs
        synchronized(listCustomer){
//        System.out.println("Customer ",customer.);
        if(listCustomer.size()==numberOfChairs){
            lostCustomer++;
            
        }
        else if(availableBarbers > 0){
            ((LinkedList<Customer>)listCustomer).offer(customer); // add cutomer to the last position of the list
            listCustomer.notify(); // notify must be put in a synchronized block
            
        }
        else{ // barbers are busy and there are empty chairs in the shop
            System.out.println("Barbers are busy");
            System.out.println("Customer " + customer.customer_id + " takes a chair and wait for his turn");
            ((LinkedList<Customer>)listCustomer).offer(customer); // add cutomer to the last position of the list
            
        }
    }
    
}}
