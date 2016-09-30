/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Entity.CadeteEntity;
import Entity.ClienteEntity;
import Entity.EnvioEntity;
import Entity.VehiculoEntity;
import Herramientas.Utiles;
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
public class EnvioBean {

    private static final String ADMIN = "ADMIN";

//    @Resource(lookup = "jms/ConnectionFactory")
//    private ConnectionFactory cf;
//    
//    @Resource(lookup = "jms/Queue")
//    private Queue queue;
//    
//    @Resource(lookup = "jms/Topic")
//    private Topic topic;
    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    private void init() {
        System.out.println("INSTANCIA ENVIO BEAN");
    }

    public EnvioBean() {
    }

    public EnvioEntity agregarEnvio(String descripcion, ClienteEntity clienteEnvia, ClienteEntity clienteRecibe, CadeteEntity unCadete,
            VehiculoEntity unVehiculo, String direccionRetiro, String direccionRecibo) {
        EnvioEntity e = new EnvioEntity();
        try {
            e.setDescripcion(descripcion);
            e.setClienteEnvia(clienteEnvia);
            e.setClienteRecibe(clienteRecibe);
            e.setUnCadete(unCadete);
            e.setUnVehiculo(unVehiculo);
            e.setDireccionRetiro(direccionRetiro);
            e.setDireccionRecibo(direccionRecibo);
            em.persist(e);
        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********AGREGAR*ENVIO************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return e;
    }

    public EnvioEntity modificarEnvio(Integer id, String descripcion, ClienteEntity clienteEnvia, ClienteEntity clienteRecibe, CadeteEntity unCadete,
            VehiculoEntity unVehiculo, String direccionRetiro, String direccionRecibo) {
        EnvioEntity e = null;
        try {
            e = em.find(EnvioEntity.class, id);
            e.setDescripcion(descripcion);
            e.setClienteEnvia(clienteEnvia);
            e.setClienteRecibe(clienteRecibe);
            e.setUnCadete(unCadete);
            e.setUnVehiculo(unVehiculo);
            e.setDireccionRetiro(direccionRetiro);
            e.setDireccionRecibo(direccionRecibo);
            em.merge(e);
        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********MODIFICACION*ENVIO************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return e;
    }

    public boolean eliminarEnvio(Integer id) {
        boolean ret = false;
        try {
            EnvioEntity v = em.find(EnvioEntity.class, id);
            em.remove(v);
            ret = true;
        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********ELIMINAR*ENVIO************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return ret;
    }

    public List<EnvioEntity> listarEnvios() {
        List<EnvioEntity> list = new ArrayList();
        try {
            list = em
                    .createQuery("select v from EnvioEntity v")
                    .getResultList();
        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********LISTAR*ENVIOS************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return list;
    }

    public EnvioEntity buscarEnvio(Integer id) {
        EnvioEntity e = new EnvioEntity();
        try {
            EnvioEntity ent = em.find(EnvioEntity.class, id);

            e.setId(ent.getId());
            e.setDescripcion(ent.getDescripcion());
            e.setClienteEnvia(ent.getClienteEnvia());
            e.setClienteRecibe(ent.getClienteRecibe());
            e.setUnCadete(ent.getUnCadete());
            e.setUnVehiculo(ent.getUnVehiculo());
            e.setDireccionRetiro(ent.getDireccionRetiro());
            e.setDireccionRecibo(ent.getDireccionRecibo());
        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********BUSCAR*ENVIO*POR*ID************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return e;
    }

}
