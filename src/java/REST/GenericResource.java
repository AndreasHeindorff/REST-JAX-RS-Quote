/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import exception.QuoteNotFoundException;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import static javax.ws.rs.client.Entity.json;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Andreas
 */
@Path("quotes")
public class GenericResource {
    Gson gson;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
        gson= new GsonBuilder().
                setPrettyPrinting().
                setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).
                create();
    }

    private static Map<Integer,String> quotes = new HashMap() {
    {
    put(1, "Friends are kisses blown to us by angels");
    put(2, "Do not take life too seriously. You will never get out of it alive");
    put(3, "Behind every great man, is a woman rolling her eyes");
    }
    };
    
    
//    @GET
//    @Path("{id}")
//    @Produces("application/json")
//    public String getQuote(@PathParam("id") String id) {
//        return gson.toJson(quotes.get(id));  
//    }
    
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response getQuote(@PathParam ("id") int id) throws QuoteNotFoundException{
        JsonObject quote = new JsonObject();
        quote.addProperty("quote", quotes.get(id));
        if(quotes.containsKey(id)){
        String jsonResponse = new Gson().toJson(quote);  
        return Response.status(Response.Status.OK).entity(jsonResponse).build();
        }
        else throw new QuoteNotFoundException("No quote with that ID");
    }
    
    @GET
    @Produces("application/json")
    public Response getAllQuotes() throws QuoteNotFoundException{
        if(quotes.isEmpty())
            throw new QuoteNotFoundException("No quotes were found");
        String jsonResponse = new Gson().toJson(quotes.values());
        return Response.status(Response.Status.OK).entity(jsonResponse).build();
    }
    
    @POST
    @Consumes("application/json")
    public Response createQuote(String q){
        JsonObject quote = new JsonObject();
        quote.addProperty("quote", q);
        quotes.put(quotes.size()+1, q);
        return Response.status(Response.Status.CREATED).entity(quote).build();
        
    }
   
    @DELETE
    @Path("{id}")
    @Consumes("application/json")
    public Response deleteBook(@PathParam("id") int id){
        return Response.status(Response.Status.GONE).entity(quotes.remove(id)).build();
        
    }
    
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response putJson(@PathParam("id") int id, String q) {
        String jsonResponse = new Gson().toJson(quotes.put(id, q));
        return Response.status(Response.Status.OK).entity(jsonResponse).build();  
    }
}
