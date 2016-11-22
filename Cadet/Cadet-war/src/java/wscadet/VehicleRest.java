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
    public String obtenerVehiculos() {
        return gson.toJson(vehiculo.listarVehiculos());
    }

    @GET 
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerUnVehiculo(@PathParam("id") int id) {
        return gson.toJson(vehiculo.buscarVehiculo(id));
    }
    
    //modificar
    @PUT 
    @Consumes(MediaType.APPLICATION_JSON)
    public String modificar(@QueryParam("id") Integer id, @QueryParam("matricula") String matricula, 
            @QueryParam("descripcion") String descripcion) {
        return gson.toJson(vehiculo.modificarVehiculo(id, matricula, descripcion));
    }
     
    //agregar
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String agregar(@QueryParam("matricula") String matricula, @QueryParam("descripcion") String descripcion) {
        return gson.toJson(vehiculo.agregarVehiculo(matricula, descripcion));
    }

    //eliminar
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public String eliminar(@QueryParam("id") int id) {
        return gson.toJson(vehiculo.eliminarVehiculo(id));
    }
    
    @PUT
    @Path("/{id}/{cadete}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String asociarCadeteVehiculos(@PathParam("id") Integer idVehiculo, 
            @PathParam("cadete") Integer idCadete) {
        return gson.toJson(vehiculo.asociarCadeteVehiculos(idVehiculo, idCadete));
    }
}