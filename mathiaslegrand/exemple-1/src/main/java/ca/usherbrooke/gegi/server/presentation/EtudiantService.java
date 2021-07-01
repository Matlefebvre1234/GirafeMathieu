package ca.usherbrooke.gegi.server.presentation;

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

    public String getCip() {
        Principal principal = httpServletRequest.getUserPrincipal();
        Map<String, Object> details = (Map<String, Object>) ((AttributePrincipalImpl)principal).getAttributes();

        return principal.getName();
    }

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

    @GET
    @Path("isAdmin")
    @Produces("application/json")
    public boolean isAdmin() {
        Principal principal = httpServletRequest.getUserPrincipal();
        Map<String, Object> details = (Map<String, Object>) ((AttributePrincipalImpl)principal).getAttributes();
        Etudiant etudiant = new Etudiant();
        etudiant.setCip(principal.getName());
        etudiant.setNom((String)details.get("nomFamille"));
        etudiant.setPrenom((String)details.get("prenom"));
        etudiant.setCourriel((String)details.get("courriel"));
        DataBase database = DataBase.getInstance();
        return database.isAdmin(principal.getName());
    }

    @POST
    @Path("insert_admin")
    public void insertAdminDB(@FormParam("cip") String cip){
        DataBase database = DataBase.getInstance();
        database.insertAdminDB(cip);
    }

    @GET
    @Path("remove_admin")
    public void removeAdminDB(@FormParam("cip") String cip){
        DataBase database = DataBase.getInstance();
        database.removeAdminDB(cip);
    }

    @POST
    @Path("insert_produit")
    public void insertProduitDB(@FormParam("nom") String nom, @FormParam("description") String description, @FormParam("taille") String taille, @FormParam("prix") float prix, @FormParam("couleur") String couleur, @FormParam("visibilite") int visibilite, @FormParam("etat") int etat, @FormParam("url") String url, @FormParam("quantite") int quantite) {
        DataBase database = DataBase.getInstance();
        String url1 = "https://drive.google.com/uc?export=view&id=" + url;
        //System.out.println(url1);
        database.insertProduitDB( nom,description,taille,prix,couleur,visibilite, etat,url, quantite);
    }

    @GET
    @Path("remove_produit")
    public void removeProduitDB(@FormParam("idproduit") int idproduit) {
        DataBase dataBase = DataBase.getInstance();
        dataBase.removeProduitDB(idproduit);

    }
}
