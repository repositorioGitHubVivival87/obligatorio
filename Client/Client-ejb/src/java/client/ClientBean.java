/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

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

    public ClientEntity agregarCliente(String usuario, String contrasena, int ci,
            String nombre, String apellido, String email) {
        ClientEntity cli = null;
        try {
            if (!esCliente(usuario, contrasena)) {
                cli = new ClientEntity();
                cli.setUsuario(usuario);
                cli.setContrasena(contrasena);
                cli.setCi(ci);
                cli.setNombre(nombre);
                cli.setApellido(apellido);
                cli.setEmail(email);

                em.persist(cli);
            }
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********ALTA*CLIENTE************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return cli;
    }

    public ClientEntity modificarCliente(String usuario, String contrasena,
            Integer id, Integer ci, String nombre, String apellido, String email) {
        ClientEntity cli = null;
        boolean estaCi = false;
        try {
            if (esCliente(usuario, contrasena)) {
                List<ClientEntity> list = em
                        .createQuery("select c from ClientEntity c")
                        .getResultList();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getCi().equals(ci)) {
                        estaCi = true;
                    }
                }
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getId().equals(id) && estaCi && list.get(i).getCi().equals(ci)) {
                        cli = em.find(ClientEntity.class, id);
                        cli.setNombre(nombre);
                        cli.setApellido(apellido);
                        cli.setEmail(email);
                        em.merge(cli);
                    } else {
                        if (list.get(i).getId().equals(id) && !estaCi) {
                            cli = em.find(ClientEntity.class, id);
                            cli.setCi(ci);
                            cli.setNombre(nombre);
                            cli.setApellido(apellido);
                            cli.setEmail(email);
                            em.merge(cli);
                        }
                    }
                }
            }
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********MODIFICACION*CLIENTE************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return cli;
    }

    public boolean eliminarCliente(Integer id) {
        boolean ret = false;
        try {
            ClientEntity cli = em.find(ClientEntity.class, id);
            em.remove(cli);
            ret = true;

        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********ELIMINACION*CLIENTE************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return ret;
    }

    public List<ClientEntity> listarClientes() {
        List<ClientEntity> list = new ArrayList();
        try {
            list = em
                    .createQuery("select c from ClienteEntity c")
                    .getResultList();
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********LISTAR*CLIENTES*************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return list;
    }

    public Client buscarCliente(Integer id) {
        Client cli = new Client();
        try {
            ClientEntity ent = em.find(ClientEntity.class, id);
            cli.setId(ent.getId());
            cli.setNombre(ent.getNombre());
            cli.setApellido(ent.getApellido());
            cli.setEmail(ent.getEmail());

        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********BUSCAR*CLIENTE*POR*ID************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return cli;
    }

    public boolean esCliente(String usuario, String contrasena) {
        boolean esCli = false;
        try {
            List<ClientEntity> list = em
                    .createQuery("select c from ClienteEntity c "
                            + "where usuario = '" + usuario + "' and contrasena = '" + contrasena + "'")
                    .getResultList();

            if (list.size() > 0) {
                esCli = true;
            }

        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********ES*CLIENTE*************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return esCli;
    }

    public ClientEntity obtenerUnCliente(String usuario, String contrasena) {
        ClientEntity cli = new ClientEntity();
        try {
            List<ClientEntity> list = em
                    .createQuery("select c from ClienteEntity c "
                            + "where usuario = '" + usuario + "' and contrasena = '" + contrasena + "'")
                    .getResultList();

            if (!list.isEmpty()) {
                cli = list.get(0);
            }
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********ES*CLIENTE*************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return cli;
    }
}
