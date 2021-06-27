package ca.usherbrooke.gegi.server.business;

/**
 * La classe contient toutes les informations pertinentes lorsqu'un client commande un produit
 * @author Patrick Normandin
 * @version 1.0
 * @see Produit
 */
public class Item_Commander {

    private int id_item_commander;
    private int quantite;
    private float prixtotal;
    private int id_commande;
    private int idproduit;
    private int id_etat_commade;

    public int getId_item_commander() {
        return id_item_commander;
    }

    public void setId_item_commander(int id_item_commander) {
        this.id_item_commander = id_item_commander;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrixtotal() {
        return prixtotal;
    }

    public void setPrixtotal(float prixtotal) {
        this.prixtotal = prixtotal;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public int getId_etat_commade() {
        return id_etat_commade;
    }

    public void setId_etat_commade(int id_etat_commade) {
        this.id_etat_commade = id_etat_commade;
    }
}
