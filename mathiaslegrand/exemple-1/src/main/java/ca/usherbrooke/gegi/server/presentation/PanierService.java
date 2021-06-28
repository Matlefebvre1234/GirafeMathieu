package ca.usherbrooke.gegi.server.presentation;

import ca.usherbrooke.gegi.server.business.Panier;
import ca.usherbrooke.gegi.server.business.Produit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;

@Path("/Panier")
public class PanierService {
    @GET
    @Path("/getPanier")
    @Produces("application/json")
    public Panier getPanier()
    {
        DataBase database = DataBase.getInstance();
        Panier panier = new Panier();
        return panier;
    }
}
