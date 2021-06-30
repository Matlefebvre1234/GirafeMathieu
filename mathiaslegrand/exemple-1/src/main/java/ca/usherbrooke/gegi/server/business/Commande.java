package ca.usherbrooke.gegi.server.business;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe qui regroupe toutes les informations d'une commande
 * @author Patrick Normandin
 * @version 1.0
 * @see Item_Commander
 */

public class Commande {

    private int id_commande;
    private float prix_total;
    private String cip;
    private int id_etat_commande;
    private String date;
    private ArrayList<Item_Commander> listeItem;

    public Commande(){
        listeItem = new ArrayList<>();
    }

    /**
     * Cette fonction retourne la date de la commande
     * @return
     */
    public String getDate() {
       return date;
    }

    public void setDate(Date date) {
        this.date = date.toString();
    /**
     * Cette fonction change la date de la commande
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Cette fonction retourne le prix total de la commande
     * @return
     */
    public float getPrix_total() {
        return prix_total;
    }

    /**
     * Cette fonction change le prix total de la commande
     * @param prix_total
     */
    public void setPrix_total(float prix_total) {
        this.prix_total = prix_total;
    }

    /**
     * Cette fonction retourne l'id de la commande
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
     * Cette fonction retourne le cip de l'etudiant associe a la commande
     * @return
     */
    public String getCip() {
        return cip;
    }

    /**
     * Cette fonction change le cip de l'etudiant associe a la commande
     * @param cip
     */
    public void setCip(String cip) {
        this.cip = cip;
    }

    /**
     * Cette fonction retourne l'id de l'etat de la commande
     * @return
     */
    public int getId_etat_commande() {
        return id_etat_commande;
    }

    /**
     * Cette fonction change l'id de l'etat de la commande
     * @param id_etat_commande
     */
    public void setId_etat_commande(int id_etat_commande) {
        this.id_etat_commande = id_etat_commande;
    }

    public void setListeItem(ArrayList<Item_Commander> listeItem) {
        this.listeItem = listeItem;
    }

    public ArrayList<Item_Commander> getListeItem() {
        return listeItem;
    }
}
