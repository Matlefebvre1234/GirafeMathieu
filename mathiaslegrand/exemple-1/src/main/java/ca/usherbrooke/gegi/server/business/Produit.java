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


    /**
     * Constructeur de produit avec des photos
     */
    public Produit()
    {
        arrayPhoto = new ArrayList<String>();
    }

    /**
     * La fonction retourne les photos d'un produit
     * @return
     */
    public ArrayList<String> getArrayPhoto()
    {
        return arrayPhoto;
    }

    /**
     * La fonction change les photos d'un produit
     * @param a
     */
    public void setArrayPhoto(ArrayList<String> a)
    {
        arrayPhoto = a;
    }

    /**
     * La fonction ajoute une photo d'un objet 
     * @param url
     */
    public void addPhoto(String url)
    {
        arrayPhoto.add(url);
    }

    /**
     * La fonction enleve une photo d'un objet
     * @param url
     */
    public void deletePhoto(String url)
    {
        arrayPhoto.remove(url);
    }


    /**
     * La fonction retourne le nom d'un produit
     * @return
     */
    public String getNomitem() {
        return nomitem;
    }

    /**
     * La fonction change le nom d'un produit
     * @param nomitem
     */
    public void setNomitem(String nomitem) {
        this.nomitem = nomitem;
    }

    /**
     * La fonction retourne la description d'un produit
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * La fonction change la description d'un produit
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * La fonction retourne l'id d'un produit
     * @return
     */
    public int getIdproduit() {
        return idproduit;
    }

    /**
     * La fonction change l'id d'un produit
     * @param idproduit
     */
    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    /**
     * La fonction retourne le prix d'un produit
     * @return
     */
    public int getPrix() {
        return prix;
    }

    /**
     * La fonction change le prix d'un produit
     * @param prix
     */
    public void setPrix(int prix) {
        this.prix = prix;
    }

    /**
     * La fonction retourne la taille d'un produit
     * @return
     */
    public String getTaille() {
        return taille;
    }

    /**
     * La fonction change la taille d'un produit
     * @param taille
     */
    public void setTaille(String taille) {
        this.taille = taille;
    }

    /**
     * La fonction retourne la couleur d'un produit
     * @return
     */
    public String getCouleur() {
        return couleur;
    }

    /**
     * La fonction change la couleur d'un produit
     * @param couleur
     */
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    /**
     * La fonction retourne l'etat de visibilite d'un produit sur le site
     * @return
     */
    public int getVisibilite_site() {
        return visibilite_site;
    }

    /**
     * La fonction change l'etat de visibilite d'un produit sur le site
     * @param visibilite_site
     */
    public void setVisibilite_site(int visibilite_site) {
        this.visibilite_site = visibilite_site;
    }

    /**
     * La fonction retourne l'id etat d'un produit
     * @return
     */
    public int getId_etat() {
        return id_etat;
    }

    /**
     * La fonction change l'id etat d'un produit
     * @param id_etat
     */
    public void setId_etat(int id_etat) {
        this.id_etat = id_etat;
    }
}
