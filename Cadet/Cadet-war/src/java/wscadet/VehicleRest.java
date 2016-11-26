/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wscadet;

import cadet.VehicleBean;
import com.google.gson.Gson;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Vivi
 */
@Path("vehiculo")
public class VehicleRest {
    private Gson gson = new Gson();
    @EJB
    private VehicleBean vehiculo;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of VehicleRest
     */
    public VehicleRest() {
    }

    //obtener
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerVehiculos(@QueryParam("usuario") String usuario, @QueryParam("contrasena") String contrasena) {
        return gson.toJson(vehiculo.listarVehiculos(usuario, contrasena));
    }

    @GET 
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerUnVehiculo(@QueryParam("usuario") String usuario, 
            @QueryParam("contrasena") String contrasena, @PathParam("id") int id) {
        return gson.toJson(vehiculo.buscarVehiculo(usuario, contrasena, id));
    }
    
    //modificar
    @PUT 
    @Consumes(MediaType.APPLICATION_JSON)
    public String modificar(@QueryParam("usuario") String usuario, @QueryParam("contrasena") String contrasena,
            @QueryParam("id") Integer id, @QueryParam("matricula") String matricula, 
            @QueryParam("descripcion") String descripcion) {
        return gson.toJson(vehiculo.modificarVehiculo(usuario, contrasena, id, matricula, descripcion));
    }
     
    //agregar
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String agregar(@QueryParam("usuario") String usuario, @QueryParam("contrasena") String contrasena,
            @QueryParam("matricula") String matricula, @QueryParam("descripcion") String descripcion) {
        return gson.toJson(vehiculo.agregarVehiculo(usuario, contrasena, matricula, descripcion));
    }

    //eliminar
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public String eliminar(@QueryParam("usuario") String usuario, @QueryParam("contrasena") String contrasena, 
            @QueryParam("id") int id) {
        return gson.toJson(vehiculo.eliminarVehiculo(usuario, contrasena, id));
    }
    
    @PUT
    @Path("/{id}/{cadete}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String asociarCadeteVehiculo(@QueryParam("usuario") String usuario, @QueryParam("contrasena") String contrasena,
            @PathParam("id") Integer idVehiculo, @PathParam("cadete") Integer idCadete) {
        return gson.toJson(vehiculo.asociarCadeteVehiculos(usuario, contrasena, idVehiculo, idCadete));
    }
}