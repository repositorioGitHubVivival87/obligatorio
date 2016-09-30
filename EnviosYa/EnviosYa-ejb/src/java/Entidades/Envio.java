/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author Vivi
 */
public class Envio {
    private Integer id;
    private String descripcion;
    private Cliente clienteEnvia;
    private Cliente clienteRecibe;
    private Cadete unCadete;
    private Vehiculo unVehiculo;
    private String direccionRetiro;
    private String direccionRecibo;

    //CONSTRUCTOR
    public Envio() {
    }

    //SET AND GET
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cliente getClienteEnvia() {
        return clienteEnvia;
    }

    public void setClienteEnvia(Cliente clienteEnvia) {
        this.clienteEnvia = clienteEnvia;
    }

    public Cliente getClienteRecibe() {
        return clienteRecibe;
    }

    public void setClienteRecibe(Cliente clienteRecibe) {
        this.clienteRecibe = clienteRecibe;
    }

    public Cadete getUnCadete() {
        return unCadete;
    }

    public void setUnCadete(Cadete unCadete) {
        this.unCadete = unCadete;
    }

    public Vehiculo getUnVehiculo() {
        return unVehiculo;
    }

    public void setUnVehiculo(Vehiculo unVehiculo) {
        this.unVehiculo = unVehiculo;
    }

    public String getDireccionRetiro() {
        return direccionRetiro;
    }

    public void setDireccionRetiro(String direccionRetiro) {
        this.direccionRetiro = direccionRetiro;
    }

    public String getDireccionRecibo() {
        return direccionRecibo;
    }

    public void setDireccionRecibo(String direccionRecibo) {
        this.direccionRecibo = direccionRecibo;
    }
    
}
