package ca.usherbrooke.gegi.server.business;

import ca.usherbrooke.gegi.server.presentation.DataBase;

import javax.ws.rs.*;


@Path("/ItemPanier")
public class ItemPanier {
    int quantite;
    int idItemPanier;
    Produit produit;

    public ItemPanier(){

    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @POST
    @Path("/getInfo")
    @Produces("application/json")
    public Panier getInfo(@FormParam("cip") String cip)
    {
        DataBase database = DataBase.getInstance();
        return database.getPanierFromCIP(cip);
    }

    @GET
    //@Path("C:\\Users\\Telep\\Documents\\S3\\Project\\GirafeMathieu\\mathiaslegrand\\exemple-1\\src\\main\\java\\ca\\usherbrooke\\gegi\\server\\business\\ItemPanier\\getQuantite")
    @Path("/getQuantite")
    @Produces("application/JSON")
    public int getQuantite() {
        return quantite;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setIdItemPanier(int idItemPanier) {
        this.idItemPanier = idItemPanier;
    }

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
