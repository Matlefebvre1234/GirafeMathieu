package ca.usherbrooke.gegi.server.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class Item_inventaireTest {

    @Test
    void setProduit() {
        Produit p = new Produit();
        Item_inventaire item1 = new Item_inventaire();
        item1.setProduit(p);
        Assertions.assertEquals(p,item1.getProduit());

    }

    @Test
    void getProduit() {
        Produit p = new Produit();
        Item_inventaire item1 = new Item_inventaire();
        item1.setProduit(p);
        Assertions.assertEquals(p,item1.getProduit());
    }

    @Test
    void getQuantite() {
        Produit p = new Produit();
        Item_inventaire item1 = new Item_inventaire();
        item1.setQuantite(5);
        Assertions.assertEquals(5,item1.getQuantite());
    }

    @Test
    void setQuantite() {
        Produit p = new Produit();
        Item_inventaire item1 = new Item_inventaire();
        item1.setQuantite(5);
        Assertions.assertEquals(5,item1.getQuantite());
    }
}