import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.util.Scanner;
import java.util.*;

public class ClientCode{
	public static void main(String args[]){
		try(Socket socket = new Socket("localhost",8765)){
			// socket.setSoTimeout(5000);
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
			Scanner scanner = new Scanner(System.in);
			String str;
			String response;
            BufferedReader br = new BufferedReader(new FileReader("messages.txt")); 
            String st; 
            ArrayList<String> strings = new ArrayList<>();
            while ((st = br.readLine()) != null) {
                strings.add(st);
            }
            Random rand = new Random();
            int rand_int;
            int rand_int2;
			for(int i=0;i<5;i++){
                rand_int = rand.nextInt(strings.size()-1);
                String[] text = strings.get(rand_int).split("==>");
                String textMessage = text[0];
                int textPriority = Integer.parseInt(text[1].trim());
                rand_int2 = rand.nextInt(1);
                if(rand_int2%2==0){
                    str = "<command>" + textMessage + "</command>";
                    System.out.println("The client has selected a message which is being sent to the server from messages.txt and is being send as command as : " + str + " and the priority as: " + textPriority);
                }else{
                    str = textMessage;
                    System.out.println("The client has selected a message which is being sent to the server from messages.txt and is being send as normal text as : " + str + " and the priority as: " + textPriority);
                }
				// System.out.println("enter the string : ");
				// str = scanner.nextLine();
				output.println(str);
                output.println(textPriority);
				// if(!str.equals("exit")){
				// 	response = input.readLine();
				// 	System.out.println(response);
				// }
			}
            str = "exit";
            output.println(str);
            output.println(0);
		}catch(SocketTimeoutException e){
			System.out.println("issue: "+ e);
		}catch(IOException e){
			System.out.println("issue:"+ e);
		}catch(Exception e){
			System.out.println("issue: " +e);
		}
	}
}

