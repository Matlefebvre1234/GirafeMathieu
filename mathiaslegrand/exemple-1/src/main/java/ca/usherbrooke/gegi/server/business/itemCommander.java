package ca.usherbrooke.gegi.server.business;

import java.util.Date;

/**
 * La classe contient toutes les informations pertinentes lorsqu'un client commande un produit
 * @author Patrick Normandin
 * @version 1.0
 * @see Produit
 */
public class itemCommander {

    private int id_item_commander;
    private String date;
    private int quantite;
    private float prixtotal;
    private int id_commande;
    private int idproduit;
    private int id_etat_commade;
    private Produit produit;

    /**
     * Cette fonction retourne l'id de l'item commander
     * @return
     */
    public int getId_item_commander() {
        return id_item_commander;
    }

    /**
     * Cette fonction change l'id de l'item commander
     * @param id_item_commander
     */
    public void setId_item_commander(int id_item_commander) {
        this.id_item_commander = id_item_commander;
    }

    /**
     * Cette fonction retourne la quantite d'un item commander
     * @return
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * Cette fonction change la quantite d'un item commander
     * @param quantite
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /**
     * Cette fonction retourne le prix total d'un item commander
     * @return
     */
    public float getPrixtotal() {
        return prixtotal;
    }

    /**
     * Cette fonction change le prix totale d'un item commander
     * @param prixtotal
     */
    public void setPrixtotal(float prixtotal) {
        this.prixtotal = prixtotal;
    }

    /**
     * Cette fonction retourne le id de la commande
     * @return
     */
    public int getId_commande() {
        return id_commande;
    }

    /**
     * Cette fonction change l'id de la commande
     * @param id_commande
     */
    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    /**
     * Cette fonction retourne l'id du produit commander
     * @return
     */
    public int getIdproduit() {
        return idproduit;
    }

    /**
     * Cette fonction change l'id du produit commander
     * @param idproduit
     */
    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    /**
     * Cette fonction retourne l'id de l'etat de la commande
     * @return
     */
    public int getId_etat_commade() {
        return id_etat_commade;
    }

    /**
     * Cette fonction change l'id de l'etat de la commande
     * @param id_etat_commade
     */
    public void setId_etat_commade(int id_etat_commade) {
        this.id_etat_commade = id_etat_commade;
    }

    /**
     * Cette fonction retourne le produit commander
     * @return
     */
    public Produit getProduit() {
        return produit;
    }

    /**
     * Cette fonction change le produit commander
     * @param produit
     */
    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    /**
     * Cette fonction change la date de la commande
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Cette fonction retourne la date de la commande
     * @return
     */
    public String getDate() {
        return date;
    }
}
