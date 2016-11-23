/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wsnotification;

import com.google.gson.Gson;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import notification.NotificationBean;

/**
 *
 * @author Vivi
 */
@Path("notification")
public class NotificationRest {
    private Gson gson = new Gson();
    @EJB
    private NotificationBean notification;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ClientRest
     */
    public NotificationRest() {
    }

    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerNotification() {
        return gson.toJson(notification.listarNotifications());
    }
    
    @PUT 
    @Consumes(MediaType.APPLICATION_JSON)
    public String modificar(@QueryParam("id") Integer id, 
            @QueryParam("emailEmisor") String emailEmisor, 
            @QueryParam("emailReceptor") String emailReceptor, 
            @QueryParam("mensaje") String mensaje) {
        return gson.toJson(notification.modificarNotification(id, emailEmisor, emailReceptor, mensaje));
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String agregar(@QueryParam("emailEmisor") String emailEmisor, 
            @QueryParam("emailReceptor") String emailReceptor, 
            @QueryParam("mensaje") String mensaje) {
        return gson.toJson(notification.agregarNotification(emailEmisor, emailReceptor, mensaje));
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public String eliminar(@QueryParam("id") int id) {
        return gson.toJson(notification.eliminarNotification(id));
    }
}