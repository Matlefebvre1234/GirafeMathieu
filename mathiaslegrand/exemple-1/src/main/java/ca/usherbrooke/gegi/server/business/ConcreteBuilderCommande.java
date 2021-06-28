package ca.usherbrooke.gegi.server.business;

import java.lang.reflect.Array;
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
    public void construireCommande(int idCcommande, String cip, Date date, int prixTotal, int idEtatCommande, ArrayList<Item_Commander> liste) {
        resultat.setId_commande(idCcommande);
        resultat.setCip(cip);
        resultat.setDate(date);
        resultat.setPrix_total(prixTotal);
        resultat.setId_etat_commande(idEtatCommande);
        resultat.setListeItem(liste);
    }

    /**
     * Methode qui permet de construire les item_commander de la commande
     */
    @Override
    public void constuireItemCommander() {
        //un peu useless
    }

    /**
     * Methode qui permet de construire les produits des item_commander
     */
    @Override
    public void construireProduit() {
        //un peu useless
    }

    /**
     * Getter
     * @return resultat de la commande
     */
    public Commande getResult(){
        return resultat;
    }
}
