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

/**
 * NOTES IMPORTANTES TOUS LES API SERVENT G/N/RALEMENT DE LIEN ENTRE L'INTERFACE GRAPHIQUE ET LE CODE EN JAVA/BASE DE DONNEES
 */
@Path("/inventaire")
public class InventaireService extends Application {

    /**Microservice
     * Cette fonction permet de recuperer tous les produits
     * @return
     */
    @GET
    @Path("/listeproduits")
    @Produces("application/json")
    public ArrayList<Produit> listeProduits()
    {
        DataBase database = DataBase.getInstance();
        return database.getListeProduit();
    }

    /**
     * Cette fonction permet de recuperer les items dans l'inventaire
     * @return
     */
    @GET
    @Path("/inventaire")
    @Produces("application/json")
    public ArrayList<Item_inventaire> getInventaire()
    {
        DataBase database = DataBase.getInstance();
        return database.getInventaire();
    }

    /**
     * Cette fonction permet d'inserer des nouveaux  produits dans la base de donnees via l'interface graphique
     * @param idProduit
     * @param quantite
     */
    @POST
    @Path("/insererProduitInventaire")
    public void insererProduitInventaire(@FormParam("id") int idProduit, @FormParam("quantite") int quantite){
        DataBase dataBase = DataBase.getInstance();
        dataBase.ajouterItemInventaire(idProduit, quantite);
    }
}
