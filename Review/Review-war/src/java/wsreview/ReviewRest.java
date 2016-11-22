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
    public String agregar(@QueryParam("comment") String comment, 
            @QueryParam("rating") Integer rating, 
            @QueryParam("status") String status) {
        return gson.toJson(review.agregarReview(comment, rating, status));
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public String eliminar(@QueryParam("id") int id) {
        return gson.toJson(review.eliminarReview(id));
    }
}
