

/**
 * Class to represent a deck of Cards.
 * @author -Kay Alexis-
 */
public class Deck {
    
    /**
     * Makes a random Card.
     * @return A random Card.
     */
    public Card drawCard(){

        int value = (int)(Math.random() * 14 + 1);
        int suitValue = (int)(Math.random() * 4 + 1);

        String suit = ""; 
         if (suitValue == 1){
            suit = "Clubs";
         }else if (suitValue == 2){
            suit = "Diamonds";
         }else if (suitValue == 3){
            suit = "Hearts";
         }else if (suitValue == 4){
            suit = "Spades";
         }
        
        return new Card(value, suit);
    }
}
 