package sg.edu.nus.iss.baccarat.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Class to manage logic of decks and cards
public class Deck {
    private final int TOTAL_SUITS = 4;      // ♤♧♡◇
    private final int TOTAL_RANKS = 13;     // A-10, J, Q, K

    private List<String> cards = new ArrayList<>();

    public List<String> getCards() {
        return cards;
    }

    public Deck() {
        // Init deck
        for(int suit = 0; suit < TOTAL_SUITS; suit++) {
            String cardSuffix = "." + (suit+1);

            for(int rank = 0; rank < TOTAL_RANKS; rank++) {
                String card = (rank+1) + cardSuffix;

                cards.add(card);
            }
        }

        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    // Fn.s to display readable cards     
}