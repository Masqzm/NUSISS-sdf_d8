package sg.edu.nus.iss.baccarat.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import sg.edu.nus.iss.baccarat.Constants;

//  java -cp classes sg.edu.nus.iss.baccarat.server.ServerApp 12345 4
public class ServerApp {
    public static void main(String[] args) throws IOException {
        int portNo = 12345;
        int totalDecks = 1;

        if(args.length > 1) {
            portNo = Integer.parseInt(args[0]);
            totalDecks = Integer.parseInt(args[1]);
        } else {
            System.err.println("Missing arguments! Arg1: port no. & Arg2: total deck of cards to use");
            System.exit(-1);
        }

        // Init engine        
        BaccaratEngine bacEng = new BaccaratEngine(totalDecks);

        // Init server
        ServerSocket ss = new ServerSocket(portNo);
        System.out.printf("Websocket server started on port %s. Waiting for connection...\n", portNo);

        // Try-with-resources - resources will be autoclosed
        try(Socket s = ss.accept();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()))) {

            String msgRcv = "";

            while(true) 
            {
                System.out.println("Waiting for client input...");
              
                // Get client request
                msgRcv = br.readLine();

                // Exit server loop if player exit game
                if(msgRcv.equals(Constants.CMD_EXIT))
                    break;

                // Update recieved msg
                String msgSend = "SERVER: " + BaccaratEngine.clientRequest(msgRcv);  

                bw.write(msgSend); 
                bw.newLine();
                bw.flush();
            }

            System.out.println("Player exited game. Closing server...");
        } catch (EOFException ex) {
            System.out.println("EOF ERROR");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("IOStream ERROR");
            ex.printStackTrace();
        } 
    }
}
