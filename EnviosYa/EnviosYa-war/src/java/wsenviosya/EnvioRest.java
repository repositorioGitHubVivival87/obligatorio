/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wsenviosya;

import bean.EnvioBean;
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
@Path("envio")
public class EnvioRest {

    private Gson gson = new Gson();
    @EJB
    private EnvioBean envio;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EnvioRest
     */
    public EnvioRest() {
    }
    
    @PUT 
    @Consumes(MediaType.APPLICATION_JSON)
    public String modificar(@QueryParam("id") Integer id, @QueryParam("ci") Integer ci, 
            @QueryParam("nombre") String nombre, @QueryParam("apellido") String apellido, 
            @QueryParam("email") String email) {
        return "";
    }
    
    @POST
    @Path("/{unCadete}/{unVehiculo}/{clienteEnvia}/{clienteRecibe}/{direccionRetiro}/{direccionRecibo}/{descripcion}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String agregar(@PathParam("unCadete")  Integer unCadete, 
            @PathParam("unVehiculo") Integer unVehiculo,
            @PathParam("clienteEnvia")  Integer clienteEnvia, 
            @PathParam("clienteRecibe")  Integer clienteRecibe, 
            @PathParam("direccionRetiro") String direccionRetiro, 
            @PathParam("direccionRecibo") String direccionRecibo,
            @PathParam("descripcion") String descripcion) {
        return gson.toJson(envio.agregarEnvio(unCadete, unVehiculo, clienteEnvia, clienteRecibe,
                direccionRetiro, direccionRecibo, descripcion));
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public String eliminar(@QueryParam("id") int id) {
        return "";
    }
    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public String consulta() {
        return gson.toJson(envio.consulta());
    }
}
