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
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Vivi
 */
public class VehicleRest {

    private Gson gson = new Gson();
    @EJB
    private VehicleBean cliente;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ClientRest
     */
    public VehicleRest() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String agregar(@QueryParam("usuario") String usuario, @QueryParam("contrasena") String contrasena,
            @QueryParam("matricula") String matricula, @QueryParam("descripcion") String descripcion) {
        return gson.toJson(cliente.agregarVehiculo(usuario, contrasena, matricula, descripcion));
    }
}
