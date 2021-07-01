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
    @Path("/listeproduitsdistinct")
    @Produces("application/json")
    public ArrayList<Produit> listeProduitsDistinct()
    {
        Boolean doublons = false;
        DataBase database = DataBase.getInstance();
        ArrayList<Produit> produitsDistincts = new ArrayList<>();
        ArrayList<Produit> produits = database.getListeProduit();
        //produitsDistincts.add(produits.get(0));

        for(int i = 0; i < produits.size(); i++){
            for(int y = 0; y < produitsDistincts.size(); y++){
                if(produits.get(i).getNomitem().equals(produitsDistincts.get(y).getNomitem())){
                    doublons = true;
                    System.out.println("doublons");
                }

                System.out.println("produit distinct: " + produitsDistincts.get(y).getNomitem() + " == " + produits.get(i).getNomitem());
            }

            if(doublons == false)
            {
                System.out.println("ajoute: " + produits.get(i).getNomitem());
                produitsDistincts.add(produits.get(i));
            }

            doublons = false;
        }

        return produitsDistincts;
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
        System.out.println("ariel2.0: "+ idProduit);
        return database.getProduit(idProduit);
    }

    @POST
    @Path("/getTaillesProduit")
    @Produces("application/json")
    public ArrayList<String> getTaillesProduit(@FormParam("id") int idProduit)
    {
        DataBase database = DataBase.getInstance();
        return database.getTailleProduit(idProduit);
    }
    
}
