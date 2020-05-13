import java.util.*;
import java.util.concurrent.TimeUnit;


public class Hearts {
    private static Deck deck = new Deck();
    private static Player player = new Player();
    private static Bot bot1 = new Bot("Bot1");
    private static Bot bot2 = new Bot("Bot2");
    private static Bot bot3 = new Bot("Bot3");


    public static void main(String[] args) throws InterruptedException {
        playGame();
    }

    private static void playGame() throws InterruptedException {
        int startGame;
        String cardType;
        int pause = 1;

        while (player.getHandPoints() < 50 && bot1.getHandPoints() < 50 && bot2.getHandPoints() < 50 && bot3.getHandPoints() < 50) {
            deck.shuffleDeck();
            dealCards();

            startGame = findStart();
            cardType = "CLUBS";

            for (int x = 0; x < 13; x++) {
                Game game = new Game();
                int next = 0;
             
            
            	   
            	TimeUnit.SECONDS.sleep(pause + 1);
                 System.out.println("Game Starts...");
                 System.out.println();
                TimeUnit.SECONDS.sleep(pause);
               

                showPoints();

                switch (startGame) {
                    case 1:
                        showHand(player, cardType, true);
                        game.add(player.drawCard(), 1);
                        cardType = game.getCardType(0);
                        next = 2;
                        break;
                    case 2:
                        game.add(bot1.drawCard(cardType, true), 2);
                        TimeUnit.SECONDS.sleep(pause);
                        cardType = game.getCardType(0);
                        next = 3;
                        break;
                    case 3:
                        game.add(bot2.drawCard(cardType, true), 3);
                        TimeUnit.SECONDS.sleep(pause);
                        cardType = game.getCardType(0);
                        next = 4;
                        break;
                    case 4:
                        game.add(bot3.drawCard(cardType, true), 4);
                        TimeUnit.SECONDS.sleep(pause);
                        cardType = game.getCardType(0);
                        next = 1;
                        break;
                }
                for (int i = 0; i < 3; i++) {
                    switch (next) {
                        case 1:
                            showHand(player, cardType, false);
                            game.add(player.drawCard(), 1);
                            TimeUnit.SECONDS.sleep(pause);
                            next = 2;
                            break;
                        case 2:
                            game.add(bot1.drawCard(cardType, false), 2);
                            TimeUnit.SECONDS.sleep(pause);
                            next = 3;
                            break;
                        case 3:
                            game.add(bot2.drawCard(cardType, false), 3);
                            TimeUnit.SECONDS.sleep(pause);
                            next = 4;
                            break;
                        case 4:
                            game.add(bot3.drawCard(cardType, false), 4);
                            TimeUnit.SECONDS.sleep(pause);
                            next = 1;
                            break;
                    }
                }
                int winner = game.findWinner();
                switch (winner) {
                    case 1:
                        System.out.println("\n" + player.toString() + " Won!");
                        player.addPoints(game.handPoints());
                        break;
                    case 2:
                        System.out.println("\n" + bot1.toString() + " Won!");
                        bot1.addPoints(game.handPoints());
                        break;
                    case 3:
                        System.out.println("\n" + bot2.toString() + " Won!");
                        bot2.addPoints(game.handPoints());
                        break;
                    case 4:
                        System.out.println("\n" + bot3.toString() + " Won!");
                        bot3.addPoints(game.handPoints());
                        break;
                }
                startGame = winner;
                cardType = "";
               
            }
            showPoints();
        }
        findWinner();
    }

    private static void dealCards() {
        // Deals cards between the 4 players
        for (int i = 0; i < 13; i++) {
            player.addCard(deck.draw_card());
        }
        for (int i = 0; i < 13; i++) {
            bot1.addCard(deck.draw_card());
        }
        for (int i = 0; i < 13; i++) {
            bot2.addCard(deck.draw_card());
        }
        for (int i = 0; i < 13; i++) {
            bot3.addCard(deck.draw_card());
        }
        player.sortHand();
        bot1.sortHand();
        bot2.sortHand();
        bot3.sortHand();
    }

    public static void showPoints() {
        System.out.println( " Points of " + player.toString()  + " = "+ player.getHandPoints());
        System.out.println( " Points of " + bot1.toString() + " = "+ bot1.getHandPoints());
        System.out.println( " Points of " + bot2.toString() + " = " + bot2.getHandPoints());
        System.out.println( " Points of " + bot3.toString()  + " = " +bot3.getHandPoints() + "\n");
    }

    public static int findStart() {
        int startGame = 0;
        if (player.hasCard("CLUBS", "2")) {
        	startGame = 1;
        } else if (bot1.hasCard("CLUBS", "2")) {
        	startGame = 2;
        } else if (bot2.hasCard("CLUBS", "2")) {
        	startGame = 3;
        } else if (bot3.hasCard("CLUBS", "2")) {
            return 4;
        }
        return startGame;
    }

    private static void showHand(Player p, String cardType, boolean start) {
        System.out.println(p.toString() + "'s Hand:");
        System.out.println(" Points: " + p.getHandPoints());
        for (int i = 0; i < p.getHand().length(); i++) {
            if (p.getCardAtIndex(i).get_suit().equals(cardType)) {
                System.out.println("[" + (i + 1) + "]: " + p.getCardAtIndex(i));
            } else if (!p.hasCardOfSuit(cardType)) {
                if (start) {
                    if (p.getCardAtIndex(i).get_suit().equals("HEARTS")) {
                        System.out.println("--" + p.getCardAtIndex(i) + "--");
                    } else {
                        System.out.println("[" + (i + 1) + "]: " + p.getCardAtIndex(i));
                    }
                } else {
                    System.out.println("[" + (i + 1) + "]: " + p.getCardAtIndex(i));
                }
            } else {
                System.out.println("--" + p.getCardAtIndex(i) + "--");
            }
        }
    }

    private static void findWinner() {
        int playerPoints = player.getHandPoints();
        int bot1Points = bot1.getHandPoints();
        int bot2Points = bot2.getHandPoints();
        int bot3Points = bot3.getHandPoints();
        if (playerPoints < bot1Points && playerPoints < bot2Points && playerPoints < bot3Points) {
            System.out.println("\n\n\nPlayer Wins!");
        }
        if (bot1Points < playerPoints && bot1Points < bot2Points && bot1Points < bot3Points) {
            System.out.println("\n\n\n" + bot1.toString() + " Wins!");
        }
        if (bot2Points < playerPoints && bot2Points < bot1Points && bot2Points < bot3Points) {
            System.out.println("\n\n\n" + bot2.toString() + " Wins!");
        }
        if (bot3Points < playerPoints && bot3Points < bot1Points && bot3Points < bot2Points) {
            System.out.println("\n\n\n" + bot1.toString() + " Wins!");
        }
    }
}
