/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package notification;

import tools.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Vivi
 */
@Stateless
@LocalBean
public class NotificationBean {

    private static final String ADMIN = "ADMIN";
    Utils util = new Utils();

    @PersistenceContext
    private EntityManager em;

    public NotificationBean() {
    }

    @PostConstruct
    private void init() {
        System.out.println("INSTANCIA NOTIFICATION BEAN");
    }

    public NotificationEntity agregarNotification(String emailEmisor, String emailReceptor, String mensaje) {
        NotificationEntity not = null;
        try {
            //if (!esCadete(usuario, contrasena)) {
            not = new NotificationEntity();
            not.setEmailEmisor(emailEmisor);
            not.setEmailReceptor(emailReceptor);
            not.setMensaje(mensaje);
            em.persist(not);
        } catch (Exception exe) {
            Utils.logInfo(" ***********ALTA*NOTIFICATION************");
            Utils.logInfo("Error:" + exe.getMessage());
        }
        return not;
    }

    public NotificationEntity modificarNotification(Integer id,
            String emailEmisor, String emailReceptor, String mensaje) {
        NotificationEntity not = null;
        try {
            not = em.find(NotificationEntity.class, id);
            not.setEmailEmisor(emailEmisor);
            not.setEmailReceptor(emailReceptor);
            not.setMensaje(mensaje);
            em.merge(not);

        } catch (Exception exe) {
            Utils.logError(" ***********MODIFICACION*NOTIFICATION************");
            Utils.logError(exe.getMessage());
        }
        return not;
    }

    public boolean eliminarNotification(Integer id) {
        boolean ret = false;
        try {
            NotificationEntity not = em.find(NotificationEntity.class, id);
            em.remove(not);
            ret = true;

        } catch (Exception exe) {
            Utils.logError(" ***********ELIMINACION*NOTIFICATION************");
            Utils.logError(exe.getMessage());
        }
        return ret;
    }

    public Notification buscarNotificarion(Integer id) {
        Notification not = new Notification();

        try {
            NotificationEntity ent = em.find(NotificationEntity.class, id);
            not.setId(ent.getId());
            not.setEmailEmisor(ent.getEmailEmisor());
            not.setEmailReceptor(ent.getEmailReceptor());
            not.setMensaje(ent.getMensaje());
        } catch (Exception exe) {
            Utils.logError(" ***********BUSCAR*NOTIFICACION*POR*ID************");
            Utils.logError("Error:" + exe.getMessage());
        }
        return not;
    }

    public List<NotificationEntity> listarNotifications() {
        List<NotificationEntity> list = new ArrayList();
        try {
            list = em
                    .createNativeQuery("SELECT * FROM notificationentity c")
                    .getResultList();
        } catch (Exception exe) {
            Utils.logError(" ***********LISTAR*NOTIFICATION*************");
            Utils.logError(exe.getMessage());
        }
        return list;
    }

    public String notificarRecepcionEnvio(Integer idEnvio, Integer idCadete, String direccionEnvia,
            String direccionRecibe, String clienteEnvia, String clienteRecibe, String emailEnvia, 
            String emailRecibe, Double costoEnvio, String fecha, String hora) {
        String retorno = "EXITO";
        try {
            //Aqui enviamos un correo al clienteEnvio y clienteRecibe con el link para 
            //acceder al metodo CalificarServicio que expone el modulo review
        } catch (Exception exe) {
            Utils.logError(" ***********Notificar*Recepcion*Envio************");
            Utils.logError(exe.getMessage());
        }
        return retorno;
    }

    public String notificarEnvioCadete(Integer idEnvio, Integer idCadete, String direccionEnvia,
            String direccionRecibe, String clienteEnvia, String clienteRecibe, String emailCadete, Double costoEnvio) {
        String retorno = "EXITO";
        try {
            //Aqui enviamos un correo al cadete con la informacion del envio que debe realizar
            
        } catch (Exception exe) {
            Utils.logError(" ***********Notificar*Envio*Cadete************");
            Utils.logError(exe.getMessage());
        }
        return retorno;
    }

    public NotificationEntity notificarRecepcionEnvio(Integer idReview, Integer 
            idEnvio, Integer calificacion, String mensaje) {
        NotificationEntity not = null;
        try {
            em.persist(not);
        } catch (Exception exe) {
            Utils.logError(" ***********Notificar*Recepcion*Envio************");
            Utils.logError(exe.getMessage());
        }
        return not;
    }
}
