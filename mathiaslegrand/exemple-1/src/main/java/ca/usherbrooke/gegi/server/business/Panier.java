package ca.usherbrooke.gegi.server.business;

import java.util.ArrayList;

public class Panier {

    String cip;
    int idPanier;
    //ArrayList<Produit>

    public Panier(){

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
}
