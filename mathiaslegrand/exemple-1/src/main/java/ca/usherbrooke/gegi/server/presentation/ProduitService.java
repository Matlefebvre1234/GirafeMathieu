/*
package ca.usherbrooke.gegi.server.presentation;

import ca.usherbrooke.gegi.server.business.Produit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import java.sql.*;
import java.util.ArrayList;

@Path("/produit")
public class ProduitService extends Application {

    //localhost:8080/exemple-1/api/produit/salut
    public Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://zeus.gel.usherbrooke.ca:5432/s3iprojet04", "s3iprojet04", "s3iprojet");
    }

    @GET
    @Path("/listeproduits")
    @Produces("application/json")
    public ArrayList<Produit> listeProduits()
    {
        int index =0;
        ArrayList<Produit> maliste = new ArrayList<Produit>();

        String SQL = "SELECT nomitem, description, prix, taille , couleur , visibilite_site , id_etat FROM produit" ;
        try {Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL);

            while(rs.next())
            {
                maliste.add(new Produit());
                maliste.get(index).setNomitem(rs.getString(1),1);
                maliste.get(index).setDescription(rs.getString(2));
                maliste.get(index).setPrix(rs.getInt(3));
                maliste.get(index).setTaille(rs.getString(4));
                maliste.get(index).setCouleur(rs.getString(5));
                maliste.get(index).setVisibiliteSite(rs.getInt(6));
                maliste.get(index).setIdEtat(rs.getInt(7));
                index++;

            }
            return  maliste;

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
*/

package ca.usherbrooke.gegi.server.presentation;

import ca.usherbrooke.gegi.server.business.Produit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

}
