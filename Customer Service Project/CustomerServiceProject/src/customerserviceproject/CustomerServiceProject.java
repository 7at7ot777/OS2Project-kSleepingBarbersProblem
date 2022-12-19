/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package customerserviceproject;

import java.util.Scanner;

/**
 *
 * @author Hathout
 */
public class CustomerServiceProject {

    /**
     * @param args the command line arguments
     */
  
    public static void main(String[] args) throws InterruptedException {
       int numOfCS;
       
       int numberOfCalls= 1000;
       
       Scanner input = new Scanner(System.in);
       

       //declaring number of Customer Service Representative
       
       System.out.println("Please enter number of  Customer Service Representative : ");
        numOfCS = input.nextInt();
        
        Thread[] CustomerServiceThreads = new Thread[numOfCS];
        Thread[] CallsThreads = new Thread[numberOfCalls];
        
     
        
        //making a shop object
        Company company = new Company(numOfCS);
        
        System.out.println("The Company has " + numOfCS + " CSR" );
        
        for(int i = 0 ; i< numOfCS ; i++)
        {
            CustomerService cs = new CustomerService(i+1,company);
            CustomerServiceThreads[i] = cs ;
            CustomerServiceThreads[i].start();
        }
        
        
        for(int i = 0 ; i< numberOfCalls ; i++ ){
                Client customer = new Client(i+1,company);
                CallsThreads[i] = customer;
                CallsThreads[i].start();
        }
        
        for(int i = 0 ; i < numberOfCalls; i++){
            
            if(i < numOfCS){
                CustomerServiceThreads[i].join();
            }
            CallsThreads[i].join();
            
        }
    
    
          
    
    }
    
}
