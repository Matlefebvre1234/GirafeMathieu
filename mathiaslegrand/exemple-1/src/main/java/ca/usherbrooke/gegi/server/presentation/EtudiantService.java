package ca.usherbrooke.gegi.server.presentation;

import ca.usherbrooke.gegi.server.business.BooleanMat;
import ca.usherbrooke.gegi.server.persistence.EtudiantMapper;
import ca.usherbrooke.gegi.server.business.Etudiant;
import org.jasig.cas.client.authentication.AttributePrincipalImpl;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.xml.crypto.Data;
import java.security.Principal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;


/**
 * Contient toutes les methodes pouvant etre appelees dans le frontend qui ont rapport aux etudiants
 * @author Mathias Gagnon
 * @version 1.0
 * @see DataBase
 */
@Path("")
public class EtudiantService {

    @Context
    HttpServletRequest httpServletRequest;

    @Inject
    EtudiantMapper etudiantMapper;




  /*  @GET
    @Path("etudiant")
    @Produces("application/json")

    public List<Etudiant> getEtudiant(@QueryParam("id") Integer id) {
      //  System.out.println(httpServletRequest.getUserPrincipal().getName());
        List<Etudiant> etudiants = etudiantMapper.select(id);
        return etudiants;
    }*/

   /* @Produces("text/plain")
      public String getEtudiant(@QueryParam("id") Integer id) {
        System.out.println(httpServletRequest.getUserPrincipal().getName());
        List<Etudiant> etudiants = etudiantMapper.select(id);
        return etudiants.get(0).toString();
    }*/

    /**Microservice
     *Cette fonction permet de prendre l'information de l'interface graphique et de creer un etudiant et de l'inserer dans la database
     * @return
     */
    @GET
    @Path("insert_etudiant")
    @Produces("application/json")
    public Etudiant insertEtudiant() {
        Principal principal = httpServletRequest.getUserPrincipal();
        Map<String, Object> details = (Map<String, Object>) ((AttributePrincipalImpl)principal).getAttributes();
        Etudiant etudiant = new Etudiant();
        etudiant.setCip(principal.getName());
        etudiant.setNom((String)details.get("nomFamille"));
        etudiant.setPrenom((String)details.get("prenom"));
        etudiant.setCourriel((String)details.get("courriel"));

        DataBase database = DataBase.getInstance();
        database.insertEtudiantDB(etudiant);
        return etudiant;
    }

    /**
     * Cette fonction permet de retourner le cip ecrit sur l'interface graphique
     * @return
     */
    public String getCip() {
        Principal principal = httpServletRequest.getUserPrincipal();
        Map<String, Object> details = (Map<String, Object>) ((AttributePrincipalImpl)principal).getAttributes();

        return principal.getName();
    }

    /**Microservice
     * CEtte fonction permet de retourner l'utilisateur creer avec certaines de ses informations
     * @return
     */
    @GET
    @Path("getUtilisateur")
    @Produces("application/json")
    public Etudiant getUtilisateur() {
        Principal principal = httpServletRequest.getUserPrincipal();
        Map<String, Object> details = (Map<String, Object>) ((AttributePrincipalImpl)principal).getAttributes();
        Etudiant etudiant = new Etudiant();
        etudiant.setCip(principal.getName());
        etudiant.setNom((String)details.get("nomFamille"));
        etudiant.setPrenom((String)details.get("prenom"));
        etudiant.setCourriel((String)details.get("courriel"));
        return etudiant;
    }

    /**Microservices
     * Cette fonction permet de recuperer de l'information de l'interface graphique et savoir si une personne
     * est un admin
     * @return
     */
    @GET
    @Path("isAdmin")
    @Produces("application/json")
    public ArrayList<BooleanMat> isAdmin() {
        Principal principal = httpServletRequest.getUserPrincipal();
        Map<String, Object> details = (Map<String, Object>) ((AttributePrincipalImpl)principal).getAttributes();
        Etudiant etudiant = new Etudiant();
        etudiant.setCip(principal.getName());
        etudiant.setNom((String)details.get("nomFamille"));
        etudiant.setPrenom((String)details.get("prenom"));
        etudiant.setCourriel((String)details.get("courriel"));
        DataBase database = DataBase.getInstance();
        BooleanMat bool = new BooleanMat();
       bool.setmybool(database.isAdmin(principal.getName()));
        ArrayList<BooleanMat> test = new ArrayList<BooleanMat>();
        test.add(bool);
       return test;
    }

    /** Microservice
     *  Cette fonction permet d'insert des admin dans la database
     * @param cip
     */
    @POST
    @Path("insert_admin")
    public boolean insertAdminDB(@FormParam("cip") String cip){
        DataBase database = DataBase.getInstance();
        System.out.println(database.insertAdminDB(cip));
         return database.insertAdminDB(cip);
    }

    /** Microservice
     *  Cette fonction permet d'enlever des admin dans la database
     * @param cip
     */
    @POST
    @Path("remove_admin")
    public void removeAdminDB(@FormParam("cip") String cip){
        DataBase database = DataBase.getInstance();
        database.removeAdminDB(cip);
    }

    /**Microservice
     * Cette fonction permet de rajouter des items dans la database avec l'interface graphique
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
    @POST
    @Path("insert_produit")
    public void insertProduitDB(@FormParam("nom") String nom, @FormParam("description") String description, @FormParam("taille") String taille, @FormParam("prix") float prix, @FormParam("couleur") String couleur, @FormParam("visibilite") int visibilite, @FormParam("etat") int etat, @FormParam("url") String url, @FormParam("quantite") int quantite) {
        DataBase database = DataBase.getInstance();
        String url1 = "https://drive.google.com/uc?export=view&id=" + url;
        //System.out.println(url1);
        database.insertProduitDB( nom,description,taille,prix,couleur,visibilite, etat,url, quantite);
    }

    /**Microservice
     * Cette fonction permet de retirer un produit de la base de donnee par l'interface graphique =
     * @param idproduit
     */
    @POST
    @Path("remove_produit")
    public void removeProduitDB(@FormParam("idproduit") int idproduit) {
        DataBase dataBase = DataBase.getInstance();
        dataBase.removeProduitDB(idproduit);

    }
}
