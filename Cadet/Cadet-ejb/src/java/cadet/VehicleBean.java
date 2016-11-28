/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cadet;

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
public class VehicleBean {
    private static final String ADMIN = "ADMIN";

    @PersistenceContext
    private EntityManager em;

    public VehicleBean() {
    }

    @PostConstruct
    private void init() {
        System.out.println("INSTANCIA VEHICLE BEAN");
    }
    
    public Vehicle agregarVehiculo(String usuario, String contrasena, String matricula, String descripcion) {
        Vehicle vehiculo = new Vehicle();
        try {
            vehiculo.setMatricula(matricula);
            vehiculo.setDescripcion(descripcion);
            em.persist(vehiculo);
        } catch (Exception exe) {
            Utils.logWs("EnviosYa", " ***********ALTA*VEHICULO************");
            Utils.logWs("EnviosYa", "Error:" + exe.getMessage());
        }
        return vehiculo;
    }

    public Vehicle modificarVehiculo(String usuario, String contrasena, 
            Integer id, String matricula, String descripcion) {
        Vehicle vehiculo = null;
        try {
            vehiculo = em.find(Vehicle.class, id);
            vehiculo.setMatricula(matricula);
            vehiculo.setDescripcion(descripcion);
            em.merge(vehiculo);

        } catch (Exception exe) {
            Utils.logWs("EnviosYa", " ***********MODIFICACION*VEHICULO************");
            Utils.logWs("EnviosYa", "Error:" + exe.getMessage());
        }
        return vehiculo;
    }

    public boolean eliminarVehiculo(String usuario, String contrasena, Integer id) {
        boolean ret = false;
        try {
            Vehicle vehiculo = em.find(Vehicle.class, id);
            em.remove(vehiculo);
            ret = true;
        } catch (Exception exe) {
            Utils.logWs("EnviosYa", " ***********ELIMINACION*VEHICULO************");
            Utils.logWs("EnviosYa", "Error:" + exe.getMessage());
        }
        return ret;
    }

    public List<Vehicle> listarVehiculos(String usuario, String contrasena) {
        List<Vehicle> list = new ArrayList();
        try {
            list = em
                    .createNativeQuery("select v.* from VehicleEntity v")
                    .getResultList();
        } catch (Exception exe) {
            Utils.logWs("EnviosYa", " ***********LISTAR*VEHICULOS************");
            Utils.logWs("EnviosYa", "Error:" + exe.getMessage());
        }
        return list;
    }

    public Vehicle buscarVehiculo(String usuario, String contrasena, Integer id) {
        Vehicle vehiculo = new Vehicle();
        try {
            VehicleEntity ent = em.find(VehicleEntity.class, id);

            vehiculo.setId(ent.getId());
            vehiculo.setMatricula(ent.getMatricula());
            vehiculo.setDescripcion(ent.getDescripcion());
        } catch (Exception exe) {
            Utils.logWs("EnviosYa", " ***********BUSCAR*VEHICULO*POR*ID************");
            Utils.logWs("EnviosYa", "Error:" + exe.getMessage());
        }
        return vehiculo;
    }

    public VehicleEntity asociarCadeteVehiculos(String usuario, String contrasena, 
            Integer idVehiculo, Integer idCadete) {
        VehicleEntity vehiculo = null;
        try {
            CadetEntity cadete = em.find(CadetEntity.class, idCadete);
            vehiculo = em.find(VehicleEntity.class, idVehiculo);
            vehiculo.setCadete(cadete);
            em.persist(vehiculo);
        } catch (Exception exe) {
            Utils.logWs("EnviosYa", " ***********ASOCIAR*CADETE*CON*VEHICULOS************");
            Utils.logWs("EnviosYa", "Error:" + exe.getMessage());
        }
        return vehiculo;
    }
}
