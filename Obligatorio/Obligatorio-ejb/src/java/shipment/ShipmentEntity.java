/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shipment;


/**
 *
 * @author Vivi
 */
public class ShipmentEntity {
    private Integer id;
    private String descripcion;
    private String direccionEnvia;
    private String direccionDestinatario;
    private String formaDePago;
    private Double comision;
    private String foto;
    
    
    //CONSTRUCTOR
    public ShipmentEntity() {
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
    
}
