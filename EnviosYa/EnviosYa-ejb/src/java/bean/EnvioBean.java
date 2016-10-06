/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import entity.CadeteEntity;
import entity.ClienteEntity;
import entity.EnvioEntity;
import entity.VehiculoEntity;
import herramientas.Utiles;
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
public class EnvioBean {

    private static final String ADMIN = "ADMIN";

    @PersistenceContext
    private EntityManager em;

    @Resource(lookup = "jms/ConnectionFactory")
    private ConnectionFactory cf;

    @Resource(lookup = "jms/Topic")
    private Topic topic;
    
    public EnvioBean() {
    }

    @PostConstruct
    private void init() {
        System.out.println("INSTANCIA ENVIO BEAN");
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
            Logger.getLogger(EnvioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public EnvioEntity agregarEnvio(Integer unCadete, Integer unVehiculo, 
            Integer clienteEnvia, Integer clienteRecibe,
            String direccionRetiro, String direccionRecibo, String descripcion) {
        EnvioEntity env = null;
        try {
            env = new EnvioEntity();
            env.setDescripcion(descripcion);
            ClienteEntity cliEnvia = em.find(ClienteEntity.class, clienteEnvia);
            if (cliEnvia != null) {
                env.setClienteEnvia(cliEnvia);
            }
            ClienteEntity cliRecibe = em.find(ClienteEntity.class, clienteRecibe);
            if (cliRecibe != null) {
                env.setClienteRecibe(cliRecibe);
            }
            CadeteEntity cadete = em.find(CadeteEntity.class, unCadete);
            if (cadete != null) {
                env.setUnCadete(cadete);
            }
            VehiculoEntity vehiculo = em.find(VehiculoEntity.class, unVehiculo);
            if (vehiculo != null) {
                env.setUnVehiculo(vehiculo);
            }
            env.setDireccionRetiro(direccionRetiro);
            env.setDireccionRecibo(direccionRecibo);
            em.persist(env);
            
            /*Envio de mensajes*/
            if (cliRecibe.getNombre() == null) {
                cliRecibe.setNombre(" ");
            }
            if (cliRecibe.getApellido() == null) {
                cliRecibe.setApellido(" ");
            }
            if (cliEnvia.getNombre() == null) {
                cliEnvia.setNombre(" ");
            }
            if (cliEnvia.getApellido() == null) {
                cliEnvia.setApellido(" ");
            }
            if (cadete.getNombre() == null) {
                cadete.setNombre(" ");
            }
            if (cadete.getApellido() == null) {
                cadete.setApellido(" ");
            }
            if (vehiculo.getMatricula() == null) {
                vehiculo.setMatricula(" ");
            }
            //PRIMER MENSAJE AL CLIENTE QUE RECIBE
            encolar("Sr/a. cliente " + cliRecibe.getNombre() + " " + cliRecibe.getApellido()
                    + " le notificamos que el paquete está en viaje,"
                    + " el/la cadete es el Sr/a: " + cadete.getNombre() + " " + cadete.getApellido()
                    + " y va en el vehículo con matricula: " + vehiculo.getMatricula());
            //SEGUNDO MENSAJE AL CLIENTE QUE ENVIA
            encolar("Sr/a. cliente " + cliEnvia.getNombre() + " " + cliEnvia.getApellido()
                    + " le notificamos que su paquete fue enviado,"
                    + " el cadete es el/la Sr/a: " + cadete.getNombre() + " " + cadete.getApellido()
                    + " y va en el vehículo con matricula: " + vehiculo.getMatricula());
            //TERCER MENSAJE AL CADETE ASIGNADO
            encolar("Sr/a. " + cadete.getNombre() + " " + cadete.getApellido()
                    + " ud. debera enviar el paquete con descipcion: " + descripcion
                    + ", a la siguiente direccion: " + direccionRecibo
                    + " en su vehículo con matricula: " + vehiculo.getMatricula()
                    + ". Debera recibirlo el Sr/a. " + cliRecibe.getNombre() + " " + cliRecibe.getApellido());
        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********AGREGAR*ENVIO************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return env;
    }

    public List<EnvioEntity> consulta() {
        List<EnvioEntity> listRetorno = new ArrayList();
        try {
            //lista de todos los envios
            listRetorno = em
                    .createQuery("select e from EnvioEntity e")
                    .getResultList();

        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********CONSULTA*ENVIOS*************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return listRetorno;
    }
}
