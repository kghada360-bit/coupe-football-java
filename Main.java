package coupe;

import java.util.Scanner;

public class Main {
     
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        Tournoi tournoi = new Tournoi();
        boolean quitter = false;

        while (!quitter) {
            afficherMenu();
            System.out.print("Votre choix : ");
            String choix = sc.nextLine();

            switch (choix) {
                case "1":
                    tournoi.entrerEquipes(sc);
                    animation("Chargement des équipes");
                    break;
                case "2":
                    System.out.print("Niveau (1/8,1/4,1/2,Finale,3e place) : ");
                    String niveau = sc.nextLine();
                    tournoi.organiserNiveau(niveau, sc);
                    animation("🎮 Organisation du niveau " + niveau);
                    break;
                case "3":
                    tournoi.afficherCalendrier();
                    break;
                case "4":
                    tournoi.afficherEquipes();
                    break;
                case "5":
                    tournoi.afficherEliminees();
                    break;
                case "6":
                    tournoi.afficherEnCompetition();
                    break;
                case "7":
                    tournoi.afficherClassementFinal();

                    // animation simple
                    System.out.print("\nNom du champion : ");
                    String champ = sc.next();  
                    championSimple(champ);

                    
                    break;
                case "8":
                    System.out.println("Fin du programme.");
                    quitter = true;
                    break;
                default:
                    System.out.println("⚠ Choix invalide !");
            }

            if (!quitter) {
                System.out.println("\nAppuyez sur Entrée pour continuer...");
                sc.nextLine();
            }
        }

        sc.close();
    }

    public static void afficherMenu() {
        System.out.println("\n==================== MENU ==========================");
        System.out.println("********.1. Entrer les équipes***********************");
        System.out.println("********.2. Organiser un niveau**********************");
        System.out.println("********.3. Afficher le calendrier*******************");
        System.out.println("********.4. Liste des équipes + matchs gagnés********");
        System.out.println("********.5. Équipes éliminées************************");
        System.out.println("********.6. Équipes encore en compétition************");
        System.out.println("********.7. Classement final*************************");
        System.out.println("********.8. Quitter**********************************");
        System.out.println("=====================================================\n");
    }

    public static void animation(String message) throws InterruptedException {
        System.out.print(message);
        for (int i = 0; i < 5; i++) {
            System.out.print(".");
            Thread.sleep(300); 
        }
        System.out.println(" OK !");
    }
    public static void championSimple(String nom) {
        System.out.println("\n==========================");
        System.out.println("🏆 CHAMPION DU TOURNOI 🏆");
        System.out.println("==========================");

        for (int i = 0; i < 3; i++) {
            System.out.println(">>> " + nom + " <<<");
            try {
                Thread.sleep(400);
            } catch (Exception e) {}
        }

        System.out.println("==========================");
    }

}
