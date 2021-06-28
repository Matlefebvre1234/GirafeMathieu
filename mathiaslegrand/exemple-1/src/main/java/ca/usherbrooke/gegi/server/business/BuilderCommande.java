package ca.usherbrooke.gegi.server.business;

import java.util.Date;

/**
 * Interface pour le builder de commande
 * @author Mathias Gagnon
 * @version 1.0
 * @see ConcreteBuilderCommande
 */
public interface BuilderCommande {
    void reset();
    void construireCommande(int idCcommande, String cip, Date date, int prixTotal, int idEtatCommande);
    void constuireItemCommander();
    void construireProduit();
}

