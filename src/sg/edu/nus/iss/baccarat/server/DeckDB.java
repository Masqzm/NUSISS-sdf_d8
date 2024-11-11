package sg.edu.nus.iss.baccarat.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.baccarat.Constants;

// Class to manage read/write of decks to db
public class DeckDB {
    private final String dbDirPath;
    private final String dbFileName;
    private File dbFile;

    private int totalDecks;

    private List<Deck> decks;        

    public DeckDB(int totalDecks) {
        dbDirPath = Constants.DECK_DB_DIRPATH;
        dbFileName = Constants.DECK_DB_FILENAME;

        this.totalDecks = totalDecks;

        // Create dir to store db file if it doesn't exist (creates to root folder where program runs)
        File newDir = new File(dbDirPath);
        if(!newDir.exists()) 
            newDir.mkdir();
        
        dbFile = new File(dbDirPath + File.separator + dbFileName);
        decks = new ArrayList<>();

        // Init decks
        for(int i = 0; i < totalDecks; i++)
        {
            Deck deck = new Deck();
            deck.shuffle();

            decks.add(deck);
        }

        save();
    }

    // Saves 
    public void save() {
        // Try-with-resources - resources will be autoclosed
        try(FileWriter fw = new FileWriter(dbFile);
            BufferedWriter bw = new BufferedWriter(fw)) {
            
            // Loop thru all decks
            for(Deck deck : decks) {
                // Loop thru each deck's cards
                for(String card : deck.getCards()) {
                    bw.write(card);
                    bw.newLine();
                }
            }

            bw.flush();
        } catch(IOException ex) {
            System.err.println("ERROR: DB FILE WRITE ERROR!");
            ex.printStackTrace();
        }
    }
    
    public void load() {
        // List<String> cartList = new ArrayList<>();

        // // Get abstract pathname (e.g. "cartdb" + "/" + user + ".db")
        // String absPathName = dirPath + File.separator + user + Constants.DEFAULT_DB_EXTENSION;
        
        // File file = new File(absPathName); 

        // // Create new db file if file doesnt exist (ie. new user)
        // if(!file.exists())
        // {
        //     try {
        //         file.createNewFile();
        //     } catch(IOException ex) {
        //         System.err.println("ERROR: DB FILE CREATION ERROR!");
        //         ex.printStackTrace();
        //     }
        // }

        // try {
        //     FileReader fr = new FileReader(file);
        //     BufferedReader br = new BufferedReader(fr);

        //     // Read and store each line into cart list
        //     String line = "";

        //     try {
        //         while((line = br.readLine()) != null) 
        //             cartList.add(line);
        //     } catch(IOException ex) {
        //         System.err.println("ERROR: DB FILE READ ERROR!");
        //         ex.printStackTrace();
        //     }

        //     br.close();
        //     fr.close();
        // } catch(FileNotFoundException ex) {
        //     System.err.println("ERROR: DB FILE NOT FOUND!");
        //     ex.printStackTrace();
        // } catch(IOException ex) {
        //     System.err.println("ERROR: DB FILE IO ERROR!");
        //     ex.printStackTrace();
        // } 

        // // User succesfully registered, add them to the list
        // if(!usersList.contains(user))
        //     usersList.add(user);

        // return cartList;
    }
}
