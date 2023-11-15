package server;


import java.io.*;
import java.net.*;

public class ClientEx {

	  public static void main(String[] args) throws Exception {
	        String[] file = new String[2];
	        file = FileInfo.getInfo("server_info.dat"); //Get file information

	        String ip = file[0];
	        int port = Integer.parseInt(file[1]);

	        String sentence; //Sentence send to server
	        String answerSentence; //Sentence receive from server

	        System.out.println("Client Connection Success");
	        while(true) {

	        	System.out.println("Enter the formula");
                System.out.println("Enter 'END' to stop");

	            //Create client socket, connect to server
	            Socket clientSocket = new Socket(ip, port);

	            //Create input stream
	            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

	            //Create output stream attached to socket
	            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

	            //Create input stream attached to socket
	            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

	            //Receive send expression
	            sentence = inFromUser.readLine();

	            //Send line to server
	            outToServer.writeBytes(sentence + '\n');

	            //When user enter end, close socket
	            if(sentence.equalsIgnoreCase("end")) {
	                System.out.println("== END ==");
	                clientSocket.close();
	                break;
	            }

	            //Read line from server
	            answerSentence = inFromServer.readLine();

	            System.out.println(answerSentence);
	        }
	    }
}
