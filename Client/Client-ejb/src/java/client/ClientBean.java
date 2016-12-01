/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
public class ClientBean {

    private static final String ADMIN = "ADMIN";

    @PersistenceContext
    private EntityManager em;
    
    Utils util = new Utils();
    public ClientBean() {

    }

    @PostConstruct
    private void init() {
        System.out.println("INSTANCIA CLIENT BEAN");
    }

    public String agregarCliente(String usuario, String contrasena, Integer ci,
            String nombre, String apellido, String email, Integer nivel) {
        String ret = "";

        try {
            //si el nivel== 1 es cliente, si nivel==2 es administrador
            List<Object> client = em
                    .createNativeQuery("SELECT * FROM ClientEntity c "
                            + "WHERE c.ci = " + ci).getResultList();
            if (!client.isEmpty()) {
                ret = "ERROR: Ya existe el cliente";
            } else {
                List<Object> user = em
                        .createNativeQuery("SELECT * FROM ClientEntity c "
                                + "WHERE c.usuario = '" + usuario + "'").getResultList();
                if (!user.isEmpty()) {
                    ret = "ERROR: Por favor ingrese otro nombre de usuario";
                } else {
                    ClientEntity cli = new ClientEntity();
                    cli.setUsuario(usuario);
                    cli.setContrasena(contrasena);
                    cli.setCi(ci);
                    cli.setNombre(nombre);
                    cli.setApellido(apellido);
                    cli.setEmail(email);
                    cli.setNivel(nivel);

                    em.persist(cli);
                    ret = "EXITO";
                    
                    util.logInfo("MENSAJE");
                    util.logInfo("***********ALTA*CLIENTE************");
                    util.logInfo("Se ingreso el nuevo cliente: " + ci);
                }
            }
        } catch (Exception exe) {

            util.logError("***********ALTA*CLIENTE************");
            util.logError(exe.getMessage());
            ret = exe.getMessage();
        }
        return ret;
    }

    public String modificarCliente(String usuario, String contrasena,
            Integer id, Integer ci, String nombre, String apellido, String email, Integer nivel) {
        String ret = "";
        try {
            //si el nivel== 1 es cliente, si nivel==2 es administrador
            List<Object> client = em
                    .createNativeQuery("SELECT * FROM ClientEntity c "
                            + "WHERE c.id = " + id).getResultList();
            if (client.isEmpty()) {
                ret = "ERROR: No existe el cliente";
            } else {
                ClientEntity cli = em.find(ClientEntity.class, id);
                cli.setContrasena(contrasena);
                cli.setCi(ci);
                cli.setNombre(nombre);
                cli.setApellido(apellido);
                cli.setEmail(email);
                cli.setNivel(nivel);
                em.merge(cli);
                ret = "EXITO";
            }
        } catch (Exception exe) {
            Utils.logError(" ***********MODIFICACION*CLIENTE************");
            Utils.logError(exe.getMessage());
            ret = exe.getMessage();
        }
        return ret;
    }

    public String login(String usuario, String contrasena) {
        String ret = "";
        try {
            List<ClientEntity> list = em
                    .createNativeQuery("select c.* from ClientEntity c "
                            + "where c.usuario = '" + usuario + "' and "
                            + "c.contrasena = '" + contrasena + "'")
                    .getResultList();
            if (list.isEmpty()) {
                ret = "ERROR: Usuario o Contraseña invalido";
            } else {
                Integer id = list.get(0).getId();
                ClientEntity cli = em.find(ClientEntity.class, id);

                Random rnd = new Random();
                System.out.println(rnd.nextLong());
                String token = rnd.toString();

                cli.setNombre(token);

                em.merge(cli);

                //La idea ademas de loguear el cliente es guardar la session para luego tener un 
                //proceso que cada tanto tiempo la
                //inactive por seguridad y tambien borre el token del usuario.
                ret = "LOGIN";
            }
        } catch (Exception exe) {
            Utils.logError(" ***********MODIFICACION*CLIENTE************");
            Utils.logError(exe.getMessage());
            ret = exe.getMessage();
        }
        return ret;
    }

