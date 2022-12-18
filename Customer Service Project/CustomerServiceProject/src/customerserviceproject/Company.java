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
        int availableCSR;
    List<Client> listCustomer;
    
    Company(int numOfCS ){
        
//        this.numberOfChairs = numberOfChairs;
        this.numOfCS = numOfCS;
        this.availableCSR = numOfCS;
        this.listCustomer = new LinkedList<>();
    }
    
 
    
    
    public int MakeCall(int barberID) throws InterruptedException{
        
        Client customer ;
        
        synchronized(listCustomer){
            while(listCustomer.size() == 0){
                System.out.println("#### The Employee " +barberID +" is waiting for a Call #### " );
                try {
                    
                 
                    listCustomer.wait(); // wait for a customer to arrive and notify to complete the rest of the code 
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);
                }
            }   
                 
                 customer = (Client)((LinkedList<?>)listCustomer).poll();	
                 System.out.println("@@@@ Customer "+customer.customer_id +" Make a Call and " + barberID+" anwered to this call@@@@" );
            
        }
        availableCSR--;
        System.out.println("--- Employee "+barberID+ " dealing with customer " + customer.customer_id + " ---");
        Thread.sleep(5000);
        System.out.println("---Employee "+barberID+ " finishes customer " + customer.customer_id + " ---");
        availableCSR++;
        totalHairCuts++;
        
    return 0;
    }
    
    
    

    public void add(Client customer){ // add customer to the customer list in case of customers <= number of chairs
        synchronized(listCustomer){
//        System.out.println("Customer ",customer.);

        if(availableCSR > 0){
            
            ((LinkedList<Client>)listCustomer).offer(customer); 
            listCustomer.notify();
        }
        else{
             System.out.println("The System are busy");
            System.out.println("Customer " + customer.customer_id + " is listening to the waiting message...");
            ((LinkedList<Client>)listCustomer).offer(customer);
        }

}
    }
}
