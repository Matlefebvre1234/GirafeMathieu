package ca.usherbrooke.gegi.server.business;

/**
 * Etudiant peut etre un client ou un administrateur du site
 * @author Mathieu Lefebvre
 * @version 1.0
 */
public class Etudiant {

    /**
     * Cip unique de l'etudiant
     */
    private String cip;

    /**
     * Adresse couriel de l'etudiant
     */
    private String courriel;

    /**
     * Nom de l'etudiant
     */
    private String nom;

    /**
     * Prenom de l'etudiant
     */
    private String prenom;

    /**
     * Cette fonction retourne le cip de l'etudiant
     * @return
     */
    public String getCip() {
        return cip;
    }

    /**
     * Cette fonction change le cip de l'etudiant
     * @param cip
     */
    public void setCip(String cip) {
        this.cip = cip;
    }

    /**
     * Cette fonction retourne l'adresse courriel de l'etudiant
     * @return
     */
    public String getCourriel() {
        return courriel;
    }

    /**
     * Cette fonction change l'adresse courriel de l'etudiant
     * @param courriel
     */
    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    /**
     * Cette fonction retourne le nom de l'etudiant
     * @return
     */
    public String getNom(){return nom;}

    /**
     * Cette fonction change le nom de l'etudiant
     * @param nom
     */
    public void setNom(String nom){this.nom = nom;}

    /**
     * Cette fonction retourne le prenom de l'etudiant
     * @return
     */
    public String getPrenom(){return prenom;}

    /**
     * Cette fonction change le prenom de l'etudiant
     * @param prenom
     */
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