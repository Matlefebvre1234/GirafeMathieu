package ca.usherbrooke.gegi.server.business;

/**
 * Implementation concrete du builder de commande
 * @author Mathieu Lefebvre
 * @version 1.0
 * @see BuilderProduit
 */
public class ConcreteBuilderProduit implements builderProduit{

    /**
     * Construit un produit avec les données utiles pour l'interface utilisateur client
     * @param idproduit id produit
     * @param nomitem nom de l'item
     * @param description description de l'item
     * @param prix prix de l'item
     * @param taille taille de l'item
     * @param couleur couleur de l'item
     * @param visibilite_site visibilite de l'item sur le site
     * @param id_etat etat de l'itenm (precommande, etc.)
     * @return
     */
    @Override
    public Produit construireProduitInterface(int idproduit, String nomitem, String description, int prix, String taille, String couleur, int visibilite_site, int id_etat) {
        Produit po = new Produit();

        po.setIdproduit(idproduit);
        po.setNomitem(nomitem);
        po.setDescription(description);
        po.setPrix(prix);
        po.setTaille(taille);
        po.setCouleur(couleur);
        po.setVisibilite_site(visibilite_site);
        po.setId_etat(id_etat);
        return po;
    }

    /**
     * Construit un produit avec toutes les données nécessaires pour un produit utilise a des fins de logistique
     * @param idproduit id du produit
     * @param nomitem nom de l'item
     * @param prix prix de l'item
     * @param visibilite_site visibilite de l'item sur le site
     * @param id_etat etat de l'item
     * @return
     */
    @Override
    public Produit construireProduitLogique(int idproduit, String nomitem, int prix, int visibilite_site, int id_etat) {
        Produit po = new Produit();

        po.setIdproduit(idproduit);
        po.setNomitem(nomitem);
        po.setPrix(prix);
        po.setVisibilite_site(visibilite_site);
        po.setId_etat(id_etat);
        return po;
    }
}
