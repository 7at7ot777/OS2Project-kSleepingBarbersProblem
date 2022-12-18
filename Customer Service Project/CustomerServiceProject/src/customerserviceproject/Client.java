/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customerserviceproject;

/**
 *
 * @author Hathout
 */
public class Client extends Thread {
    int customer_id;
    Company company;
    Client(int customer_id, Company company){
        this.customer_id = customer_id;
        this.company = company;
        
    }


    @Override
    public void run(){

       MakeCall();
    }
    
    public synchronized void MakeCall(){
        company.add(this);
    }
}
