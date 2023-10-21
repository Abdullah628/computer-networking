
package socketprogramming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class serverSide {
    public static void main(String [] args) throws IOException{
        ServerSocket ss = new ServerSocket(4500);
        
        System.out.println("Waiting for clients");
        
        Socket s = ss.accept();
        System.out.println("client request is accepted");
        
        
        
        DataInputStream input = new DataInputStream(s.getInputStream());
        DataOutputStream output = new DataOutputStream(s.getOutputStream());
        
        Scanner scn = new Scanner(System.in);
        
        String str = "", str1= "";
        while(!str.equals("Bye")){
            str = input.readUTF();
            System.out.println("client says: " + str);
            
            str1= scn.nextLine();
            output.writeUTF(str1);
            
        }
        
        s.close();
        ss.close();
    }
}
