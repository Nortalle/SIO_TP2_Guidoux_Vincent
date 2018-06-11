/**
 * @author Vincent Guidoux
 * @brief Represents a car with a value
 */
public class Card {

    private final int value;

    /**
     * Generate a random value to the card, it will be between 0 and maxNbr(not inclusive)
     *
     * @param maxNbr
     */
    public Card(int maxNbr) {
        value = Utils.getRand().nextInt(maxNbr);

    }

    /**
     * @return the value of the player
     */
    public int getValue() {
        return value;
    }

    @Override
    public String toString(){
        return Integer.toString(value);
    }
}
