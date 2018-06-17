import java.util.Random;

public class Utils {

    private static Utils ourInstance = new Utils();
    private static final int seed = 0x133EE7F;

    private static Random rand;

    private static int m = 9;

    private Utils(){

    }



    /**
     * sets the number of different card
     *
     * @param maxCardsNumber : number of different card
     */
    public static void init(int maxCardsNumber){
        m = maxCardsNumber;
        rand = new Random(seed);
    }

    /**
     * @return the random generator
     */
    public static Random getRand() {
        return rand;
    }

    /**
     * @return the number of different card
     */
    public static int getM() {
        return m;
    }

}
