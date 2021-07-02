package ca.usherbrooke.gegi.server.business;

import java.util.ArrayList;

public class Panier {

    String cip;
    int idPanier;
    ArrayList<ItemPanier> items;

    /**
     * Cette fonction initialise un panier avec une liste d'items
     */
    public Panier(){
        items = new ArrayList<>();
    }

    /**
     * Cette fonction change le cip associe au panier
     * @param cip
     */
    public void setCip(String cip) {
        this.cip = cip;
    }

    /**
     * Cette fonction retourne le cip associe au panier
     * @return
     */
    public String getCip() {
        return cip;
    }


    /**
     * Cette fonction change l'id du panier
     * @param idPanier
     */
    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
    }

    /**
     * Cette fonction retourne l'id du panier
     * @return
     */
    public int getIdPanier() {
        return idPanier;
    }

    /**
     * Cette fonction change la liste d'item dans le panier
     * @param items
     */
    public void setItems(ArrayList<ItemPanier> items) {
        this.items = items;
    }

    /**
     * Cette fonction retourne la liste des items dans le panier
     * @return
     */
    public ArrayList<ItemPanier> getItems() {
        return items;
    }

    /**
     * Cette fonction ajoute un item dans le panier
     * @param item
     */
    public void addItem(ItemPanier item){
        items.add(item);
    }

    /**
     * Cette fonction enleve un item dans le panier
     * @param item
     */
    public void removeItem(ItemPanier item) {
        items.remove(item);
    }
}
