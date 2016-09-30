/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Entidades.Cadete;
import Entity.CadeteEntity;
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
public class CadeteBean {

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
        System.out.println("INSTANCIA CADETE BEAN");
    }

    public CadeteBean() {
    }

    public CadeteEntity agregarCadete(int ci, String nombre, String apellido, String email) {
        CadeteEntity c = new CadeteEntity();
        try {
            c.setCi(ci);
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setEmail(email);
            em.persist(c);

        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********ALTA*CADETE************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return c;
    }

    public CadeteEntity modificarCadete(Integer id, Integer ci, String nombre, String apellido, String email) {
        CadeteEntity c = null;
        try {
            c = em.find(CadeteEntity.class, id);
            c.setCi(ci);
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setEmail(email);
            em.merge(c);
        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********MODIFICACION*CADETE************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return c;
    }

    public boolean eliminarCadete(Integer id) {
        boolean ret = false;
        try {
            CadeteEntity u = em.find(CadeteEntity.class, id);
            em.remove(u);
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
        Cadete u = new Cadete();
        try {
            CadeteEntity ent = em.find(CadeteEntity.class, id);
            u.setId(ent.getId());
            u.setNombre(ent.getNombre());
        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********BUSCAR*CADETE*POR*ID************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return u;
    }

}
