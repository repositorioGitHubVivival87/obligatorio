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
public class CadetBean {
    private static final String ADMIN = "ADMIN";

    @PersistenceContext
    private EntityManager em;

    public CadetBean() {
    }

    @PostConstruct
    private void init() {
        System.out.println("INSTANCIA CADETE BEAN");
    }
    
    public CadetPersistence agregarCadete(int ci, String nombre, String apellido, String email) {
        CadetPersistence cadete = null;
        try {
            cadete = new CadetPersistence();
            cadete.setCi(ci);
            cadete.setNombre(nombre);
            cadete.setApellido(apellido);
            cadete.setEmail(email);
            em.persist(cadete);

        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********ALTA*CADETE************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return cadete;
    }

    public CadetPersistence modificarCadete(Integer id, Integer ci, String nombre, String apellido, String email) {
        CadetPersistence cadete = null;
        boolean estaCi = false;
        try {
            List<CadetPersistence> list = em
                    .createQuery("select c from CadeteEntity c")
                    .getResultList();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getCi().equals(ci)) {
                    estaCi = true;
                }
            }
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId().equals(id) && estaCi && list.get(i).getCi().equals(ci)) {
                    cadete = em.find(CadetPersistence.class, id);
                    cadete.setNombre(nombre);
                    cadete.setApellido(apellido);
                    cadete.setEmail(email);
                    em.merge(cadete);
                } else {
                    if (list.get(i).getId().equals(id) && !estaCi) {
                        cadete = em.find(CadetPersistence.class, id);
                        cadete.setCi(ci);
                        cadete.setNombre(nombre);
                        cadete.setApellido(apellido);
                        cadete.setEmail(email);
                        em.merge(cadete);
                    }
                }
            }

        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********MODIFICACION*CADETE************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return cadete;
    }

    public boolean eliminarCadete(Integer id) {
        boolean ret = false;
        try {
            CadetPersistence cadete = em.find(CadetPersistence.class, id);
            em.remove(cadete);
            ret = true;
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********ELIMINACION*CADETE************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return ret;
    }

    public List<CadetPersistence> listarCadetes() {
        List<CadetPersistence> list = new ArrayList();
        try {
            list = em
                    .createQuery("select c from CadeteEntity c")
                    .getResultList();
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********LISTAR*CADETES************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return list;
    }

    public CadetEntity buscarCadetes(Integer id) {
        CadetEntity cadete = new CadetEntity();
        try {
            CadetPersistence ent = em.find(CadetPersistence.class, id);
            cadete.setId(ent.getId());
            cadete.setNombre(ent.getNombre());
            cadete.setApellido(ent.getApellido());
            cadete.setCi(ent.getCi());
            cadete.setEmail(ent.getEmail());
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********BUSCAR*CADETE*POR*ID************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return cadete;
    }   
}
