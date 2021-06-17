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

        String SQL = "SELECT produit.idproduit, nomitem, description, prix, taille , couleur , visibilite_site , id_etat FROM produit" ;
        try {Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL);

            while(rs.next())
            {
                maliste.add(new Produit());
                maliste.get(index).setIdproduit(rs.getInt(1));
                maliste.get(index).setNomitem(rs.getString(2));
                maliste.get(index).setDescription(rs.getString(3));
                maliste.get(index).setPrix(rs.getInt(4));
                maliste.get(index).setTaille(rs.getString(5));
                maliste.get(index).setCouleur(rs.getString(6));
                maliste.get(index).setVisibilite_site(rs.getInt(7));
                maliste.get(index).getId_etat(rs.getInt(8));

                index++;

                System.out.println(index);
            }

            String sqlPhoto = "SELECT url from produit_photo , produit Where produit_photo.idproduit = produit.idproduit AND produit.idproduit = 11";
            Connection conn2= connect();
            PreparedStatement stmt2 = conn.prepareStatement(sqlPhoto);

            for (Produit p:maliste
                 ) {try {

               // stmt2.setInt(1,p.getIdproduit());
                ResultSet rs2 = stmt2.executeQuery();

                while(rs2.next())
                {
                    System.out.println("aawdawawdwad");
                    System.out.println(rs2.getString(1));
                    p.addPhoto(rs2.getString(1));
                }
            }catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }

            }

            return  maliste;

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
