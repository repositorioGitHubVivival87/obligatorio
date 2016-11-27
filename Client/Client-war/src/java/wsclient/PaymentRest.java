/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wsclient;

import client.PaymentBean;
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

/**
 *
 * @author Vivi
 */
@Path("mediodepago")
public class PaymentRest {
    private Gson gson = new Gson();
    @EJB
    private PaymentBean mdp;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PaymentRest
     */
    public PaymentRest() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String agregar(@QueryParam("idCliente") Integer idCliente, 
            @QueryParam("sello") String sello,
            @QueryParam("nombreembosado") String nombreembosado, 
            @QueryParam("fechavencimiento") Integer fechavencimiento, 
            @QueryParam("cvv") Integer cvv, 
            @QueryParam("nroTarjeta") Integer nroTarjeta) {
        return gson.toJson(mdp.agregarMedioPago(idCliente, sello, nombreembosado, fechavencimiento, cvv, nroTarjeta));
    }
    
    @PUT 
    @Consumes(MediaType.APPLICATION_JSON)
    public String modificar(@QueryParam("id") Integer id, 
           @QueryParam("idCliente") Integer idCliente, 
            @QueryParam("sello") String sello,
            @QueryParam("nombreembosado") String nombreembosado, 
            @QueryParam("fechavencimiento") Integer fechavencimiento, 
            @QueryParam("cvv") Integer cvv, 
            @QueryParam("nroTarjeta") Integer nroTarjeta) {
        return gson.toJson(mdp.modificarMedioDePago(idCliente, id, sello, 
                nombreembosado, fechavencimiento, cvv, nroTarjeta));
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public String eliminar(@QueryParam("id") int id) {
        return gson.toJson(mdp.eliminarMedioDePago(id));
    }

    //LISTA TODOS LOS MEDIOS DE PAGO DE UN CLIENTE
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerListarMediosDePago(@QueryParam("idCliente") Integer idCliente) {
        return gson.toJson(mdp.listarMediosDePagoPorCliente(idCliente));
    }

}
