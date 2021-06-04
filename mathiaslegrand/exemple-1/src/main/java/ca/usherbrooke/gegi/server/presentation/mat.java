package ca.usherbrooke.gegi.server.presentation;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

@Path("/test")
public class mat extends Application {

    //localhost:8080/exemple-1/api/test/salut
    @GET
    @Path("/get")
    public String testget()
    {
        System.out.println("tamere");
        return "salut";
    }

    @POST
    @Path("/post")
    public void testPost(@FormParam("cip") String cip, @FormParam("nom") String nom)
    {
        System.out.println(cip);
        System.out.println(nom);


    }


}
