
public class Card {
    
    /**
     *Class to represent a single Card.
     * @author Kayli
     */
    
    private int value;
    private String suit;

    /**
     * Constructor for the Card
     * @param value Number on the card
     * @param suit Card suit
     */
    public Card (int value, String suit){

        this.value = value;
        this.suit = suit;
    }
    
    /**
     * Gets the value of a card
     * @return Value of card
     */
    public int getValue(){
        return value;
    }
    
    /**
     * Gets the value of the card, converting face cards to numbers.
     * @param isBlackjack True if it's a face card.
     * @return Value of the card.
     */
    public int getValue(boolean isBlackjack){
        if (!isBlackjack){
            return value;
        }else{
            if (value == 1){
                return 11;
            }else if (value > 10){
                return 10;
            }else{
                return value;
            }
        }
    }
    
    /**
     * Gets the suit of a card.
     * @return The suit
     */
    public String getSuit(){
        return suit;
    }
    
    /**
     * Gets the String representation of the Card.
     * @return The card in String form
     */
    public String declareCard(){
        if (this.value == 11){
            return ("The Jack of " + suit);
        }
        if (this.value == 12){
            return ("The Queen of " + suit);
        }
        if (this.value == 13){
            return ("The King of " + suit);
        }
        if (this.value == 1){
            return ("The Ace of " + suit);
        }
        else{
            return "The " + value + " of " + suit;
        }
    }

}
