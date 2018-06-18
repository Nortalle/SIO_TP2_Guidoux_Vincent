/*
 -----------------------------------------------------------------------------
 Labo        : SIO -  TP2
 Fichier     : main.java
 Auteur      : Vincent Guidoux
 Date        : 17.06.2018

 But         : Implémente un jeu de hasard dont l’objectif est d’obtenir au
               moins un exemplaire de chacune des m cartes existantes dans le
               jeu. Chaque participant reçoit chaque jour une carte choisie au
               hasard (les choix sont indépendants les uns des autres et
               suivent une loi uniforme). Si un joueur possède certaines cartes
               en plus d’un exemplaire il peut les échanger en utilisant une
               des deux règles qui suivent.

               1. Il est possible d’échanger instantanément deux cartes
               différentes et possédées en au moins deux exemplaires chacune
               contre une nouvelle carte choisie au hasard mais différentes des
               deux premières.
               2. Il est possible d’échanger instantanément deux cartes identiques
               mais possédées en au moins trois exemplaires contre une nouvelle
               carte choisie hasard et différente.

               Et il y a trois mode de jeux possibles :
               1. Le joueur n’effectue aucun échange.
               2. Le joueur utilise uniquement la première règle pour effectuer
               des échanges et il l’utilise dès que cela est possible.
               3. Le joueur utilise les deux règles et effectue un échange dès
               qu’une des deux conditions est remplie.

               Ce programme acceptent comme paramètres d’entrée :
                ⊲ le nombre m >= 3de cartes dans le jeu,
                ⊲ un entier positif n de simulations à réaliser,
                ⊲ un des trois modes de jeu exposés ci-dessus.
 -----------------------------------------------------------------------------
*/

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m;

        do {
            System.out.print("Veuillez entrer le nombre de cartes différentes : ");
            m = scanner.nextInt();
            System.out.println();
            //Nous voulons un minimum de 3 cartes dans le jeu
        } while (m < 3);

        System.out.print("Veuillez entrer le nombre de simulations à effectuer: ");
        double nbrSimulations = scanner.nextDouble();
        System.out.println();

        System.out.print("Veuillez choisir votre mode (1-2-3): ");
        int mode = scanner.nextInt() - 1;
        System.out.println();

        double sommeJours = 0;
        double sommeCarree = 0;

        //Nous initialisons Le générateur aléatoire
        Utils.init(m);

        //On commence les simulations
        for (int i = 0; i < nbrSimulations; i++) {
            double nbrJours = 0;
            //Nous créons un joueur
            Player player = new Player(mode);

            //Il joue jusqu'à ce qu'il ait un exemplaire de chaque carte
            do {
                player.pickACard();
                nbrJours++;
            } while (!player.isFinished());

            //On met à jour les variables qui vont nous aider à calculer la moyenne, la variance
            // et l'écart-type
            sommeJours += nbrJours;
            sommeCarree += (nbrJours * nbrJours);
        }

        double moyenne = (sommeJours / nbrSimulations);
        double variance = ((sommeCarree / nbrSimulations) - (moyenne * moyenne));

        System.out.println("Mode " + mode + " : nombre de jours moyen : " + moyenne);
        System.out.println("Mode " + mode + " : Variance : " + variance);
        System.out.println("Mode " + mode + " : Ecart-type : " + Math.sqrt(variance));
    }
}
