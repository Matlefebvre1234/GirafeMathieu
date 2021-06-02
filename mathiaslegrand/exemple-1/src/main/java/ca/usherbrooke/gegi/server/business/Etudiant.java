package ca.usherbrooke.gegi.server.business;


public class Etudiant {
    private String cip;
    private String courriel;
    private String nom;
    private String prenom;

    public String getCip() {
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public String getNom(){return nom;}

    public void setNom(String nom){this.nom = nom;}

    public String getPrenom(){return prenom;}
    public void setPrenom(String prenom){this.prenom = prenom;}

    /*@Override
    public String toString() {
        return "Etudiant{" +
                ", cip='" + cip + '\'' +
                ", nom= " + nom + ", prenom= " + prenom +
                ", courriel='" + courriel + '\'' +
                '}';
    }*/
}