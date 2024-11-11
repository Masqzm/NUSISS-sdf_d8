package sg.edu.nus.iss.baccarat.server;

import java.util.List;

import sg.edu.nus.iss.baccarat.Constants;

// Class for game logic
public class BaccaratEngine {
    private DeckDB deckDB;

    public BaccaratEngine(int totalDecks) {
        // Setup deck
        deckDB = new DeckDB(totalDecks);
    }

    public String clientRequest(String req) {
        // SERVER REQUESTS
        //================
        // Split input cmd into array
        String[] cmdParts = req.split("\\|");
        String cmd = cmdParts[0];

        switch(cmd.toLowerCase()) {
            // check if login successful, return success/invalid (amt must be +ve int)
            // e.g. cmd: login|kenneth|100
            case Constants.CMD_LOGIN:




                return "CLIENT LOGIN";

            // check bet entered result, return success/invalid (bet amt must be +ve int)
            // e.g. cmd: bet|50
            case Constants.CMD_BET:
                return "CLIENT BET";
        
            // check deal success/failure
            //  - depends on player's amt remaining 
            //  - depends on cards avail  
            // return deal results (if successful)
            // e.g. cmd: deal|B OR deal|P
            case Constants.CMD_DEAL:
                return "CLIENT DEAL";

            case Constants.CMD_EXIT:
                return Constants.CMD_EXIT;

            default:
                break;
        }

        return "INVALID COMMAND ENTERED!";
    }
}


// case Constants.CMD_LOGIN:
//     // Send server request
//     //dos.writeUTF(input);
//     //dos.flush();

//     // Read cookie (received from server)
//     //cookie = dis.readUTF();
//     System.out.println("logging in...");
//     bw.write(cmdParts[1]);  // send user's name
//     bw.write(cmdParts[2]);  // send user's total money
//     break;

// case Constants.CMD_BET:
//     System.out.println("sending bet...");
//     bw.write(cmdParts[1]);  // send user's bet value
//     break;  

// case Constants.CMD_DEAL:
//     System.out.println("sending deal...");
//     bw.write(cmdParts[1]);  // send user's bet choice
//     break;

// case Constants.CMD_EXIT:
//     System.out.println("Exiting game...");
//     bw.write(cmd);          // send user exiting game
//     break;

// default:
//     System.out.println("WRONG COMMAND ENTERED!");
//     break;