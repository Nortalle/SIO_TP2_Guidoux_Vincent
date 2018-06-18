/*
 -----------------------------------------------------------------------------
 Labo        : SIO -  TP2
 Fichier     : Player.java
 Auteur      : Vincent Guidoux
 Date        : 17.06.2018

 But         : Implémente un joueur qui peut tirer une carte et jouer suivant
               le mode de jeu choisi
 -----------------------------------------------------------------------------
*/
public class Player {
    private int[] cards;
    private int mode = 0;
    private int length;

    public Player(int mode) {
        this.mode = mode;
        this.length = Utils.getM();
        cards = new int[length];
    }

    public void pickACard() {
        switch (mode) {

            /**
             * Le joueur n’effectue aucun échange.
             */
            case 0: {
                //On tire une carte aléatoirement
                cards[Utils.getRand().nextInt(length)]++;
                break;
            }

            /**
             * Le joueur utilise uniquement la première règle pour effectuer
             * des échanges et il l’utilise dès que cela est possible.
             */
            case 1: {
                boolean swaped;
                //On tire une carte aléatoirement
                cards[Utils.getRand().nextInt(length)]++;
                do {
                    swaped = false;
                    int nbrDoubleCard = 0;
                    int[] cardsToSwap = new int[2];

                    //On parcourt nos cartes pour chercher les deux doublons
                    for (int i = 0; i < length; ++i) {
                        if (cards[i] >= 2) {
                            cardsToSwap[nbrDoubleCard] = i;
                            nbrDoubleCard++;
                            if (nbrDoubleCard == 2)
                                break;
                        }
                    }

                    //Si deux doublons sont trouvés : règle n°1
                    if (nbrDoubleCard >= 2) {
                        cards[swapTwoValues(cardsToSwap[0], cardsToSwap[1])]++;
                        swaped = true;
                    }


                } while (swaped); //Il peut y avoir plusieurs échanges par jours avec le mode 2
                break;
            }
            /**
             * Le joueur utilise les deux règles et effectue un échange dès
             * qu’une des deux conditions est remplie.
             */
            case 2: {
                //On tire une carte aléatoirement
                cards[Utils.getRand().nextInt(length)]++;

                int nbrDoubleCard = 0;
                int tripledCard = -1;
                int[] cardsToSwap = new int[2];

                //On parcourt nos cartes pour chercher les deux doublons ou un triplés
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

                //Si deux doublons sont trouvés : règle n°1
                if (nbrDoubleCard == 2) {
                    cards[swapTwoValues(cardsToSwap[0], cardsToSwap[1])]++;

                    //Si un triplé est trouvé : règle n°2
                } else if (tripledCard != -1) {
                    cards[swapOneCard(tripledCard)]++;
                }
                break;
            }
        }
    }

    /**
     * Il est possible d’échanger instantanément deux cartes identiques mais possédées en au
     * moins trois exemplaires contre une nouvelle carte choisie hasard et différente.
     *
     * @param tripledCard : carte en trois exemplaires à échanger
     * @return la carte reçue contre tripledCard
     */
    private int swapOneCard(int tripledCard) {
        int nextCard = Utils.getRand().nextInt(length - 1);

        //Si la même carte a été choisie aléatoirement, nous prenons sont mappage
        if (nextCard == tripledCard) {
            nextCard = length - 1;
        }


        cards[tripledCard]--;
        cards[tripledCard]--;

        return nextCard;
    }


    /**
     * Il est possible d’échanger instantanément deux cartes différentes et
     * possédées en au moins deux exemplaires chacune contre une nouvelle carte
     * choisie au hasard mais différentes des deux premières.
     *
     * @param firstCard  : première carte à échanger
     * @param secondCard : deuxième carte à échanger
     * @return la carte reçue après les échanges
     */
    private int swapTwoValues(int firstCard, int secondCard) {

        int nextCard = Utils.getRand().nextInt(length - 2);

        //Si la même carte a été choisie aléatoirement, nous prenons sont mappage
        if (nextCard == firstCard) {
            nextCard = length - 2;
        }

        //Si la même carte a été choisie aléatoirement, nous prenons sont mappage
        if (nextCard == secondCard) {
            nextCard = length - 1;
        }
        cards[firstCard]--;
        cards[secondCard]--;

        return nextCard;
    }

    /**
     * @return Si le personnage a un exemplaire de chaque carte
     */
    public boolean isFinished() {
        for (int i = 0; i < length; ++i) {
            if (cards[i] == 0) {
                return false;
            }
        }
        return true;
    }
}
