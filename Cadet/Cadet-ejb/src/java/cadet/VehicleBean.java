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
    
    public VehicleEntity agregarVehiculo(String matricula, String descripcion) {
        VehicleEntity vehiculo = new VehicleEntity();
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

    public VehicleEntity modificarVehiculo(Integer id, String matricula, String descripcion) {
        VehicleEntity vehiculo = null;
        try {
            vehiculo = em.find(VehicleEntity.class, id);
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
            VehicleEntity vehiculo = em.find(VehicleEntity.class, id);
            em.remove(vehiculo);
            ret = true;
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********ELIMINACION*VEHICULO************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return ret;
    }

    public List<VehicleEntity> listarVehiculos() {
        List<VehicleEntity> list = new ArrayList();
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

    public VehicleEntity buscarVehiculo(Integer id) {
        VehicleEntity vehiculo = new VehicleEntity();
        try {
            VehiclePersistence ent = em.find(VehiclePersistence.class, id);

            vehiculo.setId(ent.getId());
            vehiculo.setMatricula(ent.getMatricula());
            vehiculo.setDescripcion(ent.getDescripcion());
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********BUSCAR*VEHICULO*POR*ID************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return vehiculo;
    }

    public VehiclePersistence asociarCadeteVehiculos(Integer idVehiculo, Integer idCadete) {
        CadetPersistence cadete = null;
        VehiclePersistence vehiculo = null;
        try {
            cadete = em.find(CadetPersistence.class, idCadete);
            vehiculo = em.find(VehiclePersistence.class, idVehiculo);
       //     vehiculo.setCadete(cadete);
            em.persist(vehiculo);
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********ASOCIAR*CADETE*CON*VEHICULOS************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return vehiculo;
    }
}
