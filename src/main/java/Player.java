import java.util.LinkedList;

public class Player {
    private final int id;
    private static int nbrJoueur = 1;
    private int[] cards;
    private int mode = 0;
    private boolean isFinished = false;
    private int length;

    public Player(int mode) {
        this.mode = mode;
        this.id = nbrJoueur++;
        this.length = Utils.getM();
        cards = new int[length];
    }

    public int getId() {
        return id;
    }

    public void pickACard() {
        switch (mode) {
            case 0: {
                cards[Utils.getRand().nextInt(length)]++;
                break;
            }
            case 1: {
                boolean swaped;
                cards[Utils.getRand().nextInt(length)]++;
                do {
                    swaped = false;
                    int nbrDoubleCard = 0;
                    int[] cardsToSwap = new int[2];

                    for (int i = 0; i < length; ++i) {
                        if (cards[i] >= 2) {
                            cardsToSwap[nbrDoubleCard] = i;
                            nbrDoubleCard++;
                            if (nbrDoubleCard == 2)
                                break;
                        }
                    }

                    if (nbrDoubleCard >= 2) {
                        cards[swapTwoValues(cardsToSwap[0], cardsToSwap[1])]++;
                        swaped = true;
                    }

                } while (swaped);
                break;
            }
            case 2: {
                cards[Utils.getRand().nextInt(length)]++;

                int nbrDoubleCard = 0;
                int tripledCard = -1;
                int[] cardsToSwap = new int[2];

                for (int i = 0; i < length; ++i) {
                    if (cards[i] >= 3) {
                        tripledCard = i;
                        break;
                    }
                    if (cards[i] >= 2) {
                        cardsToSwap[nbrDoubleCard] = i;
                        nbrDoubleCard++;

                        if (nbrDoubleCard == 2)
                            break;
                    }
                }

                if (nbrDoubleCard == 2) {
                    cards[swapTwoValues(cardsToSwap[0], cardsToSwap[1])]++;
                } else if (tripledCard != -1) {
                    cards[swapOneCard(tripledCard)]++;
                }
                break;
            }
        }
        testIsFinished();
    }

    private int swapOneCard(int tripledCard) {
        int nextCard = Utils.getRand().nextInt(length - 1);

        if (nextCard == tripledCard) {
            nextCard = length - 1;
        }
        cards[tripledCard]--;
        cards[tripledCard]--;

        return nextCard;
    }


    private int swapTwoValues(int firstValue, int secondValue) {

        int nextCard = Utils.getRand().nextInt(length - 2);

        if (nextCard == firstValue) {
            nextCard = length - 2;
        }

        if (nextCard == secondValue) {
            nextCard = length - 1;
        }
        cards[firstValue]--;
        cards[secondValue]--;

        return nextCard;
    }


    public boolean testIsFinished() {
        for (int i = 0; i < length; ++i) {
            if (cards[i] == 0) {

                return false;
            }
        }
        isFinished = true;
        return true;
    }

    @Override
    public String toString() {
        String s = "[";
        for (int i = 0; i < length; ++i) {
            s += cards[i] + " ";
        }
        s += "]";


        return Integer.toString(id) + s;
    }


    public boolean isFinished() {
        return isFinished;
    }
}
