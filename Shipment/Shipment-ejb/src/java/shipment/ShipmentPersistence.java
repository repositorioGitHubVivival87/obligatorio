/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shipment;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Vivi
 */
public class ShipmentPersistence implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(length = 1000)
    private String descripcion;
    
    @Column(length = 500)
    private String direccionEnvia;
    
    @Column(length = 500)
    private String direccionDestinatario;

    @Column(length = 500)
    private String formaDePago;
    
    @Column(length = 10)
    private Double comision;
    
    @Column(length = 1000)
    private String foto;
    
    @Column(length = 1)
    private String estado;
    
    
    //CONSTRUCTOR
    public ShipmentPersistence() {
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

    public String getDireccionEnvia() {
        return direccionEnvia;
    }

    public void setDireccionEnvia(String direccionEnvia) {
        this.direccionEnvia = direccionEnvia;
    }

    public String getDireccionDestinatario() {
        return direccionDestinatario;
    }

    public void setDireccionDestinatario(String direccionDestinatario) {
        this.direccionDestinatario = direccionDestinatario;
    }

    public String getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }

    public Double getComision() {
        return comision;
    }

    public void setComision(Double comision) {
        this.comision = comision;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
