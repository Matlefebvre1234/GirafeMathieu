package ca.usherbrooke.gegi.server.business;

public class Commande {
    private String date;
    private int id_commande;
    private float prix_total;
    private String cip;
    private int id_etat_commande;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
}
