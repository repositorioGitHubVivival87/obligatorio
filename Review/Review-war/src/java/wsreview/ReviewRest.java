/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wsreview;

import com.google.gson.Gson;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
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
@Path("revision")
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

    //ESTE METODO HARIA EL ALTA DE REVIEW
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String calificarEnvio(@QueryParam("idEnvio") Integer idEnvio, 
            @QueryParam("idCadete") Integer idCadete,
            @QueryParam("rating") Integer rating, 
            @QueryParam("comment") String comentario,
            @QueryParam("idCliente") Integer idCliente) {
        return gson.toJson(review.calificarEnvio(idEnvio, idCadete, rating, comentario, idCliente));
    }
    
    @PUT 
    @Consumes(MediaType.APPLICATION_JSON)
    public String modificar(@QueryParam("id") Integer id,
            @QueryParam("comment") String comment, 
            @QueryParam("rating") Integer rating, 
            @QueryParam("status") String estado) {
        return gson.toJson(review.modificarReview(id, comment, rating, estado));
    }
     
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerReviews() {
        return gson.toJson(review.listarReview());
    }
    
    @GET 
    @Path("/listarEnviosPendientesCalificacion")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarEnviosPendientesCalificacion(@QueryParam("idCliente") Integer idCliente) {
        //pide a shipment todos los envios de este idCliente
        //si no tiene review para cada shipment lo agrego a la lista a retornar
        return gson.toJson(review.listarEnviosPendientesCalificacion(idCliente));
    }

    @GET 
    @Path("/listarCalificacionesCadetes")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarCalificacionesCadete(@QueryParam("idCadete") Integer idCadete) {
        //pide a shipment todos los envios de este idCadete
        //si tiene review para cada shipment lo agrego a la lista a retornar
        return gson.toJson("");
    }
    
    @GET 
    @Path("/rechazarCalificacionCadete")
    @Produces(MediaType.APPLICATION_JSON)
    public String rechazarCalificacionCadete(@QueryParam("idCliente") Integer idCliente) {
        //llama al metodo anterior: listarCalificacionesCadete
        // y puede setear el estado de la review en rechazado(rejected)
        return gson.toJson("");
    }
    
    @GET 
    @Path("/listarCalificacionesNoAprobadas")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarCalificacionNoAprobadas(@QueryParam("idCliente") Integer idCliente) {
        //pide a shipment todos los envios con  reviews "no aprobado" y lo agrego a la lista a retornar
        return gson.toJson("");
    }  
    
}
