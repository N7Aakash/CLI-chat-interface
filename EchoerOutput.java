import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.concurrent.*;

public class EchoerOutput extends Thread{
	private Socket socket;
    PriorityBlockingQueue<Message> pq;
    boolean flag = false;
	Scanner sc = new Scanner(System.in);
	EchoerOutput(PriorityBlockingQueue<Message> pq){
        this.pq = pq;
	}

    public void run(){
        try{
            
            while(true){
                Thread.sleep(1000);
                // System.out.println("Inside while");
                Message m = pq.take();
                System.out.println("sending response to this message: "+m.toString());
                // bw.write("hello");
                // bw.newLine();
                Socket s = m.socket;
                PrintWriter output = new PrintWriter(s.getOutputStream(), true);
                output.println("ACK");

            }


        }catch(Exception e){
            System.out.println("issue: " + e);
        }finally{

        }
    }

    
}