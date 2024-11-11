package sg.edu.nus.iss.baccarat.client;

import java.io.*;
import java.net.Socket;

import sg.edu.nus.iss.baccarat.Constants;

//  java -cp classes sg.edu.nus.iss.baccarat.client.ClientApp localhost:12345
public class ClientApp {
    public static void main(String[] args) {
        int portNo = 12345;
        String serverAddr = "localhost";

        if(args.length > 0) {
            serverAddr = args[0].split("\\:")[0];
            portNo = Integer.parseInt(args[0].split("\\:")[1]);
        } else {
            System.err.println("Missing arguments!!");
            System.exit(-1);
        }
        
        // Try-with-resources - resources will be autoclosed
        // Get IO streams to/from socket
        try(Socket s = new Socket(serverAddr, portNo);
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()))) {      

            System.out.println("Connected to server at " + serverAddr + ":" + s.getPort());
            
            Console cons = System.console();
            String input = "";
            String msgRcv = "";

            while(!msgRcv.toLowerCase().equals(Constants.CMD_EXIT))
            {
                // SERVER REQUESTS
                //================
                input = cons.readLine("> ");

                // Split input cmd into array
                // String[] cmdParts = input.split("\\|");
                // String cmd = cmdParts[0];

                bw.write(input); 
                bw.newLine();
                bw.flush();

                // Quit game without waiting for server 
                if(input.equals(Constants.CMD_EXIT))
                    break;

                msgRcv = br.readLine();

                System.out.println(msgRcv);

                // SERVER RESPONSE
                //================
                // br.readLine()
                // 1. login successful (success/invalid amt - non +ve int)
                // 2. bet entered result (success/invalid amt - non +ve int)
                // 3a. deal success/failure 
                //  - depends on player's amt remaining 
                //  - depends on cards avail 
                // 3b. deal results (if successful)
            }
            // Close socket
            s.close();
        } catch(IOException ex) {
            System.err.println(ex.toString());
        }

        System.out.println("Exiting game...");
    }
}
