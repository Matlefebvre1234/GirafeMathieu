package ca.usherbrooke.gegi.server.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteBuilderProduitTest {

    @Test
    void construireProduitInterface() {
        ConcreteBuilderProduit concreteBuilderProduit = new ConcreteBuilderProduit();
        Produit po = concreteBuilderProduit.construireProduitInterface(1,"WOW","SA",399,"M","BLEU",1,1);
        Assertions.assertEquals(1,po.getIdproduit());
        Assertions.assertEquals("WOW",po.getNomitem());
        Assertions.assertEquals("SA",po.getDescription());
        Assertions.assertEquals(399,po.getPrix());
        Assertions.assertEquals("M",po.getTaille());
        Assertions.assertEquals("BLEU",po.getCouleur());
        Assertions.assertEquals(1,po.getVisibilite_site());
        Assertions.assertEquals(1,po.getId_etat());
    }

    @Test
    void construireProduitLogique() {
        ConcreteBuilderProduit concreteBuilderProduit = new ConcreteBuilderProduit();
        Produit po  = concreteBuilderProduit.construireProduitLogique(1,"CVCV",399,1,2);
        Assertions.assertEquals(1,po.getIdproduit());
        Assertions.assertEquals("CVCV",po.getNomitem());
        Assertions.assertEquals(399,po.getPrix());
        Assertions.assertEquals(1,po.getVisibilite_site());
        Assertions.assertEquals(2,po.getId_etat());
    }
}