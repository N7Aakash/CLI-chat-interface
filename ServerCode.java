import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.lang.Thread;
import java.util.*;
import java.util.concurrent.*;

public class ServerCode{
	public static void main(String args[]){
        
        PriorityBlockingQueue<Message> pq = new PriorityBlockingQueue<>(1,new MessageComparator());

		try(ServerSocket serverSocket = new ServerSocket(8765)){
            // ClientCode.main(new String[0]);
            Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"javac ClientCode.java && java ClientCode\" ");
            Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"javac ClientCode.java && java ClientCode\" ");
			while(true){
                
				Socket s  = serverSocket.accept();
                
				/*echoer e = new echoer(s);
				e.start();
				*/
				new Echoer(s,pq).start();
				// new EchoerOutput(s).start();
			}
		}catch(IOException e){
			System.out.println("issue " + e);
		}
	}
}