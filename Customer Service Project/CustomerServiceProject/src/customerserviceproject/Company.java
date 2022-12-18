/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customerserviceproject;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hathout
 */
public class Company {
        int totalHairCuts = 0 ; // number of customers
//    int lostCustomer = 0;
//    int numberOfChairs;
    int numOfCS;
    int availableBarbers;
    List<Client> listCustomer;
    
    Company(int numOfCS ){
        
//        this.numberOfChairs = numberOfChairs;
        this.numOfCS = numOfCS;
        this.availableBarbers = numOfCS;
        this.listCustomer = new LinkedList<>();
    }
    
 
    
    
    public int MakeCall(int barberID) throws InterruptedException{
        
        Client customer ;
        
        synchronized(listCustomer){
            while(listCustomer.size() == 0){
                System.out.println("####Barber " +barberID +" is waiting for a customer#### " );
                System.out.println("####Barber "+ barberID + " sleeps in his chair####");
                try {
                    
                 
                    listCustomer.wait(); // wait for a customer to arrive and notify to complete the rest of the code 
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);
                }
            }   
                 
                 customer = (Client)((LinkedList<?>)listCustomer).poll();	//takes the first customer from the head of the list for haircut
                 System.out.println("@@@@customer "+customer.customer_id +" Arrive and wake up the barber " + barberID+"@@@@" );
            
        }
        availableBarbers--;
        System.out.println("---Barber "+barberID+ " is cutting " + customer.customer_id + " ---");
        Thread.sleep(5000);
        System.out.println("---Barber "+barberID+ " finishes " + customer.customer_id + " ---");
        totalHairCuts++;
        
    return 0;
    }
    
    
    

    public void add(Client customer){ // add customer to the customer list in case of customers <= number of chairs
        synchronized(listCustomer){
//        System.out.println("Customer ",customer.);

        if(availableBarbers > 0){
            //if there are available customers //pop from the list and notify barber thread
            ((LinkedList<Client>)listCustomer).offer(customer); // add cutomer to the last position of the list
            listCustomer.notify(); // notify must be put in a synchronized block
        }
        else{
             System.out.println("Barbers are busy");
            System.out.println("Customer " + customer.customer_id + " takes a chair and wait for his turn");
            ((LinkedList<Client>)listCustomer).offer(customer); // add cutomer to the last position of the list
        }

}
    }
}
