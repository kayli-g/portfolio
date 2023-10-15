
import java.util.Scanner;

/**
 * A class to hold the logic for the game BlackJack.
 *
 * @author -Kay Alexis-
 */
public class BlackJack {

    private static Deck deck = new Deck();
    private static Scanner scnr = new Scanner(System.in);

    /**
     * The method holding the logic for the game.
     *
     * @throws InterruptedException
     */
    public void playGame() throws InterruptedException {

        boolean keepPlaying = true;
        int houseWins = 0;
        int playerWins = 0;

        while (keepPlaying) {

            //Get the card values for the house and player
            int houseTotal = deck.drawCard().getValue(true) + deck.drawCard().getValue(true);
            int playerTotal = deck.drawCard().getValue(true) + deck.drawCard().getValue(true);
            System.out.println("The house is showing " + houseTotal);

            OUTER:
            while (playerTotal < 22) {

                //Let the player decide whether to hit or stand
                Thread.sleep(500);
                System.out.println("Player total is " + playerTotal);
                System.out.println("Would you like to hit of stand? \n\tEnter 1 for hit, 0 for stand.");
                int choice = scnr.nextInt();
                switch (choice) {
                    case 0:

                        //Stand: Break loop
                        break OUTER;
                    case 1:

                        //Hit: Add the next card to the player's total
                        Thread.sleep(500);
                        Card nextCard = deck.drawCard();
                        System.out.println("The player has been dealt the " + nextCard.declareCard());
                        playerTotal += nextCard.getValue(true);
                        break;
                    default:
                        System.out.println("Invalid option, try again.");
                        break;
                }
            }

            //Player busted
            if (playerTotal > 21) {
                Thread.sleep(500);
                System.out.println("The player has busted. You lose!");
            } else {

                //Player continues
                Thread.sleep(500);
                System.out.println("\nThe player stands with " + playerTotal);
                Thread.sleep(500);
                System.out.println("The House will play next\n");
            }
            while (houseTotal < 22) {

                //Add next card to house
                if (houseTotal < 17) {
                    Card nextCard = deck.drawCard();
                    Thread.sleep(500);
                    System.out.println("The house has been dealt the " + nextCard.declareCard());
                    houseTotal += nextCard.getValue(true);
                } else if (houseTotal >= 17 && houseTotal <= 21) {
                    break;
                }
            }

            //House busted
            if (houseTotal > 21) {
                Thread.sleep(500);
                System.out.println("The house busted!");
            } //House stands
            else {
                Thread.sleep(500);
                System.out.println("The house stands with " + houseTotal);
            }
            if (playerTotal > 21) {
                playerTotal = -1;
            }
            if (houseTotal > 21) {
                houseTotal = -1;
            }

            //Player wins
            if (playerTotal > houseTotal) {
                Thread.sleep(500);
                System.out.println("You Win!");
                playerWins++;
            }

            //Player loses
            if (houseTotal > playerTotal) {
                Thread.sleep(500);
                System.out.println("You Lose!");
                houseWins++;
            }

            //Tie
            if (houseTotal == playerTotal) {
                Thread.sleep(500);
                System.out.println("It's a Tie");
            }
            Thread.sleep(500);

            //Game over
            System.out.println("Number of house wins: " + houseWins);
            System.out.println("Number of player wins: " + playerWins);
            Thread.sleep(500);
            System.out.println("Would you like to keep playing blackjack? Enter 'yes' or 'no'");
            String newGame = scnr.nextLine();
            
            if (newGame.equalsIgnoreCase("yes")) {
            keepPlaying = true;
            }
            else {
                keepPlaying = false;
            }
        }
    }
}
