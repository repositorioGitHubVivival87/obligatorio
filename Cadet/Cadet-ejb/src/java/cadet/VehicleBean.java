/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cadet;

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
    
    public Vehicle agregarVehiculo(String matricula, String descripcion) {
        Vehicle vehiculo = new Vehicle();
        try {
            vehiculo.setMatricula(matricula);
            vehiculo.setDescripcion(descripcion);
            em.persist(vehiculo);
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********ALTA*VEHICULO************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return vehiculo;
    }

    public Vehicle modificarVehiculo(Integer id, String matricula, String descripcion) {
        Vehicle vehiculo = null;
        try {
            vehiculo = em.find(Vehicle.class, id);
            vehiculo.setMatricula(matricula);
            vehiculo.setDescripcion(descripcion);
            em.merge(vehiculo);

        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********MODIFICACION*VEHICULO************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return vehiculo;
    }

    public boolean eliminarVehiculo(Integer id) {
        boolean ret = false;
        try {
            Vehicle vehiculo = em.find(Vehicle.class, id);
            em.remove(vehiculo);
            ret = true;
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********ELIMINACION*VEHICULO************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return ret;
    }

    public List<Vehicle> listarVehiculos() {
        List<Vehicle> list = new ArrayList();
        try {
            list = em
                    .createQuery("select v from VehiculoEntity v")
                    .getResultList();
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********LISTAR*VEHICULOS************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return list;
    }

    public Vehicle buscarVehiculo(Integer id) {
        Vehicle vehiculo = new Vehicle();
        try {
            VehicleEntity ent = em.find(VehicleEntity.class, id);

            vehiculo.setId(ent.getId());
            vehiculo.setMatricula(ent.getMatricula());
            vehiculo.setDescripcion(ent.getDescripcion());
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********BUSCAR*VEHICULO*POR*ID************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return vehiculo;
    }

    public VehicleEntity asociarCadeteVehiculos(Integer idVehiculo, Integer idCadete) {
        CadetEntity cadete = null;
        VehicleEntity vehiculo = null;
        try {
            cadete = em.find(CadetEntity.class, idCadete);
            vehiculo = em.find(VehicleEntity.class, idVehiculo);
       //     vehiculo.setCadete(cadete);
            em.persist(vehiculo);
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********ASOCIAR*CADETE*CON*VEHICULOS************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return vehiculo;
    }
}
