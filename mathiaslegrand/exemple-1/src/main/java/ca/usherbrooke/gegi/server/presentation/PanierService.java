package ca.usherbrooke.gegi.server.presentation;

import ca.usherbrooke.gegi.server.business.ConcreteBuilderProduit;
import ca.usherbrooke.gegi.server.business.Panier;
import ca.usherbrooke.gegi.server.business.Produit;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.xml.crypto.Data;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/Panier")
public class PanierService {

    @Context
    HttpServletRequest httpServletRequest;

    /**
     * Cette fonction permet d'aller chercher le panier associer a un cip suite a une demande sur l'interface
     * @param cip
     * @return
     */
    @POST
    @Path("/getPanier")
    @Produces("application/json")
    public Panier getPanier(@FormParam("cip") String cip) throws SQLException {
        DataBase database = DataBase.getInstance();
        Panier panier;
        panier = database.getPanierFromCIP(cip);
        System.out.println(panier.getIdPanier());
        return panier;
    }

    public Panier getPanierBackEnd(@FormParam("cip") String cip)
    {
        DataBase database = DataBase.getInstance();
        Panier panier;
        panier = database.getPanier(cip);
        return panier;
    }

    @GET
    @Path("/ajouterPanier")
    public void ajouterPanier(){

        System.out.println("ajouterPanier a ete appelee");
        Principal principal = httpServletRequest.getUserPrincipal();

        Panier panier;
        DataBase dataBase = DataBase.getInstance();
        panier = dataBase.getPanier(principal.getName());

        if(panier.getIdPanier() == 2147483647){
            dataBase.creerPanier(principal.getName());
            dataBase.getPanier(principal.getName());
        }
    }

    /**
     * Cette fonction permet d'ajouter des item au panier
     */
    @POST
    @Path("/ajouterItemPanier")
    public void ajouterItemPanier(@FormParam("id") int idProduit, @FormParam("quantite") int quantite, @FormParam("taille") String taille){

        Principal principal = httpServletRequest.getUserPrincipal();

        Panier panier = null;
        try {
            panier = getPanier(principal.getName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(panier.getIdPanier() != getPanierBackEnd(principal.getName()).getIdPanier()){
            panier = getPanierBackEnd(principal.getName());
        }

        System.out.println("idProduit: " + idProduit);
        System.out.println("quantite: " + quantite);
        System.out.println("idPanier: " + panier.getIdPanier());
        System.out.println("taille: " + taille);

        DataBase database = DataBase.getInstance();
        database.ajouterItemPanier(idProduit, quantite, panier.getIdPanier(), taille);

    }

    /**
     * Cette fonction permet de retirer des item du panier
     */
    @POST
    @Path("/retirerItemPanier")
    public void retirerItemPanier(@FormParam("id") int idProduit){

        Principal principal = httpServletRequest.getUserPrincipal();

        Panier panier = null;
        try {
            panier = getPanier(principal.getName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        DataBase database = DataBase.getInstance();
        database.retirerItemPanier(idProduit, panier.getIdPanier());

    }

}
