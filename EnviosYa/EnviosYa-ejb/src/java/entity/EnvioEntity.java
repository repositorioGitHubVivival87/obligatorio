/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Vivi
 */
@Entity
public class EnvioEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(length = 1000)
    private String descripcion;
    
    private ClienteEntity clienteEnvia;
    private ClienteEntity clienteRecibe;
    private CadeteEntity unCadete;
    private VehiculoEntity unVehiculo;
    
    @Column(length = 500)
    private String direccionRetiro;
    
    @Column(length = 500)
    private String direccionRecibo;

    //CONSTRUCTOR
    public EnvioEntity() {
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

    public ClienteEntity getClienteEnvia() {
        return clienteEnvia;
    }

    public void setClienteEnvia(ClienteEntity clienteEnvia) {
        this.clienteEnvia = clienteEnvia;
    }

    public ClienteEntity getClienteRecibe() {
        return clienteRecibe;
    }

    public void setClienteRecibe(ClienteEntity clienteRecibe) {
        this.clienteRecibe = clienteRecibe;
    }

    public CadeteEntity getUnCadete() {
        return unCadete;
    }

    public void setUnCadete(CadeteEntity unCadete) {
        this.unCadete = unCadete;
    }

    public VehiculoEntity getUnVehiculo() {
        return unVehiculo;
    }

    public void setUnVehiculo(VehiculoEntity unVehiculo) {
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

    //METODOS
    @Override
    public int hashCode() {
        int hash = 0;
        hash += id != null ? id.hashCode() : 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof EnvioEntity)) {
            return false;
        }
        EnvioEntity other = (EnvioEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.EnvioEntity[ id=" + id + " ]";
    }
    
}
