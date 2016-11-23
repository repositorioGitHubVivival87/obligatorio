/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wsreview;

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
import review.ReviewBean;

/**
 *
 * @author Vivi
 */
@Path("cliente")
public class ReviewRest {
    private Gson gson = new Gson();
    @EJB
    private ReviewBean review;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ReviewRest
     */
    public ReviewRest() {
    }

    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerReview() {
        return gson.toJson(review.listarReview());
    }
    
    @PUT 
    @Consumes(MediaType.APPLICATION_JSON)
    public String modificar(@QueryParam("id") Integer id,
            @QueryParam("comment") String comment, 
            @QueryParam("rating") Integer rating, 
            @QueryParam("status") String status) {
        return gson.toJson(review.modificarReview(id, comment, rating, status));
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String altaReview(@QueryParam("idEnvio") Integer idEnvio) {
        //da de alta una review con ese idEnvio, comentario "", raiting 0, y estado "pending"
        return gson.toJson("");
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String calificarServicioYCadete(@QueryParam("idEnvio") Integer idEnvio, 
            @QueryParam("idCadete") Integer idCadete,
            @QueryParam("rating") Integer rating, 
            @QueryParam("comment") String comentario,
            @QueryParam("idCliente") Integer idCliente) {
        return gson.toJson("");
    }
    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public String validarCliente(@QueryParam("idCliente") Integer idCliente) {
        return gson.toJson("");
    }

    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public String validarReviewNoDuplicado(@QueryParam("idEnvio") Integer idEnvio) {
        //valida que no haya otra review para ese idEnvio
        return gson.toJson("");
    }
    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public String validarComentario(@QueryParam("comentario") String comentario) {
        //valida que tenga una cantidad minima de palabras
        return gson.toJson("");
    }
    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public String validarBuenVocabulario(@QueryParam("comentario") String comentario) {
        //valida que las palabras no esten en una "lista negra de palabras"
        return gson.toJson("");
    }
    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public String calificarSemantica(@QueryParam("comentario") String comentario) {
        //valida que la semantica del comentario sea correcto
        return gson.toJson("");
    }
    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public String notificarReviewCreada(@QueryParam("idReview") Integer idReview) {
        //Se envia la review a los distintos destinatarios para disparar otros procesos de negocio. 
        //Se comunica con el modulo Notification
        return gson.toJson("");
    }
    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public String listarEnviosPendientesCalificacion(@QueryParam("idCliente") Integer idCliente) {
        //pide a shipment todos los envios de este idCliente
    //si no tiene review para cada shipment lo agrego a la lista a retornar
        return gson.toJson("");
    }

    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public String listarCalificacionesCadete(@QueryParam("idCadete") Integer idCadete) {
        //pide a shipment todos los envios de este idCadete
        //si tiene review para cada shipment lo agrego a la lista a retornar
        return gson.toJson("");
    }
    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public String rechazarCalificacionCadete(@QueryParam("idCliente") Integer idCliente) {
        //llama al metodo anterior: listarCalificacionesCadete
        // y puede setear el estado de la review en rechazado(rejected)
        return gson.toJson("");
    }
    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public String calificacionNoAprobada(@QueryParam("idCliente") Integer idCliente) {
        //pide a shipment todos los envios con  reviews "no aprobado" y lo agrego a la lista a retornar
        return gson.toJson("");
    }
    
}
