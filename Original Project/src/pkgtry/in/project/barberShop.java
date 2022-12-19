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
    int totalHairCuts = 0 ; // used to determine if starvation has happend or not 
    int numOfBarbers; // used in determinning if all threads are finish all customers 
    int availableBarbers; //used to determine if there are sleeping barbers to notify them and cutting customer hair
    List<Customer> listCustomer; //used to add 
    
    barberShop(int numOfBarbers ){
        this.numOfBarbers = numOfBarbers;
        this.availableBarbers = numOfBarbers;
        this.listCustomer = new LinkedList<>();
    }
//    used in inturrption threads 
//    public int finishOrNot(){
//     if(availableBarbers == numOfBarbers){
//         try {
//             Thread.sleep(5000);
//         } catch (InterruptedException ex) {
//             Logger.getLogger(barberShop.class.getName()).log(Level.SEVERE, null, ex);
//         }
//         if(availableBarbers == numOfBarbers){
//             synchronized(listCustomer){
//              listCustomer.notify();
//              numOfBarbers--;
//              availableBarbers--;
//              return 1;
//             }
//         }
//         
//                  
//                  }
//     return 0;
//    }
    
    
    public int cutHair(int barberID) throws InterruptedException{
        
        Customer customer ;
        
        synchronized(listCustomer){
            while(listCustomer.size() == 0){
                System.out.println("####Barber " +barberID +" is waiting for a customer#### " );
                System.out.println("####Barber "+ barberID + " sleeps in his chair####");
                try {
                    
//                  int checkFlag =  finishOrNot();
//                   if(checkFlag == 1)
//                   {    System.out.println("called by "+barberID);
//                       return 1;
//                   }
                    listCustomer.wait(); // wait for a customer to arrive and notify to complete the rest of the code 
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(barberShop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }   
                 
                 customer = (Customer)((LinkedList<?>)listCustomer).poll();	//takes the first customer from the head of the list for haircut
                 System.out.println("@@@@ customer "+customer.customer_id +" Arrive and wake up the barber " + barberID+" @@@@" );
            availableBarbers--;
        }
        
        System.out.println("--- Barber "+barberID+ " is cutting " + customer.customer_id + " ---");
        Thread.sleep(5000);
        System.out.println("--- Barber "+barberID+ " finishes " + customer.customer_id + " ---");
        availableBarbers++;
        totalHairCuts++;
        
    return 0;
    }
    
    
    

    public void add(Customer customer){ // add customer to the customer list in case of customers <= number of chairs
        synchronized(listCustomer){
//        System.out.println("Customer ",customer.);

        if(availableBarbers > 0){
            //if there are available customers //pop from the list and notify barber thread
            ((LinkedList<Customer>)listCustomer).offer(customer); // add cutomer to the last position of the list
            listCustomer.notify(); // notify must be put in a synchronized block
        }
        else{
             System.out.println("Barbers are busy");
            System.out.println("Customer " + customer.customer_id + " takes a chair and wait for his turn");
            ((LinkedList<Customer>)listCustomer).offer(customer); // add cutomer to the last position of the list
        }

//        if(listCustomer.size()==numberOfChairs){
//            lostCustomer++;
//            
//        }
//        else if(availableBarbers > 0){
//            ((LinkedList<Customer>)listCustomer).offer(customer); // add cutomer to the last position of the list
//            listCustomer.notify(); // notify must be put in a synchronized block
//            
//        }
//        else{ // barbers are busy and there are empty chairs in the shop
//            System.out.println("Barbers are busy");
//            System.out.println("Customer " + customer.customer_id + " takes a chair and wait for his turn");
//            ((LinkedList<Customer>)listCustomer).offer(customer); // add cutomer to the last position of the list
//            
//        }
    }
    
}}
