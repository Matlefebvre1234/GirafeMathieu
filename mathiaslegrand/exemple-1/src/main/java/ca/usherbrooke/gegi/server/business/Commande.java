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

    private int idCommande;
    private float prixTotal;
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
    }
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
    public float getPrixTotal() {
        return prixTotal;
    }

    /**
     * Cette fonction change le prix total de la commande
     * @param prixTotal
     */
    public void setPrixTotal(float prixTotal) {
        this.prixTotal = prixTotal;
    }

    /**
     * Cette fonction retourne l'id de la commande
     * @return
     */
    public int getIdCommande() {
        return idCommande;
    }

    /**
     * Cette fonction change l'id de la commande
     * @param idCommande
     */
    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
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
    public int getIdEtatCommande() {
        return id_etat_commande;
    }

    /**
     * Cette fonction change l'id de l'etat de la commande
     * @param id_etat_commande
     */
    public void setIdEtatCommande(int id_etat_commande) {
        this.id_etat_commande = id_etat_commande;
    }

    /**
     * Cette fonction change la liste d'item commander
     * @param listeItem
     */
    public void setListeItem(ArrayList<Item_Commander> listeItem) {
        this.listeItem = listeItem;
    }

    /**
     * Cette fonction retourne la liste d'item commander
     * @return
     */
    public ArrayList<Item_Commander> getListeItem() {
        return listeItem;
    }
}
