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
import javax.persistence.Query;

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

    public CadetEntity agregarCadete(String usuario, String contrasena, int ci,
            String nombre, String apellido, String email) {
        CadetEntity cadete = null;
        try {
            if (!esCadete(usuario, contrasena)) {
                cadete = new CadetEntity();
                cadete.setCi(ci);
                cadete.setNombre(nombre);
                cadete.setApellido(apellido);
                cadete.setEmail(email);
                cadete.setEstado("I");
                cadete.setRating("0");
                em.persist(cadete);
            }
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********ALTA*CADETE************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return cadete;
    }

    public CadetEntity modificarCadete(String usuario, String contrasena, Integer id, Integer ci,
            String nombre, String apellido, String email) {
        CadetEntity cadete = null;
        boolean estaCi = false;
        try {
            if (esCadete(usuario, contrasena)) {
                List<CadetEntity> list = em
                        .createQuery("select * from CadetEntity c")
                        .getResultList();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getCi().equals(ci)) {
                        estaCi = true;
                    }
                }
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getId().equals(id) && estaCi && list.get(i).getCi().equals(ci)) {
                        cadete = em.find(CadetEntity.class, id);
                        cadete.setNombre(nombre);
                        cadete.setApellido(apellido);
                        cadete.setEmail(email);
                        em.merge(cadete);
                    } else {
                        if (list.get(i).getId().equals(id) && !estaCi) {
                            cadete = em.find(CadetEntity.class, id);
                            cadete.setCi(ci);
                            cadete.setNombre(nombre);
                            cadete.setApellido(apellido);
                            cadete.setEmail(email);
                            em.merge(cadete);
                        }
                    }
                }
            }
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********MODIFICACION*CADETE************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return cadete;
    }

    public boolean eliminarCadete(String usuario, String contrasena, Integer id) {
        boolean ret = false;
        try {
            if (esCadete(usuario, contrasena)) {
                CadetEntity cadete = em.find(CadetEntity.class, id);
                em.remove(cadete);
                ret = true;
            }
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********ELIMINACION*CADETE************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return ret;
    }

    public List<CadetEntity> listarCadetes() {
        List<CadetEntity> list = new ArrayList();
        try {
            String consulta = "select * from CadetEntity c";
            Query que = em.createQuery(consulta);
            list = que.getResultList();

        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********LISTAR*CADETES************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return list;
    }

    public List<CadetEntity> listarCadetesMasCercanos(String latitud, String longitud) {
        List<CadetEntity> list = new ArrayList();
        try {
            List<CadetEntity> todos = em
                    .createQuery("select * from CadetEntity c")
                    .getResultList();
            //FALTA IMPLEMENTAR
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********LISTAR*CADETES************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return list;
    }

    public Cadet buscarUnCadete(Integer id) {
        Cadet cadete = new Cadet();
        try {
            CadetEntity ent = em.find(CadetEntity.class, id);
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

    public CadetEntity actualizarRating(String usuario, String contrasena, Integer id, Integer raiting) {
        CadetEntity cadete = null;
        try {
            if (esCadete(usuario, contrasena)) {
                List<CadetEntity> list = em
                        .createQuery("select c.* from CadetEntity c where id = " + id)
                        .getResultList();
                for (int i = 0; i < list.size(); i++) {
                    CadetEntity cad = list.get(i);
                    Integer raitingBd = Integer.valueOf(cad.getRating());
                    Integer promedio = (raiting + raitingBd) / 2;
                    String prom = String.valueOf(promedio);
                    cadete = em.find(CadetEntity.class, id);
                    cadete.setRating(prom);
                    em.merge(cadete);
                }
            }
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********ACTUALIZAR*RATING************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return cadete;
    }

    public boolean esCadete(String usuario, String contrasena) {
        boolean esCad = false;
        try {
            List<CadetEntity> list = em
                    .createQuery("select c.* from CadetEntity c "
                            + "where usuario = '" + usuario + "' and contrasena = '" + contrasena + "'")
                    .getResultList();

            if (list.size() > 0) {
                esCad = true;
            }

        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********ES*CADETE*************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return esCad;
    }
}
