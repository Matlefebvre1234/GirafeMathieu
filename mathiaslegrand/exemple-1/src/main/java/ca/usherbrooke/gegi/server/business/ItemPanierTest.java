package ca.usherbrooke.gegi.server.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemPanierTest {

    @Test
    void setQuantite() {
        ItemPanier itemPanier = new ItemPanier();
        itemPanier.setQuantite(15);
        Assertions.assertEquals(15,itemPanier.getQuantite());
    }

    @Test
    void getQuantite() {
        ItemPanier itemPanier = new ItemPanier();
        itemPanier.setQuantite(15);
        Assertions.assertEquals(15,itemPanier.getQuantite());
    }

    @Test
    void setProduit() {
        Produit p = new Produit();
        ItemPanier itemPanier = new ItemPanier();
        itemPanier.setProduit(p);
        Assertions.assertEquals(p,itemPanier.getProduit());
    }

    @Test
    void getProduit() {
        Produit p = new Produit();
        ItemPanier itemPanier = new ItemPanier();
        itemPanier.setProduit(p);
        Assertions.assertEquals(p,itemPanier.getProduit());
    }

    @Test
    void setIdItemPanier() {
        ItemPanier itemPanier = new ItemPanier();
        itemPanier.setIdItemPanier(1);
        Assertions.assertEquals(1,itemPanier.getIdItemPanier());
    }

    @Test
    void getIdItemPanier() {
        ItemPanier itemPanier = new ItemPanier();
        itemPanier.setIdItemPanier(1);
        Assertions.assertEquals(1,itemPanier.getIdItemPanier());
    }
}