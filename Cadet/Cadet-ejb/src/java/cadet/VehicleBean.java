/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cadet;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tools.Utils;

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
            Utils.logError(" ***********ALTA*VEHICULO************");
            Utils.logError(exe.getMessage());
        }
        return vehiculo;
    }
}