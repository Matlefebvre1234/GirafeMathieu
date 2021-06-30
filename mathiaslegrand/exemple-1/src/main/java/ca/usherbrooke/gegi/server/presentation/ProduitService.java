package ca.usherbrooke.gegi.server.presentation;

import ca.usherbrooke.gegi.server.business.Item_inventaire;
import ca.usherbrooke.gegi.server.business.Produit;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import java.sql.*;
import java.util.ArrayList;

/**
 * Contient toutes les methodes pouvant etre appelees dans le frontend qui ont rapport aux produits
 * @author Mathieu Lefebvre
 * @version 1.0
 * @see DataBase
 */
@Path("/produit")
public class ProduitService extends Application {

    //localhost:8080/exemple-1/api/produit/salut
    @GET
    @Path("/listeproduits")
    @Produces("application/json")
    public ArrayList<Produit> listeProduits()
    {
        DataBase database = DataBase.getInstance();
        return database.getListeProduit();
    }

    @GET
    @Path("/inventaire")
    @Produces("application/json")
    public ArrayList<Item_inventaire> getInventaire()
    {
        DataBase database = DataBase.getInstance();
        return database.getInventaire();
    }

    @POST
    @Path("/getProduit")
    @Produces("application/json")
    public Produit getProduit(@FormParam("cip") int idProduit)
    {
        DataBase database = DataBase.getInstance();
        System.out.println(idProduit);
        return database.getProduit(idProduit);
    }
}
