/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Vivi
 */
@Entity
public class PaymentEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(length = 300)
    private String sello;
    
    @Column(length = 300)
    private String nombreembosado;
    
    @Column(length = 300)
    private Integer fechavencimiento;
    
    @Column(length = 300)
    private Integer cvv;
    
    @Column(length = 300)
    private Integer nroTarjeta;    
    
    @ManyToOne
    private ClientEntity cliente;
    
    //CONSTRUCTOR
    public PaymentEntity() {
    }
     
    //SET AND GET
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSello() {
        return sello;
    }

    public void setSello(String sello) {
        this.sello = sello;
    }

    public String getNombreembosado() {
        return nombreembosado;
    }

    public void setNombreembosado(String nombreembosado) {
        this.nombreembosado = nombreembosado;
    }

    public Integer getFechavencimiento() {
        return fechavencimiento;
    }

    public void setFechavencimiento(Integer fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public Integer getNroTarjeta() {
        return nroTarjeta;
    }

    public void setNroTarjeta(Integer nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    public ClientEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClientEntity cliente) {
        this.cliente = cliente;
    }
}
