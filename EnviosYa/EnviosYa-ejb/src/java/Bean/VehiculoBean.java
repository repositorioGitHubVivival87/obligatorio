/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Entidades.Vehiculo;
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
public class VehiculoBean {

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
        System.out.println("INSTANCIA CLIENTE BEAN");
    }

    public VehiculoBean() {
    }

    public VehiculoEntity agregarVehiculo(String matricula, String descripcion) {
        VehiculoEntity v = new VehiculoEntity();
        try {
            v.setMatricula(matricula);
            v.setDescripcion(descripcion);
            em.persist(v);
        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********ALTA*VEHICULO************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return v;
    }

    public VehiculoEntity modificarVehiculo(Integer id, String matricula, String descripcion) {
        VehiculoEntity v = null;
        try {
            v = em.find(VehiculoEntity.class, id);
            v.setMatricula(matricula);
            v.setDescripcion(descripcion);
            em.merge(v);
           
        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********MODIFICACION*VEHICULO************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return v;
    }

    public boolean eliminarVehiculo(Integer id) {
        boolean ret = false;
        try {
            VehiculoEntity v = em.find(VehiculoEntity.class, id);
            em.remove(v);
            ret = true;
        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********ELIMINACION*VEHICULO************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return ret;
    }

    public List<VehiculoEntity> listarVehiculos() {
        List<VehiculoEntity> list = new ArrayList();
        try {
            list = em
                    .createQuery("select v from VehiculoEntity v")
                    .getResultList();
        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********LISTAR*VEHICULOS************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return list;
    }

    public Vehiculo buscarVehiculo(Integer id) {
        Vehiculo v = new Vehiculo();
        try {
            VehiculoEntity ent = em.find(VehiculoEntity.class, id);

            v.setId(ent.getId());
            v.setMatricula(ent.getMatricula());
            v.setDescripcion(ent.getDescripcion());
        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********BUSCAR*VEHICULO*POR*ID************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return v;
    }

}
