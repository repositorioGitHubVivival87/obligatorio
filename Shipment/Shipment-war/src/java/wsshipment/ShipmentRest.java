/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wsshipment;


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
import shipment.ShipmentBean;

/**
 *
 * @author Vivi
 */
@Path("envio")
public class ShipmentRest {

    private Gson gson = new Gson();
    @EJB
    private ShipmentBean envio;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EnvioRest
     */
    public ShipmentRest() {
    }
    
    //notificar al cadete que envio que debe realizar   
    @PUT 
    @Path("/{unCadete}/{idEnvio}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String notificarEnvio(@PathParam("unCadete")  Integer unCadete, 
            @PathParam("idEnvio")  Integer idEnvio) {
        return "";
    }
    
    @POST
    @Path("/{unCadete}/{unVehiculo}/{clienteEnvia}/{clienteRecibe}/{direccionRetiro}/{direccionRecibo}/{descripcion}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String solicitudEnvio(@PathParam("unCadete")  Integer unCadete, 
            @PathParam("unVehiculo") Integer unVehiculo,
            @PathParam("clienteEnvia")  Integer clienteEnvia, 
            @PathParam("clienteRecibe")  Integer clienteRecibe, 
            @PathParam("direccionRetiro") String direccionRetiro, 
            @PathParam("direccionRecibo") String direccionRecibo,
            @PathParam("descripcion") String descripcion) {
        //CON ESTADO PENDIENTE
        return ""; //gson.toJson(envio.solicitudEnvio(unCadete, unVehiculo, clienteEnvia, clienteRecibe, direccionRetiro, direccionRecibo, descripcion));
    }

    @POST
    @Path("/{unCadete}/{clienteEnvia}/{clienteRecibe}/{idEnvio}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String recepcionEnvio(@PathParam("unCadete")  Integer unCadete, 
            @PathParam("clienteEnvia")  Integer clienteEnvia, 
            @PathParam("clienteRecibe")  Integer clienteRecibe, 
            @PathParam("idEnvio") Integer idEnvio) {
        return "";
    }
        
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public String eliminar(@QueryParam("id") int id) {
        return "";
    }
  
    //LISTA TODOS LOS ENVIOS REGISTRADOS
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public String consulta() {
        return gson.toJson(envio.consulta());
    }
    
    //LISTA TODOS LOS ENVIOS REGISTRADOS DE UN CADETE
    @GET 
    @Path("/{idCadete}")
    @Produces(MediaType.APPLICATION_JSON)
    public String enviosPorCadete(@PathParam("idCadete") int idCadete) {
        return "";//gson.toJson(envio.consulta());
    }
    
//    //LISTA TODOS LOS ENVIOS REGISTRADOS DE UN CLIENTE
//    @GET 
//    @Path("/{idCliente}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String enviosPorCliente(@PathParam("idCliente") int idCliente) {
//        return "";//gson.toJson(envio.consulta());
//    }
    
}
