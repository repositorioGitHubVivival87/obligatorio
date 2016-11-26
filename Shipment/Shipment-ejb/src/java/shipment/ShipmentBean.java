/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shipment;

import herramientas.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Vivi
 */
@Stateless
@LocalBean
public class ShipmentBean {
    private static final String ADMIN = "ADMIN";

    @PersistenceContext
    private EntityManager em;

    @Resource(lookup = "jms/ConnectionFactory")
    private ConnectionFactory cf;

    @Resource(lookup = "jms/Topic")
    private Topic topic;
    
    public ShipmentBean() {
    }

    @PostConstruct
    private void init() {
        System.out.println("INSTANCIA SHIPMENT BEAN");
    }
    
    private void encolar(String texto) {
        try {
            Connection connection = cf.createConnection();
            Session session = connection.createSession();
            TextMessage msg = session.createTextMessage(texto);
            MessageProducer producer = session.createProducer(topic);
            producer.send(msg);
            session.close();
            connection.close();
        } catch (JMSException ex) {
            Logger.getLogger(ShipmentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ShipmentEntity solicitudEnvio(String usuario, String contrasena, Integer unCadete, Integer unVehiculo, 
            Integer clienteEnvia, Integer clienteRecibe,
            String direccionRetiro, String direccionRecibo, String descripcion) {
        ShipmentEntity env = null;
        try {
            //registrar fecha y hora del envio
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********AGREGAR*ENVIO************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return env;
    }

    public List<ShipmentEntity> consulta() {
        List<ShipmentEntity> listRetorno = new ArrayList();
        try {
            //lista de todos los envios
            listRetorno = em
                    .createQuery("select e from ShipmentPersistence e")
                    .getResultList();

        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********CONSULTA*ENVIOS*************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return listRetorno;
    }

    
}
