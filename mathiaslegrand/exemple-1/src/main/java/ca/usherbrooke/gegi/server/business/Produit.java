package ca.usherbrooke.gegi.server.business;

import java.util.ArrayList;

/**
 * La classe contient toutes les informations utiles pour instancier un produit provenant de la base de donnees
 * @author Mathieu Lefebvre
 * @version 1.0
 */
public class Produit {
    private String nomitem;
    private int idproduit;
    private String description;
    private int prix;
    private String taille;
    private String couleur;
    private int visibilite_site;
    private int id_etat;
    private ArrayList<String> arrayPhoto;

   public Produit()
    {
        arrayPhoto = new ArrayList<String>();
    }


    public ArrayList<String> getArrayPhoto()
    {
        return arrayPhoto;
    }

    public void setArrayPhoto(ArrayList<String> a)
    {
        arrayPhoto = a;
    }

    public void addPhoto(String url)
    {
        arrayPhoto.add(url);
    }

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

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
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

    public int getId_etat(int anInt) {
        return id_etat;
    }

    public void setId_etat(int id_etat) {
        this.id_etat = id_etat;
    }
}
