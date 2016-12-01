/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wscadet;

import cadet.CadetBean;
import com.google.gson.Gson;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;

/**
 *
 * @author Vivi
 */

@Path("cadete")
public class CadetRest {
    private Gson gson = new Gson();
    @EJB
    private CadetBean cadete;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ClientRest
     */
    public CadetRest() {
    }
   
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String agregar(@QueryParam("usuario") String usuario, @QueryParam("contrasena") String contrasena,
            @QueryParam("ci") Integer ci, @QueryParam("nombre") String nombre, 
            @QueryParam("apellido") String apellido, @QueryParam("email") String email) {
        return gson.toJson(cadete.agregarCadete(usuario, contrasena, ci, nombre, apellido, email));
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String modificar(@QueryParam("usuario") String usuario, @QueryParam("contrasena") String contrasena,
            @QueryParam("id") Integer id, @QueryParam("ci") Integer ci,
            @QueryParam("nombre") String nombre, @QueryParam("apellido") String apellido,
            @QueryParam("email") String email) {
        return gson.toJson(cadete.modificarCadete(usuario, contrasena, id, ci, nombre, apellido, email));
    }   

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public String eliminar(@QueryParam("usuario") String usuario, @QueryParam("contrasena") String contrasena, 
            @QueryParam("id") int id) {
        return gson.toJson(cadete.eliminarCadete(usuario, contrasena, id));
    }
    
    //obtener
    @GET 
    @Path("/obtenerCadetes")
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerCadetes() {
        return gson.toJson(cadete.listarCadetes());
    }
        
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerCadetesMasCercanos(@QueryParam("latitud") String latitud, 
            @QueryParam("longitud") String longitud) {
        //esta lista retorna 4 cadetes
        return gson.toJson(cadete.listarCadetesMasCercanos(latitud, longitud));
    }

    @GET
    @Path("/obtenerUnCadete")
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerUnCadete(@QueryParam("id") int id) {
        return gson.toJson(cadete.buscarUnCadete(id));
    }
    
    @PUT
    @Path("/actualizarRating")
    @Consumes(MediaType.APPLICATION_JSON)
    public String actualizarRating(@QueryParam("usuario") String usuario, @QueryParam("contrasena") String contrasena,
            @QueryParam("idCadete") Integer idCadete, @QueryParam("rating") Integer rating) {
        //hace un promedio entre el que tiene y el que le estan pasando por parametros
        return gson.toJson(cadete.actualizarRating(usuario, contrasena, idCadete, rating));
    }
}
