package ca.usherbrooke.gegi.server.presentation;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;

@Path("/test")
public class mat extends Application {

    //localhost:8080/exemple-1/api/test/salut
    @GET
    @Path("/salut")
    public String test()
    {
        System.out.println("tamere");
        return "salut";
    }


}
