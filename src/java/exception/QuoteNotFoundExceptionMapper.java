/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import com.google.gson.JsonObject;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Andreas
 */
@Provider
public class QuoteNotFoundExceptionMapper implements ExceptionMapper<QuoteNotFoundException>{

    @Context
    ServletContext context;
    @Override
    public Response toResponse(QuoteNotFoundException e) {
        
        JsonObject jo = new JsonObject();

        jo.addProperty("Message", e.getMessage());
        return Response.status(Response.Status.NOT_FOUND).entity(jo.toString()).build();
    }
    
}