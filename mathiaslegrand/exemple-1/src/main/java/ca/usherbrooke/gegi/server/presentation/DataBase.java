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

    /**
     * Cette fonction retourne une instance de la database
     * @return
     */
    public static DataBase getInstance()
    {
        if(instance == null)
        {
            instance = new DataBase();
            return instance;
        }
        else return instance;
    }

    /**
     * Cette fonction permet de savoir si une personne possede les droits d'administrations
     * @param cip
     * @return
     */
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

    /**
     * Cette fonction permet d'inserer des clients dans la database
     * @param etudiant
     */
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

    /**
     * Cette fonction permet d'ajouter des images a des produits dans la database.
     * @param url
     * @param idProduit
     */
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


    /**
     * Cette fonction permet d'ajouter des produits dans la database.
     * @param nom
     * @param description
     * @param taille
     * @param prix
     * @param couleur
     * @param visibilite
     * @param etat
     * @param url
     * @param quantite
     */
    public void insertProduitDB(String nom, String description, String taille, float prix, String couleur, int visibilite, int etat, String url, int quantite) {
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
        ajouterItemInventaire(index, quantite);
    }

    public void modifierProduit(String nom, String description, String taille, float prix, String couleur, int visibilite, int etat, String url, int quantite, int idProduit){
        String SQL = "UPDATE produit SET nomitem = ?, description = ?, prix = ?, taille = ?, couleur = ?, visibilite_site = ?, id_etat = ? WHERE idproduit = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, nom);
            stmt.setString(2, description);
            stmt.setFloat(3, prix);
            stmt.setString(4, taille);
            stmt.setString(5, couleur);
            stmt.setInt(6, visibilite);
            stmt.setInt(7, etat);
            stmt.setInt(8, idProduit);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        modifierInventaire(idProduit, quantite);
    }

    public void modifierInventaire(int idProduit, int quantite){
        String SQL = "UPDATE inventaire_produit SET quantite = ? WHERE idproduit = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, quantite);
            stmt.setInt(2, idProduit);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Cette fonction permet de donner le numero de l'index de la dernier photo
     * @return
     */
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

    /**
     * Cette fonction permet de donner le numero de l'index du dernier produit
     * @return
     */
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
     * Cette fonction permet de retirer un produit de la database
     * @param idproduit
     */
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

    /**
     * Cette fonction permet de changer les droits d'administration d'un client.
     * @param cip
     */
    public boolean insertAdminDB( String cip){
        String SQL = "UPDATE client SET id_fonction = 1 WHERE cip = ?";

        try(Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){

            stmt.setString(1, cip);

            stmt.executeUpdate();
            return true;
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Cette fonction permet de retirer les droits d'administrations d'un client
     * @param cip
     */
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

    /**
     * Cette fonction permet de construire des produits a l'aide de la base de donnee pour un futur affichage sur Webix
     * @param id
     * @return
     */
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

    public String getNomProduit(int id){
        String nomitem = null;

        String SQL = "SELECT nomitem FROM produit WHERE idproduit = ?";
        try(Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            nomitem = rs.getString(1);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return nomitem;
    }

    public ArrayList<String> getTailleProduit(int id){
        ArrayList<String> listeTailles = new ArrayList<>();
        String SQL = "SELECT taille FROM produit WHERE nomitem = ?";
        String nomItem = getNomProduit(id);
        try{
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setString(1,nomItem);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                listeTailles.add(rs.getString(1));
            }
        }

        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return listeTailles;
    }

    /**
     * Cette fonction permet de construire des item_inventaire a l'aide de la base de donne pour une futur utilisation avec Webix
     * @return
     */
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

    /**
     * Cette fonction permet de construire des item commander a l'aide de la base de donnee pour une future utilisation avec Webix
     * @return
     */
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

    /**
     * Cette fonction permet de donner le numero de l'index du dernier item_panier
     * @return
     */
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

    /**
     * Cette fonction permet de donner le numero de l'index du dernier panier
     * @return
     */
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

    /**
     * Cette fonction permet d'ajouter des produits dans un panie
     * @param quantite
     * @param panier
     * @param produit
     */
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

    /**
     * Cette fonction retourne les commandes avec toutes les informations pertinentes.
     * @return
     */
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

                    if(malisteCommande.get(i).getId_commande() == rs.getInt(1))
                    {
                        indexTemp = i;
                    }

                }


                Item_Commander item = new Item_Commander();
                item.setId_item_commander(rs.getInt(6));
                item.setQuantite(rs.getInt(7));
                item.setPrixtotal(rs.getInt(8));
                item.setId_commande(rs.getInt(1));
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

    /**
     * Cette fonction permet de retourner la liste des produits
     * @return
     */
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
            rs.next();
            panier.setIdPanier(rs.getInt(1));

        }

        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println("Pas de panier au nom");
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

    /**
     * Cette fonction permet de rajouter un nouveau produit dans item_panier
     * @param quantite
     * @param idProduit
     */
    public void insertItemPanier(int quantite, int idProduit){
        String SQL = "INSERT INTO item_panier VALUES(?,?,?,?)";
        try(Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){

            stmt.setInt(1, quantite);
            stmt.setInt(2, getIndexItemPanier());
            stmt.setInt(3, idProduit);
            //stmt.setInt(4, getPanier(cip);

            stmt.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Permet d'aller chercher le dernier index de commande
     * @return l'index pour ajouter une nouvelle commande
     */
    public int getIndexCommande(){
        int index = 0;

        String SQL = "SELECT MAX(id_commande) FROM commande" ;

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
     * Permet d'aller chercher le dernier index de item_commander
     * @return l'index pour ajouter un nouvel item_commander
     */
    public int getIndexItemCommande(){
        int index = 0;

        String SQL = "SELECT MAX(id_item_commander) FROM item_commander" ;

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

    public int getIdTaille(int id, String taille){
        int idTaille = 0;
        String nomitem;

        nomitem = getNomProduit(id);

        String SQL2 = "SELECT idproduit FROM produit WHERE nomitem = ? AND taille = ?";
        try(Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(SQL2)){

            stmt.setString(1, nomitem);
            stmt.setString(2, taille);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            idTaille = rs.getInt(1);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return idTaille;
    }

    /**
     * Methode qui permet de commander un item individuel
     * @param id2 id du produit a commander
     */
    public int CommanderItem(int id2, int quantite, String taille, String cip){
        String SQL = "INSERT INTO COMMANDE VALUES(?,?,?,?,?)";
        int index = getIndexCommande();
        int id = getIdTaille(id2, taille);
        int prixTotal = getProduit(id).getPrix()*quantite;
        int quantiteInventaire = 2147483647;
        int idInventaire = 0;

        String SQL3 = "SELECT quantite, id_inventaire_produit FROM inventaire_produit WHERE idproduit = ?";

        try(Connection conn2 = connect();
            PreparedStatement stmt2 = conn2.prepareStatement(SQL3)){

            stmt2.setInt(1, id);

            ResultSet rs = stmt2.executeQuery();
            rs.next();
            quantiteInventaire = rs.getInt(1);
            idInventaire = rs.getInt(2);

        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        if((quantiteInventaire-quantite) < 0){
            return quantiteInventaire-quantite;
        }

        else{

            try(Connection conn = connect();
                PreparedStatement stmt = conn.prepareStatement(SQL)){

                stmt.setDate(1, new Date(System.currentTimeMillis()));
                stmt.setInt(2, index);
                stmt.setInt(3, prixTotal);
                stmt.setString(4, cip);
                stmt.setInt(5, 1);

                stmt.executeUpdate();
            } catch (SQLException ex){
                System.out.println(ex.getMessage());
            }

            String SQL2 = "INSERT INTO item_commander VALUES(?,?,?,?,?,?)";

            try(Connection conn2 = connect();
                PreparedStatement stmt2 = conn2.prepareStatement(SQL2)){

                stmt2.setInt(1, getIndexItemCommande());
                stmt2.setInt(2, quantite);
                stmt2.setInt(3, prixTotal);
                stmt2.setInt(4, index);
                stmt2.setInt(5, id);
                stmt2.setInt(6,1);

                stmt2.executeUpdate();
            } catch (SQLException ex){
                System.out.println(ex.getMessage());
            }

            diminuerQuantiteInventaire(idInventaire, quantiteInventaire-quantite);

            return quantiteInventaire-quantite;
        }
    }

    public void diminuerQuantiteInventaire(int idInventaire, int quantite){
        String SQL2 = "UPDATE inventaire_produit SET quantite = ? WHERE id_inventaire_produit = ?";

        try(Connection conn2 = connect();
            PreparedStatement stmt2 = conn2.prepareStatement(SQL2)){

            stmt2.setInt(1, quantite);
            stmt2.setInt(2, idInventaire);

            stmt2.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Cette fonction permet d'ajouter un item commander dans la base de donnees pour une futur utilisation
     * @param id
     * @param quantite
     * @param idCommande
     */
    public void ajouterItemCommander(int id, int quantite, int idCommande){
        String SQL2 = "INSERT INTO item_commander VALUES(?,?,?,?,?,?)";
        int prixTotal = getProduit(id).getPrix()*quantite;

        try(Connection conn2 = connect();
            PreparedStatement stmt2 = conn2.prepareStatement(SQL2)){

            stmt2.setInt(1, getIndexItemCommande());
            stmt2.setInt(2, quantite);
            stmt2.setInt(3, prixTotal);
            stmt2.setInt(4, idCommande);
            stmt2.setInt(5, id);
            stmt2.setInt(6,1);

            stmt2.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void ajouterItemPanier(int id2, int quantite, int idPanier, String taille){
        String SQL2 = "INSERT INTO item_panier VALUES(?,?,?,?)";
        int id = getIdTaille(id2, taille);
        try(Connection conn2 = connect();
            PreparedStatement stmt2 = conn2.prepareStatement(SQL2)){

            stmt2.setInt(1, quantite);
            stmt2.setInt(2, getIndexItemPanier());
            stmt2.setInt(3, id);
            stmt2.setInt(4, idPanier);


            stmt2.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Permet d'aller chercher le dernier index de item_commander
     * @return l'index pour ajouter un nouvel item_commander
     */
    public int getIndexItemInventaire(){
        int index = 0;

        String SQL = "SELECT MAX(id_inventaire_produit) FROM inventaire_produit" ;

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
     * Cette fonction permet d'ajouter un nouvel item dans l'inventaire
     * @param idProduit
     * @param quantite
     */
    public void ajouterItemInventaire(int idProduit, int quantite){
        String SQL2 = "INSERT INTO inventaire_produit VALUES(?,?,?)";

        try(Connection conn2 = connect();
            PreparedStatement stmt2 = conn2.prepareStatement(SQL2)){

            stmt2.setInt(1, quantite);
            stmt2.setInt(2, getIndexItemInventaire());
            stmt2.setInt(3, idProduit);

            stmt2.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Cette fonction serre a la connexion a la base de donnee
     * @return
     * @throws SQLException
     */
    public Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://zeus.gel.usherbrooke.ca:5432/s3iprojet04", "s3iprojet04", "s3iprojet");
    }
}
