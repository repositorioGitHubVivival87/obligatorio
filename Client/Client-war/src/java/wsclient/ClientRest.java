/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wsclient;


import client.ClientBean;
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
@Path("cliente")
public class ClientRest {
    private Gson gson = new Gson();
    @EJB
    private ClientBean cliente;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ClientRest
     */
    public ClientRest() {
    }

    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerClientes() {
        return gson.toJson(cliente.listarClientes());
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerUnCliente(@PathParam("id") int id) {
        return gson.toJson(cliente.buscarClientes(id));
    }
    
    @PUT 
    @Consumes(MediaType.APPLICATION_JSON)
    public String modificar(@QueryParam("id") Integer id, @QueryParam("ci") Integer ci, 
            @QueryParam("nombre") String nombre, @QueryParam("apellido") String apellido, 
            @QueryParam("email") String email) {
        return gson.toJson(cliente.modificarCliente(id, ci, nombre, apellido, email));
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String agregar(@QueryParam("ci") Integer ci, @QueryParam("nombre") String nombre, 
            @QueryParam("apellido") String apellido, @QueryParam("email") String email) {
        return gson.toJson(cliente.agregarCliente(ci, nombre, apellido, email));
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public String eliminar(@QueryParam("id") int id) {
        return gson.toJson(cliente.eliminarCliente(id));
    }
}
