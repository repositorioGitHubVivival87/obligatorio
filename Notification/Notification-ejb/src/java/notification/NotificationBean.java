/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package notification;

import herramientas.Utils;
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

    @PersistenceContext
    private EntityManager em;

    public NotificationBean() {
    }

    @PostConstruct
    private void init() {
        System.out.println("INSTANCIA NOTIFICATION BEAN");
    }
    
    public NotficationPersistence agregarNotification(String emailEmisor, String emailReceptor, String mensaje) {
        NotficationPersistence not = null;
        try {
            

            em.persist(not);

        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********ALTA*NOTIFICATION************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return not;
    }

    public NotficationPersistence modificarNotification(Integer id, 
            String emailEmisor, String emailReceptor, String mensaje) {
        NotficationPersistence not = null;
        try {
            String ret = "";
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********MODIFICACION*NOTIFICATION************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return not;
    }

    public boolean eliminarNotification(Integer id) {
        boolean ret = false;
        try {
            NotficationPersistence not = em.find(NotficationPersistence.class, id);
            em.remove(not);
            ret = true;

        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********ELIMINACION*NOTIFICATION************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return ret;
    }

    public List<NotficationPersistence> listarNotifications() {
        List<NotficationPersistence> list = new ArrayList();
        try {
            list = em
                    .createQuery("select c from NotificationPersistence c")
                    .getResultList();
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********LISTAR*NOTIFICATION*************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return list;
    }

    public NotficationPersistence buscarNotificarion(Integer id) {
        NotficationPersistence not = new NotficationPersistence();
        try {
            String ret = "";
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********BUSCAR*NOTIFICATION*POR*ID************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return not;
    }

}

