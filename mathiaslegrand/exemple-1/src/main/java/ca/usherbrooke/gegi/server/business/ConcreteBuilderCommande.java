package ca.usherbrooke.gegi.server.business;

import java.util.ArrayList;
import java.util.Date;

/**
 * Implementation concrete du builder de commande
 * @author Mathias Gagnon
 * @version -1.0
 * @see BuilderCommande
 */
public class ConcreteBuilderCommande implements BuilderCommande{

    /**
     * commande construite
     */
    private Commande resultat;

    /**
     * Produit construits pour construire la commande
     */
    private Produit produit;

    /**
     * Item_Commander construits pour construire la commande
     */
    private ArrayList<Item_Commander> listeItems;

    /**
     * Methode qui permet de reset la commande cree
     */
    @Override
    public void reset() {

    }

    /**
     * Methode qui permet de construire la commande
     */
    @Override
    public void construireCommande(int idCcommande, String cip, Date date, int prixTotal, int idEtatCommande) {
        resultat.setId_commande(idCcommande);
        resultat.setCip(cip);
        resultat.setDate(date);
        resultat.setPrix_total(prixTotal);
        resultat.setId_etat_commande(idEtatCommande);
    }

    /**
     * Methode qui permet de construire les item_commander de la commande
     */
    @Override
    public void constuireItemCommander() {
       /*item_commander = new Item_Commander();
        item_commander.setQuantite(1 getQuantitedatabase);
        item_commander.setPrixtotal(1 getPrixTotal);
        item_commander.setId_commande(1 setidcommande);
        item_commander.setId_etat_commade(1 getEtatCommande);
        item_commander.setProduit(produit);*/
    }

    /**
     * Methode qui permet de construire les produits des item_commander
     */
    @Override
    public void construireProduit() {
        //appeler builder produit
    }

    /**
     * Getter
     * @return resultat de la commande
     */
    public Commande getResult(){
        return resultat;
    }
}
