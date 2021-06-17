package ca.usherbrooke.gegi.server.business;

public class Produit {
    private String nomitem;
    private int idproduit;
    private String description;
    private int prix;
    private char taille;
    private String couleur;
    private int visibilite_site;
    private String id_etat;

    public String getNomitem() {
        return nomitem;
    }

    public void setNomitem(String nomitem) {
        this.nomitem = nomitem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public char getTaille() {
        return taille;
    }

    public void setTaille(char taille) {
        this.taille = taille;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getVisibilite_site() {
        return visibilite_site;
    }

    public void setVisibilite_site(int visibilite_site) {
        this.visibilite_site = visibilite_site;
    }

    public String getId_etat() {
        return id_etat;
    }

    public void setId_etat(String id_etat) {
        this.id_etat = id_etat;
    }
}
