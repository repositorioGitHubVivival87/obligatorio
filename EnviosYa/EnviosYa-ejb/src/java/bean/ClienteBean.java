/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import entidades.Cliente;
import entity.ClienteEntity;
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
public class ClienteBean {

    private static final String ADMIN = "ADMIN";

    @PersistenceContext
    private EntityManager em;

    public ClienteBean() {
    }

    @PostConstruct
    private void init() {
        System.out.println("INSTANCIA CLIENTE BEAN");
    }

    public ClienteEntity agregarCliente(int ci, String nombre, String apellido, String email) {
        ClienteEntity cli = null;
        try {
            cli = new ClienteEntity();
            cli.setCi(ci);
            cli.setNombre(nombre);
            cli.setApellido(apellido);
            cli.setEmail(email);

            em.persist(cli);

        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********ALTA*CLIENTE************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return cli;
    }

    public ClienteEntity modificarCliente(Integer id, Integer ci, String nombre, String apellido, String email) {
        ClienteEntity cli = null;
        boolean estaCi = false;
        try {
            List<ClienteEntity> list = em
                    .createQuery("select c from ClienteEntity c")
                    .getResultList();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getCi().equals(ci)) {
                    estaCi = true;
                }
            }
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId().equals(id) && estaCi && list.get(i).getCi().equals(ci)) {
                    cli = em.find(ClienteEntity.class, id);
                    cli.setNombre(nombre);
                    cli.setApellido(apellido);
                    cli.setEmail(email);
                    em.merge(cli);
                } else {
                    if (list.get(i).getId().equals(id) && !estaCi) {
                        cli = em.find(ClienteEntity.class, id);
                        cli.setCi(ci);
                        cli.setNombre(nombre);
                        cli.setApellido(apellido);
                        cli.setEmail(email);
                        em.merge(cli);
                    }
                }
            }
        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********MODIFICACION*CLIENTE************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return cli;
    }

    public boolean eliminarCliente(Integer id) {
        boolean ret = false;
        try {
            ClienteEntity cli = em.find(ClienteEntity.class, id);
            em.remove(cli);
            ret = true;

        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********ELIMINACION*CLIENTE************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return ret;
    }

    public List<ClienteEntity> listarClientes() {
        List<ClienteEntity> list = new ArrayList();
        try {
            list = em
                    .createQuery("select c from ClienteEntity c")
                    .getResultList();
        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********LISTAR*CLIENTES*************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return list;
    }

    public Cliente buscarClientes(Integer id) {
        Cliente cli = new Cliente();
        try {
            ClienteEntity ent = em.find(ClienteEntity.class, id);
            cli.setId(ent.getId());
            cli.setNombre(ent.getNombre());
            cli.setApellido(ent.getApellido());
            cli.setEmail(ent.getEmail());

        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********BUSCAR*CLIENTE*POR*ID************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return cli;
    }

}
