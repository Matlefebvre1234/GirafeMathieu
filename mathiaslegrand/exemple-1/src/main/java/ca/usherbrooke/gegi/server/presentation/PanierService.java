package ca.usherbrooke.gegi.server.presentation;

import ca.usherbrooke.gegi.server.business.ConcreteBuilderProduit;
import ca.usherbrooke.gegi.server.business.Panier;
import ca.usherbrooke.gegi.server.business.Produit;

import javax.ws.rs.*;
import java.util.ArrayList;

@Path("/Panier")
public class PanierService {

    /**
     * Cette fonction permet d'aller chercher le panier associer a un cip suite a une demande sur l'interface
     * @param cip
     * @return
     */
    @POST
    @Path("/getPanier")
    @Produces("application/json")
    public Panier getPanier(@FormParam("cip") String cip)
    {
        DataBase database = DataBase.getInstance();
        Panier panier;
        panier = database.getPanier(cip);
        return panier;
    }

    /**
     * Cette fonction permet d'ajouter un panier pour un cip suite a une demande de l'interface
     * @param cip
     */
    @POST
    @Path("/ajouterPanier")
    public void ajouterPanier(@FormParam("cip") String cip){
        Panier panier;
        DataBase dataBase = DataBase.getInstance();
        panier = dataBase.getPanier(cip);

        if(panier.getIdPanier() == 2147483647){
            dataBase.creerPanier(cip);
            dataBase.getPanier(cip);
        }
    }

    /**
     * Cette fonction permet d'ajouter des item au panier
     * @param quantite
     * @param idproduit
     */
    @POST
    @Path("/ajouterItemPanier")
    public void ajouterItemPanier(@FormParam("quantite") int quantite, @FormParam("idproduit") int idproduit){


    }
}
