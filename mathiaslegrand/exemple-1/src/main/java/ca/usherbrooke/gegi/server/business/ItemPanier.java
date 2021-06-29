package ca.usherbrooke.gegi.server.business;

public class ItemPanier {
    int quantite;
    int idItemPanier;
    Produit produit;

    public ItemPanier(){

    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

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
}
