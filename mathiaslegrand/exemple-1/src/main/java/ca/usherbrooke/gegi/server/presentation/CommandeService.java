package ca.usherbrooke.gegi.server.presentation;

import ca.usherbrooke.gegi.server.business.Commande;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.sql.*;
import java.util.ArrayList;



    @Path("/commande")
    public class CommandeService {
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
        @GET
        @Path("/listeproduits")
        public String listeProduits() {
            int index = 0;
            ArrayList<Commande> maliste = new ArrayList<Commande>();
            String SQL = "SELECT date, id_commande, prix_total, cip , id_etat_commande FROM commande";
            try {
                Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);

                while (rs.next()) {

                    maliste.add(new Commande());
                    maliste.get(index).setId_commande(rs.getInt(1));
                    maliste.get(index).setPrix_total(rs.getFloat(2));
                    maliste.get(index).setCip(rs.getString(3));
                    maliste.get(index).setId_etat_commande(rs.getInt(4));


                    return "salut";


                }


            } catch (SQLException e) {
                System.out.println("allo");
            }
            return "Aloha";
        }
    }

