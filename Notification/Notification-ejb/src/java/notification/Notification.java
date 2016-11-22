/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package notification;

/**
 *
 * @author Vivi
 */
public class Notification {

    private Integer id;
    private String emailEmisor;
    private String emailReceptor;
    private String mensaje;

    //CONSTRUCTOR
    public Notification() {
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
