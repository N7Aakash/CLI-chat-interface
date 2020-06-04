import java.io.*;
import java.util.*;
import java.net.Socket;
public class Message{

    public Socket socket;
    public String message;
    public int priority;


    Message(Socket socket, String message, int priority){
        this.socket = socket;
        this.message = message; 
        this.priority = priority;
    }


}