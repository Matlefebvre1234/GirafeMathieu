package ca.usherbrooke.gegi.server.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PanierTest {

    @Test
    void setCip() {
        Panier panier = new Panier();
        panier.setCip("e");
        Assertions.assertEquals("e",panier.getCip());
    }

    @Test
    void getCip() {
        Panier panier = new Panier();
        panier.setCip("e");
        Assertions.assertEquals("e",panier.getCip());
    }

    @Test
    void setIdPanier() {
        Panier panier = new Panier();
        panier.setIdPanier(1000);
        Assertions.assertEquals(1000,panier.getIdPanier());
    }

    @Test
    void getIdPanier() {
        Panier panier = new Panier();
        panier.setIdPanier(1000);
        Assertions.assertEquals(1000,panier.getIdPanier());
    }

    @Test
    void setItems() {
        ArrayList<ItemPanier> items = new ArrayList<>();
        Panier panier = new Panier();
        panier.setItems(items);
        Assertions.assertEquals(items,panier.getItems());
    }

    @Test
    void getItems() {
        ArrayList<ItemPanier> items = new ArrayList<>();
        Panier panier = new Panier();
        panier.setItems(items);
        Assertions.assertEquals(items,panier.getItems());
    }

    @Test
    void addItem() {
        ArrayList<ItemPanier> items = new ArrayList<>();
        ItemPanier itemPanier = new ItemPanier();
        ItemPanier itemPanier1 = new ItemPanier();
        Panier panier = new Panier();
        panier.addItem(itemPanier);
        panier.addItem(itemPanier1);
        Assertions.assertEquals(2,panier.getItems().size());
    }

    @Test
    void removeItem() {
        ArrayList<ItemPanier> items = new ArrayList<>();
        ItemPanier itemPanier = new ItemPanier();
        ItemPanier itemPanier1 = new ItemPanier();
        Panier panier = new Panier();
        panier.addItem(itemPanier);
        panier.addItem(itemPanier1);
        Assertions.assertEquals(2,panier.getItems().size());
        panier.removeItem(itemPanier);
        Assertions.assertEquals(1,panier.getItems().size());
    }
}