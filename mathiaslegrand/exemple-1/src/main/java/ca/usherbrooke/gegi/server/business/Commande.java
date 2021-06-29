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
    private Date date;
    private int id_commande;
    private float prix_total;
    private String cip;
    private int id_etat_commande;
    private ArrayList<Item_Commander> listeItem;

    public Commande(){
        listeItem = new ArrayList<>();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(float prix_total) {
        this.prix_total = prix_total;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public String getCip() {
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }

    public int getId_etat_commande() {
        return id_etat_commande;
    }

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
