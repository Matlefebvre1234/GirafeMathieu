package ca.usherbrooke.gegi.server.injection;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath(RestApplicationPathConfig.ROOT)

/**
 * Cette classe permet d'initialiser des api(microservices)
 */
public class RestApplicationPathConfig extends Application {

    public static final String ROOT = "api";
}
