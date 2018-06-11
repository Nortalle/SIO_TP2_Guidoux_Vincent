import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {



    @Test
    public void CardCreation(){

        final int m = 9;
        final int nbrSimulations = 100000;

        boolean isright = true;

        for(int i = 0; i < nbrSimulations; i++){
            Card test = new Card(m);
            isright = isright || (test.getValue() < m);
            isright = isright || (test.getValue() >= 0);
        }

        assertEquals(true, isright);
    }




}