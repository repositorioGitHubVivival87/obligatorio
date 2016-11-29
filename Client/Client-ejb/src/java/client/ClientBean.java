/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import tools.Utils;
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
public class ClientBean {

    private static final String ADMIN = "ADMIN";

    @PersistenceContext
    private EntityManager em;

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
                }
            }
        } catch (Exception exe) {
            Utils.logWs("EnviosYa", " ***********ALTA*CLIENTE************");
            Utils.logWs("EnviosYa", "Error:" + exe.getMessage());
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
            Utils.logWs("EnviosYa", " ***********MODIFICACION*CLIENTE************");
            Utils.logWs("EnviosYa", "Error:" + exe.getMessage());
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
            Utils.logWs("EnviosYa", " ***********ELIMINACION*CLIENTE************");
            Utils.logWs("EnviosYa", "Error:" + exe.getMessage());
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
            Utils.logWs("EnviosYa", " ***********LISTAR*CLIENTES*************");
            Utils.logWs("EnviosYa", "Error:" + exe.getMessage());
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
            Utils.logWs("EnviosYa", " ***********BUSCAR*CLIENTE*POR*ID************");
            Utils.logWs("EnviosYa", "Error:" + exe.getMessage());
        }
        return cli;
    }

    public boolean esCliente(String usuario, String contrasena) {
        boolean esCli = false;
        try {
            List<ClientEntity> list = em
                    .createQuery("select c.* from ClientEntity c "
                            + "where usuario = '" + usuario + "' and "
                            + "contrasena = '" + contrasena + "'")
                    .getResultList();

            if (list.size() > 0) {
                esCli = true;
            }

        } catch (Exception exe) {
            Utils.logWs("EnviosYa", " ***********ES*CLIENTE*************");
            Utils.logWs("EnviosYa", "Error:" + exe.getMessage());
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
            Utils.logWs("EnviosYa", " ***********ES*ADMINISTRADOR*************");
            Utils.logWs("EnviosYa", "Error:" + exe.getMessage());
        }
        return esAdmin;
    }

    public Object obtenerUnCliente(String usuario, String contrasena) {
   //     ClientEntity cli = new ClientEntity();
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
            Utils.logWs("EnviosYa", " ***********ES*CLIENTE*************");
            Utils.logWs("EnviosYa", "Error:" + exe.getMessage());
        }
        return ret;
    }
}
