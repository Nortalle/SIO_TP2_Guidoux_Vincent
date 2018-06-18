/*
 -----------------------------------------------------------------------------
 Labo        : SIO -  TP2
 Fichier     : Utils.java
 Auteur      : Vincent Guidoux
 Date        : 17.06.2018

 But         : Implémente une classe utile qui stocke un générateur random et
               le nombre de cartes à jouer

 Commentaire : Classe très pratique pour les tests et génération de résultats
               en chaîne
 -----------------------------------------------------------------------------
*/

import java.util.Random;

public class Utils {

    //C'est un singleton
    private static Utils ourInstance = new Utils();

    //Graine que nous devions utiliser
    private static final int seed = 0x133EE7F;

    private static Random rand;
    private static int m = 9;

    private Utils() {
    }

    /**
     * définit le nombre de cartes avec lequel le joueur va jouer
     * et initialise le générateur aléatoire
     *
     * @param maxCardsNumber : le nombre de cartes avec lequel le joueur va jouer
     */
    public static void init(int maxCardsNumber) {
        m = maxCardsNumber;
        rand = new Random(seed);
    }

    /**
     * @return le générateur àléatoire
     */
    public static Random getRand() {
        return rand;
    }

    /**
     * @return le nombre de cartes avec lequel le joueur va jouer
     */
    public static int getM() {
        return m;
    }

}
