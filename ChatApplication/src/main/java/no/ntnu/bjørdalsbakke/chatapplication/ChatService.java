
package no.ntnu.bjørdalsbakke.chatapplication;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import net.coobird.thumbnailator.Thumbnails;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

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
