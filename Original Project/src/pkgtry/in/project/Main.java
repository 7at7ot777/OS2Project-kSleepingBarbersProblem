/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkgtry.in.project;

import java.util.Scanner;

/**
 *
 * @author Hathout
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
       int numOfBarbers;
       
       int numberOfCustomer = 1000 ;
       
       int numberOfChairs ; 
       
       Scanner input = new Scanner(System.in);
       

       //declaring number of barbers and chairs of the shop 
       
       System.out.println("Please enter number of barbers : ");
        numOfBarbers = input.nextInt();
        
       System.out.println("Please enter number of chairs : ");
         numberOfChairs = input.nextInt();
        
        Thread[] BarbersThread = new Thread[numOfBarbers];
        Thread[] CustThreads = new Thread[numberOfCustomer];
        
     
        
        //making a shop object
        barberShop shop = new barberShop(numOfBarbers, numberOfChairs);
        
        System.out.println("Openninig the shop with " + numOfBarbers + " of barbers");
        
        //creating a barbers thread 
        for(int i = 0 ; i< numOfBarbers ; i++)
        {
            Barber barber = new Barber(i+1,shop);
            BarbersThread[i] = barber;
            BarbersThread[i].start();
        }
        
        //creating a customer thread and sleep 500 ms between every 2 threads 
        for(int i = 0 ; i< numberOfCustomer ; i++ ){
                Customer customer = new Customer(i+1,shop);
                CustThreads[i] = customer;
                CustThreads[i].start();
                 Thread.sleep(500);
        }
        //join the two threads 
        for(int i = 0 ; i < numberOfCustomer ; i++){
            
            if(i < numOfBarbers){
                BarbersThread[i].join();
            }
            CustThreads[i].join();
           
                
            
            
        }
        
//         for(int i = 0 ; i< numOfBarbers ; i++)
//        {
//            BarbersThread[i].interrupt();
//        }
        
        System.out.println("########mustn't be printed############");
          
    
           
        
        
       
    }
    
}
