/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import entidades.Cadete;
import entity.CadeteEntity;
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
public class CadeteBean {

    private static final String ADMIN = "ADMIN";

    @PersistenceContext
    private EntityManager em;

    public CadeteBean() {
    }

    @PostConstruct
    private void init() {
        System.out.println("INSTANCIA CADETE BEAN");
    }

    public CadeteEntity agregarCadete(int ci, String nombre, String apellido, String email) {
        CadeteEntity cadete = null;
        try {
            cadete = new CadeteEntity();
            cadete.setCi(ci);
            cadete.setNombre(nombre);
            cadete.setApellido(apellido);
            cadete.setEmail(email);
            em.persist(cadete);

        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********ALTA*CADETE************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return cadete;
    }

    public CadeteEntity modificarCadete(Integer id, Integer ci, String nombre, String apellido, String email) {
        CadeteEntity cadete = null;
        boolean estaCi = false;
        try {
            List<CadeteEntity> list = em
                    .createQuery("select c from CadeteEntity c")
                    .getResultList();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getCi().equals(ci)) {
                    estaCi = true;
                }
            }
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId().equals(id) && estaCi && list.get(i).getCi().equals(ci)) {
                    cadete = em.find(CadeteEntity.class, id);
                    cadete.setNombre(nombre);
                    cadete.setApellido(apellido);
                    cadete.setEmail(email);
                    em.merge(cadete);
                } else {
                    if (list.get(i).getId().equals(id) && !estaCi) {
                        cadete = em.find(CadeteEntity.class, id);
                        cadete.setCi(ci);
                        cadete.setNombre(nombre);
                        cadete.setApellido(apellido);
                        cadete.setEmail(email);
                        em.merge(cadete);
                    }
                }
            }

        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********MODIFICACION*CADETE************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return cadete;
    }

    public boolean eliminarCadete(Integer id) {
        boolean ret = false;
        try {
            CadeteEntity cadete = em.find(CadeteEntity.class, id);
            em.remove(cadete);
            ret = true;
        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********ELIMINACION*CADETE************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return ret;
    }

    public List<CadeteEntity> listarCadetes() {
        List<CadeteEntity> list = new ArrayList();
        try {
            list = em
                    .createQuery("select c from CadeteEntity c")
                    .getResultList();
        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********LISTAR*CADETES************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return list;
    }

    public Cadete buscarCadetes(Integer id) {
        Cadete cadete = new Cadete();
        try {
            CadeteEntity ent = em.find(CadeteEntity.class, id);
            cadete.setId(ent.getId());
            cadete.setNombre(ent.getNombre());
            cadete.setApellido(ent.getApellido());
            cadete.setCi(ent.getCi());
            cadete.setEmail(ent.getEmail());
        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********BUSCAR*CADETE*POR*ID************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return cadete;
    }   
}
