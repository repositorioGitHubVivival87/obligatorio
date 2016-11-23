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
    
    @PUT 
    @Consumes(MediaType.APPLICATION_JSON)
    public String modificar(@QueryParam("id") Integer id, @QueryParam("ci") Integer ci, 
            @QueryParam("nombre") String nombre, @QueryParam("apellido") String apellido, 
            @QueryParam("email") String email, @QueryParam("estado") String estado) {
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
        return gson.toJson(envio.solicitudEnvio(unCadete, unVehiculo, clienteEnvia, clienteRecibe,
                direccionRetiro, direccionRecibo, descripcion));
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
    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public String consulta() {
        return gson.toJson(envio.consulta());
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String validarUsuario(@PathParam("idUsuarioEnvia") Integer idUsuarioEnvia,
            @PathParam("idUsuarioRecibe") Integer idUsuarioRecibe) {
        return "";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String buscarCadetes(@PathParam("latitud") Integer latitud,
            @PathParam("logitud") Integer longitud) {
        return "";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String seleccionarCadete(@PathParam("idCadete1") Integer idCadete1,
            @PathParam("idCadete2") Integer idCadete2,
            @PathParam("idCadete3") Integer idCadete3,
            @PathParam("idCadete4") Integer idCadete4) {
        return "";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String asignarEnvio(@PathParam("idCadete") Integer idCadete,
            @PathParam("idEnvio") Integer idEnvio) {
        return "";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String calcularCostoEnvio(@PathParam("foto") String foto) {
        return "";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String notificarRecepcionEnvio(@PathParam("idEnvio") Integer idEnvio) {
        return "";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listarEnviosPorCliente(@PathParam("idCliente") Integer idCliente) {
        // lista ordenada por fecha-hora de mayor a menor, para el RF6
        return "";
    }
}
