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
        produit.setNomitem("Brosse a dent");
        Assertions.assertEquals("Brosse a dent",produit.getNomitem());
        produit.setNomitem("Bas");
        Assertions.assertEquals("Bas",produit.getNomitem());
        produit.setNomitem("Le Saint caliss");
        Assertions.assertEquals("Le Saint caliss",produit.getNomitem());

    }

    @Test
    void setNomitem() {
        Produit produit = new Produit();
        produit.setNomitem("Brosse a dent");
        Assertions.assertEquals("Brosse a dent",produit.getNomitem());
        produit.setNomitem("Bas");
        Assertions.assertEquals("Bas",produit.getNomitem());
        produit.setNomitem("Le Saint caliss");
        Assertions.assertEquals("Le Saint caliss",produit.getNomitem());
    }



    @Test
    void getDescription() {
        Produit produit = new Produit();
        produit.setDescription("WOW");
        Assertions.assertEquals("WOW",produit.getDescription());
        produit.setDescription("Nice");
        Assertions.assertEquals("Nice",produit.getDescription());
        produit.setDescription("Unnga Bunga");
        Assertions.assertEquals("Unnga Bunga",produit.getDescription());
        produit.setDescription("Magnifico");
        Assertions.assertEquals("Magnifico",produit.getDescription());
    }

    @Test
    void setDescription() {
        Produit produit = new Produit();
        produit.setDescription("WOW");
        Assertions.assertEquals("WOW",produit.getDescription());
        produit.setDescription("Nice");
        Assertions.assertEquals("Nice",produit.getDescription());
        produit.setDescription("Unnga Bunga");
        Assertions.assertEquals("Unnga Bunga",produit.getDescription());
        produit.setDescription("Magnifico");
        Assertions.assertEquals("Magnifico",produit.getDescription());
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
        produit.setVisibilite_site(1);
        Assertions.assertEquals(1,produit.getVisibilite_site());
        produit.setVisibilite_site(2);
        Assertions.assertEquals(2,produit.getVisibilite_site());
        produit.setVisibilite_site(3);
        Assertions.assertEquals(3,produit.getVisibilite_site());
        produit.setVisibilite_site(4);
        Assertions.assertEquals(4,produit.getVisibilite_site());
    }

    @Test
    void setVisibiliteSite() {
        Produit produit = new Produit();
        produit.setVisibilite_site(1);
        Assertions.assertEquals(1,produit.getVisibilite_site());
        produit.setVisibilite_site(2);
        Assertions.assertEquals(2,produit.getVisibilite_site());
        produit.setVisibilite_site(3);
        Assertions.assertEquals(3,produit.getVisibilite_site());
        produit.setVisibilite_site(4);
        Assertions.assertEquals(4,produit.getVisibilite_site());
    }

    @Test
    void getIdEtat() {
        Produit produit = new Produit();
        produit.setId_etat(1);
        Assertions.assertEquals(1,produit.getId_etat());
        produit.setId_etat(2);
        Assertions.assertEquals(2,produit.getId_etat());
        produit.setId_etat(3);
        Assertions.assertEquals(3,produit.getId_etat());
        produit.setId_etat(4);
        Assertions.assertEquals(4,produit.getId_etat());
    }

    @Test
    void setIdEtat() {
        Produit produit = new Produit();
        produit.setId_etat(1);
        Assertions.assertEquals(1,produit.getId_etat());
        produit.setId_etat(2);
        Assertions.assertEquals(2,produit.getId_etat());
        produit.setId_etat(3);
        Assertions.assertEquals(3,produit.getId_etat());
        produit.setId_etat(4);
        Assertions.assertEquals(4,produit.getId_etat());
    }
}