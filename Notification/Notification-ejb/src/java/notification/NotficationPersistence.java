/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package notification;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Vivi
 */
public class NotficationPersistence implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(length = 300)
    private String emailEmisor;

    @Column(length = 300)
    private String emailReceptor;

    @Column(length = 50000)
    private String mensaje;
    
    //CONSTRUCTOR
    public NotficationPersistence() {
    }
    
    //SET AND GET
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmailEmisor() {
        return emailEmisor;
    }

    public void setEmailEmisor(String emailEmisor) {
        this.emailEmisor = emailEmisor;
    }

    public String getEmailReceptor() {
        return emailReceptor;
    }

    public void setEmailReceptor(String emailReceptor) {
        this.emailReceptor = emailReceptor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
