
package multithreadingsocket;
import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;

public class serverThreading {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(3222);
        System.out.println("waiting for client request");
        
        while(true){
        Socket s = ss.accept();
        
        System.out.println("a new client is connected "+ s);
        
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        
        Thread t = new ClientHandler(s, dis, dos);
        t.start();
        }
    }
}

class ClientHandler extends Thread{
    final Socket soc;
    final DataInputStream input;
    final DataOutputStream output;
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos){
        this.soc = s;
        this.input = dis;
        this.output = dos;
    }
    
    public void run(){
        DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
        
        String recieved;
        String toreturn;
        
        while(true){
            try{
                output.writeUTF("What do you want? [Date/Time]");
                recieved = input.readUTF();
                
                if(recieved.equals("Bye")){
                    System.out.println("Client "+ this.soc + "sends exit");
                    this.soc.close();
                    break;
                }
                Date d = new Date();
                switch(recieved){
                    case "Date":
                        toreturn = fordate.format(d);
                        output.writeUTF(toreturn);
                        break;
                    case "Time":
                        toreturn = fortime.format(d);
                        output.writeUTF(toreturn);
                        break;
                    default:
                        output.writeUTF("invalid input");
                        break;
                }
                
            }catch(IOException e){
                System.out.println(e);
            }
        }
        
        try{
            this.output.close();
            this.input.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
}