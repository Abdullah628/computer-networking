
package multithreadingsocket;

import java.io.*;
import java.net.*;
import java.util.*;



public class clientThreading {
    public static void main(String[] args) throws IOException{
        Socket s = new Socket("localhost", 3222);
        
        System.out.println("Connected");
        
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        DataInputStream dis = new DataInputStream(s.getInputStream());
        
        Scanner scn = new Scanner(System.in);
        
        while(true){
            System.out.println(dis.readUTF()); //What do you want?
            String tosend = scn.nextLine();
            dos.writeUTF(tosend);
            
            if(tosend.equals("Bye")){
                System.out.println("Closing the connection "+s);
                s.close();
                break;
            }
            
            String received = dis.readUTF();
            System.out.println(received);
        }
        
        dis.close();
        dos.close();
    }
}
