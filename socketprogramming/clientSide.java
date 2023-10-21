
package socketprogramming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class clientSide {

    public static void main(String[] args) throws IOException{
        Socket s = new Socket("localhost", 4500);
        System.out.println("Connected");
        
        DataOutputStream output = new DataOutputStream(s.getOutputStream());
        DataInputStream input = new DataInputStream(s.getInputStream());
        
        Scanner scn = new Scanner(System.in);
        
        String str = "", str1 = "";
        
        while(!str.equals("Bye")){
            str = scn.nextLine();
            output.writeUTF(str);
            
            str1 = input.readUTF();
            System.out.println("Server says: "+ str1);
        }
        
        output.close();
        s.close();
        
    }
    
}
