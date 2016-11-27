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
@Path("notificacion")
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
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String agregar(@QueryParam("emailEmisor") String emailEmisor, 
            @QueryParam("emailReceptor") String emailReceptor, 
            @QueryParam("mensaje") String mensaje) {
        return gson.toJson(notification.agregarNotification(emailEmisor, emailReceptor, mensaje));
    }
    
    @PUT 
    @Consumes(MediaType.APPLICATION_JSON)
    public String modificar(@QueryParam("id") Integer id, 
            @QueryParam("emailEmisor") String emailEmisor, 
            @QueryParam("emailReceptor") String emailReceptor, 
            @QueryParam("mensaje") String mensaje) {
        return gson.toJson(notification.modificarNotification(id, emailEmisor, emailReceptor, mensaje));
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public String eliminar(@QueryParam("id") int id) {
        return gson.toJson(notification.eliminarNotification(id));
    }
    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerNotification() {
        return gson.toJson(notification.listarNotifications());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String notificarEnvioCadete(@QueryParam("idEnvio") Integer idEnvio,
            @QueryParam("idCadete") Integer idCadete,
            @QueryParam("direccionEnvia") String direccionEnvia,
            @QueryParam("direccionRecibe") String direccionRecibe,
            @QueryParam("clienteEnvia") Integer clienteEnvia,
            @QueryParam("clienteRecibe") Integer clienteRecibe,
            @QueryParam("costoEnvio") Double costoEnvio) {
        return gson.toJson(notification.notificarEnvioCadete(idEnvio, idCadete, direccionEnvia, 
                direccionRecibe, clienteEnvia, clienteRecibe, costoEnvio));
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String notificarRecepcionEnvio(@QueryParam("idEnvio") Integer idEnvio,
            @QueryParam("emailEnvia") String emailEnvia,
            @QueryParam("emailRecibe") String emailRecibe,
            @QueryParam("nombreCadete") String nombreCadete,
            @QueryParam("fechaRecibe") Integer fechaRecibido,
            @QueryParam("horaRecibe") Integer horaRecibido,
            @QueryParam("costoEnvio") Double costoEnvio) {
        return gson.toJson(notification.notificarRecepcionEnvio(idEnvio, emailEnvia, emailRecibe, nombreCadete,
                fechaRecibido, horaRecibido, costoEnvio));
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String notificarReview(@QueryParam("idReview") Integer idReview,
            @QueryParam("idEnvio") Integer idEnvio,
            @QueryParam("calificacion") Integer calificacion,
            @QueryParam("mensaje") String mensaje) {
        return gson.toJson(notification.notificarRecepcionEnvio(idReview, idEnvio, calificacion, mensaje));
    }
}
