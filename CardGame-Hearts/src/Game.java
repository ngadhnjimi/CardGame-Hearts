import java.util.*;

public class Game{
    private ArrayList<Card> game = new ArrayList<Card>();
    private ArrayList<Integer> player = new ArrayList<>();

    Game() {
    }

    public void add(Card card, int player) {
        this.game.add(card);
        this.player.add(player);
    }

    public String getCardType(int i) {
        return game.get(i).get_suit();
    }

    public int findWinner() {
        String cardType = game.get(0).get_suit();
        int[] cardValues = new int[4];
        int cardInt = 0;
        int highCard = 0;

        for (int i = 0; i < game.size(); i++) {
            if (game.get(i).get_suit().equals(cardType)) {
                cardValues[i] += getValueOf(game.get(i));
            }
        }

        for (int i = 0; i < game.size(); i++) {
            if (cardValues[i] > highCard) {
                cardInt = i;
                highCard = cardValues[i];
            }
        }

        return player.get(cardInt);
    }

	public int handPoints() {
        int handPoints = 0;
            for (int i = 0; i < game.size(); i++) {
                if (game.get(i).get_suit().equals("HEARTS")) {
                	handPoints += 1;
                }
                if (game.get(i).get_suit().equals("SPADES") && game.get(i).get_value().equals("Q")) {
                	handPoints += 13;
                }
            }
        return handPoints;
}
    
    

    public int getValueOf(Card card) {
        if (card.get_value().equals("K")) {
            return 13;
        } else if (card.get_value().equals("Q")) {
            return 12;
        } else if (card.get_value().equals("J")) {
            return 11;
        } else if (card.get_value().equals("A")) {
            return 14;
        } else {
            return Integer.parseInt(card.get_value());
        }
    }

	
		
}
