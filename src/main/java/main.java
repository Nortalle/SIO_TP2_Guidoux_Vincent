import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class main {


    public static void main(String[] args) {
        final int m = 9;
        final double nbrSimulations = 5000000;
        int mode = 0;

        List<Double> resultats = new LinkedList<>();

        double sommeJours = 0;

        double sommeCarree = 0;

        for (int j = 0; j < 3; j++) {

            Utils.init(m);
            mode = j;
            for (int i = 0; i < nbrSimulations; i++) {
                double nbrJours = 0;
                Player player = new Player(mode);
                do {
                    player.pickACard();
                    nbrJours++;
                } while (!player.isFinished());
                resultats.add(nbrJours);
                sommeJours += nbrJours;
                sommeCarree += (nbrJours * nbrJours);
                if (sommeCarree < 0)
                    System.out.println(sommeCarree);
            }

            double moyenne = (sommeJours / nbrSimulations);
            double variance = ((sommeCarree / nbrSimulations) - (moyenne * moyenne));

            System.out.println("Mode " + mode + " : nombre de jours moyen : " + moyenne);
            System.out.println("Mode " + mode + " : Variance : " + variance);
            System.out.println("Mode " + mode + " : Ecart-type : " + Math.sqrt(variance));
            sommeJours = 0;
            sommeCarree = 0;
            double min = Collections.min(resultats);
            double max = Collections.max(resultats);

            int taille = (int) (max - min);

            int[] what = new int[taille + 1];

            for (int i = (int) min, k = 0; i <= (int) (max); i++, k++) {
                what[k] = Collections.frequency(resultats, (double) i);
                System.out.println(i + " jours : " + what[k]);
            }


            BufferedWriter out;
            try {
                out = new BufferedWriter(new FileWriter(new File("test" + mode + ".csv")));
                out.newLine();
                for (double s : resultats) {
                    out.write(Double.toString(s)); //toString rend :"#nom;#prenom;#mail"
                    out.newLine();
                }
                out.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            resultats.clear();
        }
    }
}
