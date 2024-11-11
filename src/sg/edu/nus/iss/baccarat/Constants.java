package sg.edu.nus.iss.baccarat;

public class Constants {
    public static final String DECK_DB_DIRPATH = "db";      // abs path for deck db
    public static final String DECK_DB_FILENAME = "cards.db";

    // Card suits (Ace = 1, J = 12, Q = 13, K = 14; Picture cards worth 10pts)  
    public static final float SUIT_HEARTS = 0.1f;
    public static final float SUIT_DIAMONDS = 0.2f;
    public static final float SUIT_SPADES = 0.3f;
    public static final float SUIT_CLUBS = 0.4f;

    public static final String CMD_LOGIN = "login";
    public static final String CMD_BET = "bet";
    public static final String CMD_DEAL = "deal";
    public static final String CMD_EXIT = "q";
}
