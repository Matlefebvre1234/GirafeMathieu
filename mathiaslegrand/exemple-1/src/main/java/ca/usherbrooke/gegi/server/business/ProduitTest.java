package ca.usherbrooke.gegi.server.business;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;

class ProduitTest {


    @Test
    void connect() {
        //DriverManager.getConnection()
    }


    @Test
    void getNomitem() {
        Produit produit = new Produit();
        produit.setNomitem("Brosse a dent",1);
        Assertions.assertEquals("Brosse a dent",produit.getNomitem(1));
        produit.setNomitem("Bas",1);
        Assertions.assertEquals("Bas",produit.getNomitem(1));
        produit.setNomitem("Le Saint caliss",1);
        Assertions.assertEquals("Le Saint caliss",produit.getNomitem(1));

    }

    @Test
    void setNomitem() {
        Produit produit = new Produit();
        produit.setNomitem("Brosse a dent",1);
        Assertions.assertEquals("Brosse a dent",produit.getNomitem(1));
        produit.setNomitem("Bas",1);
        Assertions.assertEquals("Bas",produit.getNomitem(1));
        produit.setNomitem("Le Saint caliss",1);
        Assertions.assertEquals("Le Saint caliss",produit.getNomitem(1));
    }



    @Test
    void getDescription() {
        Produit produit = new Produit();
        produit.setDescription("WOW");
        Assertions.assertEquals("WOW",produit.getDescription(1));
        produit.setDescription("Nice");
        Assertions.assertEquals("Nice",produit.getDescription(1));
        produit.setDescription("Unnga Bunga");
        Assertions.assertEquals("Unnga Bunga",produit.getDescription(1));
        produit.setDescription("Magnifico");
        Assertions.assertEquals("Magnifico",produit.getDescription(1));
    }

    @Test
    void setDescription() {
        Produit produit = new Produit();
        produit.setDescription("WOW");
        Assertions.assertEquals("WOW",produit.getDescription(1));
        produit.setDescription("Nice");
        Assertions.assertEquals("Nice",produit.getDescription(1));
        produit.setDescription("Unnga Bunga");
        Assertions.assertEquals("Unnga Bunga",produit.getDescription(1));
        produit.setDescription("Magnifico");
        Assertions.assertEquals("Magnifico",produit.getDescription(1));
    }

    @Test
    void getIdproduit() {
        Produit produit = new Produit();
        produit.setIdproduit(1);
        Assertions.assertEquals(1,produit.getIdproduit());
        produit.setIdproduit(2);
        Assertions.assertEquals(2,produit.getIdproduit());
        produit.setIdproduit(3);
        Assertions.assertEquals(3,produit.getIdproduit());
        produit.setIdproduit(4);
        Assertions.assertEquals(4,produit.getIdproduit());

    }

    @Test
    void setIdproduit() {
        Produit produit = new Produit();
        produit.setIdproduit(1);
        Assertions.assertEquals(1,produit.getIdproduit());
        produit.setIdproduit(2);
        Assertions.assertEquals(2,produit.getIdproduit());
        produit.setIdproduit(3);
        Assertions.assertEquals(3,produit.getIdproduit());
        produit.setIdproduit(4);
        Assertions.assertEquals(4,produit.getIdproduit());

    }

    @Test
    void getPrix() {
        Produit produit = new Produit();
        produit.setPrix(14);
        Assertions.assertEquals(14,produit.getPrix());
        produit.setIdproduit(99);
        Assertions.assertEquals(99,produit.getIdproduit());
        produit.setIdproduit(154);
        Assertions.assertEquals(154,produit.getIdproduit());
        produit.setIdproduit(194);
        Assertions.assertEquals(194,produit.getIdproduit());


    }

    @Test
    void setPrix() {
        Produit produit = new Produit();
        produit.setPrix(14);
        Assertions.assertEquals(14,produit.getPrix());
        produit.setIdproduit(99);
        Assertions.assertEquals(99,produit.getIdproduit());
        produit.setIdproduit(154);
        Assertions.assertEquals(154,produit.getIdproduit());
        produit.setIdproduit(194);
        Assertions.assertEquals(194,produit.getIdproduit());

    }

