package ca.usherbrooke.gegi.server.business;

/**
 * Interface pour le builder de produits
 * @author Mathieu Lefebvre
 * @version 1.0
 * @see ConcreteBuilderProduit
 */
public interface builderProduit {
    public Produit construireProduitInterface(int idproduit, String nomitem, String description, int prix, String taille , String couleur , int visibilite_site , int id_etat);
    public Produit construireProduitLogique(int idproduit, String nomitem, int prix, int visibilite_site , int id_etat);
}
