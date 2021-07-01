package ca.usherbrooke.gegi.server.presentation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CommandeServiceTest {


    @Test
    void getLastIndex() {
        //Aller void la quantite de produit dans la base de donnees
        CommandeService commandeService = new CommandeService();
        Assertions.assertEquals(13, commandeService.getLastIndex()-1);
    }

    @Test
    void listeProduits() {
        CommandeService commandeService = new CommandeService();
        //Assertions.assertEquals(13,commandeService.listeProduits());
    }

    @Test
    void prixTotal() {
    }

    @Test
    void getListeItemCommander() {
    }

    @Test
    void getCommande() {
    }

    @Test
    void commanderItem() {
    }
}