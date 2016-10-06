/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import entidades.Vehiculo;
import entity.CadeteEntity;
import entity.VehiculoEntity;
import herramientas.Utiles;
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

    @PersistenceContext
    private EntityManager em;

    public VehiculoBean() {
    }

    @PostConstruct
    private void init() {
        System.out.println("INSTANCIA CLIENTE BEAN");
    }

    public VehiculoEntity agregarVehiculo(String matricula, String descripcion) {
        VehiculoEntity vehiculo = new VehiculoEntity();
        try {
            vehiculo.setMatricula(matricula);
            vehiculo.setDescripcion(descripcion);
            em.persist(vehiculo);
        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********ALTA*VEHICULO************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return vehiculo;
    }

    public VehiculoEntity modificarVehiculo(Integer id, String matricula, String descripcion) {
        VehiculoEntity vehiculo = null;
        try {
            vehiculo = em.find(VehiculoEntity.class, id);
            vehiculo.setMatricula(matricula);
            vehiculo.setDescripcion(descripcion);
            em.merge(vehiculo);

        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********MODIFICACION*VEHICULO************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return vehiculo;
    }

    public boolean eliminarVehiculo(Integer id) {
        boolean ret = false;
        try {
            VehiculoEntity vehiculo = em.find(VehiculoEntity.class, id);
            em.remove(vehiculo);
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
        Vehiculo vehiculo = new Vehiculo();
        try {
            VehiculoEntity ent = em.find(VehiculoEntity.class, id);

            vehiculo.setId(ent.getId());
            vehiculo.setMatricula(ent.getMatricula());
            vehiculo.setDescripcion(ent.getDescripcion());
        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********BUSCAR*VEHICULO*POR*ID************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return vehiculo;
    }

    public VehiculoEntity asociarCadeteVehiculos(Integer idVehiculo, Integer idCadete) {
        CadeteEntity cadete = null;
        VehiculoEntity vehiculo = null;
        try {
            cadete = em.find(CadeteEntity.class, idCadete);
            vehiculo = em.find(VehiculoEntity.class, idVehiculo);
            vehiculo.setCadete(cadete);
            em.persist(vehiculo);
        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********ASOCIAR*CADETE*CON*VEHICULOS************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return vehiculo;
    }
}
