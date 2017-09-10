package no.ntnu.bjørdalsbakke.chatapplication;

import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import no.ntnu.bjørdalsbakke.domain.Conversation;
import no.ntnu.bjørdalsbakke.domain.Message;

/**
 *
 * @author Hallvard
 */
@Stateless
@Path("messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConversationServices {
    
    
    @PersistenceContext
    EntityManager em;
    
    @GET
    public List<Message> getMessages(@QueryParam("name") String name)
    {
        List<Message> result = null;
        if(name != null)
        {
            result = em.createQuery("Select m From Messaqge m",
            Message.class).getResultList();
        }
        
        return result != null ? result: Collections.EMPTY_LIST;
    }
    
    @POST
    @Path("add")
    public Message addMessage(@QueryParam("name") String name,
            Message message)
    {
        System.out.println("addMessage name = " + name + "message " + message);
        Conversation c = em.find(Conversation.class, name);
        if (c != null)
        {
            message.setConversation(c);
            em.persist(message);
            
        }
        return message;
    }
}
