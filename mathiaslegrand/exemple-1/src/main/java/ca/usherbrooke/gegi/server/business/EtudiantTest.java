package ca.usherbrooke.gegi.server.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EtudiantTest {

    @Test
    void getCip() {
        Etudiant etudiant = new Etudiant();
        etudiant.setCip("elka0602");
        etudiant.getCip();
    }

    @Test
    void setCip() {
        Etudiant etudiant = new Etudiant();
        etudiant.setCip("elka0602");
        etudiant.getCip();
    }

    @Test
    void getCourriel() {
        Etudiant etudiant = new Etudiant();
        etudiant.setCourriel("FFFFFF");
        etudiant.getCourriel();
    }

    @Test
    void setCourriel() {
        Etudiant etudiant = new Etudiant();
        etudiant.setCourriel("FFFFFF");
        etudiant.getCourriel();
    }

    @Test
    void getNom() {
        Etudiant etudiant = new Etudiant();
        etudiant.setNom("Aris");
        etudiant.getNom();
    }

    @Test
    void setNom() {
        Etudiant etudiant = new Etudiant();
        etudiant.setNom("Aris");
        etudiant.getNom();
    }

    @Test
    void getPrenom() {
        Etudiant etudiant = new Etudiant();
        etudiant.setPrenom("V");
        etudiant.getPrenom();
    }

    @Test
    void setPrenom() {
        Etudiant etudiant = new Etudiant();
        etudiant.setPrenom("V");
        etudiant.getPrenom();
    }
}