    public String logout(Integer id) {
        String ret = "";
        try {

            ClientEntity cli = em.find(ClientEntity.class, id);
            String token = "";
            cli.setNombre(token);

            //Aqui la idea era ya tener el usuario logueado y poder borrarole el tocken directamente, 
            //pedimos el id por parametro porque no nos dio para implementar el manejo de la session.
            em.merge(cli);
            ret = "ACTUALIZO";

        } catch (Exception exe) {
            Utils.logError(" ***********MODIFICACION*CLIENTE************");
            Utils.logError(exe.getMessage());
            ret = exe.getMessage();
        }
        return ret;
    }

    public String eliminarCliente(String usuario, String contrasena, Integer id) {
        String ret = "";
        try {
            ClientEntity cli = em.find(ClientEntity.class, id);
            em.remove(cli);
            ret = "EXITO";

        } catch (Exception exe) {
            Utils.logError(" ***********ELIMINACION*CLIENTE************");
            Utils.logError(exe.getMessage());
            ret = exe.getMessage();
        }
        return ret;
    }

    public List<ClientEntity> listarClientes() {
        List<ClientEntity> list = new ArrayList();
        try {
            list = em
                    .createNativeQuery("select * from ClientEntity c")
                    .getResultList();
        } catch (Exception exe) {
            Utils.logError(" ***********LISTAR*CLIENTES*************");
            Utils.logError(exe.getMessage());
        }
        return list;
    }

    public Client buscarCliente(Integer id) {
        Client cli = new Client();
        try {
            ClientEntity ent = em.find(ClientEntity.class, id);
            cli.setId(ent.getId());
            cli.setCi(ent.getCi());
            cli.setNombre(ent.getNombre());
            cli.setApellido(ent.getApellido());
            cli.setEmail(ent.getEmail());

        } catch (Exception exe) {
            Utils.logError(" ***********BUSCAR*CLIENTE*POR*ID************");
            Utils.logError(exe.getMessage());
        }
        return cli;
    }

    public boolean esCliente(String usuario, String contrasena) {
        boolean esCli = false;
        try {
            List<ClientEntity> list = em
                    .createNativeQuery("select c.* from ClientEntity c "
                            + "where c.usuario = '" + usuario + "' and "
                            + "c.contrasena = '" + contrasena + "'")
                    .getResultList();

            if (list.size() > 0) {
                esCli = true;
            }

        } catch (Exception exe) {
            Utils.logError(" ***********ES*CLIENTE*************");
            Utils.logError(exe.getMessage());
        }
        return esCli;
    }

    public boolean esAdministrador(String usuario, String contrasena) {
        boolean esAdmin = false;
        try {
            List<ClientEntity> list = em
                    .createQuery("select c.* from ClientEntity c "
                            + "where usuario = '" + usuario + "' and "
                            + "contrasena = '" + contrasena + "' and nivel = " + 2)
                    .getResultList();
            if (list.size() > 0) {
                esAdmin = true;
            }

        } catch (Exception exe) {
            Utils.logError(" ***********ES*ADMINISTRADOR*************");
            Utils.logError(exe.getMessage());
        }
        return esAdmin;
    }

    public Object obtenerUnCliente(String usuario, String contrasena) {
        Object ret = new Object();
        try {
            List<Object> list = em
                    .createNativeQuery("select c.* from ClientEntity c "
                            + "where c.usuario = '" + usuario + "' and c.contrasena = '" + contrasena + "'")
                    .getResultList();

            if (!list.isEmpty()) {
                ret = list.get(0);
            }
        } catch (Exception exe) {
            Utils.logError(" ***********ES*CLIENTE*************");
            Utils.logError(exe.getMessage());
        }
        return ret;
    }
}
