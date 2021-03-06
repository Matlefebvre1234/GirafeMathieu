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
    public void insertProduitDB(@FormParam("nom") String nom, @FormParam("description") String description, @FormParam("taille") String taille, @FormParam("prix") float prix, @FormParam("couleur") String couleur, @FormParam("visibilite") int visibilite, @FormParam("etat") int etat, @FormParam("url") String url) {
       DataBase database = DataBase.getInstance();
       database.insertProduitDB( nom,description,taille,prix,couleur,visibilite, etat,url);
    }

    @GET
    @Path("remove_produit")
    public void removeProduitDB(@FormParam("idproduit") int idproduit) {
       DataBase dataBase = DataBase.getInstance();
       dataBase.removeProduitDB(idproduit);

    }
}
