package ca.usherbrooke.gegi.server.business;


/**
 * Cette classe permet de mettre dans l'inventaire un produit avec sa quantite respective
 */
public class Item_inventaire {
    Produit produit;
    int quantite;

    /**
     * Cette fonction change un produit
     * @param p
     */
    public void setProduit(Produit p)
    {
        produit = p;
    }

    /**
     * Cette fonction retourne le produit dans l'inventaire
     * @return
     */
    public Produit getProduit()
    {
        return produit;
    }

    /**
     * Cette fonction retourne la quantite d'un produit dans l'inventaire
     * @return
     */
    public int getQuantite()
    {
        return quantite;
    }

    /**
     * Cette fonction change la quantite d'un produit dans l'inventaire
     * @param i
     */
    public void setQuantite(int i)
    {
        quantite=i;
    }

}
