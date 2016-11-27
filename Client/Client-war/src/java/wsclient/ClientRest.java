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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String agregar(@QueryParam("usuario") String usuario, @QueryParam("contrasena") String contrasena,
            @QueryParam("ci") Integer ci, @QueryParam("nombre") String nombre, 
            @QueryParam("apellido") String apellido, @QueryParam("email") String email) {
        return gson.toJson(cliente.agregarCliente(usuario, contrasena, ci, nombre, apellido, email));
    }
    
    @PUT 
    @Consumes(MediaType.APPLICATION_JSON)
    public String modificar(@QueryParam("usuario") String usuario, @QueryParam("contrasena") String contrasena,
            @QueryParam("id") Integer id, @QueryParam("ci") Integer ci, 
            @QueryParam("nombre") String nombre, @QueryParam("apellido") String apellido, 
            @QueryParam("email") String email) {
        return gson.toJson(cliente.modificarCliente(usuario, contrasena, id, ci, nombre, apellido, email));
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public String eliminar(@QueryParam("usuario") String usuario, 
            @QueryParam("contrasena") String contrasena, 
            @QueryParam("id") int id) {
        return gson.toJson(cliente.eliminarCliente(usuario, contrasena, id));
    }

    //LISTA TODOS LOS CLIENTES DE LA BD
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerClientes(@QueryParam("usuario") String usuario, @QueryParam("contrasena") String contrasena) {
        return gson.toJson(cliente.listarClientes());
    }

    //OBTENER UN CLIENTE
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerUnCliente(@QueryParam("usuario") String usuario, @QueryParam("contrasena") String contrasena) {
        return gson.toJson(cliente.obtenerUnCliente(usuario, contrasena));
    }
    
    //VALIDAR CLIENTE
    @GET
    @Path("/{usuario}/{contrasena}")
    @Produces(MediaType.APPLICATION_JSON)
    public String validarCliente(@QueryParam("usuario") String usuario, @QueryParam("contrasena") String contrasena) {
        return gson.toJson(cliente.esCliente(usuario, contrasena));
    }     
}
