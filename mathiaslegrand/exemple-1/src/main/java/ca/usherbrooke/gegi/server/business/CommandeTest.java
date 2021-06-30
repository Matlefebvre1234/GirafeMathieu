package ca.usherbrooke.gegi.server.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        commande.setPrixTotal(2000);
        Assertions.assertEquals(2000,commande.getPrixTotal());
    }

    @Test
    void setPrix_total() {
        Commande commande = new Commande();
        commande.setPrixTotal(2000);
        Assertions.assertEquals(2000,commande.getPrixTotal());
    }

    @Test
    void getId_commande() {
        Commande commande = new Commande();
        commande.setIdCommande(2000);
        Assertions.assertEquals(2000,commande.getIdCommande());
    }

    @Test
    void setId_commande() {
        Commande commande = new Commande();
        commande.setIdCommande(2000);
        Assertions.assertEquals(2000,commande.getIdCommande());
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
        commande.setIdEtatCommande(2);
        Assertions.assertEquals(2,commande.getIdEtatCommande());
    }

    @Test
    void setId_etat_commande() {
        Commande commande = new Commande();
        commande.setIdEtatCommande(2);
        Assertions.assertEquals(2,commande.getIdEtatCommande());
    }
}