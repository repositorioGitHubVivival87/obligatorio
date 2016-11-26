/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

/**
 *
 * @author Vivi
 */
public class Payment {
    private Integer id;
    private String sello;
    private String nombreembosado;
    private Integer fechavencimiento;
    private Integer cvv;
    private Integer nroTarjeta;

    //CONSTRUCTOR
    public Payment() {
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

}
