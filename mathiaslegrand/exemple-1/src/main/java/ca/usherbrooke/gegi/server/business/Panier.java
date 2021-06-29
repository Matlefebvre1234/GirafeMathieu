package ca.usherbrooke.gegi.server.business;

import java.util.ArrayList;

public class Panier {

    String cip;
    int idPanier;
    ArrayList<ItemPanier> items;

    public Panier(){
        items = new ArrayList<>();
    }

    public void setCip(String cip) {
        this.cip = cip;
    }

    public String getCip() {
        return cip;
    }

    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
    }

    public int getIdPanier() {
        return idPanier;
    }

    public void setItems(ArrayList<ItemPanier> items) {
        this.items = items;
    }

    public ArrayList<ItemPanier> getItems() {
        return items;
    }

    public void addItem(ItemPanier item){
        items.add(item);
    }

}
