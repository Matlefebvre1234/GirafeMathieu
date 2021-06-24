package ca.usherbrooke.gegi.server.business;

public class ConcreteBuilderCommande implements BuilderCommande{

    private Commande resultat;
    private Produit produit;
    private Item_Commander item_commander;

    @Override
    public void reset() {

    }

    @Override
    public void construireCommande() {
        /*resultat.setId_commande(1 /*getIdCommande*/);
        resultat.setCip("1" /*getCip*/);
        resultat.setDate("1" /*getDate*/);
        resultat.setPrix_total(1 /*prixtotal*/);
        resultat.setId_etat_commande(1 /*etat*/);
        //resultat.setListItems();*/
    }

    @Override
    public void constuireItemCommander() {
        /*item_commander = new Item_Commander();
        item_commander.setQuantite(1 /*getQuantitedatabase*/);
        item_commander.setPrixtotal(1 /*getPrixTotal*/);
        item_commander.setId_commande(1 /*setidcommande*/);
        item_commander.setId_etat_commade(1 /*getEtatCommande*/);
        item_commander.setProduit(produit);*/
    }

    @Override
    public void construireProduit() {
        //appeler builder produit
    }

    public Commande getResult(){
        return resultat;
    }
}
