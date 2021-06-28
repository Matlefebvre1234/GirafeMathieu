package ca.usherbrooke.gegi.server.business;

/**
 * Interface pour le builder de commande
 * @author Mathias Gagnon
 * @version 1.0
 * @see ConcreteBuilderCommande
 */
public interface BuilderCommande {
    void reset();
    void construireCommande();
    void constuireItemCommander();
    void construireProduit();
}

