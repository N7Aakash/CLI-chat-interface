import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.concurrent.*;

public class Echoer extends Thread{
	private Socket socket;
    PriorityBlockingQueue<Message> pq;
	Scanner sc = new Scanner(System.in);
	Echoer(Socket socket, PriorityBlockingQueue<Message> pq){
		this.socket = socket;
        this.pq = pq;
	}
	public void run(){
		try{
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
			while(true){
                boolean command = false;
				String echoString = input.readLine();
                int textPriority = Integer.parseInt(input.readLine());
                // System.out.println("Server got this: " + echoString + " with priority as " + textPriority);
                String h2TextGroupRegex = "(<command>)(.*?)(</command>)";
                Pattern h2TextGroupPattern = Pattern.compile(h2TextGroupRegex);
                Matcher h2TextGroupMatcher = h2TextGroupPattern.matcher(echoString);
                if(h2TextGroupMatcher.matches()){
                    command = true;
                    echoString = echoString.replaceAll("<command>","");
                    echoString = echoString.replaceAll("</command>","");
                }
				if(echoString.equals("exit")){
					break;
				}
				System.out.println("Server got this: " + echoString);
				// try{
				// 	Thread.sleep(1000);
				// }catch(Exception e){}
				// output.println(sc.nextLine());

                //ADDING DATA INTO PRIORITY QUEUE
                pq.add(new Message(socket,echoString,textPriority));
                

			}
            
		}catch(IOException e){
			System.out.println("issue: " + e);
		}catch(Exception e){
			System.out.println("issue: "+ e);
		}finally{
			// try{
			// 	socket.close();
			// }catch(IOException e){
			// }
            //THESE 3 LINES BELOW SHOW THAT THE PRIORITY QUEUE SET IS WORKING PROPERLY.

            while (!pq.isEmpty()) { 
                System.out.println(pq.poll().priority);
                }
            System.out.println("I am here");
		}
	}
}

