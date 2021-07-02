package ca.usherbrooke.gegi.server.presentation;

import ca.usherbrooke.gegi.server.business.Commande;
import ca.usherbrooke.gegi.server.business.Item_Commander;
import org.jasig.cas.client.authentication.AttributePrincipalImpl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.security.Principal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;


/**
 * Contient toutes les methodes pouvant etre appelees dans le frontend qui ont rapport aux commandes
 * @author Patrick Normandin
 * @version 1.0
 * @see DataBase
 */

/**
 * Methode qui permet d'aller chercher l'index d'identification le plus gros dans les commandes
 */
@Path("/commande")
public class CommandeService {
    @Context
    HttpServletRequest httpServletRequest;
    //localhost:8080/exemple-1/api/produit/salut

    public Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://zeus.gel.usherbrooke.ca:5432/s3iprojet04", "s3iprojet04", "s3iprojet");
    }
    public int getLastIndex(){
        int index = 0;
        String SQL = "SELECT MAX(idproduit) FROM produit" ;
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)){
            rs.next();
            index = rs.getInt(1);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return index+1;
    }

    /**
     * Methode qui permet de retourner la liste des produits contenus dans la base de donnees, ainsi que leurs informations
     * @return liste des produits
     */
    @GET
    @Path("/listecommande")
    @Produces("application/JSON")
    public ArrayList<Commande> listeProduits() {
        int index = 0;
        ArrayList<Commande> maliste = new ArrayList<Commande>();
        String SQL = "SELECT date, id_commande, prix_total, cip , id_etat_commande FROM commande";
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {

                maliste.add(new Commande());
                maliste.get(index).setDate(rs.getDate(1));
                maliste.get(index).setId_commande(rs.getInt(2));
                maliste.get(index).setPrix_total(rs.getInt(3));
                maliste.get(index).setCip(rs.getString(4));
                maliste.get(index).setId_etat_commande(rs.getInt(5));

                index++;
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return maliste;
    }

    @GET
    @Path("/item_commander")
    @Produces("application/JSON")
    public ArrayList<Commande> prixTotal() {
        int index = 0;
        ArrayList<Commande> maliste = new ArrayList<Commande>();
        //int prix_tot =0;
        String SQL = "SELECT prix * quantite AS prixtotal FROM produit, item_commander WHERE item_commander.idproduit=produit.idproduit";
        String SQL2 = "SELECT * FROM commande";
        //String SQL3 = "SELECT * FROM etat__commande";
        String SQL3 = "SELECT * FROM etat__commande JOIN commande ON commande.id_etat_commande=etat__commande.id_etat_commande";
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                maliste.add(new Commande());
                maliste.get(index).setPrix_total(rs.getInt(1));
                //maliste.get(index).setId_commande(rs2.getInt(2));
                //maliste.get(index).setDate(rs2.getDate(1));
                //maliste.get(index).setId_etat_commande(rs2.getInt(5));
                //maliste.get(index).setCip(rs2.getString(4));
                index ++;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        index=0;
        try{
            Connection conn2 = connect();
            Statement stmt2 = conn2.createStatement();
            ResultSet rs2 = stmt2.executeQuery(SQL2);
            while (rs2.next()) {
                maliste.get(index).setId_commande(rs2.getInt(2));
                maliste.get(index).setDate(rs2.getDate(1));
                maliste.get(index).setId_etat_commande(rs2.getInt(5));
                maliste.get(index).setCip(rs2.getString(4));
                index++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        index=0;
        try{
            Connection conn3 = connect();
            Statement stmt3 = conn3.createStatement();
            ResultSet rs3 = stmt3.executeQuery(SQL3);
            while (rs3.next()) {
                maliste.get(index).setId_etat_commande(rs3.getInt(2));

                index++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return maliste;
    }

    @GET
    @Path("/item_commander")
    @Produces("application/JSON")
    public ArrayList<Item_Commander> getListeItemCommander(){
        DataBase database = DataBase.getInstance();
        return database.getItem_Commander();
    }


    @GET
    @Path("/commande")
    @Produces("application/json")
    public ArrayList<Commande> getCommande()
    {
        DataBase database = DataBase.getInstance();
       //System.out.println(database.getCommande(.get(0).getId_comm)ande());
        return database.getCommande();
    }



    @POST
    @Path("/commander_item")
    public void commanderItem(@FormParam("id") int idProduit, @FormParam("quantite") int quantite, @FormParam("taille") String taille){
        DataBase dataBase = DataBase.getInstance();
        Principal principal = httpServletRequest.getUserPrincipal();
        Map<String, Object> details = (Map<String, Object>) ((AttributePrincipalImpl)principal).getAttributes();
        dataBase.CommanderItem(idProduit, quantite, taille, principal.getName());
    }
}

