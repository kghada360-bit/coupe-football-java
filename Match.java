package coupe;

import java.util.Random;

public class Match {
    private equipe equipe1;
    private equipe equipe2;
    private equipe vainqueur;
    private equipe perdant;
    private String niveau;  

    public Match(equipe e1, equipe e2, String niveau) {
        this.equipe1 = e1;
        this.equipe2 = e2;
        this.niveau = niveau;
    }

    public void jouerMatch() {
        Random r = new Random();
        int resultat = r.nextInt(2);

        if (resultat == 0) {
            vainqueur = equipe1;
            perdant = equipe2;
        } else {
            vainqueur = equipe2;
            perdant = equipe1;
        }

        vainqueur.gagnerMatch();
        perdant.eliminer(niveau);
    }

    public equipe getVainqueur() { return vainqueur; }
    public equipe getPerdant() { return perdant; }
    public String getNiveau() { return niveau; }
    public String toString() {
        
        String s = equipe1.getNom() + " vs " + equipe2.getNom() + " [" + niveau + "]";
        if (vainqueur != null) {
            s += " → Vainqueur : " + vainqueur.getNom();
        }
        return s;
    }
}