package ca.usherbrooke.gegi.server.presentation;

import ca.usherbrooke.gegi.server.business.*;
import org.jasig.cas.client.authentication.AttributePrincipalImpl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.xml.crypto.Data;
import java.security.Principal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * Contient toutes les methodes qui communiquent avec la base de donnees
 * @author Mathieu Lefebvre
 * @version 1.0
 */
public class DataBase {

    @Context
    HttpServletRequest httpServletRequest;

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

    public Produit getProduit(int id){
        ConcreteBuilderProduit builder = new ConcreteBuilderProduit();
        String SQL = "SELECT * FROM produit WHERE idproduit = ?";
        Produit p = new Produit();

        try {Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            p = builder.construireProduitInterface(rs.getInt(2),rs.getString(1),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8));

        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        String SQL2 = "SELECT url FROM produit_photo WHERE idproduit = ?";

        try {Connection conn2 = connect();
            PreparedStatement stmt = conn2.prepareStatement(SQL2);
            stmt.setInt(1, id);
            ResultSet rs2 = stmt.executeQuery();
            while(rs2.next()){
                System.out.println("allo: " + rs2.getString(1));
                p.addPhoto(rs2.getString(1));
            }


        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return  p;
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

    public int getIndexItemPanier(){
        int index = 0;

        String SQL = "SELECT MAX(id_item_panier) FROM item_panier" ;

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

    public int getIndexPanier(){
        int index = 0;

        String SQL = "SELECT MAX(idpanier) FROM item_panier" ;

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

    public void ajouterPanier(int quantite, Panier panier, Produit produit){
        String SQL = "INSERT INTO item_panier VALUES(?,?,?,?)";

        try(Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){

            stmt.setInt(1, quantite);
            stmt.setInt(2, getIndexItemPanier());
            stmt.setInt(3,produit.getIdproduit());
            stmt.setInt(4, panier.getIdPanier());

            stmt.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Commande> getCommande()
    {
        ConcreteBuilderCommande builder = new ConcreteBuilderCommande();
        ArrayList<Commande> malisteCommande = new ArrayList<Commande>();

        String SQL1 = "SELECT Commande.id_commande,cip,Commande.prix_total,Commande.date,Commande.id_etat_commande FROM commande";
        try {Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL1);

            while (rs.next())
            {
                Commande c = builder.construireCommande(rs.getInt(1),rs.getString(2),rs.getDate(4),rs.getInt(3),rs.getInt(5),new ArrayList<Item_Commander>());
                malisteCommande.add(c);
            }
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        ConcreteBuilderProduit productbuilder = new ConcreteBuilderProduit();
        ArrayList<Item_Commander> listeItem = new ArrayList<Item_Commander>();
        String SQL = "SELECT Commande.id_commande,cip,Commande.prix_total,Commande.date,Commande.id_etat_commande, item_commander.id_item_commander," +
                "item_commander.quantite,item_commander.prixtotal,item_commander.id_etat_commande," +
                "item_commander.idproduit,produit.nomitem,produit.taille,produit.couleur,produit.id_etat FROM Commande JOIN item_commander" +
                " ON Commande.id_commande = item_commander.id_commande JOIN produit ON item_commander.idproduit = produit.idproduit order by Commande.id_commande";
        try {Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            int indexTemp = 0;
            while(rs.next())
            {


                for(int i =0;i<malisteCommande.size();i++)
                {
                    System.out.println("Test :" + malisteCommande.get(i).getId_commande() + "rs " + rs.getInt(1) );
                    if(malisteCommande.get(i).getId_commande() == rs.getInt(1))
                    {
                        System.out.println("INDEX CHANGE TO " + i);
                        indexTemp = i;
                    }

                }

                System.out.println("INdex " + indexTemp);
                Item_Commander item = new Item_Commander();
                item.setId_item_commander(rs.getInt(6));
                item.setQuantite(rs.getInt(7));
                item.setPrixtotal(rs.getInt(8));
                System.out.println("itemset : " + rs.getInt(9));
                item.setId_commande(rs.getInt(1));
                System.out.println("commande : " + item.getId_commande());
                item.setId_etat_commade(rs.getInt(9));
                Produit p = productbuilder.construireProduitLogique(rs.getInt(10), rs.getString(11),0,0, rs.getInt(14));
                p.setCouleur(rs.getString(13));
                p.setTaille(rs.getString(12));
                item.setProduit(p);

                malisteCommande.get(indexTemp).getListeItem().add(item);

            }

        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return  malisteCommande;

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

    /**
     * Retourne le panier du client
     * @return
     */
    public Panier getPanier(String cip){
        Panier panier = new Panier();
        String SQL = "SELECT idpanier FROM panier WHERE cip = ?";

        try(Connection conn = connect();
        PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setString(1, cip);
            ResultSet rs = stmt.executeQuery();
            panier.setIdPanier(rs.getInt(1));

        }

        catch (SQLException ex){
            System.out.println(ex.getMessage());
            panier.setIdPanier(2147483647);
        }

        panier.setCip(cip);
        return panier;
    }

    /**
     * Cree le panier pour le client
     * @param cip
     */
    public void creerPanier(String cip){
        String SQL = "INSERT INTO panier VALUES(?,?)";
        try(Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){

            stmt.setInt(1, getIndexPanier());
            stmt.setString(2, cip);

            stmt.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

    }

    public void insertItemPanier(int quantite, int idProduit){
        String SQL = "INSERT INTO item_panier VALUES(?,?,?,?)";
        try(Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){

            stmt.setInt(1, quantite);
            stmt.setInt(2, getIndexItemPanier());
            stmt.setInt(3, idProduit);
            stmt.setInt(4, getPanier(getCip()).getIdPanier());

            stmt.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public String getCip(){
        Principal principal = httpServletRequest.getUserPrincipal();
        Map<String, Object> details = (Map<String, Object>) ((AttributePrincipalImpl)principal).getAttributes();
        Etudiant etudiant = new Etudiant();
        etudiant.setCip(principal.getName());
        etudiant.setNom((String)details.get("nomFamille"));
        etudiant.setPrenom((String)details.get("prenom"));
        etudiant.setCourriel((String)details.get("courriel"));

        return etudiant.getCip();
    }

    /**
     * Methode qui permet de commander un item individuel
     * @param id id du produit a commander
     */
    public void CommanderItem(int id, int quantite){

    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://zeus.gel.usherbrooke.ca:5432/s3iprojet04", "s3iprojet04", "s3iprojet");
    }
}
