public class main {


    public static void main(String[] args) {
        final int m = 9;
        final double nbrSimulations = 5000000;
        int mode = 0;

        Utils.init(m);

        double sommeJours = 0;

        for (int j = 0; j < 3; j++) {
            mode = j;
            for (int i = 0; i < nbrSimulations; i++) {
                double nbrJours = 0;
                Player player = new Player(mode);
                do {
                    player.pickACard();
                    nbrJours++;
                } while (!player.isFinished());

                sommeJours += nbrJours;
            }

            System.out.println("Mode " + mode + " : nombre de jours moyen : " + (sommeJours / nbrSimulations));
            sommeJours = 0;
        }
    }
}
