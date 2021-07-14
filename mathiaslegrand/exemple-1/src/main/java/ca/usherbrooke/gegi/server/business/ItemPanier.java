package ca.usherbrooke.gegi.server.business;
import ca.usherbrooke.gegi.server.presentation.DataBase;
import javax.ws.rs.*;
import java.sql.SQLException;

@Path("/ItemPanier")
public class ItemPanier {
    int quantite;
    int idItemPanier;
    Produit produit;

    /**
     * Cette fonction initiale des produits dans le panier
     */
    public ItemPanier(){

    }

    /**
     * Cette fonction permet de modifier la quantite d'un produit dans un panier
     * @param quantite
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /**
     * Cette fonction retourne la quantie d'un produit dans un panier
     * @return
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * Cette fonction permet de modifer un produit dans le panier
     * @param produit
     */
    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    /**
     * Cette fonction retorune les produits dans le panier
     * @return
     */
    public Produit getProduit() {
        return produit;
    }

    /**
     * Cette fonction permet de modifier l'id ItemPanier
     * @param idItemPanier
     */
    public void setIdItemPanier(int idItemPanier) {
        this.idItemPanier = idItemPanier;
    }

    /**
     * Cette fonction retourne l'id de l'item du panier
     * @return
     */
    public int getIdItemPanier() {
        return idItemPanier;
    }

    @GET
    @Path("/addQuantite")
    @Produces("application/JSON")
    public int addQuantite(int x){return quantite+1;}

    @GET
    @Path("/removeQuantite")
    @Produces("application/JSON")
    public int removeQuantite(int x){
        quantite-=1;
        if(quantite<0){
            quantite=0;
        }
        return quantite;
    }

}
