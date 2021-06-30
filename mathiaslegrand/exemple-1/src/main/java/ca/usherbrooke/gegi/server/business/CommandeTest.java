package ca.usherbrooke.gegi.server.business;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandeTest {

    @Test
    void getDate() {
        Commande commande = new Commande();
        commande.setDate("41-05");
        Assertions.assertEquals("41-05",commande.getDate());
    }

    @Test
    void setDate() {
        Commande commande = new Commande();
        commande.setDate("41-05");
        Assertions.assertEquals("41-05",commande.getDate());
    }

    @Test
    void getPrix_total() {
        Commande commande = new Commande();
        commande.setPrix_total(2000);
        Assertions.assertEquals(2000,commande.getPrix_total());
    }

    @Test
    void setPrix_total() {
        Commande commande = new Commande();
        commande.setPrix_total(2000);
        Assertions.assertEquals(2000,commande.getPrix_total());
    }

    @Test
    void getId_commande() {
        Commande commande = new Commande();
        commande.setId_commande(2000);
        Assertions.assertEquals(2000,commande.getId_commande());
    }

    @Test
    void setId_commande() {
        Commande commande = new Commande();
        commande.setId_commande(2000);
        Assertions.assertEquals(2000,commande.getId_commande());
    }

    @Test
    void getCip() {
        Commande commande = new Commande();
        commande.setCip("elka0602");
        Assertions.assertEquals("elka0602",commande.getCip());
    }

    @Test
    void setCip() {
        Commande commande = new Commande();
        commande.setCip("elka0602");
        Assertions.assertEquals("elka0602",commande.getCip());
    }

    @Test
    void getId_etat_commande() {
        Commande commande = new Commande();
        commande.setId_etat_commande(2);
        Assertions.assertEquals(2,commande.getId_etat_commande());
    }

    @Test
    void setId_etat_commande() {
        Commande commande = new Commande();
        commande.setId_etat_commande(2);
        Assertions.assertEquals(2,commande.getId_etat_commande());
    }
}