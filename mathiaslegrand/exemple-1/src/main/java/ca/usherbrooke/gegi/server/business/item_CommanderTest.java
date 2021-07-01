package ca.usherbrooke.gegi.server.business;

import org.jasig.cas.client.validation.Assertion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class Item_CommanderTest {

    @Test
    void getId_item_commander() {
       Item_Commander item_commander = new Item_Commander();
       item_commander.setId_item_commander(2);
       assertEquals(2,item_commander.getId_item_commander());
       item_commander.setId_item_commander(6);
       assertEquals(6,item_commander.getId_item_commander());
       item_commander.setId_item_commander(99);
       assertEquals(99,item_commander.getId_item_commander());
    }

    @Test
    void setId_item_commander() {
        Item_Commander item_commander = new Item_Commander();
        item_commander.setId_item_commander(2);
        assertEquals(2,item_commander.getId_item_commander());
        item_commander.setId_item_commander(6);
        assertEquals(6,item_commander.getId_item_commander());
        item_commander.setId_item_commander(99);
        assertEquals(99,item_commander.getId_item_commander());
    }

    @Test
    void getQuantite() {
        Item_Commander item_commander = new Item_Commander();
        item_commander.setQuantite(2);
        assertEquals(2,item_commander.getQuantite());
        item_commander.setQuantite(6);
        assertEquals(6,item_commander.getQuantite());
        item_commander.setQuantite(99);
        assertEquals(99,item_commander.getQuantite());
    }

    @Test
    void setQuantite() {
        Item_Commander item_commander = new Item_Commander();
        item_commander.setQuantite(2);
        assertEquals(2,item_commander.getQuantite());
        item_commander.setQuantite(6);
        assertEquals(6,item_commander.getQuantite());
        item_commander.setQuantite(99);
        assertEquals(99,item_commander.getQuantite());
    }

    @Test
    void getPrixtotal() {
        Item_Commander item_commander = new Item_Commander();
        item_commander.setPrixtotal(2);
        assertEquals(2,item_commander.getPrixtotal());
        item_commander.setPrixtotal(6);
        assertEquals(6,item_commander.getPrixtotal());
        item_commander.setPrixtotal(99);
        assertEquals(99,item_commander.getPrixtotal());
    }

    @Test
    void setPrixtotal() {
        Item_Commander item_commander = new Item_Commander();
        item_commander.setPrixtotal(2);
        assertEquals(2,item_commander.getPrixtotal());
        item_commander.setPrixtotal(6);
        assertEquals(6,item_commander.getPrixtotal());
        item_commander.setPrixtotal(99);
        assertEquals(99,item_commander.getPrixtotal());
    }

    @Test
    void getId_commande() {
        Item_Commander item_commander = new Item_Commander();
        item_commander.setId_commande(2);
        assertEquals(2,item_commander.getId_commande());
        item_commander.setId_commande(6);
        assertEquals(6,item_commander.getId_commande());
        item_commander.setId_commande(99);
        assertEquals(99,item_commander.getId_commande());
    }

    @Test
    void setId_commande() {
        Item_Commander item_commander = new Item_Commander();
        item_commander.setId_commande(2);
        assertEquals(2,item_commander.getId_commande());
        item_commander.setId_commande(6);
        assertEquals(6,item_commander.getId_commande());
        item_commander.setId_commande(99);
        assertEquals(99,item_commander.getId_commande());
    }

    @Test
    void getIdproduit() {
        Item_Commander item_commander = new Item_Commander();
        item_commander.setIdproduit(2);
        assertEquals(2,item_commander.getIdproduit());
        item_commander.setIdproduit(6);
        assertEquals(6,item_commander.getIdproduit());
        item_commander.setIdproduit(99);
        assertEquals(99,item_commander.getIdproduit());
    }

    @Test
    void setIdproduit() {
        Item_Commander item_commander = new Item_Commander();
        item_commander.setIdproduit(2);
        assertEquals(2,item_commander.getIdproduit());
        item_commander.setIdproduit(6);
        assertEquals(6,item_commander.getIdproduit());
        item_commander.setIdproduit(99);
        assertEquals(99,item_commander.getIdproduit());
    }

    @Test
    void getId_etat_commande() {
        Item_Commander item_commander = new Item_Commander();
        item_commander.setId_etat_commade(2);
        assertEquals(2,item_commander.getId_etat_commade());
        item_commander.setId_etat_commade(6);
        assertEquals(6,item_commander.getId_etat_commade());
        item_commander.setId_etat_commade(99);
        assertEquals(99,item_commander.getId_etat_commade());
    }

    @Test
    void setId_etat_commade() {
        Item_Commander item_commander = new Item_Commander();
        item_commander.setId_etat_commade(2);
        assertEquals(2,item_commander.getId_etat_commade());
        item_commander.setId_etat_commade(6);
        assertEquals(6,item_commander.getId_etat_commade());
        item_commander.setId_etat_commade(99);
        assertEquals(99,item_commander.getId_etat_commade());
    }

    @Test
    void getProduit(){
        Produit produit = new Produit();
        Item_Commander item_commander = new Item_Commander();
        item_commander.setProduit(produit);
        Assertions.assertEquals(produit,item_commander.getProduit());
    }

    @Test
    void setProduit(){
        Produit produit = new Produit();
        Item_Commander item_commander = new Item_Commander();
        item_commander.setProduit(produit);
        Assertions.assertEquals(produit,item_commander.getProduit());
    }

    @Test
    void getDate(){
        Date date = new Date(5,6,7);
        Item_Commander item_commander = new Item_Commander();
        item_commander.setDate("date");
        Assertions.assertEquals("date",item_commander.getDate());
    }

    @Test
    void setDate(){

        Item_Commander item_commander = new Item_Commander();
        item_commander.setDate("date");
        Assertions.assertEquals("date",item_commander.getDate());
    }
}