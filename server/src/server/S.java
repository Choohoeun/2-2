package server;

import java.io.*;
import java.net.*;


public class S {
	//to execute with several client, we should struct multi thread
	 public static void main(String[] args) throws Exception {
	        String[] file = new String[2];
	        file = FileInfo.getInfo("server_info.dat"); //Get file information

	        String ip = file[0];
	        int port = Integer.parseInt(file[1]);

	        String clientSentence; //Sentence from client
	        String represent = ""; //message represent sentence
	        String answerSentence; //Sentence send to client
	        String split[];

	        //Create welcoming socket at port
	        ServerSocket welcomeSocket = new ServerSocket(port);
	        System.out.println("Server start");
	        while(true) {
	            //Wait, on welcoming socket for contact by client
	            Socket connectionSocket = welcomeSocket.accept();

	            //Create input stream, attached to socket
	            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

	            //Create output stream, attached to socket
	            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

	            //Read in line from socket
	            clientSentence = inFromClient.readLine();

	            //Client closed socket
	            if (clientSentence.equalsIgnoreCase("end")) {
	                System.out.println("== Client entered END ==");
	                continue;
	            }

	            split = clientSentence.split(" ");
	            try {
	                //When expression is too short, send error type
	                if (split.length < 3) {
	                    represent = "Incorrect: too short arguments";
	                    throw new Exception("Error message: too short arguments");
	                }
	                //When expression is too long, send error type
	                else if (split.length > 3) {
	                    represent = "Incorrect: too many arguments";
	                    throw new Exception("Error message: too many arguments");
	                } else {
	                    Double a = Double.parseDouble(split[1]);
	                    Double b = Double.parseDouble(split[2]);
	                    switch (split[0].toUpperCase()) {
	                        case "ADD":
	                            //When operator is addition
	                            answerSentence = "Answer: " + String.valueOf(Double.parseDouble(split[1]) + Double.parseDouble(split[2]));
	                            represent = split[1] + " + " + split[2];
	                            break;
	                        case "MINUS":
	                            //When operator is subtraction
	                            answerSentence = "Answer: " + String.valueOf(Double.parseDouble(split[1]) - Double.parseDouble(split[2]));
	                            represent = split[1] + " - " + split[2];
	                            break;
	                        case "MUL":
	                            //When operator is multiplication
	                            answerSentence = "Answer: " + String.valueOf(Double.parseDouble(split[1]) * Double.parseDouble(split[2]));
	                            represent = split[1] + " x " + split[2];
	                            break;
	                        case "DIV":
	                            //When operator is division
	                            if (split[2].equals("0")) {
	                                //When second number is 0, send error type
	                                represent = "Incorrect: divided by zero";
	                                throw new Exception("Error message: divided by zero");
	                            } else {
	                                answerSentence = "Answer: " + String.valueOf(Double.parseDouble(split[1]) / Double.parseDouble(split[2]));
	                                represent = split[1] + " / " + split[2];
	                            }
	                            break;
	                        default:
	                            //When operator isn't about four base arithmetic operations
	                        	represent = "Incorrect: invalid operator";
	                            throw new Exception("Error message: invalid operator");
	                    }
	                }
	            } catch (NumberFormatException e) {
	                represent = "Incorrect: invalid number format";
	                answerSentence = "Error message: invalid number format";
	            } catch (Exception e) {
	                //Get each error type
	                answerSentence = e.getMessage();
	            }

	            System.out.println(represent);

	            //Write out line to socket
	            outToClient.writeBytes(answerSentence + "\n");
	        }
	    }
}