    @Test
    void getTaille() {
        Produit produit = new Produit();
        produit.setTaille("Medium");
        Assertions.assertEquals("Medium", produit.getTaille());
        produit.setTaille("Large");
        Assertions.assertEquals("Large", produit.getTaille());
        produit.setTaille("XL");
        Assertions.assertEquals("XL", produit.getTaille());
        produit.setTaille("Small");
        Assertions.assertEquals("Small", produit.getTaille());
    }

    @Test
    void setTaille() {
        Produit produit = new Produit();
        produit.setTaille("Medium");
        Assertions.assertEquals("Medium", produit.getTaille());
        produit.setTaille("Large");
        Assertions.assertEquals("Large", produit.getTaille());
        produit.setTaille("XL");
        Assertions.assertEquals("XL", produit.getTaille());
        produit.setTaille("Small");
        Assertions.assertEquals("Small", produit.getTaille());
    }

    @Test
    void getCouleur() {
        Produit produit = new Produit();
        produit.setCouleur("Rouge");
        Assertions.assertEquals("Rouge", produit.getCouleur());
        produit.setTaille("Vert");
        Assertions.assertEquals("Vert", produit.getCouleur());
        produit.setTaille("Bleu");
        Assertions.assertEquals("Bleu", produit.getCouleur());
        produit.setTaille("Jaune");
        Assertions.assertEquals("Jaune", produit.getCouleur());
    }

    @Test
    void setCouleur() {
        Produit produit = new Produit();
        produit.setCouleur("Rouge");
        Assertions.assertEquals("Rouge", produit.getCouleur());
        produit.setTaille("Vert");
        Assertions.assertEquals("Vert", produit.getCouleur());
        produit.setTaille("Bleu");
        Assertions.assertEquals("Bleu", produit.getCouleur());
        produit.setTaille("Jaune");
        Assertions.assertEquals("Jaune", produit.getCouleur());

    }

    @Test
    void getVisibiliteSite() {
        Produit produit = new Produit();
        produit.setVisibiliteSite(1);
        Assertions.assertEquals(1,produit.getVisibiliteSite());
        produit.setVisibiliteSite(2);
        Assertions.assertEquals(2,produit.getVisibiliteSite());
        produit.setVisibiliteSite(3);
        Assertions.assertEquals(3,produit.getVisibiliteSite());
        produit.setVisibiliteSite(4);
        Assertions.assertEquals(4,produit.getVisibiliteSite());
    }

    @Test
    void setVisibiliteSite() {
        Produit produit = new Produit();
        produit.setVisibiliteSite(1);
        Assertions.assertEquals(1,produit.getVisibiliteSite());
        produit.setVisibiliteSite(2);
        Assertions.assertEquals(2,produit.getVisibiliteSite());
        produit.setVisibiliteSite(3);
        Assertions.assertEquals(3,produit.getVisibiliteSite());
        produit.setVisibiliteSite(4);
        Assertions.assertEquals(4,produit.getVisibiliteSite());
    }

    @Test
    void getIdEtat() {
        Produit produit = new Produit();
        produit.setIdEtat(1);
        Assertions.assertEquals(1,produit.getIdEtat());
        produit.setIdEtat(2);
        Assertions.assertEquals(2,produit.getIdEtat());
        produit.setIdEtat(3);
        Assertions.assertEquals(3,produit.getIdEtat());
        produit.setIdEtat(4);
        Assertions.assertEquals(4,produit.getIdEtat());
    }

    @Test
    void setIdEtat() {
        Produit produit = new Produit();
        produit.setIdEtat(1);
        Assertions.assertEquals(1,produit.getIdEtat());
        produit.setIdEtat(2);
        Assertions.assertEquals(2,produit.getIdEtat());
        produit.setIdEtat(3);
        Assertions.assertEquals(3,produit.getIdEtat());
        produit.setIdEtat(4);
        Assertions.assertEquals(4,produit.getIdEtat());
    }
}