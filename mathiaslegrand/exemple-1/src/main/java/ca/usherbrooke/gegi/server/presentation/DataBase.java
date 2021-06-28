package ca.usherbrooke.gegi.server.presentation;

import ca.usherbrooke.gegi.server.business.*;

import javax.ws.rs.FormParam;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;

/**
 * Contient toutes les methodes qui communiquent avec la base de donnees
 * @author Mathieu Lefebvre
 * @version 1.0
 */
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


    public boolean isAdmin(String cip)
    {
        String SQL = "SELECT id_fonction from Client WHERE cip = ?" ;

        try(Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){

            stmt.setString(1, cip);
            ResultSet rs2 = stmt.executeQuery();
            rs2.next();
            if(rs2.getInt(1) == 1)return true;
            else return false;

        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
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





    public ArrayList<Item_inventaire> getInventaire()
    {
        ConcreteBuilderProduit builder = new ConcreteBuilderProduit();
        ArrayList<Item_inventaire> maliste = new ArrayList<Item_inventaire>();
        String SQL = "SELECT produit.idproduit, nomitem, description, prix, taille , couleur , visibilite_site , id_etat,inventaire_produit.quantite FROM inventaire_produit JOIN produit ON inventaire_produit.idproduit = produit.idproduit" ;
        try {Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while(rs.next())
            {
                Produit p = builder.construireProduitInterface(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8));
                Item_inventaire item = new Item_inventaire();
                item.setProduit(p);
                item.setQuantite(rs.getInt(9));
                maliste.add(item);
            }

        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return  maliste;

    }

    public ArrayList<Item_Commander> getItem_Commander(){
        ArrayList<Item_Commander> listeItems = new ArrayList<>();
        String SQL = "SELECT commande.date, id_item_commander, idproduit,quantite, prixtotal, item_commander.id_commande, item_commander.id_etat_commande FROM item_commander JOIN commande ON commande.id_commande = item_commander.id_commande";

        try {Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while(rs.next())
            {
                Item_Commander item = new Item_Commander();
                item.setDate(rs.getDate(1));
                item.setId_item_commander(rs.getInt(2));
                item.setIdproduit(rs.getInt(3));
                item.setQuantite(rs.getInt(4));
                item.setPrixtotal(rs.getInt(5));
                item.setId_commande(rs.getInt(6));
                item.setId_etat_commade(rs.getInt(7));
                //item.setProduit(p);
                listeItems.add(item);
            }

        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return listeItems;
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
