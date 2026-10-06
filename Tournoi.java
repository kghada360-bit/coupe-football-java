package coupe;

import java.util.*;

public class Tournoi {
    private ArrayList<equipe> equipes = new ArrayList<>();
    private ArrayList<equipe> qualifies = new ArrayList<>();
    private HashMap<String, ArrayList<Match>> calendrier = new HashMap<>();

    
    public void entrerEquipes(Scanner sc) {
        equipes.clear();

        ArrayList<equipe> equipesParDefaut = new ArrayList<>(Arrays.asList(
            new equipe(1, "Est", "Rouge"),
            new equipe(2, "CA", "Blanc"),
            new equipe(3, "ST", "Vert"),
            new equipe(4, "Bizerte", "Jaune"),
            new equipe(5, "Monastir", "Bleu"),
            new equipe(6, "CSS", "Noir"),
            new equipe(7, "ESS", "RougeBlanc"),
            new equipe(8, "Real Madrid", "Blanc"),
            new equipe(9, "Barcelone", "Bleu"),
            new equipe(10, "Liverpool", "Rouge"),
            new equipe(11, "City", "Cyan"),
            new equipe(12, "Juventus", "Noir"),
            new equipe(13, "Chelsea", "Bleu"),
            new equipe(14, "Tottenham", "Blanc"),
            new equipe(15, "Atl Madrid", "Rouge"),
            new equipe(16, "Tunisie", "Rouge")
        ));

        String reponse;
        do {
            System.out.print("Voulez-vous utiliser les équipes par défaut ? (O/N) : ");
            reponse = sc.nextLine().trim();
            if (!reponse.equalsIgnoreCase("O") && !reponse.equalsIgnoreCase("N")) {
                System.out.println("⚠ Réponse invalide ! Veuillez taper O ou N.");
            }
        } while (!reponse.equalsIgnoreCase("O") && !reponse.equalsIgnoreCase("N"));

        if (reponse.equalsIgnoreCase("O")) {
            equipes.addAll(equipesParDefaut);
            System.out.println("✔ Équipes par défaut chargées !");
        } else {
            System.out.println("=== Entrée des 16 équipes ===");
            for (int i = 1; i <= 16; i++) {
                int id;
                while (true) {
                    try {
                        System.out.print("ID équipe : ");
                        id = Integer.parseInt(sc.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("⚠ Veuillez entrer un nombre entier !");
                    }
                }
                System.out.print("Nom équipe : ");
                String nom = sc.nextLine();
                System.out.print("Couleur : ");
                String couleur = sc.nextLine();
                equipes.add(new equipe(id, nom, couleur));
            }
            System.out.println("✔ Toutes les équipes ont été enregistrées !");
        }

       
        qualifies.clear();
        qualifies.addAll(equipes);
    }

    
    public void organiserNiveau(String niveauInput, Scanner sc) {
        String niveau = niveauInput.trim().toLowerCase();
        ArrayList<equipe> equipesNiveau = new ArrayList<>();

        switch (niveau) {
            case "1/8":
            case "1/4":
            case "1/2":
            case "finale":
                equipesNiveau.addAll(qualifies);
                break;
            case "3e place":
                ArrayList<Match> demiFinales = calendrier.get("1/2");
                if (demiFinales != null) {
                    for (Match m : demiFinales) {
                        equipesNiveau.add(m.getPerdant());
                    }
                }
                break;
            default:
                System.out.println("Niveau inconnu !");
                return;
        }

        if (equipesNiveau.size() % 2 != 0) {
            System.out.println("⚠ Nombre d'équipes impair pour ce niveau !");
            return;
        }

        ArrayList<Match> matchs = new ArrayList<>();
        for (int i = 0; i < equipesNiveau.size(); i += 2) {
            Match match = new Match(equipesNiveau.get(i), equipesNiveau.get(i + 1), niveauInput);
            match.jouerMatch();
            matchs.add(match);
        }

        calendrier.put(niveauInput, matchs);

        if (!niveau.equals("3e place")) {
            qualifies.clear();
            for (Match m : matchs) {
                qualifies.add(m.getVainqueur());
            }
        }

        System.out.println("✔ Niveau " + niveauInput + " organisé !");
    }

   
    public void afficherCalendrier() {
        for (String niv : Arrays.asList("Finale", "3e place", "1/2", "1/4", "1/8")) {
            ArrayList<Match> matchs = calendrier.get(niv);
            if (matchs != null) {
                System.out.println("\n--- " + niv + " ---");
                for (Match m : matchs) {
                    System.out.println(m + " → Vainqueur : " + m.getVainqueur().getNom());
                }
            }
        }
    }

  
    public void afficherEquipes() {
        int i = 1;
        for (equipe e : equipes) {
            System.out.println(i + " - " + e.getNom() + " (" + e.getCouleur() + ") - Victoires : " + e.getMatchsGagnes());
            i++;
        }
    }

    
    public void afficherEliminees() {
        for (equipe e : equipes) {
            if (e.getNiveauElimination() != null && !e.getNiveauElimination().isEmpty()) {
                System.out.println("✖ " +e.getNom() + " éliminée au niveau " + e.getNiveauElimination());
            }
        }
    }

 
    public void afficherEnCompetition() {
        for (equipe e : equipes) {
            if (e.getNiveauElimination() == null || e.getNiveauElimination().isEmpty()) {
                System.out.println("✔ " +e.getNom());
            }
        }
    }

    
    public void afficherClassementFinal() {
        equipe vainqueurFinale = null;
        equipe finaliste = null;
        equipe vainqueur3e = null;
        equipe perdant3e = null;

        ArrayList<Match> finale = calendrier.get("Finale");
        ArrayList<Match> troisieme = calendrier.get("3e place");

        if (finale != null && !finale.isEmpty()) {
            vainqueurFinale = finale.get(0).getVainqueur();
            finaliste = finale.get(0).getPerdant();
        }

        if (troisieme != null && !troisieme.isEmpty()) {
            vainqueur3e = troisieme.get(0).getVainqueur();
            perdant3e = troisieme.get(0).getPerdant();
        }

        System.out.println("\n===== 🏆 PODIUM FINAL 🏆 =====");
        System.out.println("🥇 1 → " + (vainqueurFinale != null ? vainqueurFinale.getNom() : "Non joué"));
        System.out.println("🥈 2 → " + (finaliste != null ? finaliste.getNom() : "Non joué"));
        System.out.println("🥉 3 → " + (vainqueur3e != null ? vainqueur3e.getNom() : "Non joué"));
        System.out.println("🏅 4 → " + (perdant3e != null ? perdant3e.getNom() : "Non joué"));
        System.out.println("\n🏁 Fin du tournoi");
    }
        
}
