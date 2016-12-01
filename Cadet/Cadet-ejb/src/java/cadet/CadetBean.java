/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadet;

import java.util.ArrayList;
import java.util.List;
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
public class CadetBean {

    private static final String ADMIN = "ADMIN";

    @PersistenceContext
    private EntityManager em;

    public CadetBean() {

    }

    @PostConstruct
    private void init() {
        System.out.println("INSTANCIA CADET BEAN");
    }

    public String agregarCadete(String usuario, String contrasena, Integer ci,
            String nombre, String apellido, String email) {
        String ret = "";

        try {
            List<Object> cadet = em
                    .createNativeQuery("SELECT * FROM CadetEntity c "
                            + "WHERE c.ci = " + ci).getResultList();
            if (!cadet.isEmpty()) {
                ret = "ERROR: Ya existe el cadete";
            } else {
                List<Object> user = em
                        .createNativeQuery("SELECT * FROM CadetEntity c "
                                + "WHERE c.usuario = '" + usuario + "'").getResultList();
                if (!user.isEmpty()) {
                    ret = "ERROR: Por favor ingrese otro nombre de usuario";
                } else {
                    CadetEntity cad = new CadetEntity();
                    cad.setUsuario(usuario);
                    cad.setContrasena(contrasena);
                    cad.setCi(ci);
                    cad.setNombre(nombre);
                    cad.setApellido(apellido);
                    cad.setEmail(email);

                    em.persist(cad);
                    ret = "EXITO";

                    Utils.logInfo("***********ALTA*CADETE************");
                    Utils.logInfo("Se ingreso el nuevo cadete: " + ci);
                }
            }
        } catch (Exception exe) {
            Utils.logError("***********ALTA*CADETE************");
            Utils.logError(exe.getMessage());
            ret = exe.getMessage();
        }
        return ret;
    }

    public String modificarCadete(String usuario, String contrasena, Integer id, Integer ci,
            String nombre, String apellido, String email) {
        String ret = "";
        try {
            CadetEntity cadete = em.find(CadetEntity.class, id);
            cadete.setCi(ci);
            cadete.setNombre(nombre);
            cadete.setApellido(apellido);
            cadete.setEmail(email);
            em.merge(cadete);
            ret = "EXITO";

            Utils.logInfo("***********MODIFICAR*CADETE************");
            Utils.logInfo("Se modifico el cadete: " + ci);
        } catch (Exception exe) {
            Utils.logError(" ***********MODIFICACION*CADETE************");
            Utils.logError(exe.getMessage());
            ret = exe.getMessage();
        }
        return ret;
    }

    public String eliminarCadete(String usuario, String contrasena, Integer id) {
        String ret = "";
        try {
            CadetEntity cadete = em.find(CadetEntity.class, id);
            em.remove(cadete);
            ret = "EXITO";

            Utils.logInfo("***********ELIMINAR*CADETE************");
            Utils.logInfo("Se elimino correctamente el cadete con id: " + id);
        } catch (Exception exe) {
            Utils.logError(" ***********ELIMINACION*CADETE************");
            Utils.logError(exe.getMessage());
            ret = exe.getMessage();
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
            Utils.logError(" ***********LISTAR*CADETES************");
            Utils.logError(exe.getMessage());
        }
        return list;
    }

    public List<CadetEntity> listarCadetesMasCercanos(String latitud, String longitud) {
        List<CadetEntity> todos = new ArrayList();
        try {
            todos = em
                    .createNativeQuery("select * from CadetEntity c limit 4")
                    .getResultList();

            Utils.logInfo("***********LISTAR*CADETES*MAS*CERCANOS************");
            Utils.logInfo("Se listan los cadetes mas cercanos al punto: " + latitud + " - " + longitud);
        } catch (Exception exe) {
            Utils.logError(" ***********LISTAR*4*CADETES*MAS*CERCANOS************");
            Utils.logError(exe.getMessage());
        }
        return todos;
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

            Utils.logInfo("***********BUSCAR*CADETE*POR*ID************");
            Utils.logInfo("Se busco el cadete con id: " + id);
        } catch (Exception exe) {
            Utils.logError(" ***********BUSCAR*CADETE*POR*ID************");
            Utils.logError(exe.getMessage());
        }
        return cadete;
    }

    public CadetEntity actualizarRating(String usuario, String contrasena, Integer id, Integer raiting) {
        CadetEntity cadete = null;
        try {
            CadetEntity ent = em.find(CadetEntity.class, id);
            Integer raitingBd = ent.getRating();
            Integer promedio = (raiting + raitingBd) / 2;

            cadete = em.find(CadetEntity.class, id);

            cadete.setRating(promedio);

            em.merge(cadete);

            Utils.logInfo("***********ACTUALIZAR*RATING************");
            Utils.logInfo("Se actualizo correctamente el rating");
        } catch (Exception exe) {
            Utils.logError(" ***********ACTUALIZAR*RATING************");
            Utils.logError(exe.getMessage());
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
            Utils.logError(" ***********ES*CADETE*************");
            Utils.logError(exe.getMessage());
        }
        return esCad;
    }
}
