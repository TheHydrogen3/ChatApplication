
package no.ntnu.bjørdalsbakke.chatapplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Hallvard
 */

@Path("chat")
@Produces(MediaType.APPLICATION_JSON)
public class ChatService {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
   
    @GET
    @Path("hello/")
    public Response helloWorld(@QueryParam("name") String name){
        JsonObject result = Json.createObjectBuilder()
                .add("message", "Hello," + name + "! ")
                .add("sub", Json.createObjectBuilder()
                        .add("subattrib", "sub"))
                .add("time",format.format(new Date()))
                .build();
        return Response.ok(result).build();
    }
            
    
}
