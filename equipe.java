package coupe;

public class equipe {
    private int id;
    private String nom;
    private String couleur;
    private int matchsGagnes;
    private String niveauElimination;  
    private boolean eliminee;

    public equipe(int id, String nom, String couleur) {
        this.id = id;
        this.nom = nom;
        this.couleur = couleur;
        this.matchsGagnes = 0;
        this.eliminee = false;
        this.niveauElimination = "";
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getCouleur() { return couleur; }
    public int getMatchsGagnes() { return matchsGagnes; }
    public boolean isEliminee() { return eliminee; }
    public String getNiveauElimination() { return niveauElimination; }

    public void gagnerMatch() {
        this.matchsGagnes++;
    }

    public void eliminer(String niveau) {
        this.eliminee = true;
        this.niveauElimination = niveau;
    }

    @Override
    public String toString() {
        return id + " - " + nom + " (" + couleur + ") - Victoires : " + matchsGagnes;
    }
}

