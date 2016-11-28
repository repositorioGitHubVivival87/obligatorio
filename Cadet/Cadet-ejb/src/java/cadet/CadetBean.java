/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadet;

import tools.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
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

    public CadetEntity agregarCadete(String usuario, String contrasena, int ci,
            String nombre, String apellido, String email) {
        CadetEntity cadete = null;
        try {
            //if (!esCadete(usuario, contrasena)) {
            cadete = new CadetEntity();
            cadete.setCi(ci);
            cadete.setNombre(nombre);
            cadete.setApellido(apellido);
            cadete.setEmail(email);
            cadete.setEstado("I");
            cadete.setRating("0");
            em.persist(cadete);
            //}
        } catch (Exception exe) {
            Utils.logWs("EnviosYa", " ***********ALTA*CADETE************");
            Utils.logWs("EnviosYa", "Error:" + exe.getMessage());
        }
        return cadete;
    }

    public CadetEntity modificarCadete(String usuario, String contrasena, Integer id, Integer ci,
            String nombre, String apellido, String email) {
        CadetEntity cadete = null;
        try {
            //if (esCadete(usuario, contrasena)) {
            cadete = em.find(CadetEntity.class, id);
            cadete.setCi(ci);
            cadete.setNombre(nombre);
            cadete.setApellido(apellido);
            cadete.setEmail(email);
            em.merge(cadete);
            //}
        } catch (Exception exe) {
            Utils.logWs("EnviosYa", " ***********MODIFICACION*CADETE************");
            Utils.logWs("EnviosYa", "Error:" + exe.getMessage());
        }
        return cadete;
    }

    public boolean eliminarCadete(String usuario, String contrasena, Integer id) {
        boolean ret = false;

        try {
            //if (esCadete(usuario, contrasena)) {
            CadetEntity cadete = em.find(CadetEntity.class, id);
            em.remove(cadete);
            ret = true;
    //}
        } catch (Exception exe) {
            Utils.logWs("EnviosYa", " ***********ELIMINACION*CADETE************");
            Utils.logWs("EnviosYa", "Error:" + exe.getMessage());
        }
        return ret;
    }

    public List<CadetEntity> listarCadetes() {
        List<CadetEntity> list = new ArrayList();
        try {
            list = em
                    .createNativeQuery("SELECT * FROM CadetEntity c")
                    .getResultList();

        } catch (Exception exe) {
            Utils.logWs("EnviosYa", " ***********LISTAR*CADETES************");
            Utils.logWs("EnviosYa", "Error:" + exe.getMessage());
        }
        return list;
    }

    public List<CadetEntity> listarCadetesMasCercanos(String latitud, String longitud) {
        List<CadetEntity> list = new ArrayList();
        try {
            List<CadetEntity> todos = em
                    .createNativeQuery("select * from CadetEntity c")
                    .getResultList();
            double total = Double.MAX_VALUE;
            Integer cadetes = 0;
            double latOrigen = Double.valueOf(latitud);
            double longOrigen = Double.valueOf(longitud);
            while (cadetes < 4) {
                for (int i = 0; i < todos.size(); i++) {
                    double latCadete = -34;//obtenerLatCadete(todos.get(i));
                    double longCadete = 38;//obtenerLongCadete(todos.get(i));
                    double lat = latOrigen - latCadete;
                    double longi = longOrigen - longCadete;
                    double suma = Math.abs(lat + longi);
                    if (suma < total) {
                        total = suma;
                    }
                    todos.remove(todos.get(i));
                } 
                cadetes++;
            }
        } catch (Exception exe) {
            Utils.logWs("EnviosYa", " ***********LISTAR*4*CADETES*MAS*CERCANOS************");
            Utils.logWs("EnviosYa", "Error:" + exe.getMessage());
        }
        return list;
    }
    
    public double obtenerLatCadete(CadetEntity cadet) {
        return 34;
    }

    public double obtenerLongCadete(CadetEntity cadet) {
        return 38;
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
            Utils.logWs("EnviosYa", " ***********BUSCAR*CADETE*POR*ID************");
            Utils.logWs("EnviosYa", "Error:" + exe.getMessage());
        }
        return cadete;
    }

    public CadetEntity actualizarRating(String usuario, String contrasena, Integer id, Integer raiting) {
        CadetEntity cadete = null;

        try {
            //if (esCadete(usuario, contrasena)) {
            CadetEntity ent = em.find(CadetEntity.class, id);
            Integer raitingBd = Integer.valueOf(ent.getRating());
            Integer promedio = (raiting + raitingBd) / 2;
            String prom = String.valueOf(promedio);

            cadete = em.find(CadetEntity.class, id);

            cadete.setRating(prom);

            em.merge(cadete);

    //}
        } catch (Exception exe) {
            Utils.logWs("EnviosYa", " ***********ACTUALIZAR*RATING************");
            Utils.logWs("EnviosYa", "Error:" + exe.getMessage());
        }
        return cadete;
    }

    public boolean esCadete(String usuario, String contrasena) {
        boolean esCad = false;
        try {
            List<CadetEntity> list = em
                    .createNativeQuery("select c.* from CadetEntity c "
                            + "where usuario = '" + usuario + "' and contrasena = '" + contrasena + "'")
                    .getResultList();

            if (list.size() > 0) {
                esCad = true;
            }

        } catch (Exception exe) {
            Utils.logWs("EnviosYa", " ***********ES*CADETE*************");
            Utils.logWs("EnviosYa", "Error:" + exe.getMessage());
        }
        return esCad;
    }
}
