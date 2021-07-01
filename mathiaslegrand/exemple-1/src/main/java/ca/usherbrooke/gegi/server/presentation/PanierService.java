package ca.usherbrooke.gegi.server.presentation;

import ca.usherbrooke.gegi.server.business.ConcreteBuilderProduit;
import ca.usherbrooke.gegi.server.business.Panier;
import ca.usherbrooke.gegi.server.business.Produit;

import javax.ws.rs.*;
import java.util.ArrayList;

@Path("/Panier")
public class PanierService {

    @POST
    @Path("/getPanier")
    @Produces("application/json")
    public Panier getPanier(@FormParam("cip") String cip)
    {
        DataBase database = DataBase.getInstance();
        Panier panier;
        panier = database.getPanierFromCIP(cip);
        return panier;
    }

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

    @POST
    @Path("/ajouterItemPanier")
    public void ajouterItemPanier(@FormParam("quantite") int quantite, @FormParam("idproduit") int idproduit){


    }
}
