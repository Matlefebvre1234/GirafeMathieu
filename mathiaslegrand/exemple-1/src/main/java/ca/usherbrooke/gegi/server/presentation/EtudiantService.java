package ca.usherbrooke.gegi.server.presentation;

import ca.usherbrooke.gegi.server.business.Trimestre;
import ca.usherbrooke.gegi.server.persistence.EtudiantMapper;
import ca.usherbrooke.gegi.server.business.Etudiant;
import org.jasig.cas.client.authentication.AttributePrincipalImpl;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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




    @GET
    @Path("etudiant")
    @Produces("application/json")

    public List<Etudiant> getEtudiant(@QueryParam("id") Integer id) {
      //  System.out.println(httpServletRequest.getUserPrincipal().getName());
        List<Etudiant> etudiants = etudiantMapper.select(id);
        return etudiants;
    }

   /* @Produces("text/plain")
      public String getEtudiant(@QueryParam("id") Integer id) {
        System.out.println(httpServletRequest.getUserPrincipal().getName());
        List<Etudiant> etudiants = etudiantMapper.select(id);
        return etudiants.get(0).toString();
    }*/


    @GET
    @Path("insert_trimestre")
    public void insertTrimestre() {

            Client client = ClientBuilder.newClient();
            WebTarget target = client.target("http://zeus.gel.usherbrooke.ca:8080/ms/rest/trimestre?inscription=2017-01-01");
            Invocation.Builder  builder = target.request(MediaType.APPLICATION_JSON);
            Response response = builder.get();

            List<Trimestre> trimestres = response.readEntity(new GenericType<List<Trimestre>>(){});
            for (Trimestre trimestre : trimestres) {
               etudiantMapper.insertTrimestre(trimestre);
               System.out.println(trimestre);
            }


    }

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

        insertEtudiantDB(etudiant);


        return etudiant;
    }

    public Connection connect() throws SQLException{
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

    @POST
    @Path("insert_admin")
    public void insertAdminDB(@FormParam("cip") String cip){
        String SQL = "UPDATE client " + "SET id_fonction = 1" + "WHERE cip = ?";

        try(Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){

            stmt.setString(1, cip);

            stmt.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }


    @GET
    @Path("remove_admin")
    public void removeAdminDB(){
        String SQL = "UPDATE client " + "SET id_fonction = 2" + "WHERE cip = ?";

        String cip = httpServletRequest.getParameter("cip");

        try(Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){

            stmt.setString(2, cip);

            stmt.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @GET
    @Path("insert_produit")
    public void insertProduitDB(@FormParam("nom") String nom, @FormParam("description") String description, @FormParam("taille") String taille, @FormParam("prix") float prix, @FormParam("couleur") String couleur, @FormParam("visibilite") int visibilite, @FormParam("etat") int etat) {
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
    }

    @GET
    @Path("remove_produit")
    public void removeProduitDB() {
        String SQL = "DELETE FROM produit(nomitem, idproduit, description, prix, taille, couleur, visibilite_site, id_etat)" + " VALUES(?,?,?,?,?,?,?,?)";

        String nom = httpServletRequest.getParameter("nom");
        String taille = httpServletRequest.getParameter("taille");
        String couleur = httpServletRequest.getParameter("couleur");

        int index = getLastIndex();

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, nom);
            stmt.setInt(2, index);
            stmt.setString(5, taille);
            stmt.setString(6, couleur);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
