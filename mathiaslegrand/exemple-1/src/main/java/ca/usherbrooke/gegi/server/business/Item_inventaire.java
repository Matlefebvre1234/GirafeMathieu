package ca.usherbrooke.gegi.server.business;

public class Item_inventaire {
    Produit produit;
    int quantite;

    public void setProduit(Produit p)
    {
        produit = p;
    }
    public Produit getProduit()
    {
        return produit;
    }

    public int getQuantite()
    {
        return quantite;
    }

    public void setQuantite(int i)
    {
        quantite=i;
    }

}
