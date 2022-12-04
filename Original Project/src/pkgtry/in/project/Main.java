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
     */
    public static void main(String[] args) throws InterruptedException {
       int numOfBarbers,numOfChairs;
       
       int numberOfCustomer = 10 ; 
       
       Scanner input = new Scanner(System.in);
       

       //declaring number of barbers and chairs of the shop 
       
       System.out.println("Please enter number of barbers : ");
        numOfBarbers = input.nextInt();
        
        Thread[] threads = new Thread[numOfBarbers];
        
       System.out.println("Please enter number of chairs : ");
        numOfChairs = input.nextInt();
        
        //making a shop object
        barberShop shop = new barberShop(numOfBarbers,numOfChairs);
        
        System.out.println("Openninig the shop with " + numOfBarbers + " of barbers and wih " +numOfChairs + " chairs");
        
        for(int i = 0 ; i< numOfBarbers ; i++)
        {
            Barber barber = new Barber(i+1,shop);
            threads[i] = barber;
            threads[i].start();
        }
        
        
        for(int i = 1 ; i<= numberOfCustomer ; i++ ){
                Customer customer = new Customer(i,shop);
                Thread t = new Thread(customer);
                t.start();

                t.join();
        }
          
    
           
        
        
       
    }
    
}
