
package ca.usherbrooke.gegi.server.business;
import java.sql.*;
import java.util.ArrayList;


/**
 * La classe contient toutes les informations utiles pour instancier un produit provenant de la base de donnees
 * @author Mathieu Lefebvre
 * @version 1.0
 */
public class Produit {
    private String nomitem;
    private int idproduit;
    private String description;
    private int prix;
    private String taille;
    private String couleur;
    private int visibilite_site;
    private int id_etat;
    private ArrayList<String> arrayPhoto;

    public Produit()
    {
        arrayPhoto = new ArrayList<String>();
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://zeus.gel.usherbrooke.ca:5432/s3iprojet04", "s3iprojet04", "s3iprojet");
    }



    public ArrayList<String> getArrayPhoto()
    {
        return arrayPhoto;
    }

    public void setArrayPhoto(ArrayList<String> a)
    {
        arrayPhoto = a;
    }

    public void addPhoto(String url)
    {
        arrayPhoto.add(url);
    }

    public String getNomitem(int id) {
        String SQL = "SELECT nomitem FROM produit WHERE id=?";
        String retour = "";
        try (Connection conn = connect();
             PreparedStatement prestmt = conn.prepareStatement(SQL))
        {
            prestmt.setInt(1,id);
            ResultSet rs = prestmt.executeQuery();
            retour = rs.getString(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nomitem;
    }


    public void setNomitem(String nomitem, int id) {
        String SQL = "UPDATE produit SET nomitem = ? WHERE id = ?";
        String retour = "";
        try (Connection conn = connect();
             PreparedStatement prestmt = conn.prepareStatement(SQL)) {
            prestmt.setString(1, nomitem);
            prestmt.setInt(2, id);
            prestmt.executeUpdate(SQL);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.nomitem = nomitem;
    }


    public String getDescription(int id) {
        String SQL = "SELECT description FROM produit WHERE id=?";
        String retour = "";
        try (Connection conn = connect();
             PreparedStatement prestmt = conn.prepareStatement(SQL))
        {
            prestmt.setInt(1,id);
            ResultSet rs = prestmt.executeQuery();
            retour = rs.getString(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getVisibiliteSite() {
        return visibilite_site;
    }

    public void setVisibiliteSite(int visibilite_site) {
        this.visibilite_site = visibilite_site;
    }

    public int getIdEtat() {
        return id_etat;
    }

    public void setIdEtat(int id_etat) {
        this.id_etat = id_etat;
    }
}
