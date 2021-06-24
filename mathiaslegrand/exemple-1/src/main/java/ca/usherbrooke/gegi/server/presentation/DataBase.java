package ca.usherbrooke.gegi.server.presentation;

import ca.usherbrooke.gegi.server.business.ConcreteBuilderProduit;
import ca.usherbrooke.gegi.server.business.Etudiant;
import ca.usherbrooke.gegi.server.business.Produit;

import javax.ws.rs.FormParam;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;

public class DataBase {

    private static  DataBase instance;

        private DataBase()
        {
            try {
                connect();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

        public static DataBase getInstance()
        {
            if(instance == null)
            {
                instance = new DataBase();
                return instance;
            }
            else return instance;
        }


    public void insertEtudiantDB(Etudiant etudiant){
        String SQL = "INSERT INTO client(cip, courriel, nom, adresse, prenom, id_fonction)" + " VALUES(?,?,?,?,?,?)" ;

        try(Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){

            stmt.setString(1, etudiant.getCip());
            stmt.setString(2, etudiant.getCourriel());
            stmt.setString(3, etudiant.getNom());
            stmt.setInt(4, 123);
            stmt.setString(5, etudiant.getPrenom());
            stmt.setInt(6, 2);

            stmt.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }


    public void insertImageProduitDb(String url, int idProduit)
    {
        String SQL = "INSERT INTO produit_photo(id_photo, url, idproduit)" + " VALUES(?,?,?)";


        int index = getLastIndexPhoto();
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, index);
            stmt.setString(2, url);
            stmt.setInt(3, idProduit);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void insertProduitDB( String nom, String description, String taille, float prix,String couleur, int visibilite,  int etat, String url) {
        String SQL = "INSERT INTO produit(nomitem, idproduit, description, prix, taille, couleur, visibilite_site, id_etat)" + " VALUES(?,?,?,?,?,?,?,?)";

        int index = getLastIndex();

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, nom);
            stmt.setInt(2, index);
            stmt.setString(3, description);
            stmt.setFloat(4, prix);
            stmt.setString(5, taille);
            stmt.setString(6, couleur);
            stmt.setInt(7, visibilite);
            stmt.setInt(8, etat);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


        insertImageProduitDb(url, index);
    }


    public int getLastIndexPhoto(){
        int index = 0;

        String SQL = "SELECT MAX(id_photo) FROM produit_photo" ;

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

    public void removeProduitDB(int idproduit)
    {
        String SQL = "DELETE FROM produit WHERE idproduit = ?";
        try {
            Connection conn2= connect();
            PreparedStatement stmt2 = conn2.prepareStatement(SQL);
            stmt2.setInt(1,idproduit);
            ResultSet rs2 = stmt2.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void insertAdminDB( String cip){
        String SQL = "UPDATE client SET id_fonction = 1 WHERE cip = ?";

        try(Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){

            stmt.setString(1, cip);

            stmt.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }


    public void removeAdminDB(String cip)
    {
        String SQL = "UPDATE client SET id_fonction = 2 WHERE cip = ?";
        try {
            Connection conn2= connect();
            PreparedStatement stmt2 = conn2.prepareStatement(SQL);
            stmt2.setString(1,cip);
            ResultSet rs2 = stmt2.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



        public ArrayList<Produit> getListeProduit()
        {
            ConcreteBuilderProduit builder = new ConcreteBuilderProduit();
            ArrayList<Produit> maliste = new ArrayList<Produit>();
            String SQL = "SELECT produit.idproduit, nomitem, description, prix, taille , couleur , visibilite_site , id_etat FROM produit" ;
            try {Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);

                while(rs.next())
                {
                    maliste.add(builder.construireProduitInterface(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8)));
                }

                String sqlPhoto = "SELECT url from produit_photo , produit Where produit_photo.idproduit = produit.idproduit AND produit.idproduit = ?";
                Connection conn2= connect();
                PreparedStatement stmt2 = conn.prepareStatement(sqlPhoto);

                for (Produit p:maliste
                ) {try {

                    stmt2.setInt(1,p.getIdproduit());
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

    public Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://zeus.gel.usherbrooke.ca:5432/s3iprojet04", "s3iprojet04", "s3iprojet");
    }
}
