package ca.usherbrooke.gegi.server.business;

public class ConcreteBuilderProduit implements builderProduit{
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
        po.getId_etat(id_etat);
        return po;
    }

    @Override
    public Produit construireProduitLogique(int idproduit, String nomitem, int prix, int visibilite_site, int id_etat) {
        Produit po = new Produit();

        po.setIdproduit(idproduit);
        po.setNomitem(nomitem);
        po.setPrix(prix);
        po.setVisibilite_site(visibilite_site);
        po.getId_etat(id_etat);
        return po;
    }
}
